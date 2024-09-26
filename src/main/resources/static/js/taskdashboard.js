var app = angular.module('books', ['ngRoute']);

app.controller('tasks_controller', function ($scope, $http) {
    fetch('/api/v1/tasks/find/all', { // Or any other protected endpoint
        method: 'GET',
        credentials: 'include' // Important for sending cookies
    })
    .then(response => {
        if (!response.ok) {
            // Redirect to login page if not authenticated
            window.location.href = '/login.html';
        } else {
            // User is authenticated, proceed with loading task data
            return response.json();
        }
    })
    $scope.get_tasks = function () {
        $http({
            method: "GET",
            url: "/api/v1/tasks/find/all"
        }).then(function mySuccess(response) {
            $scope.tasks_data = response.data;
        }, function myError(response) {
            $scope.myWelcome = response.statusText;
        });
    };

    $scope.delete_task = function (id) {
        $http({
            method: "DELETE",
            url: "/api/v1/tasks/delete/id/"+ id
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
