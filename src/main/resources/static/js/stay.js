// 생성 기능
const createButton = document.getElementById('stay-create-btn');

if (createButton) {
    createButton.addEventListener('click', event => {
        fetch('/stays/create', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                name: document.getElementById('name').value,
                category:document.getElementById('category').value,
                price:document.getElementById('price').value,
                detail:document.getElementById('detail').value,
                use_guide:document.getElementById('use_guide').value,
                address:document.getElementById('address').value,
                amenity:document.getElementById('amenity').value,
                service:document.getElementById('service').value,
            })
        })
            .then(() => {
                alert('등록 완료되었습니다.');
                location.replace('/stays');
            });
    });
}

// 수정 기능
const modifyButton = document.getElementById('stay-modify-btn');

if (modifyButton) {
    modifyButton.addEventListener('click', event => {
        let stayId = modifyButton.getAttribute('data-stayid');

        window.location.href = `/stays/edit/${stayId}`;
    });
}

//삭제 기능
const deleteButton = document.getElementById('stay-delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let stayId = deleteButton.getAttribute('data-stayid');
        if (confirm('해당 숙소를 삭제 하시겠습니까')) {
            fetch(`/stays/delete/${stayId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
            })
                .then(response => {
                    if (response.ok) {
                        alert('삭제 완료되었습니다.');
                        window.location.href = '/stays'; // 리다이렉션
                    } else {
                        alert('삭제 실패했습니다.');
                    }
                })
                .catch(error => {
                    console.error('Error deleting stay:', error);
                    alert('삭제 중에 오류가 발생했습니다.');
                });
        }
    });
}
