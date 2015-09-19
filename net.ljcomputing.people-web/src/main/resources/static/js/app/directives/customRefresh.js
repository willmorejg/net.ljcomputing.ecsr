'use strict';

define([
        'angular'
        , 'app'
        , 'lodash'
        , 'logToServer'
        ],
        function(angular, app, _)
        {
	app.directive('stCustomRefresh', [
   '$compile',
   function($compile) {

	   'use strict';

	   return {
		   restrict: 'EA',
		   require: '^stTable',
		   link: function(scope, element, attrs, ctrl) {
			   var table = ctrl.tableState();
               element.bind('click', function(ev) {
                   scope.callback.call(function() {
                           ctrl.pipe();
                   });
               });

		   },
           scope: {
               callback: "&"
           }
	   };
   	}
   ])
});