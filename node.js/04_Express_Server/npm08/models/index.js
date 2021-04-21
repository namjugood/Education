const Sequelize = require('sequelize');

// export된 객체들 require
const User = require('./user');
const Comment = require('./comment');

const env = process.env.NODE_ENV || 'development';
const config = require(__dirname + '/../config/config.json')[env];
const db = {};

const sequelize = new Sequelize(config.database, config.username, config.password, config); 

db.sequelize = sequelize; // node.js에 접속하기 위한 접속객체
db.Sequelize = Sequelize; // 순수 Sequelize 객체를 db에 담습니다

// User 객체와 Comment 객체도 db에 담습니다.
db.User = User;
db.Comment = Comment;
// 객체를 초기화하는 함수와, 관계 형성 함수도 실행합니다
User.init(sequelize);
Comment.init(sequelize);
User.associate(db);
Comment.associate(db);
// 여기까지의 코드가 테이블이 생성되는 내용으로 구성됩니다.
// db가 exports되어 app.js에 require 되면, 
// require 된 db에서 sequelize를 꺼내어 sync 함수를 실행하게 되고
// 이때 테이블도 생성합니다.
module.exports = db;
