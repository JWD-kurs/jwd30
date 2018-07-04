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
	
	$scope.delete = function(id){
		
		var  promise = $http.delete(baseUrl + "/" + id);
		promise.then(
			function success(){
				getActivities();
			},
			function error(){
				alert("Could not delete the activity.")
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


app.controller("standoviCtrl", function($scope, $http){
	
	var baseUrlStandovi = "/api/standovi";
	var baseUrlSajmovi = "/api/sajmovi";
	
	$scope.standovi = [];
	$scope.sajmovi = [];
	
	$scope.noviStand = {};
	$scope.noviStand.zakupac = "";
	$scope.noviStand.povrsina = "";
	$scope.noviStand.sajamId = "";
	
	$scope.parametri = {};
	$scope.parametri.zakupac = "";
	$scope.parametri.minP = "";
	$scope.parametri.maxP = "";
	
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	
	var getStandovi = function(){
		
		var config = {params: {}};
		
		if($scope.parametri.zakupac != ""){
			config.params.zakupac = $scope.parametri.zakupac;
		}
		if($scope.parametri.minP != ""){
			config.params.minP = $scope.parametri.minP;
		}
		if($scope.parametri.maxP != ""){
			config.params.maxP = $scope.parametri.maxP;
		}
		
		config.params.pageNum = $scope.pageNum;
		
		$http.get(baseUrlStandovi, config).then(
			function success(res){
				$scope.standovi = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(res){
				alert("Neuspeh u dobavljanju standova.")
			}
		);
	}
	
	getStandovi();
	
	var getSajmovi = function(){
		$http.get(baseUrlSajmovi).then(
			function success(res){
				$scope.sajmovi = res.data;
			},
			function error(){
				alert("Neuspesno dobavljanje sajmova.");
			}
		);
	}
	
	getSajmovi();
	
	$scope.add = function(){
		//console.log($scope.noviStand);
		$http.post(baseUrlStandovi, $scope.noviStand).then(
			function success(){
				getStandovi();
				$scope.noviStand = {};
				$scope.noviStand.zakupac = "";
				$scope.noviStand.povrsina = "";
				$scope.noviStand.sajamId = "";
			},
			function error(){
				alert("Neuspesno dodavanje standa.");
			}
		);
	}
	
	$scope.search = function(){
		//console.log($scope.parametri);
		$scope.pageNum = 0;
		getStandovi();
	}
	
	$scope.go = function(direction){
		$scope.pageNum = $scope.pageNum + direction;
		getStandovi();
	}
	
	$scope.react = function(){
		console.log($scope.parametri.maxP);
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
		.when('/standovi', {
			templateUrl : '/app/html/partial/standovi.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);