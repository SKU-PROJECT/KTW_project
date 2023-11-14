// 생성 기능
const createButton = document.getElementById('create-btn');

if (createButton) {
    createButton.addEventListener('click', event => {
        fetch('/stays/create', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                link: document.getElementById('link').value,
                category:document.getElementById('category').value,
                description:document.getElementById('description').value,
                telephone:document.getElementById('telephone').value,
                address:document.getElementById('address').value,
                road_address:document.getElementById('road_address').value,
                mapx:document.getElementById('mapx').value,
                mapx:document.getElementById('mapy').value
            })
        })
            .then(() => {
                alert('등록 완료되었습니다.');
                location.replace('/stays');
            });
    });
}

// 수정 기능
const modifyButton = document.getElementById('modify-btn');

if (modifyButton) {
    modifyButton.addEventListener('click', event => {
        let stayId = modifyButton.getAttribute('data-stayid');

        window.location.href = `/stays/edit/${stayId}`;
    });
}

//삭제 기능
const deleteButton = document.getElementById('delete-btn');

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
                        location.reload();
                    } else {
                        alert('삭제 실패했습니다.');
                    }
                })
                .catch(error => {
                    console.error('Error deleting stay:', error);
                    alert('An error occurred while deleting the stay.');
                });
        }
    });
}