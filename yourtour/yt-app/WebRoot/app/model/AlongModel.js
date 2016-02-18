Ext.define('YourTour.model.AlongModel', {
    extend: 'YourTour.model.BaseModel',
    config: {
        fields: [
            {name: 'id', type: 'string'},
            {name: 'title', type: 'string'},
            {name: 'imageUrl', type: 'string'},
            {name: 'createTime', type: 'string'},
            {name: 'intentionCode', type: 'string'},
            {name: 'intentionName', type: 'string'},
            {name: 'memo', type: 'string'},
            {name: 'address', type: 'string'},
            {name: 'readNum', type: 'string', defaultValue:'0'},
            {name: 'followedNum', type: 'string', defaultValue:'0'},
            {name: 'commentNum', type: 'string', defaultValue:'0'},
            {name: 'deadLine', type: 'long'},
            {name: 'num', type: 'string'}
        ],

        associations: [
            {
                type: 'hasMany',
                model: 'YourTour.model.CommentModel',
                name: 'comments',
                associationKey: 'comments'
            },

            {
                type: 'hasMany',
                model: 'YourTour.model.UserModel',
                name: 'user',
                associationKey: 'user'
            },

            {
                type: 'hasMany',
                model: 'YourTour.model.RouteModel',
                name: 'route',
                associationKey: 'route'
            }
        ]
    }
});
