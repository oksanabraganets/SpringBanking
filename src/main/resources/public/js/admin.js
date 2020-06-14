angular.module("admin",[])
    .controller("AdminCtrl", ["$scope", "$http", function ($scope, $http) {
        $scope.requests = [];
        $scope.first_name = "";
        $scope.last_name = "";
        $scope.getRequests = function(){
            $http({
                method: "GET",
                url: "/admin/requests",
                headers: { "Content-Type" : "application/json" }
            }).then(
                function(data) {
                    $scope.first_name = data.data.firstName;
                    $scope.last_name = data.data.lastName;
                    $scope.requests = data.data.requests;
                },
                function(error) {
                }
            );
        }
    }]);