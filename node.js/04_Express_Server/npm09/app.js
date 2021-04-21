const express = require('express');
const path = require('path');
const nunjucks = require('nunjucks');
const cookieParser = require('cookie-parser');
const session = require('express-session'); // 세션 require

// db객체 require
const {sequelize} = require('./models');

// 라우터 require
const indexRouter = require('./routes');
const memberRouter = require('./routes/members');
const boardRouter = require('./routes/boards');
const dateFilter = require('nunjucks-date-filter');


// app과 nunjucks 등 설정
const app = express();
app.set('port',process.env.PORT || 3005);
app.set('view engine', 'html');
let env = nunjucks.configure('views', {express:app, watch:true});
env.addFilter('date', dateFilter);

app.unsubscribe(cookieParser('leenamju'));
// 세션설정
app.use(session({ // session()으로도 아래 내용이 default로 적용되어있음
    resave:false,
    saveUninitialized:false,
    // resave, saveUninitialized는 거의 변할일이 없음
    secret:"leenamju",
    // 세션을 암호와하기 위한 이름
    cookie:{
        httpOnly:true,
        secure:false,
    },
    name: 'session-cookie'
    // 검사페이지에서 나타나는 세션쿠키의 이름
}));

// 데이터베이스 연결
sequelize.sync({force:false})
.then(() => { console.log('데이터베이스 연결 성공'); })
.catch((err) => { console.error(err); });
// force : true -> 기존 테이블을 지우고 새 테이블 생성(기존 레코드까지 삭제)
// alter : true -> 기본 테이블 및 데이터 유지, 데이터만 추가
//                 수정된 테이블 자료형과 기존 레코드의 자료형이 안맞을 경우 에러 발생
// force : false -> 만들 테이블이 존재 시 변경 및 삭제를 하지 않음(없을때만 생성)

// 기타의 앱 설정
app.use(express.static(path.join(__dirname, 'public')));
app.use(express.json());
app.use(express.urlencoded({extended:false}));

app.use('/', indexRouter);
app.use('/members', memberRouter);
app.use('/boards', boardRouter);

// 에러처리 미들웨어
app.use((req, res, next)=>{
    const error = new Error(`${req.method} ${req.url} 라우터가 없습니다.`);
    error.status = 404;
    next(error);
});
app.use((err, req, res, next)=>{
    res.locals.message = err.message;
    res.locals.error = process.env.NODE_ENV !== 'production' ? err : {};
    res.status(err.status || 500);
    res.render('error');
});


// 수신대기 미들웨어
app.listen(app.get('port'), ()=>{
    console.log(app.get('port'), '번 포트에서 서버 대기중');
});
