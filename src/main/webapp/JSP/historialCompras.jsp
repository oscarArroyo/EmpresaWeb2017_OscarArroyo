<%-- 
    Document   : historialCompras
    Created on : 05-feb-2017, 22:28:45
    Author     : Oscar
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <meta name="description" content="Historial de compras" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${contexto}/BOOTSTRAP/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contexto}/CSS/estilos.css" rel ="stylesheet">
        <script type="text/javascript" language="javascript" src="${contexto}/BOOTSTRAP/jquery-3.1.1.js"></script>
        <script src="${contexto}/BOOTSTRAP/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Historial de compras  <c:out value="${sesion.email}"/></title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="../INC/cabecera.jsp"/>
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-xs-offset-0 col-sm-offset-2 toppad" >
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title"> Historial de compras de: <c:out value="${sesion.email}"/></h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-xs-12 col-sm-4" align="center" > <i class="fa fa-user-circle-o fa-5x" aria-hidden="true"></i>
                                    <input class="btn btn-info btn-pressure btn-sensitive botones" type="button" value="Modificar datos del usuario"/>
                                    <a href="${contexto}/JSP/direcciones.jsp"><input class="btn btn-info btn-pressure btn-sensitive botones" type="button" value="Añadir direccion"/></a>
                                    <input class="btn btn-info btn-pressure btn-sensitive botones" type="button" value="Historial de compras"/>
                                    <c:if test="${sesion.tipo=='a'.charAt(0)}">
                                        <a href="${contexto}/JSP/panelAdministrador.jsp"><input class="btn btn-info btn-pressure btn-sensitive botones" type="button" value="Panel administrador"/></a>
                                        </c:if>
                                </div>
                                <div class=" col-xs-12 col-sm-8 "> 
                                    <c:forEach items="${historialPedidos}" var="pedido">
                                        <div class="historial">
                                            <p> <strong>Pedido:</strong> <c:out value="${pedido.idPedido}"/></p>
                                            <p> <strong>Fecha:</strong> <c:out value="${pedido.fecha}"/></p>
                                            <p> <strong>Total:</strong><fmt:formatNumber type="currency" maxFractionDigits="2" value="${pedido.baseImponible+pedido.gastosEnvio}" /><p>
                                            <p> <strong>Base imponible:</strong> <fmt:formatNumber type="currency" maxFractionDigits="2" value="${pedido.baseImponible}" /><p>
                                            <p> <strong>Gastos envío:</strong> <fmt:formatNumber type="currency" maxFractionDigits="2" value="${pedido.gastosEnvio}" /><p>
                                            <p> <strong>Iva:</strong> <fmt:formatNumber type="number" maxFractionDigits="0" value="${pedido.iva}" />&percnt;</p>  
                                            <c:forEach items="${historialLineas}" var="lineas">
                                                <c:if test="${lineas.idPedido==pedido.idPedido}">
                                                <c:forEach items="${prods}" var="pro">
                                                    <c:if test="${pro.idProducto==lineas.idProducto}">
                                                        <p><strong>Denominacion :</strong> <c:out value="${pro.denominacion}"/></p>
                                                        </c:if>
                                                    </c:forEach>
                                                        <p><strong>Unidades : </strong><c:out value="${lineas.cantidad}"/></p>
                                                        <p><strong>Precio unitario :</strong> <fmt:formatNumber type="currency" maxFractionDigits="2" value="${lineas.precioUnitario}" /></p>
                                                        </c:if>           
                                                </c:forEach>
                                                        <hr>
                                        </div>
                                    </c:forEach>
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
