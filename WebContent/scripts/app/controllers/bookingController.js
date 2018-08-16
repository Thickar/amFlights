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

	$scope.incrementSeatCount = function () {
		$scope.seatCount++;
	}

	$scope.decrementSeatCount = function () {
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