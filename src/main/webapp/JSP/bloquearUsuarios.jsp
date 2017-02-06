<%-- 
    Document   : bloquearUsuarios
    Created on : 05-feb-2017, 1:29:11
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
        <meta name="description" content="Bloquear Usuarios" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${contexto}/BOOTSTRAP/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contexto}/CSS/estilos.css" rel ="stylesheet">
        <script type="text/javascript" language="javascript" src="${contexto}/BOOTSTRAP/jquery-3.1.1.js"></script>
        <script src="${contexto}/BOOTSTRAP/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Ofertar productos</title>
    </head>
    <body>
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
                    jQuery('#pagination').append('<li class="page-item" ><a class="page-link pgn btn btn-sensitive" href="#" style="background-color:#b7b7b7; color:black;" onClick="toTop();">' + i + '</a></li>');
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
                pagination(16);


            });
        </script>
        <script>
            function toTop() {
                window.scrollTo(0, 0);
            }

        </script>
        <div class="container-fluid">
            <jsp:include page="../INC/cabecera.jsp"/>
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-xs-offset-0 col-sm-offset-2 toppad" >


                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">Panel del admin: <c:out value="${sesion.email}"/></h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-xs-12 col-sm-4" align="center" > <i class="fa fa-user-circle-o fa-5x" aria-hidden="true"></i>
                                    <a href="${contexto}/JSP/modificarDatos.jsp"><input class="btn btn-info btn-pressure btn-sensitive botones" type="button" value="Modificar datos del usuario"/></a>
                                    <a href="${contexto}/JSP/direcciones.jsp"><input class="btn btn-info btn-pressure btn-sensitive botones" type="button" value="Añadir direccion"/></a>
                                    <c:if test="${sesion.tipo=='a'.charAt(0)}">
                                    <a href="${contexto}/JSP/panelAdministrador.jsp"><input class="btn btn-info btn-pressure btn-sensitive botones" type="button" value="Panel administrador"/></a>
                                    </c:if>
                                </div>
                                <div class=" col-xs-12 col-sm-8 "> 
                                    <form action="${contexto}/Administracion" method="post">
                                        <table class="table table-user-information">
                                            <tbody>
                                                <tr>
                                                    <td>Email</td>
                                                    <td> Bloquear? </td>
                                                </tr>

                                                <c:forEach items="${listaUsuarios}" var="usu">
                                                    <tr>
                                                        <td>${usu.email}</td>
                                                        <c:choose>

                                                            <c:when test="${usu.bloqueado=='s'.charAt(0)}">
                                                                <td><input type="checkbox" class="usuBlo" name="usuBlo" checked="">
                                                                <input type="hidden" class="idU"  name="idU" value="${usu.idUsuario}"></td></td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                <td><input type="checkbox" class="usuBlo" name="usuBlo">
                                                                <input type="hidden" class="idU"  name="idU" value="${usu.idUsuario}"></td></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </form>
                                    <div id="pagination" class="pagination justify-content-center">

                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
                                        <script>
                $('.usuBlo').click(function(event) {
                    alert("entro");
			var bloUsu="";
                         if($(this).prop('checked')){
                        var chk = 'on';
                    }else{
                        var chk='off';
                    }
                        var idU = $(this).parent().find('.idU').val();
                        alert(chk + idU);
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('${contexto}/Administracion', {
                                bloUsu:bloUsu,
                                chk:chk,
				idU : idU
			}, function(responseText) {
                            
			});
		});
       </script>
        <jsp:include page="../INC/pie.jsp"/>
    </body>
</html>