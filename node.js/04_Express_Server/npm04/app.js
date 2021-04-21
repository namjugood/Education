const express = require('express');
const path = require('path');
const cookieParser = require('cookie-parser');
const session = require('express-session');

const app = express();
app.set('port', process.env.PORT || 3000);
app.use(cookieParser());

app.use(session({
    resave:false,
    saveUninitialized:false,
    secret:"leenamju",
}));

app.use(express.json());
app.use(express.urlencoded({extended:true}));

app.get('/',(req, res)=>{
    console.log(req.cookies);
    console.log(req.cookies.test);
    // 초기 설정 당시 아무것도 없는 쿠키에 가상의 이름 제공
    // test : cookietest
    res.cookie('test', 'cookietest', {
        httpOnly:true,
        path:'/'
    });
    // name이라는 이름의 쿠키가 있으면 ooo님 반갑습니다를 send
    if(req.cookies.name){ res.send(`${req.cookies['name']} 님 안녕하세요 <br><a href="/logout">로그아웃</a>`); } // 예전방식
    // 쿠키가 없으면 아래 index.html send
    else{ res.sendFile(path.join(__dirname, '/index.html'));}
});

app.post('/login', (req, res)=>{
    // 폼데이터가 전송되어서 사용되기 위한 방법
    // 전송된 데이터를 특정 변수에 저장
    const name = req.body.name;
    console.log(req.body.name);
    // 전송된 name값을 쿠키에 저장
    const expires = new Date();
    expires.setMinutes(expires.getMinutes() + 1);
    // 'name'은 쿠키 이름 / name은 req.body.name의 변수
    // name으로 들어오는 값에 따라 'name'의 값이 변경됨
    res.cookie('name', name, {
        expires:expires,
        httpOnly:true,
        path:'/'
    });
    // 이후 redirect를 이용해 특정 리퀘스트로 이동합니다
    res.redirect('/');  
});
// 로그아웃(쿠키 삭제)
app.get('/logout', (req, res)=>{
    // 'name'은 키값, 지우려 하는 타겟 찾는 단서
    // 뒤의 name은 값, 골라서 지울때 사용
    res.clearCookie('name', req.cookies.name,{
        httpOnly:true,
        path:'/'
    });
    res.sendFile(path.join(__dirname, '/index.html'));
});

app.listen(app.get('port'), ()=>{
    console.log(app.get('port'), '번 포트에서 대기 중');
})
