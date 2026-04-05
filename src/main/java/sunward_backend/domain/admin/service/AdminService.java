package sunward_backend.domain.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sunward_backend.domain.admin.dto.LoginRequest;
import sunward_backend.domain.admin.dto.LoginResponse;
import sunward_backend.domain.admin.entity.Admin;
import sunward_backend.domain.admin.repository.AdminRepository;
import sunward_backend.global.security.JwtTokenProvider;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder; // 비밀번호 대조를 위해 필요
    private final JwtTokenProvider jwtTokenProvider; // 토큰 발행을 위해 필요

    @Transactional
    public LoginResponse login(LoginRequest request) {
        // 회원 정보 조회
        Admin admin = adminRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("회원 정보가 일치하지 않습니다."));

        // 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new IllegalArgumentException("회원 정보가 일치하지 않습니다.");
        }

        // 토큰 발급
        String accessToken = jwtTokenProvider.createAccessToken(admin.getUsername(), admin.getRole().getKey());
        String refreshToken = jwtTokenProvider.createRefreshToken();

        // Refresh Token을 DB에 저장 - Member 엔티티 내부 메서드 사용
        admin.updateRefreshToken(refreshToken);

        return LoginResponse.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
