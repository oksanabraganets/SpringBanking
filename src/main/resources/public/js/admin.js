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
        $scope.sendForm = function(auth){
                 $http({
                     method: "POST",
                     url: "/admin/approve",
                     data: $.param(auth),
                     headers: { "Content-Type" : "application/x-www-form-urlencoded" }
                             }).then(
                                 (data) => {
                                     resultMessageEl.style.color = 'green';
                                     $scope.message = "Payment was successful";
                                     inputBillId.value = '';
                                     inputAccountId.value = '';
                                 },
                                 (error) => {
                                     resultMessageEl.style.color = 'red';
                                     inputBillId.value = '';
                                     inputAccountId.value = '';
                                     $scope.message = "Not enough money to pay"
                                 }
                             );
                         }

    }]);