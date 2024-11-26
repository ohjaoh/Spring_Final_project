package Job.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Job.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	// 상태가 status인 녀석들을 조회 
	List<Board> findByboardState(String status);

}
