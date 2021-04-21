const express = require('express');
const Member = require('../models/member');
const Board = require('../models/board');
const Reply = require('../models/reply');

const multer = require('multer');
const fs = require('fs');
const path = require('path');


const router = express.Router();

try {
    fs.readdirSync('public/upload');
} catch (err) {
    console.error("/public/upload폴더 생성");
    fs.mkdirSync('public/upload');
}

const upload = multer({
    storage : multer.diskStorage({
        destination(req, file, done){
            done(null, 'public/upload/');
        },
        filename(req, file, done){
            const realfilename = path.extname(file.originalname);
            done(null, path.basename(file.originalname, realfilename) + Date.now() + realfilename);
        }
    }), limits: {filesSize: 5 * 1024 * 1024},
});

router.get('/', async (req, res, next)=>{
    try {
        const boards = await Board.findAll({
            order : [['created_at', 'DESC']]
        });
        res.json(boards);
    } catch (err) {
        console.error(err);
        next(err);
    }
});


// 글쓰기로 이동
router.get('/writeForm', (req, res)=>{
    try {
        const luser = req.session.loginUser;
        res.render('writeBoard', {luser});
    } catch (err) {
        console.error(err);
        next(err);
    }
})

// 글쓰기
router.post('/write', upload.single("image"), async (req, res, next)=>{
    // console.log('originalname+file: '+req.file.originalname);
    // console.log('filename+file: '+req.file.filename);
    // console.log('originalname+ody: '+req.body.originalname);
    // console.log('filename+body: '+req.body.filename);
    try { 
        if(req.file){
            board = await Board.create({
                subject:req.body.subject,
                content:req.body.content,
                writer:req.body.writer,
                filename:req.file.originalname,
                realfilename:req.file.filename
            });
        }else{
            board = await Board.create({
                subject:req.body.subject,
                content:req.body.content,
                writer:req.body.writer,
                filename:null,
                realfilename:null
            });
        }
        res.json(board);
    } catch (err) {
        console.error(err);
        next(err);
    }
})

// 디테일창
router.get('/boardView/:id', async (req, res, next)=>{
    try {
        // 전체 데이터 읽어오기
        // const result = await Board.findOne({
        //     where:{ id:req.params.id }
        // }); // 1개의 레코드가 있는 테이블
        const result = await Board.findOne({
            attributes : ['readCount'], // select [attribute] from 이곳을 지칭하는 명령어
            where : { id:req.params.id }
        }); // readCount만 추출한 레코드
        let cnt = result.readCount; // result_cnt.readCount
        cnt = cnt+1;
        await Board.update({
            readCount:cnt
        },{
            where:{id:req.params.id}
        });
        
        // let result = await Board.findOne({where:{id:req.params.id}});
        // let count = board.readCount+1;
        // const fin = await Board.update({
        //     readCount:count
        // },{
        //     where:{id:req.params.id}
        // });
        const board = await Board.findOne({where:{id:req.params.id}});
        const luser = req.session.loginUser;
        console.log(board);
        res.render('boardView',{board, luser});
        // render와 json 명령은 같이 쓸 수 없음
    } catch (err) {
        console.error(err);
        next(err);
    }
});

router.get('/UpdateForm/:id', async (req, res, next)=>{
    try {
        const board = await Board.findOne({where:{id:req.params.id}});
        // console.log("2:"+board);
        res.render('UpdateForm',{board});
    } catch (error) {
        console.error(err);
        next(err);
    }
});

router.post('/update', upload.single("image"), async (req, res, next)=>{
    console.log('parameter check : '+req.body.id);
    console.log('image check : '+req.body.image);
    console.log('realfilename : '+req.body.filename);
    console.log('filename : '+req.body.originalname);
    try {
        if(req.file){
            board = await Board.update({
                subject:req.body.subject,
                content:req.body.content,
                writer:req.body.writer,
                filename:req.file.originalname,
                realfilename:req.file.filename,
            },{
                where:{id:req.body.id}
            });
        }else{
            board = await Board.update({
                subject:req.body.subject,
                content:req.body.content,
                writer:req.body.writer,
                filename:null,
                realfilename:null,
            },{
                where:{id:req.body.id}
            });
        }
        res.redirect('/boards/boardView2/'+ req.body.id);
    } catch (err) {
        console.error(err);
        next(err);
    }
})

// 업데이트 이후 count를 늘리지 않게 조회페이지를 복제
router.get('/boardView2/:id', async (req, res, next)=>{
    try {
        const board = await Board.findOne({where:{id:req.params.id}});
        const luser = req.session.loginUser;
        console.log(board);
        res.render('boardView',{board, luser});
        // render와 json 명령은 같이 쓸 수 없음
    } catch (err) {
        console.error(err);
        next(err);
    }
});

router.post('/getReply', async (req, res, next)=>{
    try {
        const board = await Reply.findAll({
            where:{board_num:req.body.boardnum},
            order: [['created_at', 'DESC']]
        });
        res.json(board);
    } catch (err) {
        console.error(err);
        next(err);
    }
});

router.post('/writeReply', async (req, res, next)=>{
    const rep_writer = req.session.loginUser;
    try {
        const result = await Reply.create({
            writer   :  rep_writer.userid,
            board_num:  req.body.boardnum,
            reply    :  req.body.reply
        });
        res.json(result);
    } catch (err) {
        console.error(err);
    }
})

router.delete('/:id', async (req, res, next)=>{
    try {
        const result = await Reply.destroy({
            where:{id: req.params.id}
        });
        res.json(result);
    } catch (err) {
        console.error(err);
    }
})

// 댓글 개수 구현(I)
// router.get('/cntReply/:id', async (req, res, next)=>{
//     // console.log(req.params.id);
//     try {
//         const result = await Reply.findAndCountAll({
//             where : { board_num: req.params.id },
//             attributes : ['reply'], 
//         });
//         const cnt = result.count;
//         res.json(cnt);
//     } catch (err) {
//         console.error(err);
//     }
        
// })

//댓글개수 구현(II)
router.get('/replycnt/:id', async (req, res, next)=>{
    try {
        const result = await Reply.findAll({
            where : { board_num: req.params.id },
        });
        console.log(result.count);
        res.json(result);
    } catch (err) {
        console.error(err);
    }
})


module.exports = router;