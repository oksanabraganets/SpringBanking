angular.module("transfer",[])
    .controller("TransferCtrl", function ($scope, $http) {
        $scope.auth = {};
        $scope.first_name = "";
        $scope.last_name = "";
        $scope.date = "";
        let resultMessageEl = document.getElementById('resultMessage');
        let inputFromId = document.getElementById('inputFromId');
        let inputToId = document.getElementById('inputToId');
        let inputAmount = document.getElementById('inputAmount');
        inputFromId.addEventListener('input', () => {
            inputFromId.style.color = 'black';
            $scope.message = '';
        });
        $scope.sendForm = function(auth){
            $http({
                method: "POST",
                url: "/user/transfer",
                data: $.param(auth),
                headers: { "Content-Type" : "application/x-www-form-urlencoded" }
            }).then(
                (data) => {
                    resultMessageEl.style.color = 'green';
                    $scope.message = "Transfer was successful";
                    inputFromId.value = '';
                    inputToId.value = '';
                    inputAmount.value = '';
                    window.location.reload(false);
                },
                (error) => {
                    resultMessageEl.style.color = 'red';
                    inputFromId.value = '';
                    inputToId.value = '';
                    inputAmount.value = '';
                    $scope.message = "Bad parameters. Money was not transferred"
                }
            );
        }
         $scope.getUserNames = function(){
                     $http({
                         method: "GET",
                         url: "/user/names",
                         headers: { "Content-Type" : "application/json" }
                     }).then(
                         function(data) {
                             $scope.first_name = data.data.firstName;
                             $scope.last_name = data.data.lastName;
                             $scope.accounts = data.data.accounts;
                             $scope.all_accounts = data.data.allAccounts;
                             $scope.date = data.data.date;
                         },
                         function(error) {
                             console.log("userCtrl error")
                         }
                     );
         }
         $scope.setUKR = function(){
            $http({
                method: "GET",
                url: "/locale/ua"
            }).then(
                function(data){
                    window.location.reload(false);
                },
                function(error){
                    console.log("userCtrl error")
                }
            );
         }
         $scope.setENG = function(){
                     $http({
                         method: "GET",
                         url: "/locale/us"
                     }).then(
                         function(data){
                            window.location.reload(false);
                         },
                         function(error){
                             console.log("userCtrl error")
                         }
                     );
                  }
        $scope.logOut = function(){
                     $http({
                         method: "POST",
                         url: "/logout"
                     }).then(
                         function(data){
                            window.location.reload(false);
                         },
                         function(error){
                             console.log("userCtrl error")
                         }
                     );
                  }
    });