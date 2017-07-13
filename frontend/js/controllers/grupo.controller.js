angular.module('app').controller('GrupoController', ['$scope', 'GrupoService',
'SolicitacoesService', function ($scope, GrupoService, SolicitacoesService) {

  $scope.aceitar = aceitar;
  $scope.recusar = recusar;
  $scope.removerGrupo = removerGrupo;
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
      for (var grupo of grupos) {
        if (!grupo.passageiro) {
          $scope.gruposMotorista.push(grupo);
        } else {
          $scope.gruposPassageiro.push(grupo);
        }
      }
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
      alert(solicitacao.usuarioDono.nome + ' agora está no seu grupo!');
      buscarSolicitacoesPendentes();
      listarGrupos();
    });
  }

  function recusar(solicitacao) {
    SolicitacoesService.recusar(solicitacao.idSolicitacao).then(res =>{
      alert('Solicitação recusada')
      buscarSolicitacoesPendentes();
    })
  }

  function isUndefinedOrNull(object) {
    return (angular.isUndefined(object) || object === null);
  }
}]);
