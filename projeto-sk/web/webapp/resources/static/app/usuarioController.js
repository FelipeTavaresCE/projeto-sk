var app = angular.module('app');

app.controller('usuarioController', function ($scope, $http, $location, ngDialog) {
    $scope.usuario = {};
    $scope.usuarioCadastradoNoti = false;
    //Cria um usuário enviando parametros para classe Java. E valida se dados estão nulos, se estiver, ngDialog modal é aberto.
    //PS1: Faltou fazer validação para verificar se o usuário já está cadastrado.
    //PS2: Na verdade, a validação é feito no back-end porém aqui no front não feito :p
    $scope.salvarUsuario = function () {
        if ($scope.usuario.email == '' || $scope.usuario.email == null || $scope.usuario.senha == '' || $scope.usuario.senha == null) {
            ngDialog.open({
                scope: $scope,
                template: 'webapp/resources/static/modal/userNull.html',
                className: 'ngdialog-theme-default',
                width: 650,
                lain: true
            });
        } else {
            $http({
                method: 'POST',
                url: 'criarUsuario',
                data: $scope.usuario,
                headers: {
                    'Content-Type': 'application/json'
                }
            }).success(function (data) {

                $scope.usuarioCadastradoNoti = true;
            });
        }
        ;
    };

});
