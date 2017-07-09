angular.module('app').controller('LoginController', ['$scope', 'authservice', function ($scope, authService) {

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
                FB.api('/me?fields=id,name,email,permissions', function (response) {
                    console.log(response);
                    console.log('É bom vê-lo aqui, ' + response.name + '.');
                    console.log('É bom vê-lo aqui, ' + response.email + '.');
                    console.log('É bom vê-lo aqui, ' + response.permissions + '.');

                    // Obter token de autenticação
                    var accessToken = FB.getAuthResponse().accessToken;
                    console.log(`Token: ${accessToken}`);
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
            }
        });
    }

    function GoogleLogout() {
        let auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(function () {
            console.log('User signed out.');
        });
    }

    function logar(usuario) {
        authService.login(usuario)
            .then(
            function (response) {
                console.log(response);
                toastr.success('Login realizado com sucesso.');
            },
            function (response) {
                console.log(response);
                toastr.error('Falha ao logar.');
            });
    };
}]);