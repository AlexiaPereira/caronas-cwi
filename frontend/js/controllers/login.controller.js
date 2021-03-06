angular.module('app').controller('LoginController', ['$scope', 'authService', 'MapService',
'UsuarioService', '$window', function ($scope, authService, MapService, UsuarioService, $window) {
  $scope.name = 'Por favor, autentique-se.';
  $scope.FacebookLogin = FacebookLogin;
  $scope.FacebookLogout = FacebookLogout;

  $scope.GoogleLogin = GoogleLogin;
  $scope.GoogleLogout = GoogleLogout;


  function FacebookLogin() {
    FB.login(function (response) {
      if (response.authResponse) {
        console.log('Welcome!  Fetching your information.... ');
        console.log(response);
        FB.api('/me?fields=id,name,email,picture,permissions', function (response) {
          console.log(response);
          console.log('É bom vê-lo aqui, ' + response.name + '.');
          console.log('É bom vê-lo aqui, ' + response.email + '.');
          console.log('É bom vê-lo aqui, ' + response.permissions + '.');

          // Obter token de autenticação
          var accessToken = FB.getAuthResponse().accessToken;
          console.log(`Token: ${accessToken}`);

          let usuario = { nome: response.name, email: response.email, idAutorizacao: response.id, senha: response.id, urlFoto: response.picture.data.url };
          console.log(usuario);

          criarUsuario(usuario);
        });
      } else {
        console.log('User cancelled login or did not fully authorize.');
      }
    });
  }

  function FacebookLogout() {
    FB.logout(response => {
      console.log(response);
    });
  }

  function GoogleLogin() {
    let auth2 = GoogleInit();
    // Sign the user in, and then retrieve their ID.
    auth2.signIn().then(function (response) {
      if (response.Zi) {
        console.log('Welcome!  Fetching your information.... ');
        console.log(response);
        $scope.idGoogle = response.w3.Eea;

        let usuario = { nome: response.w3.ig, email: response.w3.U3, idAutorizacao: response.w3.Eea, senha: response.w3.Eea, urlFoto: response.w3.Paa };
        console.log(usuario);

        criarUsuario(usuario);
      }
    });
  }

  function GoogleLogout() {
    let auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
  }

  function criarUsuario(usuario) {
    UsuarioService
    .criar(usuario)
    .then(response => {
      console.log(response);
      logar(usuario);
    });
  }

  function logar(usuario) {
    authService.login(usuario)
    .then(
      function (response) {
        swal({
          title: "Login realizado com sucesso!",          text: 'Bem vindo, '  + usuario.nome + '.',          timer: 2000,          showConfirmButton: false        });        localStorage.setItem('nome', usuario.nome);
        localStorage.setItem('foto', usuario.urlFoto);
        $window.location.reload();
      },
      function (response) {
        console.log(response);
        swal({          title: "Erro ao logar!",          text: "Houve algum erro com o login, por favor tente novamente!",          type: "error",          confirmButtonText: "OK"        });
      });
    };
  }]);