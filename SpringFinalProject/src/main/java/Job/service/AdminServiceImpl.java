package Job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Job.controller.AdminPasswordHashingUtils;
import Job.entity.Admin;
import Job.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepo;

	// 관리자 가입 메서드 (비밀번호는 해시하여 저장)
	@Override
	public void registerAdmin(Admin admin) {
		// 랜덤 Salt 생성
		String salt = AdminPasswordHashingUtils.generateRandomSalt();

		// 비밀번호 해싱
		String hashedPassword = AdminPasswordHashingUtils.hashPassword(admin.getAdminPassword(), salt);

		// Admin 엔티티에 해싱된 비밀번호와 Salt 설정
		admin.setAdminPassword(hashedPassword);
		admin.setAdminSalt(salt);

		// 데이터베이스에 저장
		adminRepo.save(admin);
	}

	// 관리자 로그인
	@Override
	public boolean AdminLogin(String inputAdminId, String inputPassword) {
		// 아이디를 조회하여 존재여부 체크
		Admin admin = adminRepo.findByAdminId(inputAdminId);
		System.out.println("입력한 id는 무엇인가요? "+inputAdminId);
		if (admin == null) {
			throw new RuntimeException("존재하지 않는 관리자입니다.");
		}
		// 존재하는 경우 아이디로 찾아온 관리자의 비밀번호와 솔트를 가져와 디코딩 후 입력한 비밀번호와 비교
		String getSalt = admin.getAdminSalt();
		String getPassword = admin.getAdminPassword();

		// 입력한 비밀번호를 가져온 솔트값과 합하여 해시화
		String hashedInputPasswrod = AdminPasswordHashingUtils.hashPassword(inputPassword, getSalt);

		// 해싱된 입력한 비밀번호와 기존의 비밀번호를 비교하여 boolean형으로 반환
		return getPassword.equals(hashedInputPasswrod);
	}
}
