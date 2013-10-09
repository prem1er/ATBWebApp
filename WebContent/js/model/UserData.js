Ext.define('js.model.UserData', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'userName',  type: 'string'},
        {name: 'email',   type: 'string'},
        {name: 'firstName', type: 'string'},
        {name: 'lastName', type: 'string'}
    ]
});