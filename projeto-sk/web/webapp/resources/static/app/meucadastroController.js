var app = angular.module('app');

app.controller('meucadastroController', function ($scope, $localStorage, $http, $location, ngDialog) {
    //Pega usuario armazenado no storage.
    $scope.usuario = $localStorage.usuarioLogado;
    
    //Edita o usuario recebendo parametro e abre modal para efetuar o procedimento.
    $scope.editarUsuario = function (item) {
        $scope.usuarioAlterar = item;
        ngDialog.open({
            template: 'webapp/resources/static/modal/editarUsuarioModal.html',
            className: 'ngdialog-theme-default',
            scope: $scope
        });
    };

    //Envia os dados alterado através do objeto alterarUsuario
    $scope.salvarUsuarioAlterado = function (usuario) {
        $http({
            method: 'POST',
            url: 'alterarUsuario',
            data: usuario,
            headers: {'Content-Type': 'application/json'}

        }).success(function (usuario) {
            $location.path('/home');
            ngDialog.close();
        });
    };

    //Salva no storage usuario para ser deletado.
    $scope.deletarUsuario = function (usuario) {
        $localStorage.obterUsuarioParaExclusao = usuario;
    };

   $scope.usuarioExcluir = {};
   
    //Exclui usuário enviando objeto para classe java. 
    $scope.excluirUsuarioSelectionado = function () {
        $scope.usuarioExcluir = $localStorage.obterUsuarioParaExclusao;
        $http({
            method: 'POST',
            url: 'excluirUsuario',
            data: $scope.usuarioExcluir,
            headers: {'Content-Type': 'application/json'}
        }).success(function () {
             $location.path('/home');
        });
    };
});
