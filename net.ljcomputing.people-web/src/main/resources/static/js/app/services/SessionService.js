'use strict';

define([
    'angular'
    , 'app'
    , 'lodash'
    , 'logToServer'
],
function(angular, app, _)
{
	return app.service('Session', function (){
		this.create = function (sessionId, userId, userRole) {
			this.id = sessionId;
			this.userId = userId;
			this.userRole = userRole;
		};
		this.destroy = function () {
			this.id = null;
			this.userId = null;
			this.userRole = null;
		};
	});
});
