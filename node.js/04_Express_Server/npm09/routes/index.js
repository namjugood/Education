const express = require('express');
const Member  = require('../models/member');
const Board   = require('../models/board');
const Reply = require('../models/reply');

const router = express.Router();

router.get('/', (req, res, next)=>{
    try {
        res.render('index', { });
        // res.sendFile( path.join(__dirname, '../views/login.html'));
        // sendFile은 절대경로를 나타냄
    } catch (err) {
        console.error(err);
        next(err);
    }
});

router.get('/main', (req, res, next)=>{
    const luser = req.session.loginUser
    try {
        res.render('main', {luser});
    } catch (err) {
        console.error(err);
        next(err);
    }
});

router.get('/join', (req, res, next)=>{
    try {
        res.render('join', { });
    } catch (err) {
        console.error(err);
        next(err);
    }
});


module.exports = router;