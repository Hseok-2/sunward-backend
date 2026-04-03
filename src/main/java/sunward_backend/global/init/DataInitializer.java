package sunward_backend.global.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import sunward_backend.domain.admin.entity.Admin;
import sunward_backend.domain.admin.entity.Role;
import sunward_backend.domain.admin.repository.AdminRepository;

/**
 * application-prod.yml 관련 admin
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${admin.email}")
    private String adminEmail;

    @Override
    public void run(String... args) throws Exception {
        // 관리자가 존재하지 않으면 실행
        if (adminRepository.count() == 0) {
            Admin admin = Admin.builder()
                    .username(adminUsername)
                    .password(passwordEncoder.encode(adminPassword))
                    .email(adminEmail)
                    .role(Role.ADMIN)
                    .build();

            adminRepository.save(admin);
            log.info("최초 관리자 계정이 생성되었습니다: " + adminUsername);
        }
    }
}
