var app = angular.module("amFlights", ['ngAnimate', 'ui.router', 'ngMaterial','ngMessages']);

app.run(function($rootScope,$http,$state){
/* post to server*/
$http.get("Login").then(function (response) {
    //First function handles success
    $rootScope.user = response.data;
    $rootScope.isUserLoggedIn = true;
}, function (response) {
    //Second function handles error
    $state.go("login");
});
});