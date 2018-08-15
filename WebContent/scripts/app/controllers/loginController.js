app.controller("loginController", function ($scope, $http, $state) {

	$scope.submitLogin = function () {
		/* while compiling form , angular created this object*/
		var data = angular.toJson($scope.input);

		var Indata = { 'email': $scope.input.email, 'password': $scope.input.password };

		/* post to server*/
		$http.post("Login", Indata).then(function (response) {
			//First function handles success
			$scope.content = response.data;
			$state.go('booking.chooseFlight');

		}, function (response) {
			//Second function handles error
			$scope.content = "Something went wrong";
		});
	}
});