app.controller("summaryController", function ($scope, $rootScope,flightService,$http,$mdDialog,$state) {
	
	function getFlights() {

		flightService.then(function (response) {
			//First function handles success
			$scope.flights = response.data;
		}, function (response) {
			//Second function handles error
			$scope.content = "Something went wrong";
		}); 
	};

    $scope.getBookings = function()
    {
        $http.get($rootScope.baseUrl+"Booking?flightId="+$scope.selectedFlight.flight_id).then(function (response) {
			//First function handles success
			$scope.bookings = response.data;

		}, function (response) {
			var errorMessage = 'Something went wrong';
			//Second function handles error

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
			clickOutsideToClose:false,
			fullscreen: true // Only for -xs, -sm breakpoints.
		  });
	};
	
	$scope.cancelBooking = function (bookingId) {

		$http.put($rootScope.baseUrl + "Booking?bookingId=" + bookingId).then(function (response) {
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
		$scope.bookAnother = function() {
		  $mdDialog.hide();
		  $state.go("booking.chooseFlight");
		};
	
		$scope.Close = function() {
		  $mdDialog.hide();
		};
	
	  };

	function init() {
		getFlights();
		// $scope.booking.selectedFlight = "test";
	};

	

	init();
});