package Job.entity;

import java.time.LocalDateTime;

public interface UserLiteInfo {

	String getUserNo();

	String getUserId();

	String getUserName();

	String getUserEmail();

	String getUserState();

	Integer getUserAge();

	LocalDateTime getCreatedAt();

	LocalDateTime getUpdatedAt();

}
