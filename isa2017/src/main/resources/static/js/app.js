var app = angular.module('webApp',['ngRoute']);

// routeProvider
app.config(function($routeProvider, $locationProvider) {

	$routeProvider.when('/login', {
		templateUrl : 'pages/login.html',
		controller : 'loginController'
	}).when('/home', {
		templateUrl: 'home.html',
		controller: 'homeController'
	})
});