app.controller("summaryController", function ($scope, $rootScope, flightService, $http, $mdDialog, $state) {

	function getFlights() {

		flightService.then(function (response) {
			$scope.flights = response.data;
		}, function (response) {
			$scope.content = "Something went wrong";
		});
	};

	$scope.getBookings = function () {
		$http.get($rootScope.baseUrl + "api/Booking?flightId=" + $scope.selectedFlight.flight_id).then(function (response) {
			$scope.bookings = response.data;
		}, function (response) {
			var errorMessage = 'Something went wrong';
			$mdToast.show(
				$mdToast.simple()
					.textContent(errorMessage)
			);
		});
	};

	function showBookingSuccess() {
		$mdDialog.show({
			controller: DialogController,
			templateUrl: 'views/Alerts/CancellationSuccessFull.html',
			parent: angular.element(document.body),
			clickOutsideToClose: false,
			fullscreen: true
		});
	};

	$scope.cancelBooking = function (bookingId) {

		$http.put($rootScope.baseUrl + "api/Booking?bookingId=" + bookingId).then(function (response) {
			$scope.getBookings();
			showBookingSuccess();
		}, function (response) {
			var errorMessage = 'Something went wrong';
			$mdToast.show(
				$mdToast.simple()
					.textContent(errorMessage)
			);
		});
	};

	function DialogController($scope, $mdDialog) {
		$scope.bookAnother = function () {
			$mdDialog.hide();
			$state.go("booking.chooseFlight");
		};

		$scope.Close = function () {
			$mdDialog.hide();
		};

	};

	function init() {
		getFlights();
	};



	init();
});