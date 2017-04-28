var baseUrl = "api/v1/";

mainApp.factory('ToDo',['$resource',function($resource){
    return $resource(baseUrl + 'crud/todo/:id',{id:'@_id'},{
        update: {
            method: 'PUT'
        }
    });
}]);
mainApp.factory('SecurityRole',['$resource',function($resource){
    return $resource(baseUrl + 'crud/securityRole/:id',{id:'@_id'},{
        update: {
            method: 'PUT'
        }
    });
}]);
mainApp.factory('SecurityPermission',['$resource',function($resource){
    return $resource(baseUrl + 'crud/securityPermission/:id',{id:'@_id'},{
        update: {
            method: 'PUT'
        }
    });
}]);
mainApp.factory('User',['$resource',function($resource){
    return $resource(baseUrl + 'crud/user/:id',{id:'@_id'},{
        update: {
            method: 'PUT'
        }
    });
}]);

mainApp.factory('StockSearch',['$resource',function($resource){
    return $resource(baseUrl + 'search/stock');
}]);

mainApp.factory('StockTagSearch',['$resource',function($resource){
    return $resource(baseUrl + 'tag/stock');
}]);
mainApp.factory('Menu',['$resource',function($resource){
    return $resource(baseUrl + 'menu');
}]);