Ext.define('js.util.Config', {
	config: {
		devRestUrl: 'http://localhost:8080/rest',
		prodRestUrl: 'http://localhost/rest',
	},
	
	getDevRestUrl: function() {
		return this.devRestUrl;
	},
	
	getProdRestUrl: function() {
		return this.prodRestUrl;
	}
});