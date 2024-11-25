package Job.entity;

import java.time.LocalDateTime;

// Projection 인터페이스
public interface UserLiteInfo {

    // Getter 메서드만 선언 (구현 X)
    String getUserId();

    String getUserName();

    String getUserEmail();

    Integer getUserAge();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
    
    
}
