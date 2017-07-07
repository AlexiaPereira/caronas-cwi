modulo.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
    .when('/login', {
        controller: 'LoginController',
        templateUrl: '/templates/login.html'
    })
    .otherwise('/login');
}]);