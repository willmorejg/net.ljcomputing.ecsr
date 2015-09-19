'use strict';

define([
        'angular'
        , 'app'
        , 'lodash'
        , 'logToServer'
        ],
        function(angular, app, _, logToServer)
        {
	app.factory('EmailAddressPreferences', 
			['Person', 'EmailAddress', 'CONTACT_TYPE', 'CONTACT_ORDER', 
			 function(Person, EmailAddress, CONTACT_TYPE, CONTACT_ORDER){

				function EmailAddressPreferences(id, uuid, contactType, contactOrder, person, emailAddress) {
					this.id = id;
					this.uuid = uuid;
					this.contactType = contactType;
					this.contactOrder = contactOrder;
					this.person = person;
					this.emailAddress = emailAddress;
				}

				EmailAddressPreferences.prototype.getId = function() {
					return this.Id;
				};

				EmailAddressPreferences.prototype.getUuid = function() {
					return this.uuid;
				};

				EmailAddressPreferences.prototype.getContactType = function() {
					return this.contactType;
				};

				EmailAddressPreferences.prototype.getContactOrder = function() {
					return this.contactOrder;
				};

				EmailAddressPreferences.prototype.getPerson = function() {
					return this.person;
				};

				EmailAddressPreferences.prototype.getEmailAddress = function() {
					return this.emailAddress;
				};

				EmailAddressPreferences.build = function (data) {
					return new EmailAddressPreferences(
							data.id,
							data.uuid,
							data.contactType,
							data.contactOrder,
							data.person = Person.build(data.person),
							data.emailAddress = EmailAddress.build(data.emailAddress)
					);
				};

				EmailAddressPreferences.construct = function (data) {
					var result = null;

					if(_.isArray(data)) {
						result = [];
						_.forEach(data, function(emailAddressPreference){
							result.push(EmailAddressPreferences.build(emailAddressPreference));
						});
					} else {
						result = EmailAddressPreferences.build(data);
					}

					return result;
				};

				return EmailAddressPreferences;
			}]);
});