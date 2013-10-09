Ext.apply(Ext.form.field.VTypes, {
   	passwordCompare: function(val, field) {
	    var passMatch = true;
	    if(field.initialPassField) {
            var initialPass = Ext.getCmp(field.initialPassField);
            passMatch = (val === initialPass.getValue());
	    }
	
	    return passMatch;
    },

    passwordCompareText: 'Passwords do not match'
});