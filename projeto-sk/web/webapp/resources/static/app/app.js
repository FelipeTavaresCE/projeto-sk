var app = angular.module('app', ['ui.router'], ['ngDialog'], ['ngStorage']);

//Definie rotas
angular.module('app', ['ui.router', 'ngDialog', 'ngStorage'])
        .config(['$stateProvider', '$urlRouterProvider', '$locationProvider',
            function ($stateProvider, $urlRouterProvider) {
                $urlRouterProvider.otherwise('/');

                $stateProvider
                        .state('cadastro', {
                            url: '/',
                            views: {
                                'main@': {
                                    templateUrl: 'webapp/resources/static/views/cadastro.html'
                                }
                            }
                        })
                        .state('login', {
                            url: '/login',
                            views: {
                                'main@': {
                                    templateUrl: 'webapp/resources/static/views/login.html'}
                            }
                        })
                        .state('home', {
                            url: '/home',
                            views: {
                                'main@': {
                                    templateUrl: 'webapp/resources/static/views/home.html'}
                            }
                        })
                        .state('produtos', {
                            url: '/produtos',
                            views: {
                                'main@': {
                                    templateUrl: 'webapp/resources/static/views/produtos.html'}
                            }
                        })
                        .state('meucadastro', {
                            url: '/meucadastro',
                            views: {
                                'main@': {
                                    templateUrl: 'webapp/resources/static/views/meucadastro.html'}
                            }
                        })
                        .state('enderecocadastro', {
                            url: '/enderecocadastro',
                            views: {
                                'main@': {
                                    templateUrl: 'webapp/resources/static/views/enderecocadastro.html'}
                            }
                        })
                        .state('listaenderecos', {
                            url: '/listaenderecos',
                            views: {
                                'main@': {
                                    templateUrl: 'webapp/resources/static/views/listaenderecos.html'}
                            }
                        })
                        .state('pedidos', {
                            url: '/pedidos',
                            views: {
                                'main@': {
                                    templateUrl: 'webapp/resources/static/views/pedidos.html'}
                            }
                        });
            }
        ]);