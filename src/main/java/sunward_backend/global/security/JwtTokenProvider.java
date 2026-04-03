package sunward_backend.global.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    // application.yml을 거쳐 .env의 JWT_SECRET_KEY 값을 가져옵니다.
    @Value("${jwt.secret}")
    private String secretKey;

    private Key key;

    // 토큰 유효시간 설정
    private final long ACCESS_TOKEN_EXPIRE_TIME = 1000L * 60 * 60; // 1시간
    private final long REFRESH_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 * 24 * 7; // 7일

    // 의존성 주입이 끝나면 비밀키 암호화 알고리즘에 맞게 세팅함
    @PostConstruct
    protected void init() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // Access Token 생성 (사용자 이름과 권한 담음)
    public String createAccessToken(String username, String role) {
        Claims claims = Jwts.claims().setSubject(username); // JWT payload 에 저장되는 정보단위
        claims.put("role", role); // 권한 정보 저장 (예: ROLE_ADMIN)
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME)) // 만료 시간 설정
                .signWith(key, SignatureAlgorithm.HS256) // 사용할 암호화 알고리즘과 서명 키
                .compact();
    }

    // Refresh Token 생성 (정보는 담지 않고 유효시간만 길게 설정합니다)
    public String createRefreshToken() {
        Date now = new Date();
        return Jwts.builder()
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰의 유효성 및 만료일자 확인
    public boolean validateToken(String token) {
        try {
            // 토큰을 열어보고 에러가 안 나면 true, 에러가 나면 catch로 빠짐 (false)
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.warn("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.warn("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.warn("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.warn("JWT 토큰이 비어있습니다.");
        }
        return false;
    }

    // 4. 토큰에서 회원 정보(username) 추출
    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
