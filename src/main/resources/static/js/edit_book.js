const app = angular.module('edit_book', ['ngRoute']);
app.controller('edit_book_controller', ['$scope', '$http', '$location', function ($scope, $http, $location) {
    $scope.validate_and_update_book = function () {
        $http({
            method: "PUT",
            url: "/api/v1/book/update",
            data: JSON.stringify({id: $scope.id, title: $scope.title, cost: $scope.cost, numberOfPages: $scope.numberOfPages, author: $scope.author})

        }).then(function mySuccess(response) {
            $scope.form_error = false;
            $scope.form_success = true;
            $scope.form_success_message = "Successfully updated Book information";
            $scope.form_error_message = "";
            $scope.id = response.data.id;
            //location.reload()
        }, function myError(response) {
            $scope.form_error = true;
            $scope.form_success = false;
            $scope.form_success_message = "";
            $scope.form_error_message = "Unable to save Book, Please check the information";
        });
    };

    $scope.get_book_details = function () {
        $scope.id = getIdFromUrl($location.absUrl());
        $http({
            method: "GET",
            url: "/api/v1/book/find/id/" + $scope.id

        }).then(function mySuccess(response) {
            $scope.form_error = false;
            $scope.form_success = false;
            $scope.id = response.data.id;
            $scope.title = response.data.title;
            $scope.cost = response.data.cost;
            $scope.numberOfPages = response.data.numberOfPages;
            $scope.author = response.data.author;

        }, function myError(response) {
            $scope.form_error = true;
            $scope.form_success = false;
            $scope.form_success_message = "";
            $scope.form_error_message = "Unable to load Book information, Please try again";
        });
    };

    function getIdFromUrl(url) {
        var regex = /id=(.*)#/;
        var id = null;
        id = regex.exec(url);
        if (id == null) {
            regex = /id=(.*)/;
            return regex.exec(url)[1];
        }

        return id[1];
    }
}]);


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
