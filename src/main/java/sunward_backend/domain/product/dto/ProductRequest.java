package sunward_backend.domain.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductRequest {
    private Long categoryId; // 제품이 속한 카테고리 번호
    private String name;
    private String description;
    private String imageUrl;
    private String catalogUrl;
    private int sortOrder;
}
