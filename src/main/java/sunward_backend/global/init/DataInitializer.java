//package sunward_backend;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
///**
// * application-prod.yml 관련 admin
// */
//@Component
//@RequiredArgsConstructor
//public class DataInitializer implements CommandLineRunner {
//
//    private final MemberRepository memberRepository;
//    private final BCryptPasswordEncoder passwordEncoder;
//
//    @Value("${admin.username}")
//    private String adminUsername;
//
//    @Value("${admin.password}")
//    private String adminPassword;
//
//    @Value("${admin.email}")
//    private String adminEmail;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // 관리자가 존재하지 않으면 실행
//        if (memberRepository.count() == 0) {
//            Member admin = Member.builder()
//                    .username(adminUsername)
//                    .password(passwordEncoder.encode(adminPassword))
//                    .email(adminEmail)
//                    .role("ROLE_ADMIN")
//                    .build();
//
//            memberRepository.save(admin);
//            System.out.println("최초 관리자 계정이 생성되었습니다: " + adminUsername);
//        }
//    }
//}
