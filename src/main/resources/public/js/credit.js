angular.module("credit",[])
    .controller("CreditCtrl", function ($scope, $http) {
        $scope.first_name = "";
        $scope.last_name = "";
        $scope.auth = {};
        let resultMessageEl = document.getElementById('resultMessage');
        let inputLimitId = document.getElementById('inputLimit');
        let divCredit =  document.getElementById('divCreditExists');
        let divLimit = document.getElementById('divNewCredit');
        divCredit.hidden = true;
        divLimit.hidden = false;
        inputLimitId.addEventListener('input', () => {
                inputLimitId.style.color = 'black';
                $scope.message = '';
        });
         $scope.getUserCredit = function(){
                     $http({
                         method: "GET",
                         url: "/credit/credit",
                         headers: { "Content-Type" : "application/json" }
                     }).then(
                         function(data) {
                            $scope.first_name = data.data.firstName;
                            $scope.last_name = data.data.lastName;
                            $scope.credit = data.data.credit;
                            $scope.creditExists = data.data.creditExists;
                            if (data.data.creditExists){
                                divCredit.hidden = false;
                                divLimit.hidden = true;
                            }
                         },
                         function(error) {
                             console.log("BillsCtrl error")
                         }
                     );
         }
         $scope.sendForm = function(auth){
                     $http({
                         method: "POST",
                         url: "/credit/limit",
                         data: $.param(auth),
                         headers: { "Content-Type" : "application/x-www-form-urlencoded" }
                     }).then(
                         (data) => {
                             resultMessageEl.style.color = 'green';
                             $scope.message = "Credit request was sent to bank";
                             inputBillId.value = '';
                             inputAccountId.value = '';
                         },
                         (error) => {
                             resultMessageEl.style.color = 'red';
                             inputBillId.value = '';
                             inputAccountId.value = '';
                             $scope.message = "Request was not sent"
                         }
                     );
                 }
    });