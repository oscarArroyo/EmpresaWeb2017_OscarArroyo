<%-- 
    Document   : cabecera
    Created on : 12-ene-2017, 11:27:44
    Author     : Oscar
--%>
<%@taglib prefix="c" 
          uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<header>  
    <div class="container-fluid">
        <div class="row">
            <div class=" col-sm-3 col-xs-12">
                <a href="${contexto}/"><img class="logo" alt="logo" src="${contexto}/IMG/logo.png" ></a>
            </div>
            <div class="col-sm-offset-1 col-sm-3 col-xs-12">
                <div class="buscador">
                    <form action="${contexto}/Buscador" class="search-form" method="post">
                        <div class="input-group stylish-input-group">
                            <input type="text" name="buscador" class="form-control" placeholder="Buscar" >
                            <span class="icono input-group-addon">
                                <button type="submit" class="boton">
                                    <span class="glyphicon glyphicon-search"></span>
                                </button>  
                            </span>
                        </div>
                    </form>
                </div>
            </div>

            <div class="col-sm-offset-2 col-sm-3 col-xs-12">  
                <c:choose>
                    <c:when test="${sesion==null}">
                        <div class="errores">
                            <c:out value="${error}" default=" "></c:out>
                            <c:out value="${error2}" default=" "></c:out>
                            <c:out value="${error3}" default=" "></c:out>
                            </div>
                            <div class="lg">
                                <button type="button" class="btn btn-default" data-toggle="modal" data-target="#login-modal"><a href="#">Login</a></button>
                                <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                                    <div class="modal-dialog">
                                        <div class="loginmodal-container">
                                            <h1>Conectate a tu cuenta</h1><br/>
                                            <form method="post" action="${contexto}/Login">
                                <input type="email" name="emLog" maxlength=60 style="width: 100%;height: 4rem" placeholder="Email del Usuario">
                                            <input type="password" name="passLog" placeholder="Contraseña">
                                            <input type="submit" class="login loginmodal-submit" name="lg" value="Enviar">
                                        </form>
                                        <div class="login-help">
                                            <a href="${contexto}/JSP/registro.jsp">Registro</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <button type="button" class="reg btn btn-default"><a href="${contexto}/JSP/registro.jsp">Registro</a></button>
                    </c:when>
                    <c:otherwise>
                        <div class="usu-log">
                            <a href="${contexto}/JSP/panelUsuario.jsp" class="lista"><i class="fa fa-user-o fa-1x" aria-hidden="true"></i>Panel del usuario: <c:out value="${sesion.email}"></c:out><br/></a>
                            <a href="${contexto}/Login?cs=s" class="lista"><i class="fa fa-window-close-o fa-1x" aria-hidden="true"></i>Cerrar sesi&oacute;n</a>
                            

                        </div>
                            <div class="carrito text-center">
                            <i class="fa fa-cart-plus fa-3x carro" aria-hidden="true"></i>
                            </div>
                    </c:otherwise>
                </c:choose>
            </div>

        </div>
        <div class="row">
            <div class=" navegador col-sm-10 col-xs-12 col-sm-offset-1">
                <div id="navbar" class="navegador navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="${contexto}/">Inicio</a></li>
                        <li><a href="${contexto}/JSP/catalogoProductos.jsp">Cat&aacute;logo de productos</a></li>
                        <li><a href="${contexto}/JSP/ofertas.jsp">Ofertas</a></li>
                        <li class="dropdown mega-dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Marcas </a>
                            <ul class="dropdown-menu mega-dropdown-menu">
                                <c:forEach items="${marcas}" var="mar">
                                    <li class="col-sm-2">
                                        <a href="${contexto}/JSP/marcas.jsp?p=${mar.denominacion}"> <c:out value="${mar.denominacion}"/></a>
                                    </li>
                                </c:forEach>
                            </ul>		
                        </li>
                        <li class="dropdown mega-dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Categor&iacute;as </a>
                            <ul class="dropdown-menu mega-dropdown-menu">
                                <c:forEach items="${categorias}" var="cat">
                                    <li class="col-sm-3">
                                        <a href="${contexto}/JSP/categorias.jsp?c=${cat.nombre}"><c:out value="${cat.nombre}"/></a>
                                    </li>
                                </c:forEach>
                            </ul
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>                
</header>
