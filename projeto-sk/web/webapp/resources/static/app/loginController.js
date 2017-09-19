app.controller('loginController', function ($scope, $http, $location, $localStorage, ngDialog) {
    //Cria objeto usuário
    $scope.usuario = {};

    //Efetua login. Se estiver abre ngDialog informado o usuário.
    $scope.loginUsuario = function () {
        if ($scope.usuario.email == '' || $scope.usuario.email == null || $scope.usuario.senha == '' || $scope.usuario.senha == null) {
            ngDialog.open({
                scope: $scope,
                template: 'webapp/resources/static/modal/userNull.html',
                className: 'ngdialog-theme-default',
                width: 650,
                lain: true
            });
        } else {
            //Envia email e senha para classe java controller.
            $http({
                method: 'POST',
                url: 'loginUsuario',
                data: $scope.usuario,
                headers: {
                    'Content-Type': 'application/json'
                }
            }).success(function (data) {
                //Salva usuário em um storage.
                $localStorage.usuarioLogado = data;

                $location.path('/home');
            });

        }
        ;
    };
});

