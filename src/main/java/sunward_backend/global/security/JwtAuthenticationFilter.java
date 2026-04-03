package sunward_backend.global.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import sunward_backend.domain.admin.entity.Admin;
import sunward_backend.domain.admin.repository.AdminRepository;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final AdminRepository adminRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. 클라이언트의 요청 헤더에서 JWT 토큰을 빼옵니다.
        String token = resolveToken(request);

        // 2. 토큰이 있고, 위조/만료되지 않은 정상 토큰인지 검사합니다.
        if (token != null && jwtTokenProvider.validateToken(token)) {
            // 3. 정상 토큰이면 안에 들어있는 사용자 아이디(username)를 꺼냅니다.
            String username = jwtTokenProvider.getUsername(token);

            // 4. DB를 조회해 진짜 있는 회원인지, 그리고 권한(Role)은 무엇인지 확인합니다.
            Admin admin = adminRepository.findByUsername(username).orElse(null);

            if (admin != null) {
                // 5. 스프링 시큐리티가 알아들을 수 있는 '인증 배지(Authentication)'를 만듭니다.
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        admin.getUsername(),
                        null,
                        List.of(new SimpleGrantedAuthority(admin.getRole().getKey())) // 예: ROLE_ADMIN
                );

                // 6. '보안 명부(SecurityContext)'에 이 배지를 등록합니다. (이제 이 요청은 프리패스!)
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 7. 검사가 끝났으니 다음 필터(혹은 원래 가려던 컨트롤러)로 요청을 넘겨줍니다.
        filterChain.doFilter(request, response);
    }

    // 헤더에서 "Bearer "를 떼어내고 진짜 토큰 값만 추출하는 헬퍼 메서드
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        // 헤더에 값이 있고, "Bearer "로 시작하는지 확인
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // 앞의 7글자("Bearer ")를 떼어내고 반환
        }
        return null;
    }
}
