angular.module('app').factory('GrupoService', ['$http', function($http) {

    let porta = 9090;
    let url = `http://localhost:${porta}/api/grupos`;

    function listar() {
        return $http.get(`${url}/usuario`);
    }

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
        listar: listar,
        aceitar: aceitar,
        recusar: recusar
    });
}]);
