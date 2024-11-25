package Job.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "BoardCategory")
public class BoardCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int boardCategoryNo;

	@NotBlank(message = "카테고리이름이 비었습니다.")
	private String categoryName;

	public int getBoardCategoryNo() {
		return boardCategoryNo;
	}

	public void setBoardCategoryNo(int boardCategoryNo) {
		this.boardCategoryNo = boardCategoryNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String boardCategoryName) {
		categoryName = boardCategoryName;
	}

	@Override
	public String toString() {
		return "BoardCategory [boardCategoryNo=" + boardCategoryNo + ", CategoryName=" + categoryName + "]";
	}

}
