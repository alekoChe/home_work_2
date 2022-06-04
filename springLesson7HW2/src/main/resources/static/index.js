angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products')
            .then(function (response) {
                console.log(response.data);
                $scope.productList = response.data;
            });
    };

    $scope.selectionProductInPriceRange = function (min, max) {
        console.log($scope.productsBetween)
        $http({
            url: contextPath + "/products/between",
            method: 'get',
            params: {
                min: $scope.productsBetween.min,
                max: $scope.productsBetween.max
            }
        }).then(function (response){
            //$scope.loadProducts();  // выведет весь список
            $scope.productList = response.data;
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
    $scope.deleteStudent = function (studentId) {
            $http.get(contextPath + '/students/delete/' + studentId)
                .then(function (response) {
                    $scope.loadStudents();
                });
    }

    $scope.deleteProduct = function (productId) {
            console.log($scope.deleteProductById)
            $http({
                url: contextPath + "/products/deleteById",
                method: 'get',
                params: {
                    productId: $scope.deleteProductById.productId
                }
            }).then(function (response){
                $scope.loadProducts();
            });
    }

    $scope.createProductJson = function (){
            console.log($scope.newProductJson);
            $http.post(contextPath + '/products', $scope.newProductJson)
                .then(function (response) {
                    $scope.loadProducts()
                });
    }

    $scope.loadProducts();

});