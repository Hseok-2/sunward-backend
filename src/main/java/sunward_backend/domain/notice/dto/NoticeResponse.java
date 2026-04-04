package sunward_backend.domain.notice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import sunward_backend.domain.notice.entity.Notice;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class NoticeResponse {

    private Long id;
    private String title;
    private String content;
    private int viewCount;
    private boolean isPinned;
    private LocalDateTime createdAt;

    public NoticeResponse(Notice notice) {
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.viewCount = notice.getViewCount();
        this.isPinned = notice.isPinned();
        this.createdAt = notice.getCreatedAt();
    }
}
