Ext.onReady(function() {
	var restUrl = Ext.create('js.util.Config').getProdRestUrl();
	var paramObj = Ext.Object.fromQueryString(document.location.href.split('?')[1]);
	var userId = paramObj.uid;
	var activationId = paramObj.aid;
	
	if(userId.length > 0 && activationId.length > 0) {
		Ext.Ajax.request({
			url: restUrl + '/userservice/activateUser',
			method: 'POST',
			scope: this,
			params: {userId: userId, activationId: activationId},
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
		Ext.Msg.alert('Error', 'TODO: invalid parameter list. send to error page.');
	}
});