const createBtn = document.getElementById('create-btn');
const deleteBtn = document.getElementById('delete-btn');
const modifyBtn = document.getElementById('modify-btn');

if(createBtn){
    createBtn.addEventListener('click', event => {
        fetch("/api/attractions", {
            method: 'POST',
            headers: {
                "Content-Type" : "application/json",
            },
            body: JSON.stringify({
                name: document.getElementById("name").value,
                category: document.getElementById("category").value,
                location: document.getElementById("location").value,
                address: document.getElementById("address").value,
                detail: document.getElementById("detail").value
            })
        }).then(()=>{
            alert('등록');
            location.replace("/attractions");
        });
    });
}

if(deleteBtn) {
    deleteBtn.addEventListener('click', event => {
        let id = document.getElementById('attraction-id').value;
        fetch(`/api/attractions/${id}`, {
            method: 'DELETE'
        }).then(()=> {
            alert('삭제');
            location.replace('/attractions');
        });
    });
}

if(modifyBtn){
    modifyBtn.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/attractions/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                name: document.getElementById('name').value,
                category: document.getElementById('category').value,
                location: document.getElementById('location').value,
                address: document.getElementById('address').value,
                detail: document.getElementById('detail').value
            })
        }).then(()=>{
            alert('수정');
            location.replace(`/attractions/${id}`);
        });
    });
}