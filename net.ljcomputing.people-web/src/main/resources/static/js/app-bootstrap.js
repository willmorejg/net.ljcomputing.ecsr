require([
  'angular'
  , 'app'
  /* providers */
  /* services */
  , 'app/services/ValidationServices'
  , 'app/services/ToastService'
  , 'app/services/SessionService'
  , 'app/services/PeopleServices'
  /* factories */
  , 'app/factories/Base64'
  , 'app/factories/AuthFactory'
  , 'app/factories/EmailAddressFactory'
  , 'app/factories/PersonFactory'
  , 'app/factories/EmailAddressPreferencesFactory'
  , 'app/factories/PersonalContactPreferencesFactory'
  /* controllers */
  , 'app/controllers/LoginController'
  , 'app/controllers/PeopleController'
  /* directives */
  , 'app/directives/customPagination'
  , 'app/directives/customClearFilter'
  , 'app/directives/customRefresh'
],
function(
  angular
  , app
){
	'use strict';

	angular.bootstrap(document, [ 'app' ]);
	$('[data-toggle="tooltip"]').tooltip();
});
