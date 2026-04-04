package sunward_backend.domain.notice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sunward_backend.global.common.BaseTimeEntity;

@Entity
@Getter
@Table(name = "notice")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private int viewCount; // 초기 조회수는 0으로

    @Column(nullable = false)
    private boolean isPinned; // 공지사항 상단 고정 여부 (true이면 [공지] 태그)

    @Builder
    public Notice(String title, String content, boolean isPinned) {
        this.title = title;
        this.content = content;
        this.isPinned = isPinned;
    }

    // 공지사항 수정 메서드
    public void updateNotice(String title, String content, boolean isPinned) {
        this.title = title;
        this.content = content;
        this.isPinned = isPinned;
    }

    // 조회수 증가 메서드
    public void incrementViewCount() {
        this.viewCount++;
    }
}
