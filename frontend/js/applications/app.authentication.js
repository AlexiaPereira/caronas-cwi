// FACEBOOK
window.fbAsyncInit = function () {
    FB.init({
        appId: 'KEY',
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

// GOOGLE
GoogleInit();
function GoogleInit() {
    let auth2;
    gapi.load('auth2', function () {
        auth2 = gapi.auth2.init({
            client_id: 'KEY.apps.googleusercontent.com',
            fetch_basic_profile: false,
            scope: 'profile'
        })
    })
    return auth2;
}