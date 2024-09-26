var app = angular.module('index', ['ngRoute']);

app.controller('index',function($scope,$http)
{
});


//AngularJS not using this router
app.config(function($routeProvider)
{
    $routeProvider
        .when("/", {
            templateUrl : "../index.html"
        })
        .when("/save_book", {
            templateUrl : "../save_book.html"
        })
        .when("/edit_book/:id", {
            templateUrl : "../edit_book.html"
        })
        .when("/pj", {
            templateUrl : "../books.html"
        });
});
