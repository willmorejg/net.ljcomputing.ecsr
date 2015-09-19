'use strict';

define([
	'angular'
	, 'app'
	, 'lodash'
	, 'logToServer'
],
function(angular, app, _, logToServer)
{
	app.factory('Validation', [function() {
		var emailRegEx = /^(\w+)@([a-zA-Z_]+?\.[a-zA-Z]{2,3})$/;
		var phoneRegEx = /^(\(\d{3}\))\s(\d{3})-(\d{4})$|^(\(\d{3}\))\s(\d{3})-(\d{4})\sextension\s(\d+)$/;
		var phoneFilterRegEx = /^[\(]?(\d{3})[\)]?\s+(\d{3})-(\d{4})$|^[\(]?(\d{3})[\)]?\s+(\d{3})-(\d{4})\s+ext[ension]?\s+(\d+)$/;
		
		// zip code - (\d{5})$|(^\d{5}-\d{4})
		
		
		var validateEmail = function(email) {
			var result = {
				valid: false,
				recipient: '',
				domain: ''
			};
			
			var matches = email.match(emailRegEx);
			
			if(matches) {
				result.valid = true;
				result.recipient = matches[1];
				result.domain = matches[2];
			}
			
			return result;
		};
/*
    private String areaCode;
    private String extension = "";
    private String number;
    private String phoneNumber;
    private String prefix;
 */
/**/
		var validatePhone = function(phone) {
			var result = {
				valid: false,
				areaCode: '',
				prefix: '',
				number: '',
				extension: ''
			};
			
			var matches = phone.match(phoneFilterRegEx);
			
			if(matches) {
				result.valid = true;
				if(matches[4]) {
					result.areaCode = matches[4];
					result.prefix = matches[5];
					result.number = matches[6];
					result.extension = matches[7];
				} else {
					result.areaCode = matches[1];
					result.prefix = matches[2];
					result.number = matches[3];
				}
			}
			
			return result;
		};
/**/
		return {
			validateEmail: function(email) {
				return validateEmail(email);
			},
			validatePhone: function(phone) {
				return validatePhone(phone);
			}
		};
	}]);
});
