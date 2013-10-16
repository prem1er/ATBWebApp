Ext.onReady(function() {
	var restUrl = Ext.create('js.util.Config').getProdRestURL();
	var userId = Ext.Object.fromQueryString(document.location.href.split('?')[0]);
	
	if(userId.length > 0) {
		Ext.ajax.request({
			url: restUrl + '/userservice/activateUser',
			method: 'POST',
			scope: this,
			params: {userId: userId},
			success: function(response, request) {
				var jsonObj = Ext.decode(response.responseText);
				var responseCode = jsonObj.responseCode;
				if(responseCode == '200') {
					window.location = jsonObj.responseMessage;
				} else {
					Ext.Msg.alert('Error', jsonObj.resonseMessage);
				}
			},
			failure: function(response, request) {
				Ext.Msg.alert('Error', 'System communication error');
			}
		});
	} {
		Ext.Msg.alert('Error', 'No User ID found');
	}
});