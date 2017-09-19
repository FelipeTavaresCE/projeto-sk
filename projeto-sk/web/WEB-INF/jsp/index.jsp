<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html ng-app="app">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="webapp/resources/static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="webapp/resources/static/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="webapp/resources/static/css/ngDialog.css" rel="stylesheet" type="text/css"/>
        <link href="webapp/resources/static/css/ngDialog-theme-default.css" rel="stylesheet" type="text/css"/>
        <title>Login</title>

    </head>
    <body>

    <main >
        <div ui-view="main" ng-hide="main"></div>
    </main>

    <script src="webapp/resources/static/lib/jquery-3.1.1.min.js" type="text/javascript"></script>
    <script src="webapp/resources/static/js/bootstrap.min.js" type="text/javascript"></script>

    <script src="webapp/resources/static/lib/angular.js" type="text/javascript"></script>
    <script src="webapp/resources/static/lib/angular-ui-router.min.js" type="text/javascript"></script>
   
    <script src="webapp/resources/static/lib/ngStorage.js" type="text/javascript"></script>
    <script src="webapp/resources/static/lib/ngDialog.js" type="text/javascript"></script>
    
    <script src="webapp/resources/static/app/app.js" type="text/javascript"></script>
    <script src="webapp/resources/static/app/usuarioController.js" type="text/javascript"></script>
    <script src="webapp/resources/static/app/loginController.js" type="text/javascript"></script>
    <script src="webapp/resources/static/app/meucadastroController.js" type="text/javascript"></script>
    <script src="webapp/resources/static/app/enderecoController.js" type="text/javascript"></script>
    <script src="webapp/resources/static/app/listaEnderecosController.js" type="text/javascript"></script>
    <script src="webapp/resources/static/app/produtosController.js" type="text/javascript"></script>
    <script src="webapp/resources/static/app/listaPedidosController.js" type="text/javascript"></script>
</body>
</html>
