angular.module('app').factory('UsuarioGrupoService', ['$http', function ($http) {

    let porta = 9090;
    let url = `http://localhost:${porta}/api/usuarios-grupo`;

    function removerMembro(idUsuarioGrupo) {
        return $http.delete(`${url}/${idUsuarioGrupo}`);
    }

    function remover(grupo) {
        return $http.post(`${url}/remover`, grupo);
    }


    return ({
        remover: remover,
        removerMembro: removerMembro
    });
}]);
