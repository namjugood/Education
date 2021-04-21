const express = require('express');
const Member  = require('../models/member');
const Board   = require('../models/board');
const Reply = require('../models/reply');

const router = express.Router();

router.get('/', (req, res, next) => {
    try {
        res.render('join', {});
    } catch (err) {
        console.error(err);
        next(err);
    }
});

router.post('/addmember', async (req, res, next)=>{
    try {
        const member = await Member.create({
            userid: req.body.id, 
            pwd   : req.body.pw, 
            name  : req.body.name, 
            phone : req.body.phone, 
            email : req.body.email
        });
        console.log(member);
        res.json(member);
    } catch (err) {
        console.error(err);
        next(err);
    }
});

router.post('/login', async (req, res, next)=>{
    try {
        const member = await Member.findOne({ 
            where: { userid : req.body.userid},
        });
        // 결과가 있으면 세션에 저장
        if(member){
            if(member.userid == req.body.userid && member.pwd == req.body.pwd){
                req.session.loginUser = member;
                res.json(member);
            }else{ // 비밀번호가 틀리면 검색결과만 전송
                console.log(member);
                res.json(member);
            }
        }else{ // 결과가 없으면 빈 문자 전송 
            res.json('');
        } 
    } catch (err) {
        console.error(err);
        next(err);
    }
});

router.get('/memberUpdateForm/:userid', async (req, res, next)=>{
    try {
        const member = await Member.findOne({
            where:{userid:req.params.userid}
        });
        console.log(member);
        res.render('memberUpdateForm', {member});
    } catch (err) {
        console.error(err);
        next(err);
    }
});

router.post('/memberUpdate', async (req, res, next)=>{
    try {
        const result = await Member.update({ // 수정 성공 실패 여부만 들어감(1 또는 0)
            userid:req.body.id,
            pwd:req.body.pw,
            name:req.body.name,
            phone:req.body.phone,
            email:req.body.email
        },{
            where:{userid:req.body.id}
        });
        console.log("result :"+result);
        // 업데이트 이후 다시 조회, 세션에 저장
        const member = await Member.findOne({
            where: {userid:req.body.id}
        });
        console.log(member);
        req.session.loginUser = member;
        res.json(member);
    } catch (err) {
        console.error(err);
        next(err);
    }
});

// 로그아웃
router.get('/logout', async (req, res, next)=>{
    try {
        req.session.destroy(function(){
            // 세션쿠키까지 지움
            res.clearCookie( 'session-cookie' );
            res.redirect('/');
        });
    } catch (err) {
        console.error(err);
        next(err);
    }
});


module.exports = router;
