// 게시판관리 클릭시 하위목록이 보여짐
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

// 태그관리 클릭 함수
function boardTagManagement(url) {
	console.log("태그관리가 클릭됨!");
	// AJAX 요청으로 새로운 프래그먼트를 가져와 특정 영역을 대체
	$.get(url, function(data) {
		$("#fragment-container").html(data); // 기존 내용을 새 프래그먼트로 덮어쓰기
	});
}

document.addEventListener("DOMContentLoaded", function() {
	console.log("DOM이 로드되었습니다.");
});

