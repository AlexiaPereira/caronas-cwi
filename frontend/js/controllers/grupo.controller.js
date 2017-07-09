angular.module('app').controller('GrupoController', ['$scope', 'GrupoService', function ($scope, GrupoService) {

    $scope.listar = listar;
    $scope.aceitar = aceitar;
    $scope.recusar = recusar;
    $scope.sair = sair;
    $scope.remover = remover;

    function listar() {
        GrupoService
            .listar()
            .then(response => {
                console.log(response);
            });
    }

    function aceitar(usuario) {
        if (isUndefinedOrNull(usuario)) {
            console.log('undefined or null');
            return;
        }
        GrupoService
            .aceitar(usuario)
            .then(response => {
                console.log(response);
            });
    }

    function recusar(usuario) {
        if (isUndefinedOrNull(usuario)) {
            console.log('undefined or null');
            return;
        }
        GrupoService
            .recusar(usuario)
            .then(response => {
                console.log(response);
            });
    }

    function sair(grupo) {
        if (isUndefinedOrNull(grupo)) {
            console.log('undefined or null');
            return;
        }
        GrupoService
            .sair(grupo)
            .then(response => {
                console.log(response);
            });
    }

    function remover(usuarioGrupo) {
        if (isUndefinedOrNull(usuarioGrupo)) {
            console.log('undefined or null');
            return;
        }
        GrupoService
            .remover(usuarioGrupo)
            .then(response => {
                console.log(response);
            })
    }

    function isUndefinedOrNull(object) {
        return (angular.isUndefined(object) || object === null);
    }
}]);