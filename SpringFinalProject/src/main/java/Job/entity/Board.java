package Job.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "Board")
@Data
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardNo; // 게시판 번호 (기본 키, 자동 증가)

	@NotBlank(message = "제목이 비었습니다.")
	@Size(max = 100, message = "제목은 100자 이내여야 합니다.") // 최대 길이 제한
	private String boardTitle; // 게시판 제목
	
	@NotBlank(message = "내용이 비었습니다.")
	@Column(columnDefinition = "TEXT")
	private String boardContent; // 게시판 내용

	@Temporal(TemporalType.TIMESTAMP) // 날짜-시간 형식으로 저장
	@CreationTimestamp // 엔터티 생성 시 자동으로 현재 시간 설정
	@PastOrPresent(message = "작성일은 현재 또는 과거 날짜여야 합니다.") // 현재 또는 과거만 허용
	private Date writeDate; // 게시판 작성일

	@Size(max = 50, message = "작성자 이름은 50자 이내여야 합니다.") // 최대 길이 제한
	private String writerName; // 작성자 이름

	@Size(max = 20, message = "작성자 타입은 20자 이내여야 합니다.") // 최대 길이 제한
	private String writerType; // 작성자 타입

	@Size(max = 50, message = "게시판 태그는 50자 이내여야 합니다.") // 최대 길이 제한
	@Column(nullable = false, columnDefinition = "varchar(50) default 'etc'")
	private String boardTag; // 게시판 태그

	
}