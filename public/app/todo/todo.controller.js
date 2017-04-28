mainApp.controller('todoListController',['$scope','$state','$window','$q','popupService', 'authenticationService','ToDo','$mdDialog',
    function($scope,$state,$window,$q,popupService,authenticationService,ToDo,$mdDialog){
        $scope.toggleSidenav = function(menuId) {
            $scope.$emit('toggleMenu',menuId);
        };


        $scope.delete=function(){
            ToDo.delete({id:$scope.selected[0].id},function(){
                $scope.todos = ToDo.query();
            });
        };

        $scope.showForm = function(ev,selectedValues){
            $mdDialog.show({
                controller: 'todoFormController',
                templateUrl: 'assets/app/todo/todo.form.html',
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
                    //nothing
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
        $scope.onPageChange = function(){
            var deferred = $q.defer();
            ToDo.get($scope.query,function(res){
                $scope.todos = res.data;
                $scope.count = res.count;
                deferred.resolve(res.data);
            });
            return deferred.promise;
        }
        $scope.onPageChange();
}]);

mainApp.controller('todoFormController',['$scope','$mdDialog','ToDo','selectedValue', function($scope,$mdDialog,ToDo,selectedValue) {
    var isNew = true;
    if(selectedValue){
        $scope.task=angular.copy(selectedValue,{});
    }else{
        isNew = false;
        $scope.task=new ToDo();
    }

    $scope.submit = function(){
        if(isNew){
            ToDo.update({id:$scope.task.id},$scope.task,function(){
                $mdDialog.hide(true);
            });
        }else{
            $scope.task.$save(function(){
                $mdDialog.hide(true);
            });
        }

    }
    $scope.cancel = function() {
        $mdDialog.cancel();
    };

}]);