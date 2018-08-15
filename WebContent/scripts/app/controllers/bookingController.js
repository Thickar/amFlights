app.controller("bookingController", function ($scope,$http) {

	
	function getFlights() {

		$http.get("Flight").then(function (response) {
			//First function handles success
			$scope.flights = response.data;

		}, function (response) {
			//Second function handles error
			$scope.content = "Something went wrong";
		});
	}

	function getAvailabeSeatCountForType() {

		$http.get("SeatCount",booking).then(function (response) {
			//First function handles success
			$scope.flights = response.data;

		}, function (response) {
			//Second function handles error
			$scope.content = "Something went wrong";
		});
	}

	$scope.input = {
		num: 3
	};

	$scope.booking = {};

	$scope.bookTickets = function () {
		alert($scope.booking);
	};

	function init() {
		getFlights();
		// $scope.booking.selectedFlight = "test";
	}
	init();

});