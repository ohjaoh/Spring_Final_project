package Job.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne // 다대일 관계 설정
    @JoinColumn(name = "boardTagNo", nullable = false) // 외래 키 컬럼
    private BoardTag boardTag; // 게시판 태그

	public Long getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(Long boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public String getWriterType() {
		return writerType;
	}

	public void setWriterType(String writerType) {
		this.writerType = writerType;
	}

	public BoardTag getBoardTag() {
		return boardTag;
	}

	public void setBoardTag(BoardTag boardTag) {
		this.boardTag = boardTag;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", writeDate=" + writeDate + ", writerName=" + writerName + ", writerType=" + writerType
				+ ", boardTag=" + boardTag + "]";
	}
    
    
}