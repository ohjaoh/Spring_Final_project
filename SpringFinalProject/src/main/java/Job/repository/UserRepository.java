package Job.repository;

import Job.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	// id를 이용하여 회원정보를 찾는 메서드
	User findByUserId(String userId);
}