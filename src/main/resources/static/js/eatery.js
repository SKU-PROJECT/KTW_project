const createBtn = document.getElementById('create-btn');
const deleteBtn = document.getElementById('delete-btn');
const modifyBtn = document.getElementById('modify-btn');

if(createBtn){
    createBtn.addEventListener('click', event => {
        fetch("/eaterys/create", {
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
        let eatery_id = document.getElementById('eatery-id').value;
        fetch(`/eaterys/delete/${eatery_id}`, {
            method: 'POST'
        }).then(()=> {
            alert('삭제');
            location.replace('/eaterys');
        });
    });
}

//update
// if(modifyBtn){
//     modifyBtn.addEventListener('click', event => {
//         // let id = modifyBtn.getAttribute('eatery-id');
//
//         fetch(`/eaterys/edit/${id}`, {
//             method: 'POST',
//             headers: {
//                 "Content-Type": "application/json",
//             },
//             body: JSON.stringify({
//                 code: document.getElementById('code').value,
//                 name: document.getElementById('name').value,
//                 category: document.getElementById('category').value,
//                 location: document.getElementById('location').value,
//                 address: document.getElementById('address').value,
//                 detail: document.getElementById('detail').value
//             })
//         }).then(()=>{
//             alert('수정');
//             location.replace(`/eaterys/${id}`);
//         });
//     });
// }

if(modifyBtn){
    modifyBtn.addEventListener('click', event => {
        location.href = `/eaterys/edit/${id}`;
    });
}

const submitBtn = document.getElementById('submit-btn');
if(submitBtn){
    submitBtn.addEventListener('click', event => {

        const id = document.getElementById('eatery-id').value;

        fetch(`/eaterys/edit/${id}`, {
            method: 'POST',
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