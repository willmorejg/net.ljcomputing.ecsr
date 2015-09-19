require.config({
	baseUrl: 'js/',
	urlArgs : "burst=" + (new Date()).getTime(),
	waitSeconds: 20,
	paths : {
		'app-boostrap' : 'app-boostrap',
		logToServer : 'vendor/logToServer',
		lodash : 'vendor/lodash',
		moment : 'vendor/moment',
		jquery : 'vendor/jquery',
		'jquery-ui' : 'vendor/jquery-ui-1.11.4.custom/jquery-ui',
		dragevent:     'vendor/jquery-ui-1.11.4.custom/jquery.event.drag',
		dropevent:     'vendor/jquery-ui-1.11.4.custom/jquery.event.drop',
		mockajax : 'vendor/jquery.mockjax',
		bootstrap : 'vendor/bootstrap',
		angular : 'vendor/angular-1.4.4/angular',
		angularRoute : 'vendor/angular-1.4.4/angular-route',
		angularResource : 'vendor/angular-1.4.4/angular-resource',
		angularCookies : 'vendor/angular-1.4.4/angular-cookies',
		angularSanitize : 'vendor/angular-1.4.4/angular-sanitize',
		'ui.bootstrap' : 'vendor/angular-1.4.4/ui-bootstrap-tpls-0.12.1',
		xeditable : 'vendor/angular-1.4.4/xeditable',
		'smart-table' : 'vendor/angular-1.4.4/smart-table',
		restangular : 'vendor/angular-1.4.4/restangular',
		'ngToast' : 'vendor/angular-1.4.4/ngToast'
	},
	shim : {
		'jquery' : {
			exports : ['jQuery', '$']
		},
		'jquery-ui' : {
			deps : [ 'jquery' ]
		},
		'dragevent' : {
			deps : [ 'jquery' ]
		},
		'dropevent' : {
			deps : [ 'jquery' ]
		},
		'logToServer' : {
			deps : [ 'jquery' ],
			exports : 'JDEV.logging'
		},
		'mockajax' : {
			deps : [ 'jquery' ]
		},
		'bootstrap' : {
			deps : [ 'jquery' ]
		},
		'bootstrap/bootstrap-slider' : {
			deps : [ 'jquery' ],
			exports : '$.fn.slider'
		},
		'bootstrap/bootstrap-affix' : {
			deps : [ 'jquery' ],
			exports : '$.fn.affix'
		},
		'bootstrap/bootstrap-alert' : {
			deps : [ 'jquery' ],
			exports : '$.fn.alert'
		},
		'bootstrap/bootstrap-button' : {
			deps : [ 'jquery' ],
			exports : '$.fn.button'
		},
		'bootstrap/bootstrap-carousel' : {
			deps : [ 'jquery' ],
			exports : '$.fn.carousel'
		},
		'bootstrap/bootstrap-collapse' : {
			deps : [ 'jquery' ],
			exports : '$.fn.collapse'
		},
		'bootstrap/bootstrap-dropdown' : {
			deps : [ 'jquery' ],
			exports : '$.fn.dropdown'
		},
		'bootstrap/bootstrap-modal' : {
			deps : [ 'jquery' ],
			exports : '$.fn.modal'
		},
		'bootstrap/bootstrap-popover' : {
			deps : [ 'jquery' ],
			exports : '$.fn.popover'
		},
		'bootstrap/bootstrap-scrollspy' : {
			deps : [ 'jquery' ],
			exports : '$.fn.scrollspy'
		},
		'bootstrap/bootstrap-tab' : {
			deps : [ 'jquery' ],
			exports : '$.fn.tab'
		},
		'bootstrap/bootstrap-tooltip' : {
			deps : [ 'jquery' ],
			exports : '$.fn.tooltip'
		},
		'bootstrap/bootstrap-transition' : {
			deps : [ 'jquery' ],
			exports : '$.support.transition'
		},
		'bootstrap/bootstrap-typeahead' : {
			deps : [ 'jquery' ],
			exports : '$.fn.typeahead'
		},
		'angular' : {
			deps : [ 'jquery' ],
			exports : 'angular'
		},
		'angularRoute' : {
			deps : [ 'angular' ]
		},
		'angularResource' : {
			deps : [ 'angular' ]
		},
		'angularCookies' : {
			deps : [ 'angular' ]
		},
		'angularSanitize' : {
			deps : [ 'angular' ]
		},
		'ui.bootstrap' : {
			deps : [ 'angular', 'bootstrap' ]
		},
		'xeditable' : {
			deps : [ 'angular' ]
		},
		'smart-table' : {
			deps : [ 'angular' ],
			exports : 'smart-table'
		},
		'restangular' : {
			deps : [ 'angular' ],
			exports : 'Restangular'
		},
		'ngToast' : {
			deps : [ 'angular' ],
			exports : 'ngToast'
		}
	},
	deps: ['app-bootstrap']
});
