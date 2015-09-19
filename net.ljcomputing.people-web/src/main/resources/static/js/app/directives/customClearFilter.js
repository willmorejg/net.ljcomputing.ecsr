'use strict';

define([
        'angular'
        , 'app'
        , 'lodash'
        , 'logToServer'
        ],
        function(angular, app, _)
        {
	app.directive('stResetSearch', [
   '$compile',
   function($compile) {

	   'use strict';

	   return {
		   restrict: 'EA',
		   require: '^stTable',
		   link: function(scope, element, attrs, ctrl) {
			   return element.bind('click', function() {
				   return scope.$apply(function() {
					   var tableState = ctrl.tableState();
					   if(_.isEmpty(tableState.search.predicateObject)) { return; }
					   tableState.search.predicateObject = {};
					   tableState.pagination.start = 0;
					   return ctrl.pipe();
				   });
			   });
		   }
	   };
   	}
   ])
});