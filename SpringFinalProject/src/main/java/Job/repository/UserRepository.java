package Job.repository;

import Job.entity.User;
import Job.entity.UserLiteInfo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	// id를 이용하여 회원정보를 찾는 메서드
	User findByUserId(String userId);
	// 유저목록
	List<UserLiteInfo> findAllBy();
	// 비밀번호와 솔트값을 제외한 회원정보 메서드
	UserLiteInfo findLiteInfoByUserId(String userId);
}