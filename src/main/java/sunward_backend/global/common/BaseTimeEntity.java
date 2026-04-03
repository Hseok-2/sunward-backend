package sunward_backend.global.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 이 클래스를 상속받으면 아래 필드들도 컬럼으로 인식하게 합니다.
@EntityListeners(AuditingEntityListener.class) // 데이터가 생성, 수정될 때 시간을 자동으로 감지해서 넣어줌
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(name = "created_at", updatable = false) // 생성일은 한 번 만들어지면 수정되면 안 되므로 막아두기
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
