app.controller("summaryController", function ($scope, $rootScope,flightService,$http) {
	
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
        $http.get("Booking?flightId="+$scope.selectedFlight.flight_id).then(function (response) {
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
	function init() {
		getFlights();
		// $scope.booking.selectedFlight = "test";
	};
	init();
});