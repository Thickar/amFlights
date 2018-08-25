﻿app.controller("mainController", function ($scope, $rootScope, $state,$http) {

      $rootScope.showNavBar = true;
      $rootScope.baseUrl = "";//"http://localhost:8080/amFlights/"

      $scope.menuItems = [
            { link: "booking", name: "Book tickets" },
            // { link: "login", name: "Login tickets" },
            { link: "summary", name: "View summary" }];

      $scope.login = function () {
            $state.go("login");
      };

      $scope.logout =  function () {
		$http.post("Logout").then(function (response) {
			//First function handles success
			$state.go('login');
			$rootScope.user = null;
			$rootScope.isUserLoggedIn = false;
		}, function (response) {
			//Second function handles error
            })
      };

});  