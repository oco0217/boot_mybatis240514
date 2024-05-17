
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

//페이징 처리를 하여, 한페이지(더보기)에 5개 댓글을 출력
//전체 게시글 수에 따른 페이지 수
function spreadCommentList(bno, page=1){

    getListCommentToServer(bnoVal, page).then(result=>{

        console.log(result);
        const div = document.getElementById('cmtListArea');
        if(result.cmtList.length > 0){
            //   div.innerHTML = ''; //초기화

            //1페이지면 5개만 뿌리기
            //1page이상이면 1page랑 2page랑 같이 뿌릴거
            if(page==1){
                div.innerHTML = '';
            }
            
            for(let cvo of result.cmtList){


                let str = `<li class="list-group-item" data-cno="${cvo.cno}">`;
                str += `<div class="input-group mb-3">No.${cvo.cno}/`;
                str += `<div class="fw-bold">${cvo.writer}:</div>`;
                str += `${cvo.content}`;
                str += `</div> <span class="badge rounded-pill text-bg-warning">${cvo.modAt}</span>`;
                if(nickName == cvo.writer){
                    str += `<button type="button" class="btn btn-outline-warning btn-sm mod" data-bs-toggle="modal" data-bs-target="#myModal">수정</button>`;
                    str += `<button type="button" data-cno=${cvo.cno} class="btn btn-outline-danger btn-sm del">삭제</button>`;
                }
                str += `</li>`;

                div.innerHTML += str;
            }

            //더보기 버튼 설정
            let moreBtn = document.getElementById('moreBtn');
            if(result.pgvo.pageNo < result.realEndPage){
                //style.visibility = 'visible' 표시 / style.visibility = 'hidden';
                moreBtn.style.visibility = 'visible'; //버튼 표시
                moreBtn.dataset.page = page+1; //한페이지 늘린다.
            }else{
                moreBtn.style.visibility = 'hidden';
            }
            

        }else{
            div.innerHTML = '등록된 댓글이 없습니다. 댓글을 등록해보세요~';
        }

    })
}

async function getListCommentToServer(bno,page){

    try {
        const resp = await fetch('/comment/list/'+bno+'/'+page);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }

}

document.addEventListener('click',(e)=>{

    //+more
    if(e.target.id == 'moreBtn'){
        let page = parseInt(e.target.dataset.page);
        spreadCommentList(bnoVal,page);
        return;
    }
    
    //Modify
    if(e.target.classList.contains('mod')){
        let li = e.target.closest('li');
        let cmtText= li.querySelector('.fw-bold').nextSibling;
        document.querySelector('.modal-title').innerText = nickName;
        document.getElementById('cmtTextMod').value = cmtText.nodeValue;
        document.getElementById('cmtModBtn').setAttribute('data-cno',li.dataset.cno);
    }

    if(e.target.id == 'cmtModBtn'){

        const content = document.getElementById('cmtTextMod').value;
        console.log(content);
        if(content == '' || content.length == 0){
            alert('수정하실 댓글을 입력해주세요.');
            e.preventDefault();
            return;
        }

        const cmtData = {
            cno : e.target.dataset.cno,
            content : content
        };

        console.log(cmtData);
        
        modifyCommentToServer(cmtData).then(result=>{
            console.log(result);
            if(result == '1'){
                alert('댓글이 성공적으로 수정이 완료되었습니다.');
                document.querySelector('.btn-close').click();
                spreadCommentList(bnoVal);
                return;

            }
        })

    }

    //Delete
    if(e.target.classList.contains('del')){
        const cno = e.target.dataset.cno;
        //console.log(cno);
        removeCommentToServer(cno).then(result=>{
            if(result == '1'){
                alert('댓글이 성공적으로 삭제 완료되었습니다.');
                document.querySelector('.btn-close').click();
                spreadCommentList(bnoVal);
                return;
            }
        })
        
    }

})

async function modifyCommentToServer(cmtData){
    try {
        
        const url = '/comment/modify';
        const config = {
            method : 'put',
            headers : {
                'content-type' : 'application/json; charset = utf-8'
            },
                body : JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

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
