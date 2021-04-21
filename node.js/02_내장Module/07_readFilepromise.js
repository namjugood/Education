// 파일 입출력을 위한 모듈의 promises를 포함하여 로딩
const fs = require('fs').promises;

// 파일의 읽기도 에러처리를 위한 함수 없이 실행됨
// promise로 처리하므로 .then & .catch로 처리
fs.readFile('./readme.txt')
    .then((data)=>{
        console.log(data);
        console.log(data.toString());
    })
    .catch((err)=>{
        console.log(err);
    });