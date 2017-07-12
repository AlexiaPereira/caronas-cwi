angular.module('app').config(['$routeProvider', function ($routeProvider) {

    $routeProvider
    .when('/login', {
        controller: 'LoginController',
        templateUrl: '/templates/login.html'
    })
    .when('/rotina-cadastrar', {
        controller: 'RotinaController',
        templateUrl: '/templates/rotina-cadastrar.html'
    })
    .when('/rotina-buscar', {
        controller: 'RotinaBuscarController',
        templateUrl: '/templates/rotina-buscar.html'
    })
    .when('/rotina-visualizar', {
        controller: 'RotinaController',
        templateUrl: '/templates/rotina-visualizar.html'
    })
    .when('/meus-grupos', {
        controller: 'GrupoController',
        templateUrl: '/templates/meus-grupos.html'
    })
    .when('/apis', {
        controller: 'apisController',
        templateUrl: '/apis.html'
    })
    .otherwise('/login');
}]);
