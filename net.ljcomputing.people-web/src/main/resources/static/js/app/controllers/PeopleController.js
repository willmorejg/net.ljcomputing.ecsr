'use strict';

define([
        'angular'
        , 'app'
        , 'lodash'
        , 'logToServer'
        ],
        function(angular, app, _)
        {
	return app.controller('PeopleController', 
			['$scope', '$log', '$http', '$q', '$resource', '$filter', 'PersonService', 'PersonalContactPreferences', 'Person', 'Toast',
			 function($scope, $log, $http, $q, $resource, $filter, PersonService, PersonalContactPreferences, Person, Toast){
				$scope.addAlert = function(alert) {
					if(alert) {
						if(!$scope.alerts) $scope.alerts = [];
						if(_.findIndex($scope.alerts, alert) == -1) $scope.alerts.push(alert);
					}
				}

				$scope.closeAlert = function(index) {
					$scope.alerts.splice(index, 1);
				};
				
				$scope.pipeFunction = function(tableState, ctrl)
				{
					if(!$scope.stCtrl && ctrl)
					{
						$scope.stCtrl = ctrl;
					}

					if(!tableState && $scope.stCtrl) {
						$scope.stCtrl.pipe();
						return;
					}

					$scope.loadTable(tableState);
				}

				$scope.loadTable = function(tableState) {
					$scope.isLoading = true;
					$scope.showClearFilter = !_.isEmpty(tableState.search.predicateObject);

					PersonService
					.getPage(tableState)
					.then(
							function(result){
								console.debug('result: ', result);
								var list = PersonalContactPreferences.construct(result.people);
								$scope.rawPeople = list;
								$scope.people = [].concat($scope.rawPeople);
								tableState.pagination.numberOfPages = result.totalPages;
								tableState.pagination.itemsByPage = result.size;
								
								Toast.create(result.alert);
								$scope.isLoading = result.loading;
							}
							,function(err){
								Toast.create(err.alert);
								$scope.isLoading = false;						
							}
					);
				};

				$scope.savePerson = function(person) {
					$scope.isLoading = true;
					
					var req = Person.build(person);

					PersonService
					.save(person.uuid, req)
					.then(
							function(result){
								Toast.create(result.alert);
								$scope.isLoading = result.loading;
							}
							,function(err){
								Toast.create(err.alert);
							}
					);
				};

				$scope.deletePerson = function(person) {
					$scope.isLoading = true;
					PersonService
					.del(person.uuid)
					.then(
							function(result){
								Toast.create(result.alert);
								$scope.isLoading = result.loading;
								$scope.pipeFunction(null, null);
							}
							,function(err){
								Toast.create(err.alert);
								$scope.isLoading = false;						
							}
					);
				};

				$scope.addPersonModal = function() {
					PersonService.addPerson();
				};

				$scope.contactsModal = function(entity) {
					console.debug('entity: ', entity);
					PersonService.contactsModal(entity, null);
				};
			}])
			.controller('AddPersonController', function ($scope, $http, $q, $filter, PersonService, Toast, $modalInstance) {
				$scope.person = {
						firstName: '',
						middleName: '',
						lastName: ''
				};

				$scope.savePerson = function() {
					console.debug('saving new person');
					console.debug('person', $scope.person);
					$scope.isLoading = true;

					var req = {
							firstName : $scope.person.firstName,
							middleName : $scope.person.middleName,
							lastName : $scope.person.lastName
					};

					PersonService
					.save(null, req)
					.then(
							function(result){
								Toast.create(result.alert);
								$scope.isLoading = result.loading;
							}
							,function(err){
								Toast.create(err.alert);
								$scope.isLoading = false;						
							}
					);

					$modalInstance.close();
				};

				$scope.cancel = function() {
					$modalInstance.dismiss('cancel');
				};
			})
			.controller('ContactsController', function ($scope, $http, $q, $filter, Validation, PersonService, Toast, $modalInstance, personalContacts, $modalStack) {
				$scope.personalContacts = personalContacts;

				$scope.selectedAssociatedEmails = {};
				$scope.selectedAssociatedPhones = {};
				$scope.selectedAssociatedAddresses = {};

				$scope.contactOrder = [{value:'PRIMARY',text:'PRIMARY'},{value:'SECONDARY',text:'SECONDARY'},{value:'TERTIARY',text:'TERTIARY'}];
				$scope.contactType = [{value:'BUSINESS',text:'BUSINESS'},{value:'PERSONAL',text:'PERSONAL'}];

				$scope.addEmailModal = function(person) {
					$modalStack.dismissAll('cancel');
					PersonService.addEmail(person);
				};

				$scope.showContactOrder = function(obj) {
					var selected = $filter('filter')($scope.contactOrder, {value: obj.contactOrder});
					return (obj.contactOrder && selected.length) ? selected[0].text : 'Not set';
				};

				$scope.showContactType = function(obj) {
					var selected = $filter('filter')($scope.contactType, {value: obj.contactType});
					return (obj.contactType && selected.length) ? selected[0].text : 'Not set';
				};

				$scope.selectEmail = function(associatedEmails) {
					console.debug('asscoaiatedEmails:', associatedEmails)
					$scope.selectedAssociatedEmails = associatedEmails;
				};

				$scope.selectPhone = function(associatedPhones) {
					console.debug('asscoaiatedPhones:', associatedPhones)
					$scope.selectedAssociatedPhones = associatedPhones;
				};

				$scope.selectAddress = function(associatedAddresses) {
					console.debug('associatedAddresses:', associatedAddresses)
					$scope.selectedAssociatedAddresses = associatedAddresses;
				};

				$scope.saveEmail = function(data, field) {
					console.debug('update email');
					console.debug('data:', data);
					console.debug('person:', $scope.person);
					console.debug('selectedAssociatedEmails:', $scope.selectedAssociatedEmails);

					if(field == 'address'){
						var validatedEmail = Validation.validateEmail(data);
						console.debug('validatedEmail:', validatedEmail);
						if(!validatedEmail.valid) {
							return 'Email is invalid';
						}
					}

					var req = {
							id: $scope.selectedAssociatedEmails.id,
							contactOrder: ((field =='contactOrder') ? data : $scope.selectedAssociatedEmails.contactOrder),
							contactType: ((field =='contactType') ? data : $scope.selectedAssociatedEmails.contactType),
							contact: {
								id: $scope.selectedAssociatedEmails.contact.id,
								recipient: ((field =='address') ? validatedEmail.recipient : $scope.selectedAssociatedEmails.contact.recipient),
								domain: ((field =='address') ? validatedEmail.domain : $scope.selectedAssociatedEmails.contact.recipient),
								address: $scope.selectedAssociatedEmails.contact.address
							}
					};

					console.debug('req:', req);

					PersonService
					.saveEmail($scope.person.id, req)
					.then(
							function(result){
								Toast.create(result.alert);
								$scope.isLoading = result.loading;
							}
							,function(err){
								Toast.create(err.alert);
								$scope.isLoading = false;						
							}
					);
				};
				/**/
				$scope.savePhone = function(data, field) {
					console.debug('update phone');
					console.debug('data:', data);
					console.debug('person:', $scope.person);
					console.debug('selectedAssociatedPhones:', $scope.selectedAssociatedPhones);

					if(field == 'phoneNumber'){
						var validatedPhone = Validation.validatePhone(data);
						console.debug('validatedPhone:', validatedPhone);
						if(!validatedPhone.valid) {
							return 'Phone number is invalid';
						}
					}

					var req = {
							id: $scope.selectedAssociatedPhones.id,
							contactOrder: ((field =='contactOrder') ? data : $scope.selectedAssociatedPhones.contactOrder),
							contactType: ((field =='contactType') ? data : $scope.selectedAssociatedPhones.contactType),
							contact: {
								id: $scope.selectedAssociatedPhones.contact.id,

								areaCode: ((field =='phoneNumber') ? validatedPhone.areaCode : $scope.selectedAssociatedPhones.contact.areaCode),
								prefix: ((field =='phoneNumber') ? validatedPhone.prefix : $scope.selectedAssociatedPhones.contact.prefix),
								number: ((field =='phoneNumber') ? validatedPhone.number : $scope.selectedAssociatedPhones.contact.number),
								extension: ((field =='phoneNumber') ? validatedPhone.extension : $scope.selectedAssociatedPhones.contact.extension),
								phoneNumber: $scope.selectedAssociatedPhones.contact.phoneNumber
							}
					};

					console.debug('req:', req);

					PersonService
					.savePhone($scope.person.id, req)
					.then(
							function(result){
								Toast.create(result.alert);
								$scope.isLoading = result.loading;
							}
							,function(err){
								Toast.create(err.alert);
								$scope.isLoading = false;						
							}
					);
				};
				/**/
			})
			.controller('AddEmailController', function ($scope, $http, $q, PersonService, Toast, $modalInstance, person) {
				$scope.person = person;
				$scope.email = {
						uuid: null,
						contactOrder: '',
						contactType: '',
						contact: {
							uuid: null,
							domain: '',
							localPart: '',
							emailAddress: ''
						}
				};

				$scope.saveEmail = function() {
					console.debug('saving new email');
					console.debug('email', $scope.email);
					console.debug('person', $scope.person);
					$scope.isLoading = true;

					var req = {
							uuid: ((null != $scope.email.uuid || '' == $scope.email.uuid) ? $scope.email.uuid : null),
							contactOrder: $scope.email.contactOrder,
							contactType: $scope.email.contactType,
							endNode: {
								emailAddress: {
									uuid: ((null != $scope.email.contact.uuid || '' == $scope.email.contact.uuid) ? $scope.email.contact.uuid : null),
									domain: $scope.email.contact.domain,
									localPart: $scope.email.contact.localPart,
									address: $scope.email.contact.address
								}
							},
							startNode: {
								person: {
									uuid:  $scope.person.uuid,
									firstName: $scope.person.firstName,
									middleName: $scope.person.middleName,
									lastName: $scope.person.lastName
								}
							}
					};
					
					console.debug('req: ', angular.toJson(req));

					PersonService
					.saveEmail(person.uuid, req)
					.then(
							function(result){
								Toast.create(result.alert);
								$scope.isLoading = result.loading;
							}
							,function(err){
								Toast.create(err.alert);
								$scope.isLoading = false;						
							}
					);

					$modalInstance.close();
				};

				$scope.cancel = function() {
					$modalInstance.dismiss('cancel');
					PersonService.contactsModal(person, null);
				};
			});
        });
