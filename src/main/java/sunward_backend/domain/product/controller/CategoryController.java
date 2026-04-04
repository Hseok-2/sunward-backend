package sunward_backend.domain.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sunward_backend.domain.product.dto.CategoryRequest;
import sunward_backend.domain.product.dto.CategoryResponse;
import sunward_backend.domain.product.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 조회
    @Operation(summary = "카테고리 전체 조회")
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        List<CategoryResponse> response = categoryService.getCategories();
        return ResponseEntity.ok(response);
    }

    // 카테고리 등록 - 관리자
    @Operation(summary = "카테고리 등록")
    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody CategoryRequest request) {
        categoryService.createCategory(request);
        return ResponseEntity.ok().build();
    }

    // 카테고리 수정 - 관리자
    @Operation(summary = "카테고리 수정")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest request) {
        categoryService.updateCategory(id, request);
        return ResponseEntity.ok().build();
    }

    // 카테고리 삭제 - 관리자
    @Operation(summary = "카테고리 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
