<%-- 
    Document   : categorias
    Created on : 14-ene-2017, 21:21:11
    Author     : Oscar
--%>

<%@taglib prefix="c" 
          uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${contexto}/BOOTSTRAP/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contexto}/CSS/estilos.css" rel ="stylesheet">
        <script type="text/javascript" language="javascript" src="${contexto}/BOOTSTRAP/jquery-3.1.1.js"></script>
        <script src="${contexto}/BOOTSTRAP/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Categor&iacute;a ${param.p}</title>
    </head>
    <script>
            function pagination(num) {
                if (num < 5) {
                    num = 5;
                }
                var req_num_row = num;
                var $tr = jQuery('tbody tr');
                var total_num_row = $tr.length;
                var num_pages = 0;
                if (total_num_row % req_num_row == 0) {
                    num_pages = total_num_row / req_num_row;
                }
                if (total_num_row % req_num_row >= 1) {
                    num_pages = total_num_row / req_num_row;
                    num_pages++;
                    num_pages = Math.floor(num_pages++);
                }

                jQuery('#pagination').empty();
                jQuery('#pagination').append('<ul class="pagination justify-content-center">');
                for (var i = 1; i <= num_pages; i++) {
                    jQuery('#pagination').append('<li class="page-item"><a class="page-link" href="#" onClick="toTop();">' + i + '</a></li>');
                }
                jQuery('#pagination').append('</ul>');

                $tr.each(function (i) {
                    jQuery(this).hide();
                    if (i + 1 <= req_num_row) {
                        $tr.eq(i).show();
                    }

                });
                jQuery('#pagination a').click(function (e) {
                    e.preventDefault();
                    $tr.hide();
                    var page = jQuery(this).text();
                    var temp = page - 1;
                    var start = temp * req_num_row;

                    for (var i = 0; i < req_num_row; i++) {

                        $tr.eq(start + i).show();

                    }
                });
            }
            jQuery('document').ready(function () {
                pagination(10);


            });
        </script>
    <script>
        function toTop() {
            window.scrollTo(0, 0)
        }

    </script>
</head>
<body>
    <div class="container-fluid">
        <jsp:include page="../INC/cabecera.jsp"/>

        <div class="row">

            <div class="formulario-reg col-sm-offset-1 col-sm-10 col-xs-12">
                <table class="table">
                    <thead>
                        <th>Paginacion nº de registros </th>
                        <th> <div>
                                <form action="#" name="formulario">  
                                    <input style="width: 16rem;" type="number" min="5" max="250" name="pag" required placeholder="Nº de registros">
                                    <input type="submit" onclick="pagination(document.formulario.pag.value);" value="Registros">
                                </form>
                            </div>
                        </th>
                        </thead>
                    <thead>
                        <tr>
                            <th>Imagen</th>
                            <th>Denominacion</th>
                            <th>Categoria</th>
                            <th>Marca</th>
                            <th>Precio/&euro;</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${prods}" var="productos">
                            <c:if test="${productos.categoria==param.c}">
                                <tr>
                                    <td><img class="imagenes" src="${contexto}/imagenesProductos/${productos.imagen}"</td>
                                    <td class="txt"><c:out value="${productos.denominacion}"/></td>
                                    <td class="txt"><a href="${contexto}/JSP/categorias.jsp?c=${productos.categoria}"><c:out value="${productos.categoria}"/></a></td>
                                    <td class="txt"><a href="${contexto}/JSP/marcas.jsp?p=${productos.marca}"><c:out value="${productos.marca}"/></a></td>
                                    <td class="txt"><c:out value="${productos.precioUnitario}"/></td>
                                </tr>
                            </c:if>
                        </c:forEach>     
                    </tbody>
                </table>
                <div id="pagination" class="pagination">

                </div>
            </div>

        </div>
    </div>
    
    <jsp:include page="../INC/pie.jsp"/> 
</body>
</html>

