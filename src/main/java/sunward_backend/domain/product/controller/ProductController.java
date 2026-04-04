package sunward_backend.domain.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sunward_backend.domain.product.dto.ProductRequest;
import sunward_backend.domain.product.dto.ProductResponse;
import sunward_backend.domain.product.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    // 제품 조회(페이징)
    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getProducts(
            @RequestParam Long categoryId,
            @RequestParam(required = false) String keyWord,
            @RequestParam(defaultValue = "0") int page
    ) {
        Page<ProductResponse> response = productService.getProducts(categoryId, keyWord, page);
        return ResponseEntity.ok(response);
    }

    // 제품 등록 - 관리자
    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductRequest request) {
        productService.createProduct(request);
        return ResponseEntity.ok().build();
    }

    // 제품 수정 - 관리자
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
        productService.updateProduct(id, request);
        return ResponseEntity.ok().build();
    }

    // 제품 삭제 - 관리자
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
