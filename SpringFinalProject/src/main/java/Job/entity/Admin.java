package Job.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "admin")
@Data
public class Admin {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adminNo; // 관리자 번호 (기본 키, 자동 증가)

    @NotBlank(message = "아이디가 비었습니다.")
    @Size(min = 4, message = "아이디는 4자 이상이어야 합니다.")
    private String adminId; // 관리자 아이디

    @NotBlank(message = "비밀번호가 비었습니다.")
    private String adminPassword; // 관리자 비밀번호

    @NotBlank(message = "이름이 비었습니다.")
    @Size(min = 2, message = "이름은 2자 이상이어야 합니다.")
    private String adminName; // 관리자 이름

    @NotBlank(message = "전화번호가 비었습니다.")
    private String adminPhoneNumber; // 관리자 전화번호
    
    @NotBlank(message = "직책이 비었습니다.")
    private String adminPosition;

	public long getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(long adminNo) {
		this.adminNo = adminNo;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPhoneNumber() {
		return adminPhoneNumber;
	}

	public void setAdminPhoneNumber(String adminPhoneNumber) {
		this.adminPhoneNumber = adminPhoneNumber;
	}

	public String getAdminPosition() {
		return adminPosition;
	}

	public void setAdminPosition(String adminPosition) {
		this.adminPosition = adminPosition;
	}

	@Override
	public String toString() {
		return "Admin [adminNo=" + adminNo + ", adminId=" + adminId + ", adminPassword=" + adminPassword
				+ ", adminName=" + adminName + ", adminPhoneNumber=" + adminPhoneNumber + ", adminPosition="
				+ adminPosition + "]";
	}

    
}