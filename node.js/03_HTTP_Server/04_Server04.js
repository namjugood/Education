const http = require('http');
const fs = require('fs').promises;

http.createServer(async (req, res)=>{
    try{
        const data = await fs.readFile('./04_Server04.html');
        res.writeHead(200, {'Content-Type':'text/html; charset=utf-8'}); // http 상태코드
        res.end(data);
    }catch(err){
        console.error(err);
        res.writeHead(500, {'Content-Type':'text/html; charset=utf-8'});
        res.end(err.message);
    }
}).listen(8081, ()=>{
    console.log('8081번 포트에서 서버 대기중입니다');
});

// http 상태코드
// 2xx : 서버 전송 정상 완료
// 3xx : redirection(다른페이지로 이동)을 알리는 상태
// 4xx : 요청 오류를 나타냅니다. 요청 자체에 오류가 있을 때 표시됩니다
// 5xx : 서버 오류를 나타냅니다. 요청은 제대로 왔지만 서버에 오류가 생겼을 때 발생합니다.
Server()