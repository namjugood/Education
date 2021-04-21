const express = require('express');
const User = require('../models/user');
const Comment = require('../models/comment');

const router = express.Router();

router.get('/', (req, res, next)=>{
    try {
        res.render('sequelize', { });
    } catch (err) {
        console.error(err);
        next(err);
    }
});

module.exports = router;