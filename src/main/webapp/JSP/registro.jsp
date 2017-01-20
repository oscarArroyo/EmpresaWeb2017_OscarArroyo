<%-- 
    Document   : registro
    Created on : 16-ene-2017, 12:40:51
    Author     : Oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
        <link href="${contexto}/BOOTSTRAP/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contexto}/CSS/estilos.css" rel ="stylesheet">
        <script type="text/javascript" language="javascript" src="${contexto}/BOOTSTRAP/jquery-3.1.1.js"></script>
        <script src="${contexto}/BOOTSTRAP/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Registro de usuario </title>
    <body>
        <div class="container-fluid">
            <jsp:include page="../INC/cabecera.jsp"/>
            <div class="row">
                <div class="panel-heading">
                    <div class="panel-title text-center">
                        <h1 class="title text-center">ExtremComponentes</h1>
                        <hr />
                    </div>
                </div>
                <div class="col-sm-offset-4 col-sm-4 col-xs-offset-1 col-xs-10">

                    <div class="main-login main-center">
                        <form class="form-horizontal" method="post" action="${contexto}/Login">

                            <div class="form-group">
                                <label for="email" class="control-label">Email</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon caja"><i class="fa fa-envelope" aria-hidden="true"></i></span>
                                        <input type="email" required="required" class="form-control caja" name="usuario" id="user" style="width: 100%;height: 4.4rem"  placeholder="Email"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="password" class="control-label">Contrase&ntilde;a</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <span class="input-group-addon caja"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                        <input type="password" class="form-control caja" required="required" minlength="3" name="pass" id="password"  placeholder="Contraseña"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group ">
                                <button name="registro" type="submit" class="btn btn-primary btn-lg btn-block login-button">Registrarse</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../INC/pie.jsp"/> 
    </body>
</html>
