modulo.controller('loginController', ['$scope', function ($scope) {

    $scope.name = 'Por favor, autentique-se.';
    
    $scope.FacebookLogin = FacebookLogin;

    function FacebookLogin() {
        FB.login(function (response) {
            if (response.authResponse) {
                console.log('Welcome!  Fetching your information.... ');
                console.log(response);
                FB.api('/me', function (response) {
                    console.log(response);
                    console.log('É bom vê-lo aqui, ' + response.name + '.');

                    // Obter token de autenticação
                    var accessToken = FB.getAuthResponse().accessToken;
                    console.log(`Token: ${accessToken}`);
                });
            } else {
                console.log('User cancelled login or did not fully authorize.');
            }
        });
    }
}]);