const fs = require('fs').promises;

fs.writeFile('./writeme.txt', '글이 입력됩니다')
.then(()=>{
    return fs.readFile('./writeme.txt');
    // return new Promise(()=>{ fs.readFile('./writeme.txt'); });
    // 위 내용과 달리 readFile함수가 Promise를 내장하고 있어서
    // return fs.readFile('./writeme.txt'); 로도 resolve가 호출됩니다.
})
.then((data)=>{
    console.log(data.toString());
})
.catch((err)=>{
    console.error(err);
})