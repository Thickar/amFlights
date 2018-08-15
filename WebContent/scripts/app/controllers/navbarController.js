﻿app.controller("navbarController", function ($scope, $rootScope) {

      $rootScope.showNavBar = true;

      $scope.menuItems = [
            { link: "booking", name: "Book tickets" },
            { link: "cancelBooking", name: "Cancel tickets" },
            { link: "login", name: "Login tickets" },
            { link: "summary", name: "View summary" }]
});