'use strict';

define([
    'angular'
    , 'app'
    , 'lodash'
    , 'logToServer'
],
function(angular, app, _)
{
	return app.controller('LoginController', ['$scope', '$rootScope', 'AUTH_EVENTS', 'AuthService', function ($scope, $rootScope, AUTH_EVENTS, AuthService){
		$scope.credentials = {
				username: '',
				password: ''
		};
		$scope.login = function (credentials) {
			AuthService.login(credentials).then(function (user) {
				$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
				$scope.setCurrentUser(user);
			}, function () {
				$rootScope.$broadcast(AUTH_EVENTS.loginFailed);
			});
		};
	}]);
});
