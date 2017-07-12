angular.module('app').controller('GrupoController', ['$scope', 'GrupoService', 'UsuarioGrupoService', 'SolicitacaoService', function ($scope, GrupoService, SolicitacaoService, UsuarioGrupoService) {

    $scope.listar = listar;
    $scope.aceitar = aceitar;
    $scope.recusar = recusar;
    $scope.remover = remover;

    function buscarGrupo(idGrupo) {
        GrupoService
            .buscarGrupo(idGrupo)
            .then(response => {
                console.log(response);
            });
    }

    function aceitar(solicitacaoDTO) {
        if (isUndefinedOrNull(solicitacaoDTO)) {
            console.log('undefined or null');
            return;
        }
        SolicitacoesService
            .aceitar(solicitacaoDTO)
            .then(response => {
                console.log(response);
            });
    }

    function recusar(idSolicitacao) {
        if (isUndefinedOrNull(idSolicitacao)) {
            console.log('undefined or null');
            return;
        }
        SolicitacoesService
            .recusar(idSolicitacao)
            .then(response => {
                console.log(response);
            });
    }

    function remover(idUsuarioGrupo) {
        if (isUndefinedOrNull(idUsuarioGrupo)) {
            console.log('undefined or null');
            return;
        }
        UsuarioGrupoService
            .remover(idUsuarioGrupo)
            .then(response => {
                console.log(response);
            })
    }

    function isUndefinedOrNull(object) {
        return (angular.isUndefined(object) || object === null);
    }
}]);
