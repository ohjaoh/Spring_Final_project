package Job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Job.entity.BoardCategory;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Integer> {
}
