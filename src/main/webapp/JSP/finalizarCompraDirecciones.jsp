<%-- 
    Document   : finalizarCompraDirecciones
    Created on : 01-feb-2017, 12:38:36
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
        <title>Direcciones del usuario  <c:out value="${sesion.email}"/></title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="../INC/cabecera.jsp"/>
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-xs-offset-0 col-sm-offset-2" >
                    <div class="row bs-wizard" style="border-bottom:0; margin-left: 20%;">
                        <div class="col-xs-3 bs-wizard-step complete">
                            <div class="text-center bs-wizard-stepnum">Paso 1</div>
                            <div class="progress"><div class="progress-bar"></div></div>
                            <a href="#" class="bs-wizard-dot"></a>
                            <div class="bs-wizard-info text-center">Datos personales</div>
                        </div>
                        <div class="col-xs-3 bs-wizard-step active">
                            <div class="text-center bs-wizard-stepnum">Paso 2</div>
                            <div class="progress"><div class="progress-bar"></div></div>
                            <a href="#" class="bs-wizard-dot"></a>
                            <div class="bs-wizard-info text-center">Direcciones</div>
                        </div>
                        <div class="col-xs-3 bs-wizard-step disabled">
                            <div class="text-center bs-wizard-stepnum">Paso 3</div>
                            <div class="progress"><div class="progress-bar"></div></div>
                            <a href="#" class="bs-wizard-dot"></a>
                            <div class="bs-wizard-info text-center">Pago</div>
                        </div>
                    </div>
                    <form method="post" action="${contexto}/FinalizarPedido">
                        <table class="table table-user-information tabla-direcciones">
                            <tbody>
                                <c:if test="${error!=null}">
                                                <tr>
                                                    <td colspan="2" style="color:red;">${error}</td>
                                                </tr>
                                            </c:if>
                                <tr>
                                    <td><a class="btn btn-default btn-sensitive" href="${contexto}/JSP/direcciones.jsp">Crear direccion</a></td>
                                    <td>
                                        <label>Direcciones registradas: </label>
                                        <select name="sel" class="select-dir">
                                            <option selected="" value="0">Seleccione una direccion </option>
                                            <c:forEach items="${direcciones}" var="dir">
                                                <option value="${dir.idDireccion}">${dir.direccion}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    
                                </tr>
                                <tr>
                                    <td>Nombre de la direccion: </td>
                                    <td><div class="nbdir"></div></td>

                                </tr>
                                <tr>
                                    <td>Direccion: </td>
                                    <td><div class="dir"></div></td>

                                </tr>
                                <tr>
                                    <td>Tel&eacute;fono: </td>
                                    <td><div class="tlf"></div></td>

                                </tr>
                                <tr>
                                    <td>C&oacute;digo Postal:</td> </br>
                                    <td><div class="cod"></div></td>

                                </tr>
                                <tr>
                                    <td>Provincia: </td>
                                    <td><div class="pro"></div></td>

                                </tr>
                                <tr>
                                    <td>Localidad: </td>
                                    <td><div class="loc"></div></td>

                                </tr>
                                <tr>
                                    <td><input class="btn btn-success btn-pressure" type="submit" name="aceptarDirecciones" value="Aceptar" onclick=""</td>
                                    <td><input class="btn btn-danger btn-pressure" type="submit" name="cancelar" value="Cancelar"></td>

                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <script>
            $('.select-dir').change(function (event) {
                var id = $(this).val();
                var dir = "";
                alert(id);
// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
                $.post('${contexto}/FinalizarPedido', {
                    id: id,
                    dir:dir
                }, function (responseText) {
                    $.each(responseText, function (index, value) {
                       switch(index){
                           case 0:
                               $('.nbdir').html("");
                               $('.nbdir').append(value);
                               break;
                           case 1:
                               $('.dir').html("");
                               $('.dir').append(value);
                               break;
                           case 2:
                               $('.tlf').html("");
                               $('.tlf').append(value);
                               break;
                           case 3:
                               $('.cod').html("");
                               $('.cod').append(value);
                               break;
                           case 4:
                               $('.pro').html("");
                               $('.pro').append(value);
                               break;
                           case 5:
                               $('.loc').html("");
                               $('.loc').append(value);
                               break;
                       }
                    });
                });
            });

        </script>
        <jsp:include page="../INC/pie.jsp"/>
    </body>
</html>

