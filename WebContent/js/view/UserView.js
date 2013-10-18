Ext.onReady(function() {
	var restUrl = Ext.create('js.util.Config').getDevRestUrl();
	
	var userViewStore = Ext.create('Ext.data.Store', {
	    model: 'js.model.UserData',
	    proxy: {
	        type: 'rest',
	        actionMethods:{ create:'POST', read:'POST', update:'POST', destroy:'POST' },
	        url: restUrl + '/userservice/getUserInfo',
	        reader: {
	            type: 'json',
	            root: 'user'
	        },
	        extraParams: {
	        	userId: 12345678
	        },
	    },
	    //autoLoad: true
	});
	
	userViewStore.load();
	
	var panel = Ext.create('Ext.grid.Panel', {
	    title: 'User',
	    store: userViewStore,
	    columns: [
	        { header: 'User Name', dataIndex: 'userName' },
	        { header: 'First Name',  dataIndex: 'firstName' },
	        { header: 'Last Name', dataIndex: 'lastName'},
	        { header: 'Email', dataIndex: 'email', flex: 1 }
	    ],
	    width: 500,
	    renderTo: Ext.getBody()
	});	
	
	//var test = userViewStore.first().get('email');
});