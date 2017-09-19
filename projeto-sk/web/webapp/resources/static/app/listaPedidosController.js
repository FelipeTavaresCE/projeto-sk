var app = angular.module('app');

app.controller('listaPedidosController', function ($scope, $http, $localStorage, $location, ngDialog) {

    //Exibe lista de pedidos que usuário efetou na lista de produtos disponíveis.
    $http({
        method: 'GET',
        url: 'exibirPedidos?usuario=' + $localStorage.usuarioLogado.idusuario,
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        $scope.listaPedidos = data;
        //Se a lista de pedidos estiver vazia exibe mensagem.
        if ($scope.listaPedidos.length == 0) {
            $scope.naoTemPedidos = true;
            $scope.mostrarMensagem = true;
        } else {
            $scope.naoTemPedidos = false;
            $scope.mostrarMensagem = false;
        }
    });

    //ng-hide para esconder os endereços que o usuário tem cadastrado. 
    $scope.opcoesDeEndereco = true;

    //Pegando aqui usuário que está armazenado no storage para trazer os endereços que o mesmo cadastrou.
    $scope.usuario = $localStorage.usuarioLogado;

    //Habilita radio buttons para escolher qual endereço de entrega do pedido.
    $scope.habilitarOpcoesDeEndereco = function () {
        $scope.opcoesDeEndereco = false;
    };

    //Abrir modal para edição do pedido selecionado e pega o objeto para posteriormente ser alterado.
    $scope.editarPedido = function (item) {
        $scope.pedidoAlterar = item;
        ngDialog.open({
            template: 'webapp/resources/static/modal/editarPedidoModal.html',
            className: 'ngdialog-theme-default',
            scope: $scope
        });

    };

    //Seta id do endereco no objeto pedidoAlterar para efetuar alteração do pedido. 
    $scope.setIdEndereco = function (id) {
        $scope.pedidoAlterar.endereco.idEndereco = id;
    };

    //Cria objeto pedido.
    $scope.pedido = {
        produto: {},
        endereco: {idEndereco: 0},
        usuario: {idusuario: $localStorage.usuarioLogado}
    };

    //Altera o pedido enviando parametros preenchidos.
    $scope.salvarPedidoAlterado = function (pedido) {
        $scope.pedido = $scope.pedidoAlterar;
        $http({
            method: 'POST',
            url: 'alterarPedido',
            data: $scope.pedido,
            headers: {'Content-Type': 'application/json'}

        }).success(function () {
            $location.path('/home');
            ngDialog.close();
        });

    };

    //Obtem o pedido selecionado para exclusão.
    $scope.deletarPedido = function (pedido) {
        $localStorage.obterPedidoParaExclusao = pedido;
    };

    //Delata o pedido selecionado.
    $scope.excluirPedidoSelectionado = function () {
        $scope.pedido = $localStorage.obterPedidoParaExclusao;
        $http({
            method: 'POST',
            url: 'excluirPedido',
            data: $scope.pedido,
            headers: {'Content-Type': 'application/json'}
        }).success(function () {
            $location.path('/pedidos');
        });
    };
});
