package sunward_backend.domain.inquiry.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InquiryRequest {

    private String name;
    private String contact;
    private String email;
    private String title;
    private String content;
}
