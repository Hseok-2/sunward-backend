package sunward_backend.domain.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "category")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 막기
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    // 카테고리 노출 순서
    @Column(name = "sort_order", nullable = false)
    private int sortOrder;

    @Builder
    public Category(String name, int sortOrder) {
        this.name = name;
        this.sortOrder = sortOrder;
    }

    // 카테고리 이름이나 순서를 수정할 때 쓸 비즈니스 메서드
    public void updateCategory(String name, int sortOrder) {
        this.name = name;
        this.sortOrder = sortOrder;
    }
}
