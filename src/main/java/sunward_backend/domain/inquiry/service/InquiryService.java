package sunward_backend.domain.inquiry.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sunward_backend.domain.inquiry.dto.InquiryRequest;
import sunward_backend.domain.inquiry.dto.InquiryResponse;
import sunward_backend.domain.inquiry.entitiy.Inquiry;
import sunward_backend.domain.inquiry.entitiy.InquiryStatus;
import sunward_backend.domain.inquiry.repository.InquiryRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InquiryService {

    private final InquiryRepository inquiryRepository;

    // 문의 등록
    @Transactional
    public void createInquiry(InquiryRequest request) {
        // DTO 데이터를 엔티티로 변환
        Inquiry inquiry = Inquiry.builder()
                .name(request.getName())
                .contact(request.getContact())
                .email(request.getEmail())
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        inquiryRepository.save(inquiry);
    }

    // 문의글 조회(페이징 처리) - 관리자
    public Page<InquiryResponse> getInquiries(int page) {
        // 최신 등록순으로 10개씩 조회(id순)
        PageRequest pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));

        return inquiryRepository.findAll(pageable)
                .map(InquiryResponse::new);
    }

    // 문의글 상태 변경
    @Transactional
    public void updateInquiryStatus(Long id, InquiryStatus status) {
        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 문의글이 없습니다. id: " + id));
        inquiry.changeStatus(status);
    }
}
