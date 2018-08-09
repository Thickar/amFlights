app.controller("navbarController", function ($scope,$rootScope) {

$rootScope.showNavBar =  true;

$scope.menuItems =[
        {link: "booking",name:"Book tickets" },
        {link: "Cancel",name:"Cancel tickets" },
        {link: "login",name:"login tickets" },
        {link: "summary",name:"view summary" }]


  });