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
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Producto</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="../INC/cabecera.jsp"/>
            <div class="row">

                <div class="col-sm-10 col-xs-12 col-sm-offset-1 contenedor-producto"> 
                    
                        
                    
                    <div class="col-xs-12 col-sm-5 carousel-pro">
                        
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <c:set var="contador" value="0"/>
                                <c:forEach items="${imagenes}" var="imagen">
                                    <li data-target="#carousel-example-generic" data-slide-to="${contador}">
                                        <c:set var="contador" value="${contador+1}"/> </li>
                                    </c:forEach>
                            </ol>
                            <div class="carousel-inner" role="listbox">
                                <c:forEach items="${imagenes}" var="imagen">
                                    <div class="item">
                                        <img class="imgslider" src="imagenesProductos/${imagen}">
                                    </div>
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
                    <div class=" col-sm-7 col-xs-6 caracteristicas">
                        <c:if test="${sesion!=null}">
                            <a class="add-cart"><button class="compra btn btn-success text-center"  value="">A&ntilde;adir al carro</button></a>
                        </c:if>
                        <h5 class="pvp text-center">Precio: <c:out value="${pro.precioUnitario}"/>&euro; </h5>
                        <h5>Denominaci&oacute;n: <c:out value="${pro.denominacion}"/> </h5>
                        <h5>Descripci&oacute;n: <c:out value="${pro.descripcion}"/></h5>
                        <h5>Stock: <c:out value="${pro.stock}"/></h5>
                        <h5>Marca: <c:out value="${pro.marca}"/></h5>
                        <h5>Categor&iacute;a:  <c:out value="${pro.categoria}"/></h5>
                        <h5>Caracter&iacute;sticas: </h5>
                        <ul>

                            <c:forEach items="${cp}" var="CaractProd">
                                <li><c:out value="${CaractProd.nombre}"/> :
                                    <c:out value="${CaractProd.descripcion}"/> </li>

                            </c:forEach>
                        </ul>
                    </div>
                </div> 
            </div>
            <script>
                $(".carousel-indicators li:first").addClass("active");
                $(".carousel-inner .item:first").addClass("active");
            </script>    
            <script>
                $('.add-cart').click(function(event) {
			var idProducto = ${pro.idProducto};
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('${contexto}/Carrito', {
				idProducto : idProducto
			}, function(responseText) {
				$('.carro').html(responseText);
			});
		});
            </script>
        </div>
        <jsp:include page="../INC/pie.jsp"/> 
        
    </body>
</html>
