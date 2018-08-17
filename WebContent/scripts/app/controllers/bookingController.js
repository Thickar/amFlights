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

		var Indata = { 'flight_id': $scope.booking.selectedFlight.flight_id, 'seatType': $scope.booking.seatType };

		$http.get("Seat?flight_id=" + $scope.booking.selectedFlight.flight_id + "&seat_type=" + $scope.booking.seatType).then(function (response) {
			//First function handles success
			$scope.seatCountMax = response.data;
			$scope.seatCount = response.data;

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
		alert($scope.booking);
		/* while compiling form , angular created this object*/
		var Indata = { 'seatCount': $scope.booking.seatCount, 'flightId': $scope.booking.selectedFlight.flight_id, 'seatType': $scope.booking.seatType, 'isMealRequired': $scope.booking.isMealRequired };

		/* post to server*/
		$http.post("Booking", Indata).then(function (response) {
			//First function handles success
			$scope.content = response.data;
			$state.go('booking.chooseFlight');

		}, function (response) {
			//Second function handles error
			$scope.content = "Something went wrong";
		});
	};

	function init() {
		getFlights();
		// $scope.booking.selectedFlight = "test";
	}
	init();

});