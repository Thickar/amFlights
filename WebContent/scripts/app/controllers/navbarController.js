app.controller("navbarController", function ($scope, $rootScope) {

      $rootScope.showNavBar = true;
      $rootScope.baseUrl = "";//"http://localhost:8080/amFlights/"

      $scope.menuItems = [
            { link: "booking", name: "Book tickets" },
            // { link: "login", name: "Login tickets" },
            { link: "summary", name: "View summary" }]
});