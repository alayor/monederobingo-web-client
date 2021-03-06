angular
    .module('app')
    .controller('settingsPromotionsAddCtrl', [
        '$scope', '$http', 'ApiService', 'Session', '$window', '$translate', function($scope, $http, ApiService, Session, $window, $translate) {
            if(Session.isClosed()) {
                $window.location.href = "/#/";
            }
            $scope.formData = {};
            $scope.showMessage = false;
            $scope.isProcessing = false;
            $scope.processForm = function() {
                $scope.isProcessing = true;
                $scope.isError = false;
                $scope.formData.companyId = Session.user.companyId;
                ApiService.sendRequestToApi('POST', 'promotion_configuration/', $scope.formData)
                    .success(function(data) {
                        console.log(data);
                        $scope.isProcessing = false;
                        if (data.success) {
                            $scope.message = data.message.message;
                            $scope.formData.description = '';
                            $scope.formData.requiredPoints = '';
                        } else {
                            $scope.message = data.message.message;
                            $scope.isError = true;
                        }
                        $scope.showMessage = true;
                    })
                    .error(function(data) {
                        $scope.isProcessing = false;
                        $scope.isError = true;
                        $scope.message = $translate.instant('AN_ERROR_OCCURRED');
                        $scope.showMessage = true;
                    });

            };

        }
    ]);