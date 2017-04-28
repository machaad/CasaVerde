//ROUTE
mainApp.config(['$stateProvider','$urlRouterProvider',function($stateProvider,$urlRouterProvider){
    var createState = function(prefix){
        $stateProvider.state(prefix,{
            url:'/' + prefix,
            templateUrl:'assets/app/'+ prefix+ '/' + prefix + '.list.html',
            controller: prefix + 'ListController',
            ncyBreadcrumb: {
                label: 'view.' + prefix + '.title'
            }
        })
    };
    createState('todo');
    createState('securityRole');
    createState('user');
    createState('stock');
    createState('withdraw');
    createState('income');


    // when there is an empty route, redirect to /index
    $urlRouterProvider.when('', '/todo');

    // You can also use regex for the match parameter
    $urlRouterProvider.otherwise('/todo');
}]);

mainApp.run(['$rootScope', '$location', '$localStorage','$window',
    function($rootScope, $location,$localStorage,$window){
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/register']) === -1;
            var loggedIn = $localStorage.authToken;
            if (restrictedPage && !loggedIn) {
                $window.location.href = '/signIn';
            }

        });
        $rootScope.$on('$locationChangeSuccess', function() {
            $rootScope.actualLocation = $location.path();
        });

        $rootScope.$watch(function () {return $location.path()}, function (newLocation, oldLocation) {
            if($rootScope.actualLocation === newLocation) {

            }
        });
}]);
signInApp.config(['$stateProvider','$urlRouterProvider',function($stateProvider,$urlRouterProvider){
    $stateProvider.state('signIn', {
        url : '/',
        controller: 'loginController',
        templateUrl: 'assets/app/login/login.view.html'
    });
    $urlRouterProvider.when('', '/');
    $urlRouterProvider.otherwise('/');
}]);