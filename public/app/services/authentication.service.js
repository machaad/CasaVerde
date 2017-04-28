
var authService = ['$resource','$localStorage','$location','$window','$timeout',function($resource,$localStorage,$location,$window,$timeout) {
    var storageTimeout = 500;
    var authenticationService = {};
    var resource = $resource("/api/v1/login");
    authenticationService.doLogin = function (email,password,callback){
        var user = {emailAddress : email,password:  password};
        resource.save(user,
            function success(data, headers ) {
                $localStorage.authToken = data.authToken;
                callback(data);
                //El timeout es necesario para que el browser termine de guardar en local storage
                // antes de cambiar de url
                $timeout(function(){
                    authenticationService.redirectToApp();
                },storageTimeout);

            },
            function error(data, headers){
                delete $localStorage.authToken;
                callback(data,headers);
            });
    }
    authenticationService.logout = function(){
        delete $localStorage.authToken;
        //El timeout es necesario para que el browser termine de guardar en local storage
        // antes de cambiar de url
        $timeout(function(){
            $window.location.replace('/signIn');
        },storageTimeout);
    }
    authenticationService.isLoggedIn = function(){
        return $localStorage.authToken;
    }
    authenticationService.redirectToApp = function(){
        $window.location.replace('/app');
    }
    return authenticationService;
}]

mainApp.factory('authenticationService', authService);
signInApp.factory('authenticationService', authService);

mainApp.service('testToaster',['toaster',function(toastr){
    this.error = function(text,title){
        toastr.error(error.module +"." + error.field + "." +error.message,"Error" );

    }
}]);
mainApp.factory('authInterceptor', ['$rootScope', '$q', '$window','$localStorage','$timeout','toaster',function ($rootScope, $q, $window,$localStorage,$timeout,toaster) {
    return {
        request: function (config) {
            config.headers = config.headers || {};
            if ($localStorage.authToken) {
                config.headers.Authorization =  $localStorage.authToken;
            }
            return config;
        },
        responseError: function (response) {

            if (response.status === 401) {
                // handle the case where the user is not authenticated
                delete $localStorage.authToken;
                $timeout(function(){
                    $window.location.replace('signIn');
                },1000);
            }else if(response.status !== 200 && response.data && response.data.errors){
                for(var i in response.data.errors){
                    var error = response.data.errors[i];
                    toaster.pop('error',"Error",error.module + "." +error.message +"." + error.field);
                }

            }
            return response || $q.when(response);
        }
    };
}]);

mainApp.config(function ($httpProvider) {
    $httpProvider.interceptors.push('authInterceptor');
});
