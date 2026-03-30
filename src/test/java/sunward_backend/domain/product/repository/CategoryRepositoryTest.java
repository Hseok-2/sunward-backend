package sunward_backend.domain.product.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sunward_backend.domain.product.entity.Category;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("카테고리가 sortOrder 오름차순으로 정렬되어 조회되는지 확인한다")
    void findAllByOrderBySortOrderAscTest() {
        // given: 무작위 순서로 데이터를 저장
        categoryRepository.save(Category.builder().name(    "바닥재(마루)").sortOrder(30).build());
        categoryRepository.save(Category.builder().name("바닥재(타일)").sortOrder(10).build());
        categoryRepository.save(Category.builder().name("바닥재(시트)").sortOrder(20).build());

        // when 정렬 조회 메서드를 실행
        List<Category> categories = categoryRepository.findAllByOrderBySortOrderAsc();

        // then 10-20-30 순으로 정렬 되는지 확인
        assertThat(categories).hasSize(3);

        assertThat(categories.get(0).getName()).isEqualTo("바닥재(타일)");
        assertThat(categories.get(0).getSortOrder()).isEqualTo(10);

        assertThat(categories.get(1).getName()).isEqualTo("바닥재(시트)");
        assertThat(categories.get(1).getSortOrder()).isEqualTo(20);

        assertThat(categories.get(2).getName()).isEqualTo("바닥재(마루)");
        assertThat(categories.get(2).getSortOrder()).isEqualTo(30);
    }

}