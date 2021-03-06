Ext.onReady(function() {
	var restUrl = Ext.create('js.util.Config').getDevRestUrl();
	
	var registrationWindow = Ext.create('Ext.window.Window', {
		title: 'User Registration',
		height: 250, 
		width: 330,
		modal: true,
		resizable: false,
		draggable: true,
		hidden: true,
		closeAction: 'hide',
		items: [{
			xtype: 'form',
			defaultType: 'textfield',
			bodyPadding: 10,
			defaults: {
				allowBlank: false,
				labelAlign: 'left',
				msgTarget: 'side',
				vtype: 'alphanum',
				labelWidth: 120,
				validateOnChange: false,
			},
			items: [{
				fieldLabel: 'First Name',
				id: 'firstName'
			},{
				fieldLabel: 'Last Name',
				id: 'lastName'
			},{
				fieldLabel: 'Email',
				id: 'email',
				vtype: 'email'
			},{
				fieldLabel: 'User Name',
				id: 'userName',
				minLength: 8,
				maxLength: 15
			},{
				fieldLabel: 'Password',
				id: 'password',
                inputType: 'password',
                minLength: 8,
                maxLength: 12
			},{
                fieldLabel: 'Confirm Password',
                id: 'confirmPassword',
                inputType: 'password',
                minLength: 8,
                maxLength: 12,
                vtype: 'passwordCompare',
                initialPassField: 'password'
        	}],
        	buttons:  [{
    			text: 'Reset',
    			handler: function() {
    		        this.up('form').getForm().reset();
    			}
    		},{
    			text: 'Submit',
    			handler: function() {
                    var formObj = this.up('form').getForm();
                    if(formObj.isValid()) {
                        var firstNameVal = Ext.getCmp('firstName').getValue();
                        var lastNameVal = Ext.getCmp('lastName').getValue();
                        var emailVal = Ext.getCmp('email').getValue();
                        var userNameVal = Ext.getCmp('userName').getValue();
                        var passwordVal = Ext.getCmp('password').getValue();
                        Ext.Ajax.request({
                            url: restUrl + '/userservice/createUser',
                            method: 'POST',
                            scope: this,
                            params: {firstName: firstNameVal, lastName: lastNameVal, email: emailVal, 
                            	userName: userNameVal, password: passwordVal},
                            success: function(response, request) {
                                var jsonObj = Ext.decode(response.responseText);
                                var responseCode = jsonObj.responseCode;
                                if(responseCode == '200') {
                                    Ext.Msg.alert('Success', jsonObj.responseMessage);
                                    registrationWindow.hide();
                                } else {
                                    Ext.Msg.alert('Error', jsonObj.responseMessage);
                                }
                            },
                            failure: function(request, response) {
                                Ext.Msg.alert('Error', 'System communication error');
                            }
                        });
                    }
    			}
    		}]
		}]
	});

	var pageView = Ext.create('Ext.container.Viewport', {
		border: false,
		frame: false,
		layout: {
			align: 'center',
			type: 'vbox',
			padding: '200 0 0 0'
		},
		items: [{
			xtype: 'form',
			bodyStyle: 'padding: 10px;',	
			height: 135,
			width: 300,
			title: 'Login',
			defaultType: 'textfield',
			defaults: {
				validateOnChange: false,
				validateOnBlur: false
			},
			items: [{
				fieldLabel: 'Username',
				id: 'j_username'
			},{
				fieldLabel: 'Password',
				inputType: 'password',
				id: 'j_password'
			}],
			buttons: [{
				text: 'Register',
				handler: function() {
					registrationWindow.show();
				}
			},{
				text: 'Login',
				handler: function() {
					var user = Ext.getCmp('j_username').getValue();
					var pass = Ext.getCmp('j_password').getValue();
					Ext.Ajax.request({
						url: restUrl + '/j_spring_security_check',
						method: 'POST',
						scope: this,
						params: {j_username: user, j_password: pass},
						success: function(response, request) {
							var jsonObj = Ext.decode(response.responseText);
							var responseCode = jsonObj.responseCode;
							if(responseCode == '200') {
								window.location = jsonObj.responseMessage;
							} else {
								Ext.Msg.alert('Error', jsonObj.responseMessage);
							}
						},
						failure: function(response, request) {
							Ext.Msg.alert('Error', 'System communication error');
						}
					});
				}
			}]
		}]
	});
	
	Ext.getCmp('userName').on('change', function() {
		 if(this.isValid()) {
			 var userNameVal = this.getValue();
			 
			 Ext.Ajax.request({
	             url: restUrl + '/userservice/validateUserName',
	             method: 'POST',
	             scope: this,
	             params: {userName: userNameVal},
	             success: function(response, request) {
	                 var jsonObj = Ext.decode(response.responseText);
	                 var responseCode = jsonObj.responseCode;
	                 if(responseCode == '409'){
	                    this.markInvalid(jsonObj.responseMessage);
	                 } else if(responseCode == '200') {
	                	 this.clearInvalid();
	                 }
	             },
	             failure: function(request, response) {
	            	 Ext.Msg.alert('Error', 'System communication error');
	             }
	         }); 
		 }
	});
	
	Ext.getCmp('email').on('change', function() {
		if(this.isValid()) {
			var emailVal = this.getValue();
			
			Ext.Ajax.request({
				url: restUrl + '/userservice/validateEmail',
				method: 'POST',
				scope: this,
				params: {email: emailVal},
				success: function(response, request) {
					var jsonObj = Ext.decode(response.responseText);
					var responseCode = jsonObj.responseCode;
					if(responseCode == '409') {
						this.markInvalid(jsonObj.responseMessage);
					} else if(responseCode == '200') {
						this.clearInvalid();
					}
				},
				failure: function(response, request) {
					Ext.Msg.alert('Error', 'System communication error');
				}
			});
		}
	});
	
	
	registrationWindow.on('hide', function() {
	    this.down('form').getForm().reset();
	});
});

