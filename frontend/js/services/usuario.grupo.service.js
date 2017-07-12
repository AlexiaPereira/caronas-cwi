angular.module('app').factory('UsuarioGrupoService', ['$http', function ($http) {

    let porta = 9090;
    let url = `http://localhost:${porta}/api/usuarios-grupo`;

    function remover(idUsuarioGrupo) {
        return $http.delete(`${url}/${idUsuarioGrupo}`);
    }

    return ({
        remover: remover
    });
}]);