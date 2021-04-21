const http = require('http');

// createServer 함수로 서버기능을 실행시킴
http.createServer( (req, res)=>{ 
    // 서버요청 시 응답내용
    res.write('<h1>Hello Node Server!</h1>');
    res.write('<p>Welcome to my Node Server!</p>');
}) .listen( 8081, ()=>{
    // http://localhost:8081/로 진입시 html이 작동
    console.log('8081 포트에서 서버가 대기중입니다');
});