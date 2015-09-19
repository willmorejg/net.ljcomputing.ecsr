'use strict';

define([
        'angular'
        , 'app'
        , 'lodash'
        , 'logToServer'
        ],
        function(angular, app, _, logToServer)
        {
	app.factory('EmailAddress', [function(){

		function EmailAddress(id, uuid, localPart, domain, emailAddress) {
			this.id = id;
			this.uuid = uuid;
			this.localPart = localPart;
			this.domain = domain;
			this.emailAddress = emailAddress;
		}

		EmailAddress.prototype.getId = function() {
			return this.id;
		};

		EmailAddress.prototype.getUuid = function() {
			return this.uuid;
		};

		EmailAddress.prototype.getLocalPart = function() {
			return this.localPart;
		};

		EmailAddress.prototype.getDomain = function() {
			return this.domain;
		};

		EmailAddress.prototype.getEmailAddress = function() {
			return this.emailAddress;
		};

		EmailAddress.build = function (data) {
			return new EmailAddress(
					data.id,
					data.uuid,
					data.localPart,
					data.domain,
					data.emailAddress
			);
		};

		EmailAddress.construct = function (data) {
			var result = null;

			if(_.isArray(data)) {
				result = [];
				_.forEach(data, function(emailAddress){
					result.push(EmailAddress.build(emailAddress));
				});
			} else {
				result = EmailAddress.build(data);
			}

			return result;
		};

		return EmailAddress;
	}]);
});