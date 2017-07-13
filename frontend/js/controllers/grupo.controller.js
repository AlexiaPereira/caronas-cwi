angular.module('app').controller('GrupoController', ['$scope', 'GrupoService', 'UsuarioGrupoService', 'SolicitacoesService', function ($scope, GrupoService, SolicitacaoService, UsuarioGrupoService) {

    $scope.aceitar = aceitar;
    $scope.recusar = recusar;
    $scope.removerGrupo = removerGrupo;

    listarGrupos();

    $scope.gruposMotorista = [];
    $scope.gruposPassageiro = [];

    function listarGrupos() {
        GrupoService
            .listarGrupos()
            .then(response => {
        debugger;
                let grupos = response.data;
                for (var grupo of grupos) {
                    if (!grupo.passageiro) {
                        $scope.gruposMotorista.push(grupo);
                    } else {
                        $scope.gruposPassageiro.push(grupo);
                    }
                }
                console.log('Motorista' + $scope.gruposMotorista);
                console.log('Passageiro' + $scope.gruposPassageiro);
            });
    }

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

    function removerGrupo(idGrupo) {
        if (isUndefinedOrNull(idGrupo)) {
            console.log('undefined or null');
            return;
        }
        GrupoService
            .remover(idGrupo)
            .then(response => {
                console.log(response);
            })
    }

    function isUndefinedOrNull(object) {
        return (angular.isUndefined(object) || object === null);
    }
}]);
