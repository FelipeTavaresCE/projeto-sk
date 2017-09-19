var app = angular.module('app');

app.controller('listaEnderecosController', function ($scope, $http, $localStorage, $location, ngDialog) {
  
    //Recebe lista de endereço dependendo do usuário que está logado. É enviado o id do usuário que está logado através da url.s  
    $http({
        method: 'GET',
        url: 'exibirEnderecos?usuario=' + $localStorage.usuarioLogado.idusuario,
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        $scope.listaEnderecos = data;
        //Se a lista de endereços estiver vazia, mostrará a mensagem que não há endereço cadastrado.
        //Aqui é usado ng-hide
        if ($scope.listaEnderecos.length == 0) {
            $scope.naoTemEndereco = true;
            $scope.mostrarMensagem = true;
        } else {
            $scope.naoTemEndereco = false;
            $scope.mostrarMensagem = false;
        }
    });
    
    //Abre modal para edição do endereço anteriormente cadastrado. Utilizei aqui ngDiaglog mas também poderia ter sido usando angular-ui-bootstrap.
    //O objeto preenchido é recebido através do parametro 'item' conforme o que o usuário escolheu na lista disponível.
    $scope.editarEndereco = function (item) {
        $scope.enderecoAlterar = item;
        ngDialog.open({
            template: 'webapp/resources/static/modal/editarEnderecoModal.html',
            className: 'ngdialog-theme-default',
            scope: $scope
        });
    };
    
    //Envia o endereco para alterção para classe java EnderecoController.
    $scope.salvarEnderecoAlterado = function (endereco) {
        $http({
            method: 'POST',
            url: 'alterarEndereco',
            data: endereco,
            headers: {'Content-Type': 'application/json'}

        }).success(function (endereco) {
            $location.path('/home');
            ngDialog.close();
        });
    };

    //Seta id do usuário logado no objeto Endereço.
    $scope.endereco = {
        usuario: {idusuario: $localStorage.usuarioLogado.idusuario}
    };

    //Salva em um storage o endereço para ser deletado. 
    $scope.deletarEndereco = function (endereco) {
        $localStorage.obterEnderecoParaExclusao = endereco;
    };
    
    //Excluir o endereço que está agora armazenado no storage. Envia para classe java controller.
    $scope.excluirEnderecoSelectionado = function () {
        $scope.endereco = $localStorage.obterEnderecoParaExclusao;
        $http({
            method: 'POST',
            url: 'excluirEndereco',
            data: $scope.endereco,
            headers: {'Content-Type': 'application/json'}

        }).success(function (endereco) {
        });
    };
});
