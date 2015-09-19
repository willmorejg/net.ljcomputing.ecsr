'use strict';

define([
	'angular'
	, 'app'
	, 'lodash'
	, 'logToServer'
],
function(angular, app, _, logToServer)
{
	app.service('Toast', ['ngToast', function(ngToast) {
		
		var createToast = function(params) {
			ngToast.create({
				className: params.type,
				content: params.msg,
				dismissOnTimeout: true,
				timeout: params.timeout
			});
		};

		return {
			create : function(params) {
				return createToast(params);
			}
		};
	}]);
});
