'use strict';

define([
    'angular'
    , 'app'
    , 'lodash'
    , 'logToServer'
],
function(angular, app, _)
{
	return app.factory('AuthService', ['$http', 'AUTH_EVENTS', 'Session', 'Base64', function ($http, AUTH_EVENTS, Session, Base64){
		  var authService = {};
		  
		  authService.login = function(credentials) {
			var stuff = Base64.encodeInput(credentials.username + ':' + credentials.password);
			console.debug('stuff', stuff);
		    return $http
		      .get('/index.htm', ({data: credentials, withCredentials: true, headers: {'Authorization': 'Basic ' + stuff}}))
		      .then(function (res) {
		    	  console.debug('res', res);
		        Session.create(res.data.id, res.data.user.id,
		                       res.data.user.role);
		        return res.data.user;
		      });
		  };
		 
		  authService.isAuthenticated = function (){
		    return !!Session.userId;
		  };
		 
		  authService.isAuthorized = function(authorizedRoles) {
		    if (!angular.isArray(authorizedRoles)) {
		      authorizedRoles = [authorizedRoles];
		    }
		    return (authService.isAuthenticated() &&
		      authorizedRoles.indexOf(Session.userRole) !== -1);
		  };
		 
		  return authService;
	}]);
});
