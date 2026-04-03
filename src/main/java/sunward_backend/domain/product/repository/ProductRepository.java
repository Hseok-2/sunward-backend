package sunward_backend.domain.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sunward_backend.domain.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // 특정 카테고리의 제품 목록 조회 (페이징 적용)
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

    // 특정 카테고리 내에서 제품명으로 검색 (페이징 적용)
    Page<Product> findByCategoryIdAndNameContaining(Long categoryId, String keyWord, Pageable pageable);
}
