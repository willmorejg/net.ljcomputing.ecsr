'use strict';

define([
        'angular'
        , 'app'
        , 'lodash'
        , 'logToServer'
        ],
        function(angular, app, _, logToServer)
        {
	app.factory('Person', [function(){

		function Person(id, uuid, firstName, middleName, lastName, fullName) {
			this.id = id;
			this.uuid = uuid;
			this.firstName = firstName;
			this.middleName = middleName;
			this.lastName = lastName;
			this.fullName = fullName;
		}

		Person.prototype.getId = function() {
			return this.id;
		};

		Person.prototype.getUuid = function() {
			return this.uuid;
		};

		Person.prototype.getFirstName = function() {
			return this.firstName;
		};

		Person.prototype.getMiddleName = function() {
			return this.middleName;
		};

		Person.prototype.getLastName = function() {
			return this.lastName;
		};

		Person.prototype.getFullName = function() {
			return this.fullName;
		};

		Person.build = function (data) {
			return new Person(
					data.id,
					data.uuid,
					data.firstName,
					data.middleName,
					data.lastName,
					data.fullName
			);
		};

		Person.construct = function(data) {
			var result = null;

			if(_.isArray(data)) {
				result = [];
				_.forEach(data, function(person){
					result.push(Person.build(person));
				});
			} else {
				result = Person.build(data);
			}

			return result;
		};

		return Person;
	}]);
});