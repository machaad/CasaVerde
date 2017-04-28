mainApp.controller('AppCtrl', ['$scope', '$mdBottomSheet','$mdSidenav', '$mdDialog','$mdMedia','$state','Menu','authenticationService', function($scope, $mdBottomSheet, $mdSidenav, $mdDialog,$mdMedia,$state,Menu,authenticationService){
    $scope.toggleSidenav = function(menuId) {
        $mdSidenav(menuId).toggle();
    };

    $scope.$on('toggleMenu',function(evt,menuId){
        $mdSidenav(menuId).toggle();
    });

    $scope.go = function(state){
        console.log(state);
        $state.go(state);
    };
    $scope.logout = function(){
        authenticationService.logout();
    }
    $scope.data = Menu.get();
    //$scope.menu = [
    //    {
    //        link : '',
    //        title: 'Dashboard',
    //        icon: 'dashboard'
    //    },
    //    {
    //        link : '',
    //        title: 'Friends',
    //        icon: 'group'
    //    },
    //    {
    //        link : '',
    //        title: 'Messages',
    //        icon: 'message'
    //    }
    //];



}]);