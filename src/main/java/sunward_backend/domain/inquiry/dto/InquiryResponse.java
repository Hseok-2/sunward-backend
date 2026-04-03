package sunward_backend.domain.inquiry.dto;

import lombok.Getter;
import sunward_backend.domain.inquiry.entitiy.Inquiry;

import java.time.LocalDateTime;

@Getter
public class InquiryResponse {
    private Long id;
    private String name;
    private String contact;
    private String email;
    private String title;
    private String content;
    private String statusDescription; // "답변 대기" 등 한글 설명
    private LocalDateTime createdAt;

    public InquiryResponse(Inquiry inquiry) {
        this.id = inquiry.getId();
        this.name = inquiry.getName();
        this.contact = inquiry.getContact();
        this.email = inquiry.getEmail();
        this.title = inquiry.getTitle();
        this.content = inquiry.getContent();
        this.statusDescription = inquiry.getStatus().getDescription();
        this.createdAt = inquiry.getCreatedAt();
    }
}
