const path = require('path');
const Sequelize = require('sequelize');

const Member = require('./member');
const Board  = require('./board');
const Reply  = require('./reply');

const env    = process.env.NODE_ENV || 'development';
const config = require(__dirname + '/../config/config.json')[env];
const db     = {};

const sequelize = new Sequelize(config.database, config.username, config.password, config);

db.sequelize = sequelize; // 데이터베이스 연결정보가 저장된 객체
db.Sequelize = Sequelize; // 설치된 Sequelize 객체

db.Member = Member;
db.Board  = Board;
db.Reply  = Reply;

Member.init(sequelize);
Board.init(sequelize);
Reply.init(sequelize);

Member.associate(db);
Board.associate(db);
Reply.associate(db);

module.exports = db;
