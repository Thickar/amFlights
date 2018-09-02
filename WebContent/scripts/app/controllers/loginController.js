app.controller("loginController", function ($rootScope,$scope, $http, $state) {

	$scope.submitLogin = function (email,password) {
		var data = angular.toJson($scope.input);

		var Indata = { 'email': $scope.input.email, 'password': $scope.input.password };

		$http.post($rootScope.baseUrl + "Login", Indata).then(function (response) {
			$state.go('booking.chooseFlight');
			$rootScope.user = response.data;
			$rootScope.isUserLoggedIn = true;
		}, function (response) {
			$scope.content = "Something went wrong";
		});
	}
});