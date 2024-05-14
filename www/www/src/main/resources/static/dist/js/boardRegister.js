console.log("board Register In");

document.getElementById('trigger').addEventListener('click',()=>{
    document.getElementById('files').click();
})

const regExp = new RegExp('\.(exe|sh|bat|jar|dll|msi)$'); //실행파일 막기
const maxSize = 1024*1024*20;

function fileValidation(fileName, fileSize){
    if(regExp.test(fileName)){
        return 0;
    }
    if(fileSize > maxSize){
        return 0;
    }
    return 1;
}

document.addEventListener('change',(e)=>{
    if(e.target.id == 'files'){
        const fileObject = document.getElementById('files').files;
        console.log(fileObject);
        document.getElementById('regBtn').disabled=false;

        const div = document.getElementById('fileZone');
        div.innerHTML='';
        let isOk = 1;
        
        let ul = `<ul class="list-group list-group-flush">`;
        for(let file of fileObject){
            //개별 파일 검증 통과 결과
            let vaildResult = fileValidation(file.name, file.size);
            isOk *= vaildResult;
            ul += `<li class="list-group-item">`;
            ul += `${vaildResult ? '<div class="fw-bold text-success">업로드 가능</div>' : '<div class="fw-bold text-danger">업로드 불가능</div>'}`;
            ul += `${file.name}</div>`;
            ul+= `<span class="badge rounded-pill text-bg-${vaildResult ? 'success' : 'danger'}">${file.size} Bytes</span>`;
            ul += `</li>`;
        }
        ul += `</ul>`;
        div.innerHTML = ul;

        if(isOk == 0){
            document.getElementById('regBtn').disabled=true;
        }

        
    }
})