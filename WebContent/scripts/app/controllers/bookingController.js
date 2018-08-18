app.controller("bookingController", function ($scope,$state, $http, $mdToast) {


	function getFlights() {

		$http.get("Flight").then(function (response) {
			//First function handles success
			$scope.flights = response.data;

		}, function (response) {
			//Second function handles error
			$scope.content = "Something went wrong";
		});
	}

	$scope.getAvailabeSeatCountForType = function () {

		$http.get("Seat?flight_id=" + $scope.booking.selectedFlight.flight_id + "&seat_type=" + $scope.booking.seatType).then(function (response) {
			//First function handles success
			$scope.seatCountMax = response.data;
			$scope.booking.seatCount = response.data;

		}, function (response) {
			var errorMessage = 'Something went wrong';
			//Second function handles error
			if (response.status == 412) {
				errorMessage = 'Choose different seatType!';
				$state.go("booking.seatType");
			}

			$mdToast.show(
				$mdToast.simple()
					.textContent(errorMessage)
			);
		});
	}


	$scope.booking = {};

	$scope.bookTickets = function () {
		/* while compiling form , angular created this object*/
		var Indata = { 'seatCount': $scope.booking.seatCount, 'flightId': $scope.booking.selectedFlight.flight_id, 'seatType': $scope.booking.seatType,'seatCount' : $scope.booking.seatCount , 'isMealRequired': $scope.booking.isMealRequired };

		/* post to server*/
		$http.post("Booking", Indata).then(function (response) {
			//First function handles success
			$state.go('welcome');

		}, function (response) {
			//Second function handles error
			$scope.content = "Something went wrong";
		});
	};

	function init() {
		getFlights();
		$state.go("booking.chooseFlight");
		// $scope.booking.selectedFlight = "test";
	}
	init();

});