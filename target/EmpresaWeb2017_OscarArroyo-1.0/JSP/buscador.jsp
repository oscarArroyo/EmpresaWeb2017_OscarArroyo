<%-- 
    Document   : buscador
    Created on : 14-ene-2017, 21:57:54
    Author     : Oscar
--%>
<%@taglib prefix="c" 
          uri="http://java.sun.com/jsp/jstl/core" %>
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
        <meta name="description" content="Buscador" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${contexto}/BOOTSTRAP/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contexto}/CSS/estilos.css" rel ="stylesheet">
        <script type="text/javascript" language="javascript" src="${contexto}/BOOTSTRAP/jquery-3.1.1.js"></script>
        <script src="${contexto}/BOOTSTRAP/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Busqueda</title>
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

            <div class="col-sm-offset-1 col-sm-10 col-xs-12">

                <table class="table">
                    <thead>
                    <th>Paginacion nº de registros </th>
                    <th> <div>
                            <form action="#" name="formulario" method="post">  
                                <input style="width: 16rem;" type="number" min="5" max="250" name="pag" required placeholder="Nº de registros">
                                <input type="button" onclick="pagination(document.formulario.pag.value);" value="Registros">
                            </form>
                        </div>
                    </th>
                    </thead>
                    <thead>
                        <tr>
                            <th>Imagen</th>
                            <th class="denominacion">Denominacion <i class="fa fa-sort" aria-hidden="true"></i></th>
                            <th>Categoria</th>
                            <th>Marca</th>
                            <th class="precio">Precio/&euro; <i class="fa fa-sort" aria-hidden="true"></i></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${busqueda}" var="productos">
                            <c:out value=""></c:out>
                                <tr>
                                    <td><a href="${contexto}/MostrarProductoCompleto?producto=${productos.idProducto}"><img class="imagenes" src="${contexto}/imagenesProductos/${productos.imagen}"/></a></td>
                                    <td class="txt"><a href="${contexto}/MostrarProductoCompleto?producto=${productos.idProducto}"><c:out value="${productos.denominacion}"/></a></td>
                                    <td class="txt"><a href="${contexto}/JSP/categorias.jsp?c=${productos.categoria}"><c:out value="${productos.categoria}"/></a></td>
                                    <td class="txt"><a href="${contexto}/JSP/marcas.jsp?p=${productos.marca}"><c:out value="${productos.marca}"/></a></td>
                                    <td class="txt"><fmt:formatNumber type="currency" maxFractionDigits="2" value="${productos.precioUnitario}" /></td>
                            </tr>
                        </c:forEach>     
                    </tbody>
                </table>
                <div id="pagination" class="pagination">

                </div>
            </div>

        </div>
    </div>

    <jsp:include page="../INC/pie.jsp"/> 
    <script>
        function sortTable(f, n) {
            var rows = $('.table tbody  tr').get();

            rows.sort(function (a, b) {

                var A = getVal(a);
                var B = getVal(b);

                if (A < B) {
                    return -1 * f;
                }
                if (A > B) {
                    return 1 * f;
                }
                return 0;
            });

            function getVal(elm) {
                var v = $(elm).children('td').eq(n).text().toUpperCase();
                if ($.isNumeric(v)) {
                    v = parseFloat(v, 10);
                }
                return v;
            }

            $.each(rows, function (index, row) {
                $('.table').children('tbody').append(row);
            });
        }
        var denom = 1;
        var pre = 1;
        $(".denominacion").click(function () {
            denom *= -1;
            var n = $(this).prevAll().length;
            sortTable(denom, n);
            pagination(10);
        });
        $(".precio").click(function () {
            pre *= -1;
            var n = $(this).prevAll().length;
            sortTable(pre, n);
            pagination(10);
        });

    </script>
</body>
</html> 
