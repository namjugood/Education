// 파일 읽기, 쓰기를 위한 모듈
const fs = require('fs');

// 앞선 내용에서 파일읽기 위한 이름 설정 방법 참조
fs.readFile('./readme.txt', (err, data)=>{
    // err : 파일읽기에 실패했을 때 전달되는 값을 받는 매개변수
    // data : 파일읽기에 성공했을 때 읽어온 파일 내용(데이터)
    if(err){
        throw err;
    }
    console.log(data); // 16진수로 출력
    console.log(data.toString()); // 문자열로 변환
}); 