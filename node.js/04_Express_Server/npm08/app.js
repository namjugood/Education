// 프로젝트 시작 전에 npx sequelize init을 터미널에서 실행하여
// sequelize 구조를 생성합니다.

const express = require('express');
const path = require('path');
const nunjucks = require('nunjucks');

// config.json의 내부 정보로 연결하기 위한 db 객체를 require 합니다
const { sequelize } = require('./models');

// 라우터들을 분리합니다
const indexRouter = require('./routes');
const usersRouter = require('./routes/users');
const commentsRouter = require('./routes/comments');

// app 설정 및 nunjucks 설정
const app = express();
app.set('port', process.env.PORT || 3030);
app.set('view engine', 'html');
nunjucks.configure('views', {express:app, watch:true});

// 숨김폴더 및 공용폴더 설정
// 클라이언트에서 /sequelaixe.js를 요청하면 서버에서는 
// localhost:3003/public/sequelize.js로 응답
app.use(express.static(path.join(__dirname, 'public')));
// 폼데이터 및 json 사용을 위한 설정
app.use(express.json());
app.use(express.urlencoded({extended:false}));

app.use('/', indexRouter);
app.use('/users', usersRouter);
app.use('/comments', commentsRouter);



// 데이터베이스 연결
// 모델 제작 후 데이터베이스 연결 시, 해당 모델에 매핑되는 테이블이 없으면
// 새로 테이블을 만들라는 옵션. 값이 true이면 기존 테이블도 지우고 강제로
// 새 테이블을 생성합니다.
sequelize.sync({force:false})
.then(() => { 
    console.log('데이터베이스 연결 성공');
})
.catch((err) => {
    console.error(err);
});

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
app.listen(app.get('port'), ()=>{
    console.log(app.get('port'), '번 포트에서 서버 대기중');
});