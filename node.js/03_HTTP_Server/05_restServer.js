const http = require('http');
const fs = require('fs').promises;
const users = {};

http.createServer(async (req, res)=>{
    try{
        if(req.method==='GET'){
            if(req.url==='/'){
                const data = await fs.readFile('./05_restFront.html');
                res.writeHead(200, {'Content-Type':'text/html; charset=utf-8'});
                return res.end(data);
            }else if(req.url==='/about'){
                const data = await fs.readFile('./05_about.html');
                res.writeHead(200, {'Content-Type':'text/html; charset=utf-8'});
                return res.end(data);
            }else if(req.url==='/users'){
                // json 데이터 전송을 위한 헤더 설정
                res.writeHead(200, {'Content-Type':'application/json; charset=utf-8'});
                // users 객체 안의 내용을 json 형식으로 변경하여 전송
                return res.end(JSON.stringify(users));
            }    
            try{
                const data = await fs.readFile(`.${req.url}`);
                return res.end(data);
            }catch(err){
                // 주소에 해당하는 라우트를 못찾았다는 404 Not Found error 발생
            }
        }else if(req.method==='POST'){
            if(req.url==='/user'){
                let body='';
                // 요청의 body를 stream 형식으로 받음
                req.on('data',(data)=>{ // {name:'홍길동'}
                    body += data;
                });
                // 요청의 body를 다 받은 후 실행됨
                return req.on('end', ()=>{
                    console.log('POST 본문(Body):', body);
                    const {name} = JSON.parse(body); // 전달된 데이터를 name변수에 
                    const id = Date.now(); // id 변수에 날짜를
                    users[id] = name; // id가 키값, name이 value
                    res.writeHead(201, {'Content-Type':'text/html; charset=utf-8'});
                    res.end('ok');
                });
            }
        }else if(req.method==='PUT'){
            // 요청내용 : axios.put('/user/' + key, {name});
            // console.log(req.url); /user/1617773005525
            if(req.url.startsWith('/user/')){
                // 키 값 1617773005525
                const key = req.url.split('/')[2];
                let body = '';
                // data <- {name:실제전송된 값}
                req.on('data', (data)=>{
                    body += data;
                });
                return req.on('end', ()=>{
                    // PUT 본문(Body): {"name":"홍길동"}
                    console.log('PUT 본문(Body):', body);
                    // 기존 키 값의 자리에 데이터 변경
                    users[key] = JSON.parse(body).name; 
                    res.writeHead(200, {'Content-Type':'text/plain; charset=utf-8'});
                    return res.end('ok');
                });
            }
        }else if(req.method==='DELETE'){
            if(req.url.startsWith('/user/')){
                const key = req.url.split('/')[2];
                delete users[key];
                res.writeHead(200, {'Content-Type':'text/plain; charset=utf-8'});
                return res.end('ok');
            }
        }
        res.writeHead(404);
        return res.end('NOT FOUND');

    }catch(err){
        console.error(err);
        res.writeHead(500, {'Content-Type':'text/html; charset=utf-8'});
        res.end(err.message);
    }
}).listen(8083, ()=>{
    console.log('8083번 포트에서 서버 대기중입니다.');
});