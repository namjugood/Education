getReply();

async function getReply() {
    const boardnum = document.getElementById("boardnum").value;
    console.log("boardnum : " + boardnum);
    try {
        // 해당 게시물의 댓글을 얻어옵니다
        const res = await axios.post('/boards/getReply', {boardnum});
        const reply = res.data;
        let tbody = document.querySelector('#reply-list tbody');
        tbody.innerHTML = '';
        // 댓글의 개수만큼 작업할 함수를 실행합니다
        reply.map(function(rep){
            let row = document.createElement('tr'); // tr 생성
            let td = document.createElement('td'); // td 생성
            td.textContent
                = String(rep.created_at).substr(2,2) + "/" 
                + String(rep.created_at).substr(5,2) + "/"
                + String(rep.created_at).substr(8,2) + " "
                + String(rep.created_at).substr(11,5)
            td.id = "subject";
            row.appendChild(td);

            td = document.createElement('td');
            td.id = "subject";
            td.textContent = rep.writer;
            row.appendChild(td);

            td = document.createElement('td');
            td.textContent = rep.reply;
            row.appendChild(td);
            
            td = document.createElement('td');
            const writer = rep.writer;
            const userid = document.getElementById("userid").value;

            if(userid!=writer){
                const non = document.createElement('text');
                non.textContent = ' ';
                td.appendChild(non);
            }else if(userid==writer){
                const remove = document.createElement('button');
                remove.textContent = '삭제';
                remove.addEventListener('click', async()=>{
                    const id = rep.id;
                    try {
                        await axios.delete(`/boards/${id}`);
                        getReply(boardnum);
                    } catch (err) {
                        console.error(err);
                    }
                });
                td.appendChild(remove);
            }
            td.id = "subject";
            row.appendChild(td);

            tbody.appendChild(row);
        })
    } catch (err) {
        console.error(err);
    }
}

async function writeReply() {
    const reply = document.getElementById("reply").value;
    const boardnum = document.getElementById("boardnum").value;
    if (reply=='' || !reply) {
        return alert('내용을 입력하세요');
    }
    
    try {
        await axios.post('/boards/writeReply', {boardnum, reply});
    } catch (err) {
        console.error(err);
    }
    document.getElementById("reply").value = '';
    getReply();
}












