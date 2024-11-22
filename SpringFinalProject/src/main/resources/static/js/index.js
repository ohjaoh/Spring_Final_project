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
