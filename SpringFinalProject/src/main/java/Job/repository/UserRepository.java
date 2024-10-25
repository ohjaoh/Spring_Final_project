package Job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Job.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	
}
