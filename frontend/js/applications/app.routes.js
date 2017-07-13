angular.module('app').config(['$routeProvider', function ($routeProvider) {

    $routeProvider
    .when('/login', {
        controller: 'LoginController',
        templateUrl: '/templates/login.html'
    })
    .when('/rotina-cadastrar', {
        controller: 'RotinaController',
        templateUrl: '/templates/rotina-cadastrar.html',
        resolve: {
          // define que para acessar esta página deve ser um usuário autenticado (mas não restringe o tipo de permissão)
          autenticado: function (authService) {
            return authService.isAutenticadoPromise();
          }
        }
    })
    .when('/rotina-buscar', {
        controller: 'RotinaBuscarController',
        templateUrl: '/templates/rotina-buscar.html',
        resolve: {
          // define que para acessar esta página deve ser um usuário autenticado (mas não restringe o tipo de permissão)
          autenticado: function (authService) {
            return authService.isAutenticadoPromise();
          }
        }
    })
    .when('/rotina-visualizar', {
        controller: 'RotinaController',
        templateUrl: '/templates/rotina-visualizar.html',
        resolve: {
          // define que para acessar esta página deve ser um usuário autenticado (mas não restringe o tipo de permissão)
          autenticado: function (authService) {
            return authService.isAutenticadoPromise();
          }
        }
    })
    .when('/meus-grupos', {
        controller: 'GrupoController',
        templateUrl: '/templates/meus-grupos.html',
        resolve: {
          // define que para acessar esta página deve ser um usuário autenticado (mas não restringe o tipo de permissão)
          autenticado: function (authService) {
            return authService.isAutenticadoPromise();
          }
        }
    })
    .otherwise('/login');
}]);
