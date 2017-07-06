var modulo = angular.module('appModule', ['ngRoute']);

modulo.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
    .when('/login', {
        controller: 'loginController',
        templateUrl: '/template/login.html'
    })
    .otherwise('/login');
}]);

// FACEBOOK LOGIN

window.fbAsyncInit = function () {
    FB.init({
        appId: 'your-app-id',
        autoLogAppEvents: true,
        xfbml: true,
        version: 'v2.9'
    });
    FB.AppEvents.logPageView();
};

(function (d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) { return; }
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));