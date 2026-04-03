package sunward_backend.domain.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sunward_backend.domain.product.dto.CategoryRequest;
import sunward_backend.domain.product.dto.CategoryResponse;
import sunward_backend.domain.product.entity.Category;
import sunward_backend.domain.product.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    public final CategoryRepository categoryRepository;

    // 카테고리 조회
    public List<CategoryResponse> getCategories() {
        return categoryRepository.findAllByOrderBySortOrderAsc()
                .stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }

    // 카테고리 등록
    @Transactional
    public void createCategory(CategoryRequest request) {
        Category category = Category.builder()
                .name(request.getName())
                .sortOrder(request.getSortOrder())
                .build();
        categoryRepository.save(category);
    }

    // 카테고리 수정
    @Transactional
    public void updateCategory(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다. id: " + id));

        // Category 엔티티 내부 메서드
        category.updateCategory(request.getName(), request.getSortOrder());
    }

    // 카테고리 삭제
    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다. id: " + id));

        categoryRepository.delete(category);
    }
}
