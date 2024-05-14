console.log("board Detail JS in")

document.addEventListener('click',(e)=>{
    //리스트 버튼 클릭했을 경우
    if(e.target.id == 'listBtn'){
        console.log('리스트 클릭');
        location.href="/board/list";
        return;
    }
    //삭제 버튼을 클릭했을 경우
    if(e.target.id == 'delBtn'){
        document.getElementById('delForm').submit();
        return;
    }
    //수정 버튼 클릭 했을 경우
    if(e.target.id=='modBtn'){
        document.getElementById('title').readOnly=false;
        document.getElementById('content').readOnly=false;

        let modBtn = document.createElement('button');
        //<button type="submit"></button>
        modBtn.setAttribute('type','submit');
        modBtn.classList.add('btn','btn-outline-warning');
        modBtn.innerText = 'Submit';

        //위에서 생성한 버튼 modForm에 추가
        document.getElementById('modForm').appendChild(modBtn);

        //modBtn, delBtn 임시 삭제
        document.getElementById('modBtn').remove();
        document.getElementById('delBtn').remove();
        return;
    } 

})