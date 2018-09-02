var app = angular.module("amFlights", ['ngAnimate', 'ui.router', 'ngMaterial', 'ngMessages']);

app.run(function ($rootScope, $http, $state) {
    $http.get("Login").then(function (response) {
        $rootScope.user = response.data;
        $rootScope.isUserLoggedIn = true;
    }, function (response) {
        $state.go("login");
    });
});