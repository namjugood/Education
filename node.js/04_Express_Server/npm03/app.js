const express = require('express');
const path = require('path');
//--------------------추가 설치 모듈 require-------------------
    // 각각의 요청과 응답에 대한 필요 정보를 보기 위한 모듈
const morgan = require('morgan');
    // 쿠키 사용을 http서버때 보다 간결하게 사용하기 위한 모듈
const cookieParser = require('cookie-parser');
    // 세션 사용을 http서버때 보다 간결하게 사용하기 위한 모듈
const session = require('express-session');
// const bodyParser = require('body-parser');
//--------------------- express 설정---------------------------
const app = express();
app.set('port', process.env.PORT || 3000);
//-------------------공통 미들웨어 설정-------------------------
// app.use(morgan('dev'));
// 실행결과 : GET / 00 5.316ms - 165
// method 방식, 응답결과 코드, 요청과 실행에 걸린시간 등
app.use(cookieParser());
app.use(express.json()); // body-parser json(json 사용을 위한 모듈)
app.use(express.urlencoded({extended:true})); // body-parser form data 모듈
//app.use(body-Parser.json());
//app.use(body-Parser.urlencoded({extended:false}));
app.use(session({
    resave:false,
    saveUninitialized:false,
    secret:"leenamju",
}));


app.get('/',(req, res)=>{
    // 2. 저장된 쿠키를 불러와서 활용할 변수 req.cookies
    console.log(req.cookies);
    // 1. 쿠키의 저장
    const name = 'Leenamju';
    res.cookie('name', encodeURIComponent(name), {
        expire:new Date(),
        httpOnly:true,
        path:'/'
    });
    // 3. 쿠키의 삭제
    /*
    res.clearCookie('name', encodeURIComponent(name),{
        httpOnly:true,
        path:'/'
    });
    */
    // 4. 세션의 저장
    req.session.id = 'hello';
    req.session.data = 'afadaf';
    // 다른 미들웨어에서 req.session.data라는 이름으로 사용가능
    // (영구적 저장)

    res.sendFile(path.join(__dirname, '/index.html'));
    // morgan 적용 시
    //res.send("<h1>Hello Express Server</h1>")
});

app.listen(app.get('port'), () =>{
    console.log(app.get('port'), '번 포트에서 대기중');
});