app.controller("bookingController", function ($scope,$rootScope, $state, $http, $mdToast,$mdDialog,flightService) {


	function getFlights() {

		flightService.then(function (response) {
			//First function handles success
			$scope.flights = response.data;

		}, function (response) {
			//Second function handles error
			$scope.content = "Something went wrong";
		});
	}

	$rootScope.$on("GetFlights",function(){
		getFlights();
	});

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
		var Indata = { 'seatCount': $scope.booking.seatCount, 'flightId': $scope.booking.selectedFlight.flight_id, 'seatType': $scope.booking.seatType, 'seatCount': $scope.booking.seatCount, 'isMealRequired': $scope.booking.isMealRequired };

		/* post to server*/
		$http.post("Booking", Indata).then(function (response) {
			//First function handles success
			//  $scope.bookingClass = response.data.bookingClass;
			//  $scope.bookingFlight = response.data.bookingFlight;
			//  $scope.bookedSeatList = response.data.bookedSeatList;
			//  $scope.bookingPrice = response.data.bookingPrice;
			showBookingSuccess();
		}, function (response) {
			//Second function handles error
			$scope.content = "Something went wrong";
		});
	};

	function showBookingSuccess() {
		$mdDialog.show({
			controller: DialogController,
			templateUrl: 'views/BookingSuccessFull.html',
			parent: angular.element(document.body),
			clickOutsideToClose:false,
			fullscreen: true // Only for -xs, -sm breakpoints.
		  });
	}

	function init() {
		getFlights();
		$state.go("booking.chooseFlight");
		// $scope.booking.selectedFlight = "test";
	}
	init();

	 
	function DialogController($scope, $mdDialog) {
		$scope.bookAnother = function() {
		  $mdDialog.hide();
		  $state.go("booking.chooseFlight");
		};
	
		$scope.viewSummary = function() {
		  $mdDialog.hide();
		  $state.go("summary");
		};
	
	  }
});