mainApp.controller('toolbarController',['$scope','$state','$window','$timeout',
    function($scope,$state,$window,$timeout){
        $scope.menuItems = [];
        //$timeout(function(){
        //    $scope.menuItems = [1,2,3];
        //},3000);
        $scope.$on("menuChange",function(evt){
           console.log(evt);
        });
    }]);
