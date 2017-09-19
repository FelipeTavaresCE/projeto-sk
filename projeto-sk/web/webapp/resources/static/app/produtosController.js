var app = angular.module('app');

app.controller('produtosController', function ($scope, $http, $localStorage, $location) {
    //ng-show para notificar quando usuário edicionar um produto.
    $scope.produtoAdicionado = false;

    //Simplesmente exibe lista de produtos.
    $http({
        method: 'GET',
        url: 'exibirProdutos',
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        $scope.produtos = data;
    });

    //Cria objeto pedido setando objetos no mesmo.
    $scope.pedido = {
        usuario: {idusuario: $localStorage.usuarioLogado.idusuario},
        produto: {idProdutos: 0}
    };

    //Conforme o produto que o usuário escolheu, o produto será adicionado na tabela de pedidos que está disponível na página de pedidos. 
    $scope.adicionarPedido = function (idproduto) {
        $scope.pedido.produto = idproduto;
        $http({
            method: 'POST',
            url: 'efetuarPedido',
            data: $scope.pedido,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            $scope.pedido = data;
            $scope.produtoAdicionado = true;
        });
    };
});
