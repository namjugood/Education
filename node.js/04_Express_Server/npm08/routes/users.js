const express = require('express');
const User = require('../models/user');
const Comment = require('../models/comment');

const router = express.Router();

router.post('/', async (req, res, next)=>{
    try {
        // 레코드 추가 함수 : create
        const user = await User.create({
            // 필드명과 전달 인수들을 연결
            name: req.body.name,
            age: req.body.age,
            married: req.body.married
        });
        console.log(user);
        res.json(user);
    } catch (err) {
        console.error(err);
        next(err); // 에러 루틴이 있는 라우터로 이동
    }
});

router.get('/', async (req, res, next)=>{
    try {
        // User 객체를 통해 users 테이블의 모든 데이터 조회
        const users = await User.findAll();
        // 결과를 json 형식으로 리턴해줍니다.
        res.json(users);
    } catch (err) {
        console.error(err);
        next(err); // 에러 루틴이 있는 라우터로 이동
    }
});

router.get('/:id/comments', async (req, res, next)=>{
    try {
        const comments = await Comment.findAll({
            // 검색 where의 표현
            include: {
                // User모델의 id필드로 검색
                // -> Comment의 commenter 내 검색
                model: User,
                where: { id : req.params.id },
            },
        });
        console.log(comments);
        res.json(comments);
    } catch (err) {
        console.error(err);
        next(err); // 에러 루틴이 있는 라우터로 이동
    }
});

module.exports = router;