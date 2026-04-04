package sunward_backend.domain.notice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sunward_backend.domain.notice.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    // 제목으로 검색
    Page<Notice> findByTitleContaining(String title, Pageable pageable);
}
