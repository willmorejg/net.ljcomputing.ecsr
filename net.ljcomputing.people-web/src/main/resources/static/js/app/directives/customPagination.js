'use strict';

define([
        'angular'
        , 'app'
        , 'lodash'
        , 'logToServer'
        ],
        function(angular, app, _)
        {
	app.directive('customPagination', [
	    '$compile',
	    function($compile) {
	
			'use strict';
	
			return {
				restrict: 'E',
				template: '<input type="text" class="select-page" ng-model="inputPage" ng-change="selectPage(inputPage)">',
				link: function(scope, element, attrs) {
					scope.$watch('currentPage', function(c) {
						scope.inputPage = c;
					});
				}
			}
	    }
	])
});