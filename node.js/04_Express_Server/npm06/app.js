const express = require('express');
const fs = require('fs');
// const path = require('path');
const multer = require('multer');

const app = express();
app.set('port', process.env.PORT || 3000);
// 감춰진 경로 설정. 
// http://localhost:3000/routes/ -> http://0localhost:3000/ 로 설정
// app.use('/', express.static(path.join(__dirname, 'routes')));

const indexRouter = require('./routes'); 
// index파일은 파일이름을 생략해도 됨(알아서 찾아감)
const userRouter = require('./routes/users');

app.use('/', indexRouter);
// 현재 파일에서 사용한 '/'와 indexRouter에 있는 '/'와 조합이 됩니다.
// '//'가 '/'로 사용이 됩니다
app.use('/users', userRouter);
// 현재 파일에서 사용한 '/users'와 userRouter에 있는 '/'와 조합되어
// '/users/'가 사용됩니다

app.use((err, req, res, next)=>{
    console.error(err);
    res.status(404).send(err.message);
});
app.listen(app.get('port'), ()=>{
    console.log(app.get('port'), '번 포트에서 대기중')
});