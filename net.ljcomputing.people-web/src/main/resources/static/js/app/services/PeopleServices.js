'use strict';

define([
	'angular'
	, 'app'
	, 'lodash'
	, 'logToServer'
],
function(angular, app, _, logToServer)
{
	app.factory('PersonService', ['$q', '$http', '$modal', '$modalStack', 'ALERT', function($q, $http, $modal, $modalStack, ALERT) {

		var baseUrl = "/people/";
		var contactsUrl = baseUrl + "contacts/"; 
		var emailsUrl = '/emails/';
		var phonesUrl = '/associatedPhones/';
		
		var getTotalPages = function(total, pageSize) {
			var result = 1;
			var calculated = Math.ceil(total / pageSize);
			
			if(!_.isNaN(calculated)) {
				result = calculated;
			}
			
			return result;
		};
		
		var getSelectedPage = function(start, pageSize) {
			var result = 0;
			
			if(start > 0) {
				var calculated = Math.ceil(start / pageSize);
	
				if(!_.isNaN(calculated) && _.isFinite(calculated) && calculated > 0) {
					result = calculated;
				}
			}

			return result;
		};
		
		var getSorting = function(options) {
			var sort = {
				sortName : 'lastName',
				sortOrder : 'asc'
			};
			
			if(options) {
				if(options.sort) {
					if(options.sort.predicate) {
						sort.sortName = options.sort.predicate;
					}
					
					if(options.sort.reverse) {
						sort.sortOrder = 'desc';
					} else {
						sort.sortOrder = 'asc';
					}
				}
			}
			
			return sort;
		};
		
		var getSearchByFullName = function(options) {
			var searchText = '';
			
			if(options) {
				if(options.search) {
					if(options.search.predicateObject) {
						if(options.search.predicateObject.fullName) {
							searchText = '.*' + options.search.predicateObject.fullName + '.*';
						}
					}
				}
			}
			
			console.debug('searchText: ', searchText);
			return searchText;
		};

		var getPage = function(options) {
			console.debug('options: ', options);
			var pageSize = 5;
			var req = {
				pageNumber : getSelectedPage(options.pagination.start, options.pagination.number),
				pageSize : pageSize,
				searchText : getSearchByFullName(options),
				sortName : getSorting(options).sortName,
				sortOrder : getSorting(options).sortOrder
			};
			
			var deferred = $q.defer();

			$http.get(baseUrl, {params : req})
			.success(function(data, status, headers, config){
				deferred.resolve({
					people : data['rows'],
					total : data.total,
					totalPages : getTotalPages(data.total, req.pageSize),
					size : req.pageSize,
					alert : { type: ALERT.SUCCESS, msg: ALERT.RECORDS_LOADED, timeout: ALERT.TIMEOUT },
					loading : false
				});
			})
			.error(function(data, status, headers, config) {
				deferred.reject({
					data : (null != status && null != status.data) ? status.data : 'Fatal error occured',
					alert : { type: ALERT.DANGER, timeout: ALERT.TIMEOUT, msg: "Error occured while loading people - HTTP status: " + (null != status ? (status == 0 ? 'Connection to server failed' : status) : 'unknow') }
				});
			});
			

			return deferred.promise;
		};

		var post = function(uuid, person) {
			var deferred = $q.defer();
			
			var url = (uuid ? (baseUrl + uuid) : baseUrl);

			$http.post(url, person)
			.success(function(data, status, headers, config){
				deferred.resolve({
					alert : { type: ALERT.SUCCESS, msg: ALERT.RECORD_UPDATED, timeout: ALERT.TIMEOUT },
					loading : false
				});
			})
			.error(function(data, status, headers, config) {
				deferred.reject({
					data : status.data,
					alert : { type: ALERT.DANGER, msg: "Error occured while saving person: HTTP status is " + status },
					loading : false
				});
			});

			return deferred.promise;
		};

		var postEmail = function(personUuid, email) {
			console.debug('personUuid, email: ', personUuid, email);
			console.debug('email JSON: ', angular.toJson(email));

			var deferred = $q.defer();
			
			var url = (email.uuid ? (emailsUrl + email.uuid) : emailsUrl);

			$http.post(url, email)
			.success(function(data, status, headers, config){
				deferred.resolve({
					alert : { type: ALERT.SUCCESS, msg: ALERT.RECORD_UPDATED, timeout: ALERT.TIMEOUT },
					loading : false
				});
			})
			.error(function(data, status, headers, config) {
				deferred.reject({
					data : status.data,
					alert : { type: ALERT.DANGER, msg: "Error occured while saving email: HTTP status is " + status },
					loading : false
				});
			});

			return deferred.promise;
		};
/**/
		var postPhone = function(personId, phone) {
			var deferred = $q.defer();
			
			var url = (phone.id ? (baseUrl + personId + phonesUrl/* + phone.id*/) : (baseUrl + personId + phonesUrl));

			$http.post(url, phone)
			.success(function(data, status, headers, config){
				deferred.resolve({
					alert : { type: ALERT.SUCCESS, msg: ALERT.RECORD_UPDATED, timeout: ALERT.TIMEOUT },
					loading : false
				});
			})
			.error(function(data, status, headers, config) {
				deferred.reject({
					data : status.data,
					alert : { type: ALERT.DANGER, msg: "Error occured while saving email: HTTP status is " + status },
					loading : false
				});
			});

			return deferred.promise;
		};
/**/
		var del = function(id) {
			JDEV.logging.logToServer('Sending: ' + id);
			
			var deferred = $q.defer();

			$http.delete(baseUrl + id)
			.success(function(data, status, headers, config){
				deferred.resolve({
					alert : { type: ALERT.SUCCESS, msg: ALERT.RECORD_DELETED, timeout: ALERT.TIMEOUT },
					loading : false
				});
			})
			.error(function(data, status, headers, config) {
				deferred.reject({
					data : status.data,
					alert : { type: ALERT.DANGER, msg: "Error occured while delting person: HTTP status is " + status },
					loading : false
				});
			});

			return deferred.promise;
		};
		
		var addPersonModal = function() {
			return $modal.open({
				templateUrl: '/views/addPerson.htm',
		        controller: 'AddPersonController',
		        backdrop: 'static',
		        size: 'lg'
			});
		};


		var retrievePersonalContacts = function(uuid) {
			if(!uuid || null == uuid) return;
			var deferred = $q.defer();
			
			var url = contactsUrl + uuid;

			$http.get(url)
			.success(function(data, status, headers, config){
				deferred.resolve({
					personalContacts: data
				});
			})
			.error(function(data, status, headers, config) {
				deferred.reject({
					data : status.data,
					alert : { type: ALERT.DANGER, msg: "Error occured while saving person: HTTP status is " + status },
					loading : false
				});
			});

			return deferred.promise;
		};

		var viewContactsModal = function(personalContacts) {
			//var personalContacts = retrievePersonalContacts(person.uuid);
			return $modal.open({
				templateUrl: '/views/viewContacts.htm',
		        controller: 'ContactsController',
		        backdrop: 'static',
		        size: 'lg',
		        resolve: {
		        	personalContacts: function() {
		        		return personalContacts;
		        	}
		        }
			});
		};
		
		var addEmailModal = function(person) {
			return $modal.open({
				templateUrl: '/views/addEmail.htm',
		        controller: 'AddEmailController',
		        backdrop: 'static',
		        size: 'lg',
		        resolve: {
		        	person: function() {
		        		return person;
		        	}
		        }
			});
		};

		return {
			getPage : function(options) {
				return getPage(options);
			},
			save: function(id, person) {
				return post(id, person);
			},
			del : function(id) {
				return del(id);
			},
			addPerson : function() {
				addPersonModal();
			},
			contactsModal : function(personalContacts) {
				viewContactsModal(personalContacts);
			},
			addEmail : function(person) {
				addEmailModal(person);
			},
			saveEmail: function(personId, email) {
				return postEmail(personId, email);
			},
			savePhone: function(personId, phone) {
				return postPhone(personId, phone);
			}
		};
	}]);
});
