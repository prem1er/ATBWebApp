Ext.define('js.util.Config', {
	config: {
		devRestUrl: 'http://localhost:8080/rest',
		prodRestUrl: 'http://afterthbeep.us/rest',
	},
	
	getDevRestUrl: function() {
		return this.devRestUrl;
	},
	
	getProdRestUrl: function() {
		return this.prodRestUrl;
	},
	
	getServerHost: function() {
		
	}
});