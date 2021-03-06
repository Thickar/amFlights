﻿app.config(function($stateProvider, $urlRouterProvider) {

    $stateProvider.state('login', {
            url: '/login',
            templateUrl: "views/login.html",
         controller : "loginController"
        })

         .state('welcome', {
            url: '/welcome',
            templateUrl: "views/welcome.html",
              controller : "navbarController"
        })
         .state('booking', {
            url: '/booking',
            templateUrl: "views/booking.html",
              controller : "bookingController"
        })
        .state('booking.chooseFlight', {
            url: '/chooseFlight',
            templateUrl: "views/booking/chooseFlight.html"
        })
        .state('booking.seatType', {
            url: '/seatType',
            templateUrl: "views/booking/seatType.html"
        }) 

        .state('booking.mealsRequired', {
            url: '/mealsRequired',
            templateUrl: "views/booking/mealsRequired.html"
        })

        .state('booking.seatCount', {
            url: '/seatCount',
            templateUrl: "views/booking/seatCount.html"
        });

    // catch all route
    // send users to the form page 
    $urlRouterProvider.otherwise('welcome');
});

