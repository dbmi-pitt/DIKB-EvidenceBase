var restBaseUrl = "http://localhost:8080/WebAPI/DIKB/";

function PersonViewModel(researchStatementLabel, assertType, dateAnnotated, evidenceRole, evidence) {
	var self = this;
	
	self.researchStatementLabel = ko.observable(researchStatementLabel);
	self.assertType = ko.observable(assertType);
	self.dateAnnotated = ko.observable(dateAnnotated);
	self.evidenceRole = ko.observable(evidenceRole);
	self.evidence = ko.observable(evidence);

}


function PersonsViewModel() {
	
	var self = this;
	//var termsearch = ko.observable('');

	self.people = ko.observableArray();

	self.newPerson = ko.observable(new PersonViewModel());


	self.currentValue=ko.observable();
    
/*
	self.findAll = function() {
		$.ajax({

			url: restBaseUrl + "Index",
			type: 'GET',
			dataType: 'json',
			contentType: "application/json",
			crossDomain: true,
			success: function(data) {
				self.people.removeAll();

				for (var i = 0; i < data.length; i++) {
					var person = new PersonViewModel(data[i].term, data[i].databaseName, data[i].tableName, data[i].columnName);
                   
					self.people.push(person);
				}
			},
			error: function(data) {
				alert("Something went wrong while getting Index list. Please try again.");
			}
		});
	};*/




	self.findById = function() {
		var q = document.getElementById('key').value;
		//var splited = q.split("+");
		$.ajax({
			url: restBaseUrl + "search/"+q,
			type: 'GET',
			dataType: 'json',
			contentType: "application/json",
			crossDomain: true,
			success: function(data) {
				self.people.removeAll();

				for (var i = 0; i < data.length; i++) {
					//alert(data[i].researchStatementLabel);
					var person = new PersonViewModel(data[i].researchStatementLabel, data[i].assertType, data[i].dateAnnotated, data[i].evidenceRole, data[i].evidence);
                   
					self.people.push(person);
				}
			},
			error: function(data) {
				alert("Something went wrong while getting Index list. Please try again.");
			}
		});
	};

//test function

	self.addPerson = function() {
		$.ajax({
			url: restBaseUrl + "Index",
			type: 'PUT',
			data: ko.toJSON(self.newPerson()),
			dataType: 'json',
			contentType: "application/json",
			crossDomain: true,
			success: function(data) {
				self.people.push(new PersonViewModel("test", "test", "test", "test"));
				//self.newPerson(new PersonViewModel());
			},
			error: function(data) {
				alert("Something went wrong while adding new Index. Please try again.");
			}
		});
	};

	//self.findAll();
}



ko.applyBindings(new PersonsViewModel(), $("#personsContainer")[0]);
