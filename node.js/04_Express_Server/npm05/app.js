const express = require('express');
const multer = require('multer');
const fs = require('fs');
const path = require('path');

const app = express();
app.set('port', process.env.PORT || 3000);

app.use(express.json());
app.use(express.urlencoded({extended:false}));

// upload 폴더를 확인하는 명령, 지정위치에 해당 폴더가 없다면
// 에러발생 및 에러처리로 폴더를 새로 생성합니다
try {
    fs.readdirSync('uploads');
} catch (error) {
    console.error('upload 폴더가 없어 upload폴더를 생성합니다');
    fs.mkdirSync('uploads');
}

// multer 객체를 생성하고 필요한 옵션을 설정합니다.
const upload = multer({
    // 저장매체 및 파일 저장경로 등의 옵션 설정
    storage : multer.diskStorage({
        destination(req, file, done){
            done(null, 'uploads/'); // 폴더 설정
            // 첫 번째 인수 null은 현재 파일(file)의 경로와 이름 그대로 사용
            // (변경 및 추가옵션 기입이 null, - 변경내용 없음)
        },
        filename(req, file, done){
            const ext = path.extname(file.originalname);  // 확장자 추출
            // 파일이름 + 오늘날짜(밀리초) + 추출된 확장자로 저장 파일명 변경
            done(null, path.basename(file.originalname, ext) + Date.now() + ext);
            // abc.jpg -> abc123455612.jpg
            // 업로드 파일명이 같은 경우, 처리할 객체가 없이 위와같은 방법으로
            // 파일명의 충돌을 방지합니다(오늘 날짜 시각의 밀리초 값)
        }
    }),
    limits:{fileSize:5 * 1024 * 1024}, // 업로드 파일 사이즈의 제한
});

app.get('/', (req, res)=>{
    res.sendFile(path.join(__dirname, 'multipart.html'));
});
app.post('/upload', upload.single('image'), (req, res)=>{
    console.log(req.file);
    res.send('ok');
});
// 'image' 업로드에서 전송된 폼데이터 중, 파일 선택 태그의 이름과 일치해야 합니다.
// <input type="file" name="image" />
//app.get();

app.use((err, req, res, next)=>{
    console.error(err);
    res.status(404).send(err.message);
});

app.listen(app.get('port'), ()=>{
    console.log(app.get('port'), '번 포트에서 대기 중');
});