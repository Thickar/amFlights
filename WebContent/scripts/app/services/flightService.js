app.factory('flightService', function ($http) {
    return $http.get("Flight");
});