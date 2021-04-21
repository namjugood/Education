const Sequelize = require('sequelize');
module.exports = class Board extends Sequelize.Model {
    static init(sequelize){
        return super.init({
            filename : {
                type : Sequelize.STRING(100),
                allowNull : true,
            },
            realfilename : {
                type : Sequelize.STRING(100),
                allowNull : true,
            },
            readCount : {
                type : Sequelize.INTEGER.UNSIGNED,
                defaultValue : 0,
                allowNull : false,
            },            
            subject : {
                type : Sequelize.STRING(100),
                allowNull : false,
            },
            content : {
                type : Sequelize.STRING(1000),
                allowNull : false,
            },
            created_at : {
                type : Sequelize.DATE,
                allowNull:false,
                defaultValue : Sequelize.NOW,
            },
        }, {
            sequelize,
            timestamps : false, 
            underscored: false,
            modelName : 'Board',
            tableName : 'boards',
            paranoid : false,   
            charset : 'utf8mb4',
            collate : 'utf8mb4_general_ci',
        });
    }
    static associate(db) {
        db.Board.belongsTo( db.Member, { foreignKey : 'writer', targetKey : 'userid' } );
        db.Board.hasMany( db.Reply, { foreignKey : 'board_num', sourceKey : 'id', onDelete : 'cascade'} );
    }
};