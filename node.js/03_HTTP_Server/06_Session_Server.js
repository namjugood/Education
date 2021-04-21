const http = require('http');
const fs = require('fs').promises;
const url = require('url');
const qs = require('querystring');

const parseCookies = (cookie=' ') =>
    cookie
        .split(';') 
        .map(v=>v.split('=')) 
        .reduce( (acc, [k, v]) => {
            acc[k.trim()] = decodeURIComponent(v);
            return acc;
        }, {});

const session = {};

http.createServer(async (req, res)=>{
    const cookies = parseCookies(req.headers.cookie);
    if(req.url.startsWith('/login')){
        const {query} = url.parse(req.url);       
        const {name} = qs.parse(query);
        const expires = new Date();
        expires.setMinutes(expires.getMinutes() + 1);
        const uniqueInt = Date.now(); // 세션 객체에 저장하기 위한 고유 키값
        session[uniqueInt] = { // 고유키값과 함께 이름과 유효시간 저장
            name,
            expires,
        };
        res.writeHead(302, {
            Location : '/',
            'Set-Cookie':`session=${uniqueInt}; Expires=${expires.toGMTString()}; HttpOnly; Path=/`,
        }); // 쿠키에는 고유 키 값만 session이라는 키와 함께 저장
        res.end();
    }else if(cookies.session && session[cookies.session].expires > new Date() ){
        // 세션쿠키가 존재하고 만료기간이 지나지 않았다면,
        res.writeHead(200, {'Content-Type':'text/plain; charset=utf-8'});
        res.end(`${session[cookies.session].name}님 안녕하세요`);
    }else{
        try{
            const data = await fs.readFile('./06_Cookie_page.html');
            res.writeHead(200, {'Context-Type':'text/html; charset=utf-8'});
            res.end(data);
        }catch(err){
            res.writeHead(500, {'Context-Type':'text/plain; charset=utf-8'});
            console.error(err.message);
        }
    }
}).listen(8085, () => {
    console.log('8085번 포트에서 서버 대기중입니다');
})