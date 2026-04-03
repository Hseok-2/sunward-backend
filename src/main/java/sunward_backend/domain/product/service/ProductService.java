package sunward_backend.domain.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sunward_backend.domain.product.dto.ProductRequest;
import sunward_backend.domain.product.dto.ProductResponse;
import sunward_backend.domain.product.entity.Category;
import sunward_backend.domain.product.entity.Product;
import sunward_backend.domain.product.repository.CategoryRepository;
import sunward_backend.domain.product.repository.ProductRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // 제품 조회 (페이징까지)
    public Page<ProductResponse> getProducts(Long categoryId, String keyWord, int page) {
        // 한 페이지에 6개씩, sortOrder 기준으로 오름차순 정렬
        PageRequest pageable = PageRequest.of(page, 6, Sort.by(Sort.Direction.ASC, "sortOrder"));

        Page<Product> productPage;

        // 검색어가 있으면 검색 쿼리 실행, 없으면 전체 목록 쿼리 실행
        if (keyWord != null && !keyWord.trim().isEmpty()) {
            productPage = productRepository.findByCategoryIdAndNameContaining(categoryId, keyWord, pageable);
        }else {
            productPage = productRepository.findByCategoryId(categoryId, pageable);
        }

        // DTO 객체 변환 후 반환
        return productPage.map(ProductResponse::new);
    }

    // 제품 등록
    @Transactional
    public void createProduct(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다. id: " + request.getCategoryId()));

        Product product = Product.builder()
                .category(category)
                .name(request.getName())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .catalogUrl(request.getCatalogUrl())
                .sortOrder(request.getSortOrder())
                .build();

        productRepository.save(product);
    }

    // 제품 수정
    @Transactional
    public void updateProduct(Long id, ProductRequest request) {
        // 수정할 제품 찾기
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 제품이 없습니다. id: " + id));

        // 바꿀 카테고리 찾기
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다. id: " + request.getCategoryId()));

        // 변경감지
        product.updateProduct(
                category,
                request.getName(),
                request.getDescription(),
                request.getImageUrl(),
                request.getCatalogUrl(),
                request.getSortOrder()
        );
    }

    // 제품 삭제
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 제품이 없습니다. id:" + id));
        productRepository.delete(product);
    }
}
