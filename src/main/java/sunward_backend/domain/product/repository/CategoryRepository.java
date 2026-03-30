package sunward_backend.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sunward_backend.domain.product.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // sortOrder를 기준으로 오름차순 정렬하여 모든 카테고리를 조회
    List<Category> findAllByOrderBySortOrderAsc();
}
