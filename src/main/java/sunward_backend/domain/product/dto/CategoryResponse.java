package sunward_backend.domain.product.dto;

import lombok.Getter;
import sunward_backend.domain.product.entity.Category;

@Getter
public class CategoryResponse {

    private Long id;
    private String name;
    private int sortOrder;

    // Entity를 DTO로 변환하는 생성자
    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.sortOrder = category.getSortOrder();
    }
}
