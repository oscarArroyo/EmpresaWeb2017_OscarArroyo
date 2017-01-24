<%-- 
    Document   : panelUsuario
    Created on : 21-ene-2017, 23:16:23
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
        <title>Panel del usuario  <c:out value="${sesion.email}"/></title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="../INC/cabecera.jsp"/>
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-xs-offset-0 col-sm-offset-2 toppad" >


                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title"><c:out value="${sesion.email}"/></h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-xs-12 col-sm-4" align="center" > <i class="fa fa-user-circle-o fa-5x" aria-hidden="true"></i>
                                    <a href="${contexto}/JSP/modificarDatos.jsp"><input class="btn btn-info btn-pressure btn-sensitive botones" type="button" value="Modificar datos del usuario"/></a>
                                    <input class="btn btn-info btn-pressure btn-sensitive botones" type="button" value="Direcciones"/>
                                </div>
                                <div class=" col-xs-12 col-sm-8 "> 
                                    <table class="table table-user-information">
                                        <tbody>
                                            <tr>
                                                <td>Nombre: </td>
                                                <td><c:out value="${cliente.nombre}"/></td>
                                            </tr>
                                            <tr>
                                                <td>Apellidos: </td>
                                                <td><c:out value="${cliente.apellidos}"/></td>
                                            </tr>
                                            <tr>
                                                <td>Nif: </td>
                                                <td><c:out value="${cliente.nif}"/></td>
                                            </tr>
                                            <tr>
                                                <td>Fecha de nacimiento: </td>
                                                <td><c:out value="${cliente.fechaNacimiento}"/></td>
                                            </tr>
                                            <tr>
                                                <td>Fecha de alta: </td>
                                                <td><c:out value="${cliente.fechaAlta}"/></td>
                                            </tr>
                                        </tbody>
                                    </table>

                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../INC/pie.jsp"/>
    </body>
</html>
