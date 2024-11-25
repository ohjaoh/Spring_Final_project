package Job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Job.entity.BoardCategory;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Integer> {
	// 카테고리 이름을 기반으로 찾는 코드
	BoardCategory findBycategoryName(String categoryName);

}
