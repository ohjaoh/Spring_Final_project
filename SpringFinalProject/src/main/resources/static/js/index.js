// SweetAlert2로 관리자 모달 구현
function showAdminModal() {
	Swal.fire({
		title: '관리자 비밀번호 입력',
		input: 'password',
		inputPlaceholder: '비밀번호를 입력하세요',
		showCancelButton: true,
		confirmButtonText: '확인',
		cancelButtonText: '취소',
		preConfirm: (password) => {
			if (!password) {
				Swal.showValidationMessage('비밀번호를 입력해야 합니다.');
				return false;
			}
			return password;
		}
	}).then((result) => {
		if (result.isConfirmed) {
			if (result.value === '1234') {
				window.location.href = '/admin';
			} else {
				Swal.fire({
					icon: 'error',
					title: '실패',
					text: '비밀번호가 틀렸습니다.'
				});
			}
		}
	});
}

document.addEventListener('DOMContentLoaded', function() {
	const registerLink = document.getElementById('registerLink');
	const centerContainer = document.querySelector('.center');

	if (registerLink && centerContainer) {
		registerLink.addEventListener('click', function(event) {
			event.preventDefault();

			fetch('/registerPage')
				.then(response => {
					if (!response.ok) {
						throw new Error('네트워크 응답이 올바르지 않습니다.');
					}
					return response.text();
				})
				.then(html => {
					centerContainer.innerHTML = html;
				})
				.catch(error => {
					console.error('회원가입 페이지 로드 중 오류 발생:', error);
				});
		});
	}
});


function moveFragment(fragmentUrl) {
	fetch(fragmentUrl)
		.then(response => response.text())
		.then(html => {
			document.getElementById('fragment-container').innerHTML = html;
		})
		.catch(error => console.error('Error loading fragment:', error));
}

// 카테고리별 게시판가져오는 함수
function loadCategory(event, element) {
	event.preventDefault(); // 기본 링크 동작 방지

	const categoryId = element.getAttribute('data-id'); // 클릭한 카테고리의 ID 가져오기
	//console.log(`Category ID: ${categoryId}`); // 확인용 로그

	fetch(`/board/category/${categoryId}`) // 서버로 AJAX 요청
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.text(); // 서버에서 HTML 프래그먼트 가져오기
		})
		.then(html => {
			document.getElementById('content-area').innerHTML = html; // 가운데 content 영역 업데이트
		})
		.catch(error => {
			console.error('Error loading category:', error);
			Swal.fire({
				icon: 'error',
				title: '오류',
				text: '카테고리를 불러오는 중 문제가 발생했습니다.'
			});
		});
}

// 마이페이지 불러오는 함수
function loadMyPage(event, element) {
	event.preventDefault(); // 기본 링크 동작 방지

	fetch(`/user/myPage`) // 서버로 AJAX 요청
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.text();
		})
		.then(html => {
			document.getElementById('content-area').innerHTML = html;
		})
		.catch(error => {
			console.error('Error loading category:', error);
			Swal.fire({
				icon: 'error',
				title: '오류',
				text: '회원정보를 불러오는 중 문제가 발생했습니다.'
			});
		});
}

// 회원가입페이지 불러오는 함수
function registerPage(event, element) {
	event.preventDefault(); // 기본 링크 동작 방지

	fetch(`/registerPage`) // 서버로 AJAX 요청
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.text();
		})
		.then(html => {
			document.getElementById('content-area').innerHTML = html;
		})
		.catch(error => {
			console.error('Error loading :', error);
			Swal.fire({
				icon: 'error',
				title: '오류',
				text: '회원가입을 불러오는 중 문제가 발생했습니다.'
			});
		});
}

// 클릭된 게시글 진입하는 함수
function clickBoardItem(event) {
	// 클릭된 행 요소 가져오기
	const clickedRow = event.currentTarget;

	// boardNo 속성 값 가져오기
	const boardNo = clickedRow.getAttribute('boardNo');

	// 확인용 출력 (콘솔에 출력)
	// console.log(`게시판 번호: ${boardNo}`);


	fetch(`/boardPage/${boardNo}`) // 서버로 AJAX 요청
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.text();
		})
		.then(html => {
			document.getElementById('content-area').innerHTML = html;
		})
		.catch(error => {
			console.error('Error loading :', error);
			Swal.fire({
				icon: 'error',
				title: '오류',
				text: '게시판 페이지를 불러오는 중 문제가 발생했습니다.'
			});
		});
}

// id 중복체크하는 함수
function idCheck() {
	const userId = document.getElementById("userId").value;
	if (!userId) {
		Swal.fire({
			icon: 'warning',
			title: '경고',
			text: '아이디를 입력하세요.'
		});
		return;
	}
	// console.log(username);
	fetch(`/idCheck/${userId}`)
		.then(response => response.json())
		.then(data => {
			if (data.isAvailable) {
				Swal.fire({
					icon: 'success',
					title: '사용 가능',
					text: '사용 가능한 아이디입니다.'
				});
			} else {
				Swal.fire({
					icon: 'error',
					title: '중복 아이디',
					text: '이미 사용 중인 아이디입니다.'
				});
			}
		})
		.catch(error => {
			console.error("Error:", error);
			Swal.fire({
				icon: 'error',
				title: '오류',
				text: '아이디 확인 중 문제가 발생했습니다.'
			});
		});
}

// 게시글 수정페이지 진입
function editBoard(event) {
	const boardNo = event.currentTarget.getAttribute('boardNo');
	console.log(boardNo);

	fetch(`/boardUpdate/${boardNo}`)
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.text();
		})
		.then(html => {
			document.getElementById('content-area').innerHTML = html;
		})
		.catch(error => {
			console.error('Error loading :', error);
			Swal.fire({
				icon: 'error',
				title: '오류',
				text: '게시판 페이지를 불러오는 중 문제가 발생했습니다.'
			});
		});
}

function updateBoard(event) {
	const boardNo = event.currentTarget.getAttribute('boardNo');
	const boardTitle = document.getElementById("boardTitle").value;
	const category = document.getElementById("category").value;
	const boardContent = document.getElementById("boardContent").value;

	// 데이터 객체 생성
	const requestData = {
		boardNo: boardNo,
		boardTitle: boardTitle,
		category: category,
		boardContent: boardContent
	};

	console.log(requestData);
	fetch(`/board/update/${boardNo}`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json', // JSON 데이터 전송
		},
		body: JSON.stringify(requestData) // 데이터를 JSON 문자열로 변환
	})
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.text();
		})
		.then(html => {
			document.getElementById('content-area').innerHTML = html;
		})
		.catch(error => {
			console.error('Error loading :', error);
			Swal.fire({
				icon: 'error',
				title: '오류',
				text: '게시판 페이지를 불러오는 중 문제가 발생했습니다.'
			});
		});

}
function deleteBoard() {
	const boardNo = event.currentTarget.getAttribute('boardNo');
	fetch(`/board/delete/${boardNo}`)
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.text();
		})
		.then(html => {
			document.getElementById('content-area').innerHTML = html;
		})
		.catch(error => {
			console.error('Error loading :', error);
			Swal.fire({
				icon: 'error',
				title: '오류',
				text: '게시판 페이지를 불러오는 중 문제가 발생했습니다.'
			});
		});
}

//페이지게이션
function loadBoardList( page) {

	const categoryName = document.getElementById("categoryName").value;
	
	console.log(categoryName)
	const url = `/board/category/${categoryName}?page=${page}&size=10`;
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