angular.module('app').controller('GrupoController', ['$scope', 'GrupoService',
'SolicitacoesService', 'toastr', 'authService', 'UsuarioGrupoService',
function ($scope, GrupoService, SolicitacoesService, toastr, authService,
  UsuarioGrupoService) {

    $scope.aceitar = aceitar;
    $scope.recusar = recusar;
    $scope.removerGrupo = removerGrupo;
    $scope.remover = remover;
    $scope.aceitar = aceitar;
    $scope.recusar = recusar;

    listarGrupos();
    buscarSolicitacoesPendentes();

    $scope.gruposMotorista = [];
    $scope.gruposPassageiro = [];

    function listarGrupos() {
      GrupoService
      .listarGrupos()
      .then(response => {
        let grupos = response.data;

        $scope.gruposMotorista = grupos.filter(grupo =>
          grupo.rotina.usuario.idAutorizacao === authService.getUsuario().username);

        $scope.gruposPassageiro = grupos.filter(grupo =>
          $scope.gruposMotorista.indexOf(grupo) === -1);

      });
    }

    function buscarGrupo(idGrupo) {
      GrupoService
      .buscarGrupo(idGrupo)
      .then(response => {
      });
    }

    function aceitar(solicitacaoDTO) {
      if (isUndefinedOrNull(solicitacaoDTO)) {
        return;
      }
      SolicitacoesService
      .aceitar(solicitacaoDTO)
      .then(response => {
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
        listarGrupos();
      })
    }

    function remover(grupo) {
      debugger;
      if (isUndefinedOrNull(grupo)) {
        console.log('undefined or null');
        return;
      }
      UsuarioGrupoService
      .remover(grupo)
      .then(response => {
        listarGrupos();
      })
    }

    function buscarSolicitacoesPendentes() {
      SolicitacoesService.buscarPendentes().then(res => {
        $scope.solicitacoes = res.data
        console.log($scope.solicitacoes);
      });
    }

    function aceitar(solicitacao) {
      SolicitacoesService.aceitar(solicitacao).then(res => {
        toastr.success(solicitacao.usuarioDono.nome + ' agora está no seu grupo!');
        buscarSolicitacoesPendentes();
        listarGrupos();
      });
    }

    function recusar(solicitacao) {
      SolicitacoesService.recusar(solicitacao.idSolicitacao).then(res =>{
        toastr.success('Solicitação recusada')
        buscarSolicitacoesPendentes();
      })
    }

    function isUndefinedOrNull(object) {
      return (angular.isUndefined(object) || object === null);
    }
  }]);
