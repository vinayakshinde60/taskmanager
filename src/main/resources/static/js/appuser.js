var app = angular.module('appuser', ['ngRoute']);
app.controller('save_appuser_controller', function ($scope, $http, $location) {
    $scope.validate_and_save_appuser = function () {
        $http({
            method: "POST",
            url: "/api/v1/appuser/saveuser",
            data: JSON.stringify({username: $scope.username, password: $scope.password})
           
        }).then(function mySuccess(response) {
            var res=JSON.stringify(response);
          //  alert(res);
            $scope.form_error = false;
            $scope.form_success = true;
            $scope.id = response.data.id;

        }, function myError(response) {
            var res=JSON.stringify(response);
   alert(res);
            $scope.form_error = true;
            $scope.form_success = false;
        });
    };
});

