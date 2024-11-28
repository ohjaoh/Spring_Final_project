package Job.service;

import java.util.List;

import Job.entity.LoginInfo;
import Job.entity.User;
import Job.entity.UserLiteInfo;

public interface UserService {
	// 회원가입
	public void registerUser(User user);

	// 회원정보수정
	public void userUpdate(User user);

	// 회원목록
	public List<UserLiteInfo> userList();

	// 로그인 회원정보
	public User userInfo(LoginInfo loginInfo);

	// 회원탈퇴, 회원삭제
	public void userDelete(String userId);

	// 유저정보
	public User getUser(String userId);

	// 유저목록(관리자)
	public List<User> getUserList();
	
	// id중복체크
	public boolean idCheck(String userId);
}