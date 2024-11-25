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
			if (result.value === '1234') { // 비밀번호 확인
				window.location.href = '/admin'; // 관리자 페이지로 이동
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

document.addEventListener('DOMContentLoaded', function () {
    const registerLink = document.getElementById('registerLink');
    const centerContainer = document.querySelector('.center');

    if (registerLink && centerContainer) {
        registerLink.addEventListener('click', function (event) {
            event.preventDefault();

            // 서버에서 registerPage 내용을 가져옴
            fetch('/registerPage')
                .then(response => {
                    if (!response.ok) {
                        throw new Error('네트워크 응답이 올바르지 않습니다.');
                    }
                    return response.text();
                })
                .then(html => {
                    // center 영역에 가져온 HTML 삽입
                    centerContainer.innerHTML = html;
                })
                .catch(error => {
                    console.error('회원가입 페이지 로드 중 오류 발생:', error);
                });
        });
    }
});
