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
function moveFragment(fragmentUrl) {
	fetch(fragmentUrl)
		.then(response => response.text())
		.then(html => {
			document.getElementById('fragment-container').innerHTML = html;
		})
		.catch(error => console.error('Error loading fragment:', error));
}

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

function clickBoardItem(event) {
	// 클릭된 행 요소 가져오기
	const clickedRow = event.currentTarget;

	// data-board-no 속성 값 가져오기
	const boardNo = clickedRow.getAttribute('data-board-no');

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