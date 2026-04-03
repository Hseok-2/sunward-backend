package sunward_backend.domain.inquiry.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sunward_backend.domain.inquiry.entitiy.InquiryStatus;

@Getter
@NoArgsConstructor
public class InquiryStatusRequest {

    private InquiryStatus status; // 문의글 상태
}
