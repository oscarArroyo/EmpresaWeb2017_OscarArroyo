<%-- 
    Document   : producto
    Created on : 14-ene-2017, 21:29:47
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
        <title>Producto</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="../INC/cabecera.jsp"/>
            <div class="row">
                <div class="col-sm-8 col-xs-12 col-sm-offset-2">
                    <c:out value="${pro.denominacion}"/>
                    <c:out value="${pro.descripcion}"/>
                    <c:out value="${pro.stock}"/>
                    <c:out value="${pro.marca}"/>
                    <c:out value="${pro.categoria}"/>
                    <c:forEach items="${imagenes}" var="imagen">
                        <c:out value="${imagen}"/>
                    </c:forEach>
                    <c:forEach items="${cp}" var="CaractProd">
                        <c:out value="${CaractProd.nombre}"/>
                        <c:out value="${CaractProd.descripcion}"/>
                    </c:forEach>
                    <div class="preview-pic tab-content img-grande">
                        <div class="tab-pane active" id="pic-1"><img class="img-act" src="${contexto}/imagenesProductos/9016.1.jpg" /></div>
                        <div class="tab-pane" id="pic-2"><img class="img-act" src="${contexto}/imagenesProductos/1005.2.jpg" /></div>
                        <div class="tab-pane" id="pic-3"><img class="img-act" src="${contexto}/imagenesProductos/1005.3.jpg" /></div>
                        <div class="tab-pane" id="pic-4"><img class="img-act" src="${contexto}/imagenesProductos/1005.4.jpg" /></div>
                        <div class="tab-pane" id="pic-5"><img class="img-act" src="${contexto}/imagenesProductos/1005.5.jpg" /></div>
                    </div>
                    <ul class="preview-thumbnail nav nav-tabs">
                        <li class="enlace"><a data-target="#pic-1" data-toggle="tab"><img  class="img-prod" src="${contexto}/imagenesProductos/9016.1.jpg" /></a></li>
                        <li class="enlace"><a data-target="#pic-2" data-toggle="tab"><img  class="img-prod" src="${contexto}/imagenesProductos/1005.2.jpg" /></a></li>
                        <li class="enlace"><a data-target="#pic-3" data-toggle="tab"><img  class="img-prod" src="${contexto}/imagenesProductos/1005.3.jpg" /></a></li>
                        <li class="enlace"><a data-target="#pic-4" data-toggle="tab"><img  class="img-prod" src="${contexto}/imagenesProductos/1005.4.jpg" /></a></li>
                        <li class="enlace"><a data-target="#pic-5" data-toggle="tab"><img  class="img-prod" src="${contexto}/imagenesProductos/1005.5.jpg" /></a></li>
                    </ul>
                    <div class="details col-md-6">
                        <div class="action">
                            <button class="add-to-cart btn btn-default" type="button">add to cart</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../INC/pie.jsp"/> 
    </body>
</html>
