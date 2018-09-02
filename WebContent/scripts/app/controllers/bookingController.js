app.controller("bookingController", function ($scope, $rootScope, $state, $http, $mdToast, $mdDialog, flightService) {


	function getFlights() {

		flightService.then(function (response) {
			$scope.flights = response.data;

		}, function (response) {
			$scope.content = "Something went wrong";
		});
	};

	$scope.getAvailabeSeatCountForType = function () {

		$http.get($rootScope.baseUrl + "api/Seat?flight_id=" + $scope.booking.selectedFlight.flight_id + "&seat_type=" + $scope.booking.seatType).then(function (response) {
			$scope.seatCountMax = response.data;
			$scope.booking.seatCount = response.data;

		}, function (response) {
			var errorMessage = 'Something went wrong';
			if (response.status == 412) {
				errorMessage = 'Choose different seatType!';
				$state.go("booking.seatType");
			}

			$mdToast.show(
				$mdToast.simple()
					.textContent(errorMessage)
			);
		});
	};


	$scope.booking = {};

	$scope.bookTickets = function () {
		var Indata = { 'seatCount': $scope.booking.seatCount, 'flightId': $scope.booking.selectedFlight.flight_id, 'seatType': $scope.booking.seatType, 'seatCount': $scope.booking.seatCount, 'isMealRequired': $scope.booking.isMealRequired };

		$http.post($rootScope.baseUrl + "api/Booking", Indata).then(function (response) {
			showBookingSuccess();
		}, function (response) {
			$scope.content = "Something went wrong";
		});
	};

	function showBookingSuccess() {
		$mdDialog.show({
			controller: DialogController,
			templateUrl: 'views/Alerts/BookingSuccessFull.html',
			parent: angular.element(document.body),
			clickOutsideToClose: false,
			fullscreen: true
		});
	};

	function init() {
		getFlights();
		$state.go("booking.chooseFlight");
	};
	init();


	function DialogController($scope, $mdDialog) {
		$scope.bookAnother = function () {
			$mdDialog.hide();
			$state.go("booking.chooseFlight");
		};

		$scope.viewSummary = function () {
			$mdDialog.hide();
			$state.go("summary");
		};

	};
});