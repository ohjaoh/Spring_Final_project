package Job.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Job.controller.PasswordHashingUtils;
import Job.entity.LoginInfo;
import Job.entity.User;
import Job.entity.UserLiteInfo;
import Job.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	// 회원가입
	@Override
	public void registerUser(User user) {
		// 랜덤 Salt 생성
		String salt = PasswordHashingUtils.generateRandomSalt();

		// 비밀번호 해싱
		String hashedPassword = PasswordHashingUtils.hashPassword(user.getUserPassword(), salt);

		// User 엔티티에 해싱된 비밀번호와 Salt 설정
		user.setUserPassword(hashedPassword);
		user.setUserSalt(salt);

		System.out.println(user);
		// 데이터베이스에 저장
		userRepo.save(user);
	}

	// 회원정보 수정
	// 수정된 데이터만 받아서 처리하도록 수정
	@Override
	public void userUpdate(User user) {
		// 랜덤 Salt 생성
		String salt = PasswordHashingUtils.generateRandomSalt();

		// 비밀번호 해싱(암호화)
		String hashedPassword = PasswordHashingUtils.hashPassword(user.getUserPassword(), salt);

		// User 엔티티에 해싱된 비밀번호와 Salt 설정
		user.setUserPassword(hashedPassword);
		user.setUserSalt(salt);

		LocalDateTime current = LocalDateTime.now();

		// 가입한 시간에 수정시간을 수정
		user.setUpdatedAt(current);

		userRepo.save(user);
	}

	// 회원목록
	// 커스텀 쿼리를 이용해서 findAll이 아닌 몇가지 정보만 담아서 불러오는 형태로 진행
	@Override
	public List<UserLiteInfo> userList() {

		List<UserLiteInfo> userList = userRepo.findAllBy();

		return userList;
	}

	// 회원조회
//	일단 로그인한 경우에만 사용한다고 가정하고 로그인 정보에서 가져오는 것으로 했으나
//	수정하여 Strig userId로 해도 상관없음
	@Override
	public User userInfo(LoginInfo loginInfo) {
		User user = new User();
		// 로그인 정보를 기반으로 user 객체를 찾아오기
		String userId = loginInfo.getLoginId();
		userRepo.findByUserId(userId);

		// 민감한 정보는 null로 초기화 후 사용
		user.setUserPassword("");
		user.setUserSalt("");

		return user;
	}

	// 회원 삭제는 업데이트로 상태를 수정하는 방식
	@Override
	public void userDelete(String userId) {
		User user = new User();
		String deletedState = "Deleted";
		user = userRepo.findByUserId(userId);
		// 회원의 상태를 삭제됨으로 변경
		user.setUserState(deletedState);
		// 변경된 상태를 저장
		userRepo.save(user);
	}

	// 유저정보 불러오는 녀석
	@Override
	public User getUser(String userId) {
		User user = new User();
		user = userRepo.findByUserId(userId);
		return user;

	}

	// id 중복체크

}
