<%-- 
    Document   : index.jsp
    Created on : 11-ene-2017, 12:51:56
    Author     : Oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Index</title>
        <c:set var="contexto" value="${pageContext.request.contextPath}" scope="session"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${contexto}/BOOTSTRAP/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contexto}/CSS/estilos.css" rel ="stylesheet">
        <script type="text/javascript" language="javascript" src="${contexto}/BOOTSTRAP/jquery-3.1.1.js"></script>
        <script src="${contexto}/BOOTSTRAP/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    
    <body>
        <div class="container-fluid">
            <jsp:include page="INC/cabecera.jsp"/>
            
            <div class="row">
                
                <div class="contendor col-xs-12 col-sm-12">
                    <div class="col-xs-12 col-sm-5 col-sm-offset-2">
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <c:set var="contador" value="0"/>
                                <c:forEach items="${prods}" var="productos">
                                    <c:if test="${productos.oferta=='s'}">
                                        <li data-target="#carousel-example-generic" data-slide-to="${contador}">
                                         <c:set var="contador" value="${contador+1}"/> </li>
                                    </c:if>
                                </c:forEach>
                            </ol>
                            <div class="carousel-inner" role="listbox">
                                <c:forEach items="${prods}" var="productos">
                                    <c:if test="${productos.oferta=='s'}">
                                        <div class="item">
                                            <a href="${contexto}/MostrarProductoCompleto?producto=${productos.idProducto}"><img class="imgslider" src="imagenesProductos/${productos.imagen}"></a> 
                                            <div class="inf-slider carousel-caption">
                                                <c:out value="${productos.denominacion}"/>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            $(".carousel-indicators li:first").addClass("active");
            $(".carousel-inner .item:first").addClass("active");
        </script>
        <jsp:include page="INC/pie.jsp"/> 
    </body>
</html>
