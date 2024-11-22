function toggleBoardList() {
    const boardList = document.getElementById('boardTagListContent');
    if (boardList.style.display === 'none' || boardList.style.display === '') {
        boardList.style.display = 'flex'; // 보이기
		console.log("보이기 실행")
    } else {
        boardList.style.display = 'none'; // 숨기기
		console.log("보이기 닫기")
    }
}

// 추가적인 함수들도 동일하게 작성
function exampleFunction() {
    console.log("이 함수는 테스트용입니다!");
}

document.addEventListener("DOMContentLoaded", function () {
    console.log("DOM이 로드되었습니다.");
});
