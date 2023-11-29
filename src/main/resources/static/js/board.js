// 생성 기능
const createButton = document.getElementById('create-btn');

if (createButton) {
    createButton.addEventListener('click', event => {
        fetch('/new-board', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value,
                mem_id: document.getElementById('mem_id').value,
                board_category: document.getElementById('board_category').value
            })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(() => {
                alert('등록 완료되었습니다.');
                location.replace('/boards');
            })
            .catch(error => {
                console.error('Fetch error:', error);
            });
    });
}

// 삭제 기능
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let boardId = document.getElementById('data-boardid').value;
        fetch(`/boards/delete/${boardId}`, {
            method: 'DELETE'
        })
            .then(() => {
                alert('삭제가 완료되었습니다.');
                location.replace('/boards');
            });
    });
}
// 수정 기능
const modifyButton = document.getElementById('modify-btn');

if (modifyButton) {
    modifyButton.addEventListener('click', event => {
        let boardId = modifyButton.getAttribute('data-boardid');

        window.location.href = `/boards/edit/${boardId}`;
    });
}


// if (modifyButton) {
//     modifyButton.addEventListener('click', event => {
//         let params = new URLSearchParams(location.search);
//         let id = params.get('id');
//
//         fetch(`/boards/${id}`, {
//             method: 'PUT',
//             headers: {
//                 "Content-Type": "application/json",
//             },
//             body: JSON.stringify({
//                 title: document.getElementById('title').value,
//                 content: document.getElementById('content').value,
//                 mem_id:document.getElementById('mem_id').value,
//                 board_category:document.getElementById('board_category').value
//             })
//         })
//             .then(() => {
//                 alert('수정이 완료되었습니다.');
//                 location.replace(`/boards/${id}`);
//             });
//     });
// }

// const createButton = document.getElementById('create-btn');
//
// if (createButton) {
//     createButton.addEventListener('click', event => {
//         // 폼 데이터 생성
//         const formData = new FormData();
//         formData.append('title', document.getElementById('title').value);
//         formData.append('content', document.getElementById('content').value);
//         formData.append('mem_id', document.getElementById('mem_id').value);
//         formData.append('board_category', document.getElementById('board_category').value);
//
//         // 파일 입력 가져오기
//         const fileInput = document.getElementById('file');
//         // 모든 선택된 파일을 FormData에 추가
//         for (const file of fileInput.files) {
//             formData.append('files', file);
//         }
//
//         // Fetch를 사용하여 데이터 전송
//         fetch('/api/boards', {
//             method: 'POST',
//             body: formData,
//         })
//             .then(() => {
//                 alert('등록 완료되었습니다.');
//                 location.replace('/boards');
//             })
//             .catch(error => {
//                 console.error('에러:', error);
//             });
//     });
// }