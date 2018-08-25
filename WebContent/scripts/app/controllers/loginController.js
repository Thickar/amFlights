app.controller("loginController", function ($rootScope,$scope, $http, $state) {

	$scope.submitLogin = function (email,password) {
		/* while compiling form , angular created this object*/
		var data = angular.toJson($scope.input);

		var Indata = { 'email': $scope.input.email, 'password': $scope.input.password };

		/* post to server*/
		$http.post($rootScope.baseUrl + "Login", Indata).then(function (response) {
			//First function handles success
			$state.go('booking.chooseFlight');
			$rootScope.user = response.data;
			$rootScope.isUserLoggedIn = true;
		}, function (response) {
			//Second function handles error
			$scope.content = "Something went wrong";
		});
	}
});