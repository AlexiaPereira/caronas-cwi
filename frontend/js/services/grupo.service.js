angular.module('app').factory('GrupoService', ['$http', function ($http) {

    let porta = 9090;
    let url = `http://localhost:${porta}/api/grupos`;

    function buscarGrupo(idGrupo) {
        return $http.get(`${url}/${idGrupo}`);
    }

    return ({
        buscarGrupo: buscarGrupo
    });
}]);