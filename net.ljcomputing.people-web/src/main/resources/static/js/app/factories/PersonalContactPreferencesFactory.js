'use strict';

define([
        'angular'
        , 'app'
        , 'lodash'
        , 'logToServer'
        ],
        function(angular, app, _, logToServer)
        {
	app.factory('PersonalContactPreferences', ['Person', 'EmailAddressPreferences', function(Person, EmailAddressPreferences){

		function PersonalContactPreferences(id, uuid, firstName, middleName, lastName, person, emailAddressPreferences) {
			this.id = id;
			this.uuid = uuid;
			this.firstName = firstName;
			this.middleName = middleName;
			this.lastName = lastName;
			this.person = Person.build(person);
			this.emailAddressPreferences = EmailAddressPreferences.construct(emailAddressPreferences);
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

		PersonalContactPreferences.prototype.getPerson = function() {
			return this.person;
		};

		PersonalContactPreferences.prototype.getEmailAddressPreferences = function() {
			return this.emailAddressPreferences;
		};

		PersonalContactPreferences.build = function (data) {
			return new PersonalContactPreferences(
					data.id,
					data.uuid,
					data.firstName,
					data.middleName,
					data.lastName,
					data,
					data.emailAddressPreferences
			);
		};

		PersonalContactPreferences.construct = function(data) {
			var result = null;

			if(_.isArray(data)) {
				result = [];
				_.forEach(data, function(personalContactPreferences){
					result.push(PersonalContactPreferences.build(personalContactPreferences));
				});
			} else {
				result = PersonalContactPreferences.build(data);
			}

			return result;
		};

		return PersonalContactPreferences;
	}]);
});