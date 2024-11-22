package Job.controller;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class AdminPasswordHashingUtils {

	// 랜덤 Salt 생성 메서드
	public static String generateRandomSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16]; // 16바이트 길이의 랜덤 Salt
		random.nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt); // Base64 문자열로 변환
	}

	// 비밀번호 해싱 메서드
	public static String hashPassword(String password, String salt) {
		try {
			// SHA-256 알고리즘 사용
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(salt.getBytes()); // Salt 추가
			byte[] hashedBytes = md.digest(password.getBytes()); // 비밀번호 해싱
			return Base64.getEncoder().encodeToString(hashedBytes); // Base64 문자열 반환
		} catch (Exception e) {
			throw new RuntimeException("비밀번호 해싱 중 오류 발생", e);
		}
	}

	// 비밀번호 검증 메서드 (로그인 시 사용)
	public static boolean verifyPassword(String inputPassword, String storedHashedPassword, String storedSalt) {
		String hashedInputPassword = hashPassword(inputPassword, storedSalt);
		return hashedInputPassword.equals(storedHashedPassword); // 저장된 해시와 비교
	}
}
