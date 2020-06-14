angular.module("bills",[])
    .controller("BillsCtrl", function ($scope, $http) {
        $scope.bills = [];
        $scope.accounts = [];
        $scope.first_name = "";
        $scope.last_name = "";
        $scope.auth = {};
        let resultMessageEl = document.getElementById('resultMessage');
        let inputBillId = document.getElementById('inputBillId');
        let inputAccountId = document.getElementById('inputAccountId');
        inputBillId.addEventListener('input', () => {
                inputBillId.style.color = 'black';
                $scope.message = '';
        });
         $scope.getUserBills = function(){
                     $http({
                         method: "GET",
                         url: "/bills/bills",
                         headers: { "Content-Type" : "application/json" }
                     }).then(
                         function(data) {
                            $scope.first_name = data.data.firstName;
                            $scope.last_name = data.data.lastName;
                            $scope.bills = data.data.bills;
                            $scope.accounts = data.data.accounts;
                         },
                         function(error) {
                             console.log("BillsCtrl error")
                         }
                     );
         }
         $scope.sendForm = function(auth){
                     $http({
                         method: "POST",
                         url: "/bills/payment",
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
    });