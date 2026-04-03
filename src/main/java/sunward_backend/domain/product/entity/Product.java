package sunward_backend.domain.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 카테고리와의 다대일(N:1) 연관관계 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(length = 100, nullable = false)
    private String name; //제품명

    @Column(length = 255)
    private String description; //제품 간략설명

    @Column(name = "image_url", nullable = false)
    private String imageUrl; // 제품 썸네일 이미지 경로

    @Column(name = "catalog_url", length = 500)
    private String catalogUrl; // 외부 카탈로그 링크

    @Column(name = "sort_order", nullable = false)
    private int sortOrder;

    @Builder
    public Product(Category category, String name, String description, String imageUrl, String catalogUrl, int sortOrder) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.catalogUrl = catalogUrl;
        this.sortOrder = sortOrder;
    }

    // 데이터 수정을 위한 비즈니스 메서드
    public void updateProduct(Category category, String name, String description, String imageUrl, String catalogUrl, int sortOrder) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.catalogUrl = catalogUrl;
        this.sortOrder = sortOrder;
    }
}
