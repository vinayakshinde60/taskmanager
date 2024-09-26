var app = angular.module('books', ['ngRoute']);

app.controller('books_controller', function ($scope, $http) {
    $scope.get_books = function () {
        $http({
            method: "GET",
            url: "/api/v1/book/reactive/find/all"
        }).then(function mySuccess(response) {
            $scope.books_data = response.data;
        }, function myError(response) {
            $scope.myWelcome = response.statusText;
        });
    };

    $scope.delete_book = function (id) {
        $http({
            method: "DELETE",
            url: "/api/v1/book/find/id/" + id
        }).then(function mySuccess(response) {
            location.reload();
        }, function myError(response) {
            $scope.myWelcome = response.statusText;
        });
    }
});


//AngularJS not using this router
app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "../index.html"
        })
        .when("/save_book", {
            templateUrl: "../save_book.html"
        })
        .when("/edit_book/:id", {
            templateUrl: "../edit_book.html"
        })
        .when("/pj", {
            templateUrl: "../books.html"
        });
});
