angular
    .module('app')
    .controller('activateCtrl', [
        '$scope', '$http', 'ApiCallService', '$location', '$translate', '$timeout',
        function($scope, $http, ApiCallService, $location, $translate, $timeout) {
            $scope.showMessage = false;
            $scope.isProcessing = true;
            $scope.isError = false;
            ApiCallService.callAuthApi('GET',  'company/activate/' + $location.search().key)
                .success(function(data) {
                    $scope.isProcessing = false;
                    if (data.success) {
                        $scope.message = data.message;
                        $timeout(function(){
                            window.location.href = "/#/signin";
                        }, 3000);
                    } else {
                        $scope.message = $scope.message = $translate.instant('AN_ERROR_OCCURRED');
                        $scope.isError = true;
                    }
                    $scope.showMessage = true;
                })
                .error(function() {
                    $scope.isProcessing = false;
                    $scope.isError = true;
                    $scope.message = $scope.message = $translate.instant('AN_ERROR_OCCURRED');
                    $scope.showMessage = true;
                });
        }
    ]);