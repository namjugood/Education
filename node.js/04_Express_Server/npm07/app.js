// 템플릿 엔진
// - HTML의 단점을 개선하기 위해 사용되는 마크업 & 실행 언어
// - 반복문, 조건문, 변수 등을 사용할 수 있습니다
// - HTML태그와 같이 어울려 작성하고, 동적 페이지 작성이 가능합니다
// - HTML안에서 사용되는 JSP 문법과 비슷합니다.
// - Pug(구 Jade), el, nunjucks 등이 있습니다.

const express = require('express');
const nunjucks = require('nunjucks');
const app = express();
app.set('port', process.env.PORT || 3000);
// app.set('변수명', 저장내용);
// node server에 저장할 수 있는 저장소 중 하나이며, 가장 직관적으로
// 다룰 수 있는 공간입니다.
// 변수 값을 얻어내기 위해 app.get('변수이름')을 사용합니다
// 다만 시스템에서 사용하는 이름이 많고 노출의 위험이 있어서
// 시스템 정보 이외의 내용을 추가로 저장하는 행위는 지양해야합니다.
app.set('view engine', 'html');
// nunjucks 적용 폴더 및 설정
nunjucks.configure('views', {express:app, watch:true});
app.get('/', (req, res)=>{
    res.render('index', {title:'Express'});
});
app.get('/n01', (req, res)=>{
    res.render('nunjucks01', {title:'Express'});
});
app.get('/n02', (req, res)=>{
    res.render('nunjucks02', {title:'Express'});
});
app.get('/n03', (req, res)=>{
    res.render('nunjucks03', {title:'Express'});
});
app.get('/n04', (req, res)=>{
    res.render('nunjucks04', {title:'Express'});
});
app.use((err, req, res, next)=>{
    console.error(err);
    res.send(err.message);
});
app.listen(app.get('port'), ()=>{
    console.log(app.get('port'), '번 포트에서 서버 대기중');
});

/*
* req 내부 객체들
- req.app : req 객체를 통해 app 객체에 접근할 수 있습니다.
  req.app.get('port')와 같은 식으로 사용할 수 있습니다.
- req.body : body-parser 미들웨어가 만드는 요청의 본문을 해석한 객체입니다.
- req.cookies : cookie-parser 미들웨어가 만드는 요청의 쿠키를 해석한 객체입니다
- req.ip : 요청의 ip 주소가 담겨있습니다.
- req.params : 라우트 매개변수에 대한 정보가 담긴 객체입니다
- req.query : 쿼리스트링에 대한 정보가 담긴 객체입니다.
- req.signedCookies : 서명된 쿠키들은 req.cookies 대신 여기에 담겨있습니다
- req.get(헤더이름) : 헤더의 값을 가져오고 싶을 때 사용하는 메서드입니다.


* res 내부객체들
res.app : req.app처럼 res 객체를 통해 app 객체에 접근할 수 있습니다
res.cookie(키, 값, 옵션) : 쿠키를 설정하는 메서드입니다.
res.clearCookie(키, 값, 옵션) : 쿠키를 제거하는 메서드입니다.
res.end() : 데이터 없이 응답을 보냅니다.
res.json(JSON) : JSON 형식의 응답을 보냅니다.
res.redirect(주소) : 리다이렉트할 주소와 함께 응답을 보냅니다.
res.render(뷰, 데이터) : 다음 절에서 다룰 템플릿 엔진을 렌더링해서 응답할 때 사용하는 메서드입니다.
res.send(데이터) : 데이터와 함께 응답을 보냅니다. 데이터는 문자열 혹은 HTML, 버퍼, 객체, 배열일수 있습니다.
res.sendFile(경로) : 경로에 위치한 파일을 응답합니다.
res.setHeader(헤더, 값) : 응답의 헤더를 설정합니다.
res.status(코드) : 응답 시의 HTTP 상태코드를 지정합니다.
*/