package Job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Job.entity.BoardTag;

public interface BoardTagRepository extends JpaRepository<BoardTag, Integer> {

}
