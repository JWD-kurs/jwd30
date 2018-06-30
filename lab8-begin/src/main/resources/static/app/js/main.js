var app = angular.module("Wafepa", ["ngRoute"]);

app.controller("ctrl", function($scope){
	$scope.message = "Hello JWD 30!";
});


app.controller("activitiesCtrl", function($scope, $http, $location){

	var baseUrl = "/api/activities";
	
	$scope.activities = [];
	
	$scope.newActivity = {};
	$scope.newActivity.name = "";
	
	var getActivities = function(){
		
		var promise = $http.get(baseUrl);
		promise.then(
			function success(res){
				//console.log(res);
				$scope.activities = res.data;
			},
			function error(res){
				alert("Something went wrong!");
			}
		);
		
		//console.log("Test");
	}
	
	getActivities();
	
	
	$scope.goToEdit = function(id){
		$location.path("/activities/edit/" + id );
	}
	
	$scope.add = function(){
		//console.log($scope.newActivity);
		$http.post(baseUrl, $scope.newActivity).then(
			function success(){
				getActivities();
			},
			function error(){
				alert("Could not create new activity.");
			}
		);
	}
});


app.controller("editActivity", function($scope, $routeParams, $http, $location){
	
	console.log($routeParams);
	
	var id = $routeParams.aid;
	var url = "/api/activities/" + id;
	
	
	$scope.activity = {};
	$scope.activity.name = "";
	
	
	var getActivity = function(){
		var promise = $http.get(url);
		promise.then(
			function uspeh(odg){
				$scope.activity = odg.data;
			},
			function neuspeh(){
				alert("Could not fetch the activity!");
			}
		);
	}
	
	getActivity();
	
	$scope.edit = function(){
		$http.put(url, $scope.activity).then(
			function success(){
				$location.path("/activities");
			},
			function error(){
				alert("Could not update the activity...");
			}
		);
	}
	
});

app.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/partial/home.html',
			controller : "ctrl"
		})
		.when('/activities', {
			templateUrl : '/app/html/partial/activities.html'
		})
		.when('/activities/edit/:aid', {
			templateUrl : '/app/html/partial/edit-activity.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);