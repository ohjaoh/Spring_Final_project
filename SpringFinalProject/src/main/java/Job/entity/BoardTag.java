package Job.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "BoardTag")
public class BoardTag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int boardTagNo;
	
	@NotBlank(message="태그가 비었습니다.")
	private String TagName;

	public int getBoardTagNo() {
		return boardTagNo;
	}

	public void setBoardTagNo(int boardTagNo) {
		this.boardTagNo = boardTagNo;
	}

	public String getTagName() {
		return TagName;
	}

	public void setTagName(String tagName) {
		TagName = tagName;
	}

	@Override
	public String toString() {
		return "BoardTag [boardTagNo=" + boardTagNo + ", TagName=" + TagName + "]";
	}

	
}
