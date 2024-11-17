package Job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Job.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
