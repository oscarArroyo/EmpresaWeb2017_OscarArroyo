<%-- 
    Document   : factura
    Created on : 06-feb-2017, 0:26:18
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
        <meta name="description" content="Factura" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${contexto}/BOOTSTRAP/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contexto}/CSS/estilos.css" rel ="stylesheet">
        <script type="text/javascript" language="javascript" src="${contexto}/BOOTSTRAP/jquery-3.1.1.js"></script>
        <script src="${contexto}/BOOTSTRAP/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Factura</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="../INC/cabecera.jsp"/>
            <div class="row">
                <div class="col-xs-12 col-sm-10 col-xs-offset-0 col-sm-offset-1 toppad" >


                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">Factura </h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="fac-dir pull-right" style="margin-right: 2rem;">
                                <img  src="${contexto}/IMG/logo2.png" alt="logo de la empresa">
                                <p>AV/Via de la plata</p>
                                <p>Mérida,Badajoz (06800)</p>
                                <p>Teléfono: 682271686 </p>
                                </div>
                                <div class="fac-datos pull-left " style="margin-left: 2rem;">
                                    <p><strong>Nombre: <c:out value="${cli.nombre}"/></strong> </p>
                                    <p><strong>Apellidos: <c:out value="${cli.apellidos}"/></strong></p>
                                    <p><strong>Direccion:<c:out value="${dir.nombreDireccion}"/></strong> </p>
                                    <p><strong><c:out value="${pue.nombre}"/> <c:out value="${pro.nombre}"/> <c:out value="${dir.codigoPostal}"/></strong></p>
                                    <p><strong>Teléfono: <c:out value="${dir.telefono}"/></strong> </p>
                                </div>
                                <div class="fac-prod "style="margin-left: 2rem; margin-right: 2rem;" >
                                    <table class="table table-user-information">
                                        <thead>
                                            <tr>
                                                <th class="text-center">Unidades</th>
                                        <th class="text-center">Denominacion</th>
                                        <th class="text-center">Precio unitario</th>
                                        <th class="text-center">Importe</tr>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${lilp}" var="linea">
                                            <tr>
                                                <td>${linea.cantidad}</td>
                                                 <c:forEach items="${prods}" var="pr">
                                                   <c:if test="${pr.idProducto==linea.idProducto}">
                                                       <td>${pr.denominacion}</td>
                                                        </c:if>
                                                 </c:forEach>
                                                <td><fmt:formatNumber type="currency" maxFractionDigits="2" value="${linea.precioUnitario}" /></td>
                                                <td><fmt:formatNumber type="currency" maxFractionDigits="2" value="${linea.precioUnitario*linea.cantidad}" /></td>
                                            </tr>
                                            </c:forEach>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <td colspan="3">Subtotal</td>
                                                <td><fmt:formatNumber type="currency" maxFractionDigits="2" value="${ped.baseImponible}" /></td>
                                            </tr>
                                            <tr>
                                                <td colspan="3">IVA(21%)</td>
                                                <td><fmt:formatNumber type="currency" maxFractionDigits="2" value="${(ped.baseImponible*ped.iva)/100}" /></td>
                                            </tr>
                                            <tr>
                                                <td colspan="3">Gastos de envio</td>
                                                <td><fmt:formatNumber type="currency" maxFractionDigits="2" value="${ped.gastosEnvio}" /></td>
                                            </tr>
                                            <tr>
                                                <td colspan="3">Total</td>
                                                <td><fmt:formatNumber type="currency" maxFractionDigits="2" value="${ped.baseImponible+ped.gastosEnvio}" /></td>
                                            </tr>
                                        </tfoot>
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
