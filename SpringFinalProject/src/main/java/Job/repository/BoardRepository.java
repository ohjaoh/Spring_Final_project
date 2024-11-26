package Job.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Job.entity.Board;
import Job.entity.BoardCategory;

public interface BoardRepository extends JpaRepository<Board, Long> {

	// 상태가 status이고 카테고리 숫자가 같은 녀석을 검색
	List<Board> findByBoardStateAndCategory(String status, BoardCategory category);

}
