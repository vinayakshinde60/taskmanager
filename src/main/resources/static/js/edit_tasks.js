const app = angular.module('edit_task', ['ngRoute']);
app.controller('edit_task_controller', ['$scope', '$http', '$location', function ($scope, $http, $location) {
    $scope.validate_and_update_task = function () {
        $http({
            method: "PUT",
            url: "/api/v1/tasks/update", // Updated URL to match your task API
            data: JSON.stringify({
                id: $scope.id, 
                title: $scope.title, 
                description: $scope.description, 
                status: $scope.status
            })

        }).then(function mySuccess(response) {
            $scope.form_error = false;
            $scope.form_success = true;
            $scope.form_success_message = "Successfully updated Task information";
            $scope.form_error_message = "";
            $scope.id = response.data.id;
            //location.reload()
              // 1. Display a success message
        alert('Task updated successfully!'); 
        // 2. Clear the form
        form.reset();
        // 3. Redirect to the task list page
        window.location.href = '/taskdashboard.html'; // Replace with your actual task list page
        }, function myError(response) {
            $scope.form_error = true;
            $scope.form_success = false;
            $scope.form_success_message = "";
            $scope.form_error_message = "Unable to save Task, Please check the information";
        });
    };

    $scope.get_task_details = function () {
        $scope.id = getIdFromUrl($location.absUrl());
        $http({
            method: "GET",
            url: "/api/v1/tasks/find/id/" + $scope.id // Updated URL to match your task API

        }).then(function mySuccess(response) {
            $scope.form_error = false;
            $scope.form_success = false;
            $scope.id = response.data.id;
            $scope.title = response.data.title;
            $scope.description = response.data.description;
            $scope.status = response.data.status;
           

        }, function myError(response) {
            $scope.form_error = true;
            $scope.form_success = false;
            $scope.form_success_message = "";
            $scope.form_error_message = "Unable to load Task information, Please try again";
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
        .when("/save_task", { // Assuming you have a save_task.html
            templateUrl: "../save_task.html" 
        })
        .when("/edit_task/:id", {
            templateUrl: "../edit_task.html"
        })
        .when("/taskdashboard", { // Assuming you have a taskdashboard.html
            templateUrl: "../taskdashboard.html" 
        });
});