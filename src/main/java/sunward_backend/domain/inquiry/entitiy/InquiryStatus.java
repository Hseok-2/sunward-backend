package sunward_backend.domain.inquiry.entitiy;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InquiryStatus {

    PENDING("답변 대기"),
    COMPLETED("답변 완료");

    private final String description;
}
