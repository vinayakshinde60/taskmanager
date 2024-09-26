var app = angular.module('save_book', ['ngRoute']);
app.controller('save_book_controller', function ($scope, $http, $location) {
    $scope.validate_and_save_book = function () {
        $http({
            method: "POST",
            url: "/api/v1/book/save",
            data: JSON.stringify({title: $scope.title, cost: $scope.cost, numberOfPages: $scope.numberOfPages, author: $scope.author})

        }).then(function mySuccess(response) {
            $scope.form_error = false;
            $scope.form_success = true;
            $scope.id = response.data.id;

        }, function myError(response) {
            $scope.form_error = true;
            $scope.form_success = false;
        });
    };
});

