'use strict';

define([
        'angular'
        , 'angularRoute'
        , 'angularResource'
        , 'angularCookies'
        , 'angularSanitize'
        , 'ui.bootstrap'
        , 'ngToast'
        , 'xeditable'
        , 'smart-table'
        , 'restangular'
        , 'logToServer'
        ],
        function(
        		angular
        		, angularRoute
        		, angularResource
        		, angularCookies
        		, angularSanitize
        		, uiBootstrap
        		, ngToast
        		, xeditable
        		, smartTable
        		, Restangular
        		, logToServer
        )
        {
	/*
	 * The routes for the app. 
	 */
	var routes = {
			defaultRoutePath: '/',
			routes: {
				'/people/': {
					templateUrl: '/views/people.htm',
					controller: 'PeopleController'
				}
			}
	};

	var app = angular.module('app', [
	                                 'ngRoute'
	                                 , 'ngResource'
	                                 , 'ngCookies'
	                                 , 'ngSanitize'
	                                 , 'ui.bootstrap'
	                                 , 'ngToast'
	                                 , 'xeditable'
	                                 , 'smart-table'
	                                 ]
	);

	app.constant('ALERT', {
		'TIMEOUT' : 2500,
		'SUCCESS' : 'success',
		'DANGER' : 'danger',
		'RECORDS_LOADED' : 'Records loaded.',
		'RECORD_UPDATED' : ' Record updated.',
		'RECORD_DELETED' : 'Record deleted.'
	});

	app.constant('AUTH_EVENTS', {
		loginSuccess: 'auth-login-success',
		loginFailed: 'auth-login-failed',
		logoutSuccess: 'auth-logout-success',
		sessionTimeout: 'auth-session-timeout',
		notAuthenticated: 'auth-not-authenticated',
		notAuthorized: 'auth-not-authorized'
	});

	app.constant('USER_ROLES', {
		all: '*',
		admin: 'ROLE_ADMIN',
		user: 'ROLE_USER'
	});

	app.constant('CONTACT_TYPE', {
		'BUSINESS' : {id : 1, value : 'Business'},
		'PERSONAL' : {id : 2, value : 'Personal'},
		'UNKNOWN' : {id : 99, value : 'Unknown'}
	});

	app.constant('CONTACT_ORDER', {
		'PRIMARY' : {id : 1, value : 'Primary'},
		'SECONDARY' : {id : 2, value : 'Secondary'},
		'TERTIARY' : {id : 3, value : 'Tertiary'},
		'UNKNOWN' : {id : 1, value : 'Unknown'}
	});

	app.config(
			[
			 '$routeProvider',
			 '$locationProvider',
			 '$controllerProvider',
			 '$compileProvider',
			 '$filterProvider',
			 '$provide',

			 function($routeProvider, $locationProvider, $controllerProvider, $compileProvider, $filterProvider, $provide)
			 {
				 app.controller = $controllerProvider.register;
				 app.directive  = $compileProvider.directive;
				 app.filter     = $filterProvider.register;
				 app.factory    = $provide.factory;
				 app.service    = $provide.service;

				 $locationProvider.html5Mode({
					 enabled: false,
					 requireBase: false
				 });

				 if(routes.routes !== undefined)
				 {
					 angular.forEach(routes.routes, function(route, path)
							 {
						 if(route.controller) {
							 $routeProvider.when(path, {templateUrl:route.templateUrl, controller: route.controller});
						 } else {
							 $routeProvider.when(path, {templateUrl:route.templateUrl});
						 }
							 });
				 }

				 if(routes.defaultRoutePaths !== undefined)
				 {
					 $routeProvider.otherwise({redirectTo:config.defaultRoutePaths});
				 }

				 $provide.decorator('$exceptionHandler', ['$delegate', function($delegate) {
					 return function(exception, cause) {
						 $delegate(exception,cause);
						 JDEV.logging.logToServer('An exception occured: ' + JSON.stringify(exception.message) + '; ' + JSON.stringify(exception.stack));
					 };
				 }]);

			 }
			 ]);

	app.config(['ngToastProvider', function(ngToast) {
		ngToast.configure({
			verticalPosition: 'top',
			horizontalPosition: 'center',
			maxNumber: 5
		});
	}]);

	app.controller('ApplicationController', function ($scope,
			USER_ROLES,
			AuthService) {
		$scope.currentUser = null;
		$scope.userRoles = USER_ROLES;
		$scope.isAuthorized = AuthService.isAuthorized;

		$scope.setCurrentUser = function (user) {
			$scope.currentUser = user;
		};
	});

	app.run(function(editableOptions, editableThemes){
		editableOptions.icon_set = 'font-awesome';
		editableThemes.bs3.inputClass = 'input-sm';
		editableThemes.bs3.buttonsClass = 'btn-sm';
		editableOptions.theme = 'bs3';
	});
	
	app.run(['$templateCache', function ($templateCache) {
		 $templateCache.put('template/smart-table/pagination.html',
				 '<nav ng-if="numPages && pages.length >= 2"><ul class="pagination">' +
				 '<li ng-class=""><a ng-click="selectPage(1)">First</a></li>' +
				 '<li ng-class=""><a ng-click="selectPage(currentPage-1)"><i class="fa fa-fw fa-angle-double-left"></i></a></li>' +
				 '<li ng-repeat="page in pages" ng-class="{active: page==currentPage}"><a ng-click="selectPage(page)">{{page}}</a></li>' +
				 '<li ng-class=""><a ng-click="selectPage(currentPage+1)"><i class="fa fa-fw fa-angle-double-right"></i></a></li>' +
				 '<li ng-class=""><a ng-click="selectPage(numPages)">Last</a></li>' +
				 '</ul></nav>');
				}]);

	return app;
});
