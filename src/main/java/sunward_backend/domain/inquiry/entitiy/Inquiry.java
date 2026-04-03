package sunward_backend.domain.inquiry.entitiy;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sunward_backend.global.common.BaseTimeEntity;

@Entity
@Getter
@Table(name = "inquiry")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquiry extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String contact;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 200)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private InquiryStatus status;

    @Builder
    public Inquiry(String name, String contact, String email, String title, String content) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.title = title;
        this.content = content;
        this.status = InquiryStatus.PENDING;
    }

    public void changeStatus(InquiryStatus status) {
        this.status = status;
    }
}
