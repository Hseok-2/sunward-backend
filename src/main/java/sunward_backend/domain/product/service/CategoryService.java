package sunward_backend.domain.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sunward_backend.domain.product.dto.CategoryResponse;
import sunward_backend.domain.product.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    public final CategoryRepository categoryRepository;

    public List<CategoryResponse> getCategories() {
        return categoryRepository.findAllByOrderBySortOrderAsc()
                .stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
    }
}
