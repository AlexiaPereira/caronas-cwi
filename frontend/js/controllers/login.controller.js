modulo.controller('LoginController', ['$scope', function ($scope) {

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
        gapi.load('auth2', function () {
            auth2 = gapi.auth2.init({
                client_id: 'KEY.apps.googleusercontent.com',
                fetch_basic_profile: false,
                scope: 'profile'
            })
            // console.log(auth2);
            // Sign the user in, and then retrieve their ID.
            auth2.signIn().then(function (response) {
                if (response.Zi) {
                    console.log('Welcome!  Fetching your information.... ');
                    console.log(response);
                    $scope.idGoogle = response.w3.Eea;
                }
            });
        });
    }

    function GoogleLogout() {
        var auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(function () {
            console.log('User signed out.');
        });
    }


}]);