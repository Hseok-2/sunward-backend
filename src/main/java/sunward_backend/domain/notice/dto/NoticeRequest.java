package sunward_backend.domain.notice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeRequest {

    private String title;
    private String content;
    private boolean pinned;
}
