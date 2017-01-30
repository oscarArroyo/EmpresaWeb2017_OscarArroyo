<%-- 
    Document   : direcciones
    Created on : 29-ene-2017, 20:21:36
    Author     : Oscar
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${contexto}/BOOTSTRAP/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contexto}/CSS/estilos.css" rel ="stylesheet">
        <script type="text/javascript" language="javascript" src="${contexto}/BOOTSTRAP/jquery-3.1.1.js"></script>
        <script src="${contexto}/BOOTSTRAP/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>A&ntilde;adir direccion</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="../INC/cabecera.jsp"/>
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-xs-offset-0 col-sm-offset-2 toppad" >


                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">A&ntilde;adir direccion:  <c:out value="${sesion.email}"/></h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-xs-12 col-sm-4" align="center" > <i class="fa fa-user-circle-o fa-5x" aria-hidden="true"></i>
                                    <a href="${contexto}/JSP/modificarDatos.jsp"><input class="btn btn-info btn-pressure btn-sensitive botones" type="button" value="Modificar datos del usuario"/></a>
                                    <input class="btn btn-info btn-pressure btn-sensitive botones" type="button" value="Añadir direccion"/>
                                </div>
                                <div class=" col-xs-12 col-sm-8 direcciones"> 
                                    <table class="table table-user-information tabla-direcciones">
                                        <form method="post" action="${contexto}/CrearDirecciones">
                                            <tbody>
                                                <tr>
                                                    <td>Nombre de la direccion: </td>
                                                    <td><input type="text" name="nbdir"></td>

                                                </tr>
                                                <tr>
                                                    <td>Direccion: </td>
                                                    <td><input type="text" name="dir"></td>

                                                </tr>
                                                <tr>
                                                    <td>Tel&eacute;fono: </td>
                                                    <td><input type="text" name="tlf"></td>

                                                </tr>
                                                <tr>
                                                    <td>C&oacute;digo Postal: </br>
                                                    </td>
                                                    <td><input type="text" name="cod"><input type="button" name="btncodigo" class="btn btn-info btn-pressure btncodigo" value="Buscar Localidad"></td>

                                                </tr>
                                                <tr>
                                                    <td>Provincia: </td>
                                                    <td><input type="text" name="pro" class="pro" readonly ></td>

                                                </tr>
                                                <tr>
                                                    <td>Localidad: </td>
                                                    <td><input type="text" name="loc" class="loc" readonly></td>

                                                </tr>
                                                <tr>
                                                    <td><input class="btn btn-success btn-pressure" type="submit" name="aceptar" value="Aceptar"</td>
                                                    <td><input class="btn btn-danger btn-pressure" type="submit" name="cancelar" value="Cancelar"></td>

                                                </tr>
                                            </tbody>
                                        </form>
                                    </table>

                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <script>
          $('.btncodigo').click(function(event) {
                        alert("entro");
                        var cod = $(this).parent().val();
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('${contexto}/CrearDirecciones', {
				cod : cod
			}, function(responseText) {
                            $('.pro').html(responseText);
			});
		});  
        </script>
        <jsp:include page="../INC/pie.jsp"/>
    </body>
</html>
