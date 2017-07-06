angular.module('app').config(['$routeProvider', function ($routeProvider) {
    $routeProvider
    .when('/login', {
        controller: 'LoginController',
        templateUrl: '/login/login.html'
    })
    .otherwise('/login');
}]);
