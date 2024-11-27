package Job.entity;

import java.time.LocalDateTime;

public interface UserLiteInfo {

    String getUserId();

    String getUserName();

    String getUserEmail();

    Integer getUserAge();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
    
    
}
