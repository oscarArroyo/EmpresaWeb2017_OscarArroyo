<%-- 
    Document   : finalizarCompraPago
    Created on : 03-feb-2017, 18:12:59
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
        <meta name="copyright" content="Desarrollo web entorno servidor"/>
        <meta name="robots" content="index, follow" />
        <meta name="keywords" content="html" />
        <meta name="description" content="Finalizar compra pago"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${contexto}/BOOTSTRAP/css/bootstrap.min.css" rel="stylesheet">
        <link href="${contexto}/CSS/estilos.css" rel ="stylesheet">
        <script type="text/javascript" language="javascript" src="${contexto}/BOOTSTRAP/jquery-3.1.1.js"></script>
        <script src="${contexto}/BOOTSTRAP/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Pago del usuario  <c:out value="${sesion.email}"/></title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="../INC/cabecera.jsp"/>
            <div class="row">
                <div class="col-xs-12 col-sm-8 col-xs-offset-0 col-sm-offset-2" >
                    <div class="row bs-wizard " style="border-bottom:0; margin-left: 20%;">

                        <div class="col-xs-3 bs-wizard-step complete">
                            <div class="text-center bs-wizard-stepnum">Paso 1</div>
                            <div class="progress"><div class="progress-bar"></div></div>
                            <a href="${contexto}/JSP/finalizarCompraDatos.jsp" class="bs-wizard-dot"></a>
                            <div class="bs-wizard-info text-center">Datos personales</div>
                        </div>

                        <div class="col-xs-3 bs-wizard-step complete"><!-- complete -->
                            <div class="text-center bs-wizard-stepnum">Paso 2</div>
                            <div class="progress"><div class="progress-bar"></div></div>
                            <a href="${contexto}/JSP/finalizarCompraDirecciones.jsp" class="bs-wizard-dot"></a>
                            <div class="bs-wizard-info text-center">Direcciones</div>
                        </div>

                        <div class="col-xs-3 bs-wizard-step active"><!-- complete -->
                            <div class="text-center bs-wizard-stepnum">Paso 3</div>
                            <div class="progress"><div class="progress-bar"></div></div>
                            <a href="#" class="bs-wizard-dot"></a>
                            <div class="bs-wizard-info text-center">Pago</div>
                        </div>
                            
                    </div>
                    <div class="panel panel-default credit-card-box">
                        <div class="panel-heading display-table" >
                            <div class="row display-tr" >
                                <h3 class="panel-title display-td " >Detalles del pago: </h3>
                                <div class="display-td" >                            
                                    <img class="img-responsive pull-right" src="http://i76.imgup.net/accepted_c22e0.png">
                                </div>
                            </div>                    
                        </div>
                        <div class="panel-body">
                            <form method="post" id="payment-form" action="${contexto}/FinalizarPedido">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="form-group">
                                            <label for="cardNumber">N&uacute;mero de la tarjeta: </label>
                                            <div class="input-group">
                                                <input type="tel"class="form-control"name="cardNumber"placeholder="N&uacute;mero v&aacute;lido" autocomplete="cc-number" required autofocus pattern="^[0-9]{13,16}$" />
                                                <span class="input-group-addon"><i class="fa fa-credit-card"></i></span>
                                            </div>
                                        </div>                            
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-7 col-md-7">
                                        <div class="form-group">
                                            <label for="cardExpiry"><span class="hidden-xs">Caducidad</span><span class="visible-xs-inline">EXP</span></label>
                                            <input type="tel" class="form-control" name="cardExpiry" placeholder="MM / AA" autocomplete="cc-exp" required pattern="^\d{2}[/]\d{2}"/>
                                        </div>
                                    </div>
                                    <div class="col-xs-5 col-md-5 pull-right">
                                        <div class="form-group">
                                            <label for="cardCVC">C&oacute;digo CV </label>
                                            <input type="tel" class="form-control"name="cardCVC" placeholder="CVC" autocomplete="cc-csc" required pattern="^[0-9]{3,4}$"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <input type="submit" name="aceptarPago" class="subscribe btn btn-success btn-lg btn-block" value="Finalizar pago">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>            
                </div>
            </div>
        </div>
        <jsp:include page="../INC/pie.jsp"/>
    </body>
</html>
