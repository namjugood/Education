// 라우터 공유를 위한 패키지
const express = require('express');
// 현재 js 파일 내에 있는 라우터들을 담을 객체를 생성하여 router 변수에 저장
const router = express.Router();

// router 변수에 라우터를 하나씩 설정
// get-'/' 라우터
router.get('/', (req, res)=>{
    res.send('Hello Express');
});

module.exports = router;