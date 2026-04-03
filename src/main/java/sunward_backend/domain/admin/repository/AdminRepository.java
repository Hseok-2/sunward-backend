package sunward_backend.domain.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sunward_backend.domain.admin.entity.Admin;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    // 로그인시 아이디로 회원 정보를 찾기 위한 메서드
    Optional<Admin> findByUsername(String username);
}
