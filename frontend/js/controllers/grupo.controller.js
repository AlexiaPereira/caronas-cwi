angular.module('app').controller('GrupoController', ['$scope', 'GrupoService','SolicitacoesService', 'authService', 'UsuarioGrupoService',
function ($scope, GrupoService, SolicitacoesService, authService, UsuarioGrupoService) {
  $scope.aceitar = aceitar;
  $scope.recusar = recusar;
  $scope.removerGrupo = removerGrupo;
  $scope.removerMembro = removerMembro;
  $scope.remover = remover;
  $scope.aceitar = aceitar;
  $scope.recusar = recusar;
  $scope.souDonoDoGrupo = souDonoDoGrupo;

  listarGrupos();
  buscarSolicitacoesPendentes();

  function listarGrupos() {
    GrupoService
    .listarGrupos()
    .then(response => {
      let grupos = response.data;      $scope.gruposMotorista = grupos.filter(grupo =>
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
      function removerGrupo(grupo) {
        swal({          title: "Você tem certeza?",          text: "Ao excluir o grupo você também excluirá a rotina contida nele!",          type: "warning",          showCancelButton: true,          confirmButtonColor: "#DD6B55",          confirmButtonText: "Sim, excluir grupo!",          cancelButtonText: "Não, cancelar exclusão!",          closeOnConfirm: false,          closeOnCancel: false        },
        function(isConfirm){          if (isConfirm) {            GrupoService            .remover(grupo.idGrupo)            .then(response => {              swal("Excluído!", "Seu grupo '" + grupo.nome + "' foi excluído.", "success");              listarGrupos();            })
          } else {            swal("Cancelado", "Seu grupo permanece intacto", "error");          }        });
      }

      function removerMembro(usuarioGrupo) {        UsuarioGrupoService        .removerMembro(usuarioGrupo.idUsuarioGrupo)        .then(response => {          swal({            title: "Sucesso!",            text: usuarioGrupo.usuario.nome + ' foi removido do seu grupo',            type: "success",            timer: 2000,            showConfirmButton: false,            animation: "slide-from-top"          });          listarGrupos();        })      }

      function remover(grupo) {        if (isUndefinedOrNull(grupo)) {          console.log('undefined or null');          return;        }        UsuarioGrupoService        .remover(grupo)        .then(response => {          listarGrupos();        })      }
      function buscarSolicitacoesPendentes() {        SolicitacoesService.buscarPendentes().then(res => {          $scope.solicitacoes = res.data        });      }

      function aceitar(solicitacao) {        SolicitacoesService.aceitar(solicitacao).then(res => {          swal({            title: "Sucesso!",            text: solicitacao.usuarioDono.nome + ' agora está no seu grupo!',            type: "success",            timer: 2000,            showConfirmButton: false,            animation: "slide-from-top"          });          buscarSolicitacoesPendentes();          listarGrupos();        });      }
      function recusar(solicitacao) {        SolicitacoesService.recusar(solicitacao.idSolicitacao).then(res =>{          swal({            title: 'Solicitação recusada',            type: "success",            timer: 2000,            showConfirmButton: false,            animation: "slide-from-top"          });          buscarSolicitacoesPendentes();        })      }
      function isUndefinedOrNull(object) {        return (angular.isUndefined(object) || object === null);      }
      function souDonoDoGrupo(membro) {        return membro.usuario.idAutorizacao === authService.getUsuario().username;      }
    }]);
