modulo.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
    .when('/login', {
        controller: 'LoginController',
        templateUrl: '/templates/login.html'
    })

    .when('/apis', {
        controller: 'apisController',
        templateUrl: '/apis.html'
    })

    .otherwise('/login');
}]);
