mainApp.controller('securityRoleListController',['$scope','$state','$window','$q','popupService', 'authenticationService','SecurityRole','$mdDialog',
    function($scope,$state,$window,$q,popupService,authenticationService,DataService,$mdDialog){
        $scope.toggleSidenav = function(menuId) {
            $scope.$emit('toggleMenu',menuId);
        };

        $scope.delete=function(){
            DataService.delete({id:$scope.selected[0].id},function(){
                $scope.list = DataService.query();
            });
        };

        $scope.showForm = function(ev,selectedValues){
            $mdDialog.show({
                controller: 'securityRoleFormController',
                templateUrl: 'assets/app/securityRole/securityRole.form.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose:true,
                locals : {
                    selectedValue : selectedValues.length == 1 ?  selectedValues[0] : null
                }
            })
                .then(function(answer) {
                    $scope.onPageChange();
                }, function() {
                    //Nothing
                });
        };

        $scope.logout = function logout(){
            authenticationService.logout();
        };

        $scope.query = {
            order: '',
            limit: 10,
            page: 1
        };
        $scope.onPageChange = function(page, limit){
            var deferred = $q.defer();
            DataService.get($scope.query,function(res){
                $scope.list = res.data;
                $scope.count = res.count;
                deferred.resolve(res.data);
            });
            return deferred.promise;
        };
        $scope.onPageChange();
    }]);

mainApp.controller('securityRoleFormController',['$scope','$mdDialog','SecurityRole','SecurityPermission','selectedValue', function($scope,$mdDialog,DataService,SecurityPermission,selectedValue) {
    $scope.isNew = false;
    console.log(selectedValue);
    if(selectedValue){
        $scope.entity= DataService.get({id: selectedValue.id});
    }else{
        $scope.isNew = true;
        $scope.entity=DataService.get({id: -1});
    }

    $scope.submit = function(){
        if($scope.isNew){
            $scope.entity.$save(function(){
                $mdDialog.hide(true);
            });
        }else{
            DataService.update({id:$scope.entity.id},$scope.entity,function(){
                $mdDialog.hide(true);
            });
        }

    }
    $scope.cancel = function() {
        $mdDialog.cancel();
    };

}]);