angular.module('app').factory('GrupoService', ['$http', function($http) {
    
    let porta = 123;
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

    function sair(grupo) {
        return $http({
            url: `${url}/sair`,
            method: 'POST',
            data: grupo
        });
    }

    function remover(usuarioGrupo) {
        return $http({
            url: `${url}/remover`,
            method: 'POST',
            data: usuarioGrupo
        });
    }

    return ({
        listar: listar,
        aceitar: aceitar,
        recusar: recusar,
        sair: sair
    });
}]);