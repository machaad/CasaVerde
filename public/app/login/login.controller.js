signInApp.controller('loginController', ['$scope','$location','authenticationService',
    function ($scope,$location,authenticationService) {
        $scope.email = 'user1@demo.com';
        $scope.password = 'password';
        $scope.user ={
            email : "user1@demo.com",
            password : "password"
        };
        $scope.login = function(){
            authenticationService.doLogin($scope.user.email,$scope.user.password,
            function(data,error){
                if(error){
                    //flashService.Error(error);
                }else{
                    //$location.path('/');
                }
            }
            );
        }

    //$scope.todos = [];
    //$scope.getTodo = function(){
    //    todos.query({},
    //        function success(data, status, headers, config){
    //            $scope.todos = data;
    //            console.log(data);
    //        },
    //        function error(data, status, headers, config){
    //            $scope.todos = [];
    //            console.log(data);
    //        }
    //    );
    //
    //}

}]);
