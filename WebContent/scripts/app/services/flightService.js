app.factory('flightService', function ($http,$rootScope) {
    return $http.get($rootScope.baseUrl + "api/Flight");
});