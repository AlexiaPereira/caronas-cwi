angular.module('app').factory('GrupoService', ['$http', function ($http) {

    let porta = 9090;
    let url = `http://localhost:${porta}/api/grupos`;

    function buscarGrupo(idGrupo) {
        return $http.get(`${url}/${idGrupo}`);
    }

    function listarGrupos() {
        return $http.get(`${url}/usuario`);
    }

    return ({
        buscarGrupo: buscarGrupo,
        listarGrupos: listarGrupos
    });
}]);
