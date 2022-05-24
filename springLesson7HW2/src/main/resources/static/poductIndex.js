angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/product/all')
            .then(function (response) {
                $scope.productList = response.data;
            });
    };

    $scope.deleteStudent = function (productId) {
            $http.get(contextPath + '/products/delete/' + productId)
                .then(function (response) {
                    $scope.loadProducts();
                });
        }

    $scope.addProductIntoCart = function (productId){
            $http({
                url: contextPath + '/cart/add',
                method: 'GET',
                params: {
                    productId: productId
                }
            }).then(function (response){
                $scope.loadCart();
            });
    };

    $scope.loadProducts();

});