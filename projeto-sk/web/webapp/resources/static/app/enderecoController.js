var app = angular.module('app');

app.controller('enderecoController', function ($scope, $http, $localStorage, $location) {

    $scope.endereco = {
        usuario: {idusuario: $localStorage.usuarioLogado.idusuario}
    };

    //Adiciona um endereço enviando parametros para classe Java.
    $scope.salvarEndereco = function () {
        $http({
            method: 'POST',
            url: 'criarEndereco',
            data: $scope.endereco,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            //Atualizando storage.
            var usuario = $localStorage.usuarioLogado;
            usuario.endereco.push(data);
            var recebe = usuario;
            $localStorage.$reset('usuarioLogado');
            $localStorage.usuarioLogado = recebe;

            $location.path('/home');
        });
    };

    $scope.listaEnderecos = [];
    $scope.idUsuarioLogado = $localStorage.usuarioLogado.idusuario;
});
