const express = require('express');
const path = require('path');
const app = express();

app.set('port', process.env.PORT || 3000); // 포트 지정
app.get('/', (req, res)=>{
    // res.send('Hello Express');

    // __dirname의 내용과 index.html 파일명이 조합된 종합 경로가 만들어지고
    // 해당파일의 내용으로 클라이언트에 응답합니다
    //res.sendFile('./index.html') 대신 확장성을 위해 path 사용
    res.sendFile(path.join(__dirname, 'index.html'));
});
app.listen(app.get('port'), ()=>{
    console.log(app.get('port'), '번 포트에서 대기중입니다');
});

// 서버 구동에 핵심이 되는 파일 app.js
// 중요 메서드
// app.set('port', 포트)로 서버가 실행될 포트 지정
// app.get('키워드', 익명함수)로 GET 요청이 올 때 어떤 동작을 할 지 지정
// app.listen('포트', 익명함수)으로 몇 번 포트에서 서버를 실행할 지 지정