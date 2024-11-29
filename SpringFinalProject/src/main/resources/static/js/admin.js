document.addEventListener("DOMContentLoaded", function() {
	console.log("DOM이 로드되었습니다.");
});

function moveFragment(fragmentUrl) {
	fetch(fragmentUrl)
		.then(response => response.text())
		.then(html => {
			document.getElementById('fragment-container').innerHTML = html;
		})
		.catch(error => console.error('Error loading fragment:', error));
}

// 게시판 관리 데이터 로드 함수
function findAllBoards(event, element) {
	event.preventDefault(); // 기본 링크 동작 방지

	const categoryId = "All";
	console.log(`Category ID: ${categoryId}`); // 확인용 로그

	fetch(`/admin/adminBoard/${categoryId}`) // 서버로 AJAX 요청
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.text(); // 서버에서 HTML 프래그먼트 가져오기
		})
		.then(html => {
			document.getElementById('content-area').innerHTML = html; // content-area 업데이트
		})
		.catch(error => {
			console.error('Error loading category:', error);
			alert('카테고리를 불러오는 중 문제가 발생했습니다.');
		});
}

// 페이지게이션
function loadBoardList(page) {
	const categoryName = "All";
	const url = `/admin/adminBoard/${categoryName}?page=${page}&size=10`;
	fetch(url)
		.then(response => {
			if (!response.ok) {
				throw new Error('Failed to fetch board list');
			}
			return response.text();
		})
		.then(html => {
			document.getElementById('content-area').innerHTML = html;
		})
		.catch(error => {
			console.error('Error loading board list:', error);
			Swal.fire({
				icon: 'error',
				title: '오류',
				text: '게시판 목록을 불러오는 중 문제가 발생했습니다.'
			});
		});
}

// 카테고리 관리 데이터 로드 함수
function findAllCategory(event, element) {
	event.preventDefault(); // 기본 링크 동작 방지

	fetch(`/admin/adminCategory`) // 서버로 카테고리 데이터 요청
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.text(); // 서버에서 HTML 프래그먼트 가져오기
		})
		.then(html => {
			document.getElementById('content-area').innerHTML = html; // content-area 업데이트
		})
		.catch(error => {
			console.error('Error loading categories:', error);
			alert('카테고리를 불러오는 중 문제가 발생했습니다.');
		});
}

// 회원 관리 데이터 로드 함수
function findAllUsers(event, element) {
	event.preventDefault(); // 기본 링크 동작 방지

	fetch(`/admin/adminUsers`) // 서버로 회원 데이터 요청
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.text(); // 서버에서 HTML 프래그먼트 가져오기
		})
		.then(html => {
			document.getElementById('content-area').innerHTML = html; // content-area 업데이트
		})
		.catch(error => {
			console.error('Error loading users:', error);
			alert('회원 정보를 불러오는 중 문제가 발생했습니다.');
		});
}

// 카테고리 생성 버튼클릭시 생성폼 보여짐
function toggleCreateCategoryFormBtn() {
	const createCategoryForm = document.getElementById("createCategoryForm");
	const toggleFormBtn = document.getElementById("toggleFormBtn");

	if (createCategoryForm.style.display === "none" || createCategoryForm.style.display === "") {
		createCategoryForm.style.display = "block";
		toggleFormBtn.textContent = "닫기"
	} else {
		createCategoryForm.style.display = "none";
		toggleFormBtn.textContent = "카테고리 생성"
	}

}

// 게시글 삭제
function deleteBoard(event) {

	// boardNo 속성 값 가져오기
	const boardNo = event.currentTarget.getAttribute('boardNo');

	fetch(`/admin/deleteBoard/${boardNo}`)
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.text();
		})
		.then(html => {
			document.getElementById('content-area').innerHTML = html; // content-area 업데이트
		})
		.catch(error => {
			console.error('Error loading :', error);
			Swal.fire({
				icon: 'error',
				title: '오류',
				text: '게시판을 불러오는 중 문제가 발생했습니다.'
			});
		});

}
// 카테고리수정 모달창
function openEditModal(categoryId, categoryName) {
	// 카테고리 정보를 모달창에 설정
	document.getElementById('categoryId').value = categoryId;
	document.getElementById('categoryName').value = categoryName;

	// 모달창 표시
	const modal = document.getElementById('editCategoryModal');
	modal.style.display = 'block';
}

// 모달창 제거
function closeModal() {
	// 모달창 숨기기
	const modal = document.getElementById('editCategoryModal');
	modal.style.display = 'none';
}

// 수정된 카테고리를 Ajax 요청으로 보내는 함수
function submitEditCategory(categoryIdField, categoryNameField) {
	const categoryId = categoryIdField.value;
	const categoryName = categoryNameField.value;
	const categoryData = {
		categoryId: categoryId,
		categoryName: categoryName
	};

	console.log(categoryData)


	fetch(`/admin/editCategory`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json', // JSON 데이터 전송
		},
		body: JSON.stringify(categoryData)
	})
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.text();
		})
		.then(html => {
			document.getElementById('content-area').innerHTML = html; // content-area 업데이트
		})
		.catch(error => {
			console.error('Error loading :', error);
			Swal.fire({
				icon: 'error',
				title: '오류',
				text: '카테고리를 불러오는 중 문제가 발생했습니다.'
			});
		});

}
function deleteCategory(boardCategory) {
	console.log(boardCategory)
	const categoryNo = boardCategory;
	fetch(`/admin/deleteCategory/${categoryNo}`)
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok')
			}
			return response.text();
		})
		.then(html => {
			document.getElementById('content-area').innerHTML = html; //content-area 업데이
		})
		.catch(error => {
			console.error('Error loading :', error);
			Swal.fire({
				icon: 'error',
				title: '오류',
				text: '카테고리를 불러오는 중 문제가 발생했습니다.'
			});
		});

}