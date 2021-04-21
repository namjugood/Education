const Sequelize = require('sequelize');
module.exports = class Reply extends Sequelize.Model {
    static init(sequelize){
        return super.init({
            // board_num : {
            //     type : Sequelize.INTEGER.UNSIGNED,
            //     default : 0,
            //     allowNull : false,
            // },            
            reply : {
                type : Sequelize.STRING(100),
                allowNull : false,
            },
            writer : {
                type : Sequelize.STRING(100),
                allowNull : false,
            },
            created_at : {
                type : Sequelize.DATE,
                defaultValue: Sequelize.NOW,
                allowNull : false
            },
        }, {
            sequelize,
            timestamps : false, 
            modelName : 'Reply',
            tableName : 'reply',
            paranoid : false,   
            charset : 'utf8mb4',
            collate : 'utf8mb4_general_ci',
        });
    }
    static associate(db) {
        db.Reply.belongsTo( db.Board, { foreignKey : 'board_num', targetKey : 'id' } );
    }
};