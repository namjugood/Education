const Sequelize = require('sequelize');
module.exports = class Member extends Sequelize.Model {
    static init(sequelize) {
        return super.init({
            userid : {
                type : Sequelize.STRING(20),
                allowNull : false,
                unique : true,
                primaryKey : true,
            },
            pwd : {
                type : Sequelize.STRING(20),
                allowNull : false,
            },
            name : {
                type : Sequelize.STRING(20),
                allowNull : false,
            },
            phone : {
                type : Sequelize.STRING(20),
                allowNull : false,
            },
            email : {
                type : Sequelize.STRING(50),
                allowNull : false,
            },
            created_at : {
                type : Sequelize.DATE,
                allowNull : false,
                defaultValue : Sequelize.NOW,
            },
        },{
            sequelize,
            timestamps : false, 
            underscored: false,
            modelName : 'Member',
            tableName : 'members',
            paranoid : false, 
            charset : 'utf8mb4',
            collate : 'utf8mb4_general_ci',
        });
    }
    static associate(db){
        db.Member.hasMany( db.Board, { foreignKey : 'writer', sourceKey : 'userid', onDelete : 'cascade' });
    }   // hasMany : 하나의 userid로 다수의 board를 가질 수 있다. (일대다 관계)
};