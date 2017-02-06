<%-- 
    Document   : modificarDatos
    Created on : 22-ene-2017, 18:53:04
    Author     : Oscar
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="author" content="Óscar Arroyo León" />
        <meta name="generator" content="NetBeans IDE 8.1" />
        <meta name="copyright" content="Desarrollo web entorno servidor" />
        <meta name="robots" content="index, follow" />
        <meta name="keywords" content="html" />
        <meta name="description" content="Modificacion de datos" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${contexto}/BOOTSTRAP/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contexto}/CSS/estilos.css" rel ="stylesheet">
        <script type="text/javascript" language="javascript" src="${contexto}/BOOTSTRAP/jquery-3.1.1.js"></script>
        <script src="${contexto}/BOOTSTRAP/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Datos del usuario  <c:out value="${sesion.email}"/></title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="../INC/cabecera.jsp"/>
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-xs-offset-0 col-sm-offset-2 toppad" >
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title"> Modificaci&oacute;n de datos de: <c:out value="${sesion.email}"/></h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-xs-12 col-sm-4" align="center" > <i class="fa fa-user-circle-o fa-5x" aria-hidden="true"></i>
                                    <input class="btn btn-info btn-pressure btn-sensitive botones" type="button" value="Modificar datos del usuario"/>
                                    <a href="${contexto}/JSP/direcciones.jsp"><input class="btn btn-info btn-pressure btn-sensitive botones" type="button" value="Añadir direccion"/></a>
                                    <a href="${contexto}/HistorialCompras"><input class="btn btn-info btn-pressure btn-sensitive botones" type="button" value="Historial de compras"/></a>
                                        <c:if test="${sesion.tipo=='a'.charAt(0)}">
                                        <a href="${contexto}/JSP/panelAdministrador.jsp"><input class="btn btn-info btn-pressure btn-sensitive botones" type="button" value="Panel administrador"/></a>
                                        </c:if>
                                </div>
                                <div class=" col-xs-12 col-sm-8 "> 
                                    <form method="post" action="${contexto}/PanelCliente">
                                        <table class="table table-user-information">

                                            <tbody>

                                                <tr>
                                                    <td>Nombre: </td>
                                                    <td><input type="text" name="nombre" value="${cliente.nombre}" pattern="^([A-Z]{1}[a-zñáéíóú]+[\s]*)+$" required=""></td>
                                                </tr>
                                                <tr>
                                                    <td>Apellidos: </td>
                                                    <td><input type="text" name="apellidos" value="${cliente.apellidos}" pattern="^([A-Z]{1}[a-zñáéíóú]+[\s]*)+$" required=""></td>
                                                </tr>
                                                <tr>
                                                    <td>Nif: </td>
                                                    <td><input type="text" name="nif" value="${cliente.nif}" pattern="^\d{8}[A-Z]$" required></td>
                                                </tr>
                                                <tr>
                                                    <td>Fecha de nacimiento: </td>
                                                    <td><input type="date" name="fnacimiento" value="${cliente.fechaNacimiento}" required></td>
                                                </tr>
                                                <tr>
                                                    <td><input class="btn btn-success btn-pressure" type="submit" name="aceptar" value="Aceptar"</td>
                                                    <td><input class="btn btn-danger btn-pressure" type="submit" name="cancelar" value="Cancelar"></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </form>

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
