<%-- 
    Document   : carritoUsuario
    Created on : 26-ene-2017, 12:28:30
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
        <title>Carrito de:   <c:out value="${sesion.email}"/></title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="../INC/cabecera.jsp"/>
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-xs-offset-0 col-sm-offset-2 toppad" >


                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">Carrito de: <c:out value="${sesion.email}"/></h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class=" col-xs-12 col-sm-10 col-sm-offset-1 "> 
                                    <table class="table ">
                                        <thead>
                                            <tr>
                                                <th>Denominacion</th>
                                                <th>Precio / &euro;:</th>
                                                <th>Cantidad: </th>
                                                <th>Borrar producto: </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${listalp}" var="lp">
                                                <c:forEach items="${prods}" var="producto">
                                                    <c:if test="${producto.idProducto==lp.idProducto}">
                                            <tr>
                                                <td>${producto.denominacion}</td>
                                                <td>${producto.precioUnitario}</td>
                                                <td>
                                                    <select>
                                                        <c:forEach begin="1" step="1" var="unidades" end="${producto.stock}">
                                                            <option value="${unidades}"><c:out value="${unidades}"/></option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                                <td><button class="btn-danger borrar" value="${lp.numeroLinea}"><i class="fa fa-trash" aria-hidden="true"></i></button></td>  
                                            </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </c:forEach>
                                            <tr>
                                                <td colspan="2"><button class="btn-success">Aceptar pedido</button></td>
                                                <td colspan="2"><button class="btn-danger">Cancelar pedido</button></td>
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
        <script>
                $('.borrar').click(function(event) {
                        alert("Entro en el evento");
			$(this).parent().parent().remove();
                        var nl = $(this).val();
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('${contexto}/BorrarLinea', {
				nl : nl
			}, function(responseText) {
                            if(responseText==0){
				$('.carro').html('');
                            }else{
                                $('.carro').html(responseText);
                            }
			});
		});
       </script>
    </body>
</html>