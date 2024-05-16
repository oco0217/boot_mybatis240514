//비동기로 cmtAddBtn을 클릭하면 bno,writer,content를 비동기로 DB에 넣기.

document.getElementById('cmtAddBtn').addEventListener('click',()=>{

    const writerVal = document.getElementById('cmtWriter').innerText;
    const contentVal = document.getElementById('cmtText').value;

    if(contentVal == null || contentVal == ''){
        alert('댓글을 입력해주세요.');
        document.getElementById('cmtText').focus();
        return false;
    }

    let cmtData = {

        bno : bnoVal,
        writer : writerVal,
        content : contentVal
    };

    // console.log(cmtData);

    postCommentToServer(cmtData).then(result=>{

        if(result == '1'){
            alert('댓글이 정상적으로 등록이 완료되었습니다.');
            document.getElementById('cmtText').value = '';
            //댓글 뿌려야됌
            spreadCommentList(bnoVal);
        }

    })
})

async function postCommentToServer(cmtData){

    try {

        const url = '/comment/post';
        const config = {
            method : 'post',
            headers : {
                'content-type' : 'application/json; charset = utf-8'
            },
            body : JSON.stringify(cmtData)
        };

        const resp = await fetch(url,config);
        const result = await resp.text();
        return result;

    } catch (error) {
        console.log(error);
    }
}

function spreadCommentList(bno){

    getListCommentToServer(bno).then(result=>{

        const div = document.getElementById('cmtListArea');   
        
        div.innerHTML = '';
        
        if(result.length>0){
            for(let cvo of result){
                
                
                let str = `<li class="list-group-item" data-cno="${cvo.cno}">`;
                str += `<div class="input-group mb-3">No.${cvo.cno}/`;
                str += `<div class="fw-bold">${cvo.writer}:</div>`;
                str += `${cvo.content}`;
                str += `</div> <span class="badge rounded-pill text-bg-warning">${cvo.regAt}</span>`;
                if(nickName == cvo.writer){
                    str += `<button type="button" class="btn btn-outline-warning btn-sm mod" data-bs-toggle="modal" data-bs-target="#myModal">수정</button>`;
                    str += `<button type="button" data-cno=${cvo.cno} class="btn btn-outline-danger btn-sm del">삭제</button>`;
                }
                str += `</li>`;
                
                div.innerHTML += str;
            }
        }   
    })

}

async function getListCommentToServer(bno){

    try {
        const resp = await fetch('/comment/getList/'+bno);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }

}

document.addEventListener('click',(e)=>{
    
    //Modify
    if(e.target.classList.contains('mod')){
        document.getElementById('cmtTextMod').innerText = //closest 사용하여 cvo.content 추출
    }

    //Delete
    if(e.target.classList.contains('del')){
        const cno = e.target.dataset.cno;
        //console.log(cno);
        removeCommentToServer(cno).then(result=>{
            if(result == '1'){
                alert('댓글이 성공적으로 삭제 완료되었습니다.');
                spreadCommentList(bnoVal);
            }
        })
        
    }

})

async function removeCommentToServer(cno){
    try {
        const url = '/comment/remove/'+cno;
        const config = {
            method : 'delete'
        }
        const resp = await fetch(url,config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log()
    }
}
