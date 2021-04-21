const http = require('http');

// createServer 함수로 서버기능을 실행시킴
// + 에러처리 구문도 추가합니다.
// createServer 함수로 만든 서버객체를 server 변수에 저장하고
// 기타 설정은 server변수를 통해 별도로 실행
const server = http.createServer( (req, res)=>{ 
    // 서버요청 시 응답내용
    res.write('<h1>Hello Node Server!</h1>');
    res.write('<h2>Here is my Second Node Server!</h2>');
    res.write('<p>Welcome to my Node Server!</p>');
});
server.listen(8081);
// 기타의 설정들(대기중입니다. 에러입니다 등등)
server.on('listening', ()=>{
    console.log('8081번 포트에서 서버 대기중입니다');
});
server.on('error',(error)=>{
    console.error(error);
});