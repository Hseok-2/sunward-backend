package sunward_backend.domain.product.dto;

import lombok.Getter;
import sunward_backend.domain.product.entity.Product;

@Getter
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String catalogUrl;

    // Entity를 DTO로 변환
    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.imageUrl = product.getImageUrl();
        this.catalogUrl = product.getCatalogUrl();
    }
}
