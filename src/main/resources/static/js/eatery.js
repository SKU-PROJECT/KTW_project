const createBtn = document.getElementById('create-btn');
const deleteBtn = document.getElementById('delete-btn');
const modifyBtn = document.getElementById('modify-btn');


if(createBtn){
    createBtn.addEventListener('click', event => {
        fetch("/api/eaterys", {
            method: 'POST',
            headers: {
                "Content-Type" : "application/json",
            },
            body: JSON.stringify({
                code: document.getElementById("code").value,
                category: document.getElementById("category").value,
                name: document.getElementById("name").value,
                location: document.getElementById("location").value,
                address: document.getElementById("address").value,
                detail: document.getElementById("detail").value
            })
        }).then(()=>{
            alert('등록');
            location.replace("/eaterys");
        });
    });
}

//delete
if(deleteBtn) {
    deleteBtn.addEventListener('click', event => {
        let id = document.getElementById('eatery-id').value;
        fetch(`/api/eaterys/${id}`, {
            method: 'DELETE'
        }).then(()=> {
            alert('삭제');
            location.replace('/eaterys');
        });
    });
}

//update
if(modifyBtn){
    modifyBtn.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/eaterys/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                code: document.getElementById('code').value,
                name: document.getElementById('name').value,
                category: document.getElementById('category').value,
                location: document.getElementById('location').value,
                address: document.getElementById('address').value,
                detail: document.getElementById('detail').value
            })
        }).then(()=>{
            alert('수정');
            location.replace(`/eaterys/${id}`);
        });
    });
}