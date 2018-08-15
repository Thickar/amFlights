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

	$scope.getAvailabeSeatCountForType = function() {

		var Indata = { 'flight_id':$scope.booking.selectedFlight.flight_id };

		$http.get("Seat?flight_id="+ $scope.booking.selectedFlight.flight_id).then(function (response) {
			//First function handles success
			$scope.seatCountMax = response.data;
			$scope.seatCount = response.data;

		}, function (response) {
						//Second function handles error
			$scope.content = "Something went wrong";
		});
	}

	$scope.incrementSeatCount = function() {
		$scope.seatCount++;
	}

	$scope.decrementSeatCount = function() {
		$scope.seatCount--;
	}



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