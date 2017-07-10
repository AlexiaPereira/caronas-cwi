angular.module('app').factory('UsuarioService', ['$http', function($http) {
    
    let porta = 123;
    let url = `http://localhost:${porta}/api/usuarios`;

    function aceitar(usuario) {
        return $http({
            url: `${url}/aceitar`,
            method: 'POST',
            data: usuario
        });
    }

    function recusar(usuario) {
        return $http({
            url: `${url}/recusar`,
            method: 'POST',
            data: usuario
        });
    }

    return ({
        aceitar: aceitar,
        recusar: recusar
    });
}]);