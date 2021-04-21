const express = require('express');
const User = require('../models/user');
const Comment = require('../models/comment');

const router = express.Router();

router.post('/', async (req, res, next) => {
    try{
        const comment = await Comment.create({
            commenter: req.body.id,
            comment: req.body.comment,
        });
        console.log(comment);
        res.json(comment);
    }catch(err) {
        console.error(err);
        next(err);
    }
});

router.get('/', async (req, res, next) => {
    try{
        const comment = await Comment.findAll();
        res.json(comment);
    }catch(err) {
        console.error(err);
        next(err);
    }
});

router.patch('/:id', async (req, res, next) => {
    try {
        const result = await Comment.update({
            // 전달된 comment로 테이블 내의 comment를 수정
            comment : req.body.comment, 
        }, {
            // 전달된 id(댓글번호)
            where: {id: req.params.id},
        });
        res.json(result);
    } catch (err) {
        console.error(err);
        next(err);
    }
});

router.delete('/:id', async (req, res, next) => {
    try {
        const result = await Comment.destroy({ where: {id: req.params.id}});
        res.json(result);
    } catch (err) {
        console.error(err);
        next(err);
    }
});

module.exports = router;