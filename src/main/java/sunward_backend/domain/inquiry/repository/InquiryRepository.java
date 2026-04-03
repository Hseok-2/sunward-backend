package sunward_backend.domain.inquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sunward_backend.domain.inquiry.entitiy.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}
