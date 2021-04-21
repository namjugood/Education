getBoard_list();

async function getBoard_list(id){
    try {
        // 게시물을 조회해서 리턴받습니다
        const res = await axios.get('/boards');
        // 데이터만 추출
        const boards = res.data;
        // 테이블의 tbody에 포커스를 맞춰서
        const tbody = document.querySelector('#board-list tbody');
        tbody.innerHTML = '';
        // 게시물 하나하나를 tbody안에 추가합니다
        boards.map(async function(board){
            const row = document.createElement('tr');
            // 게시물이 클릭되면 getBoard 호출해서 게시물을 상세보기로 이동
            row.addEventListener('click', ()=>{
                getBoard(board.id);
                // ** 클릭시 카운트 증가 시켜야함
            });
            let td = document.createElement('td');
            td.textContent = board.id;
            td.id = "id";
            row.appendChild(td);

            // td = document.createElement('td');
            // let a = document.createElement('a');
            // td.textContent = board.subject;
            // const cnt = getCount(board.id);
            
            // a.textContent = " [" + cnt.length + "]";
            // a.id="count";
            // td.appendChild(a);
            // row.appendChild(td);

            const bid = board.id;
            td = document.createElement('td');
            let tContent = board.subject;
            try {
                const result = await axios.get(`/boards/replycnt/${bid}`);
                    //board.map에 async 넣을 것
                const data = result.data;
                const cnt = data.length;
                if (cnt != 0) {
                    tContent = tContent
                    + '<span style="color:red;font-weight:bold">['+cnt+']</span>';
                }
            } catch (err) {
                console.error(err);
            }
            td.innerHTML = tContent;
            row.appendChild(td);

            td = document.createElement('td');
            td.textContent = board.writer;
            td.id = "writer";
            row.appendChild(td);

            // ** 업데이트 되면 findAll로 조회해서 readCount 자동으로 표시
            td = document.createElement('td');
            td.textContent = board.readCount;
            td.id = "writer";
            row.appendChild(td);

            tbody.appendChild(row);
        });
    } catch (err) {
        console.error(err);
    }
}

async function getBoard(id) {
    location.href="/boards/boardView/" + id;
}

// async function getCount(id) {
//     try {
//         const result = await axios.get('/boards/cntReply/'+ id);
//         const cnt = result.data;
//         //alert(cnt);
//         return cnt;
//     } catch (err) {
//         console.error(err);
//     }
    
// }
