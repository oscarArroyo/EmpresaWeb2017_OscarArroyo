<%-- 
    Document   : pie
    Created on : 18-ene-2017, 12:07:41
    Author     : Oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<footer class="footer-distributed">

    <div class="footer-left">

        <img src="${contexto}/IMG/logo2.png"/>

  

        <p class="footer-company-name">ExtremComponentes &copy; 2017</p>
        <a href="#" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">Trabaja con nosotros</a>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Rellene los datos</h4>
      </div>
      <div class="modal-body">
          <form action="#" method="post">
              <label>Email: </label>
              <input type="email" required=""> <br/>
              <label>Nombre: </label>
              <input type="text" required=""> <br/>
              <label>Apellidos: </label>
              <input type="text" required=""> <br/>
              <label>Edad: </label>
              <input type="text" required=""><br/>
              <label> Env&iacute;e su curriculum</label>
              <input type="file" required="">
          </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        <input type="submit" class="btn btn-primary" data-dismiss="modal">
      </div>
    </div>
  </div>
</div>
    </div>

    <div class="footer-center">

        <div>
            <i class="fa fa-map-marker"></i>
            <p><span>AV / Via de la plata</span> M&eacute;rida, Espa&ntilde;a</p>
        </div>

        <div>
            <i class="fa fa-phone"></i>
            <p>+34 682271686</p>
        </div>

        <div>
            <i class="fa fa-envelope"></i>
            <p><a href="mailto:oarroyoleon@gmail.com">oarroyoleon@gmail.com</a></p>
        </div>

    </div>

    <div class="footer-right">

        <p class="footer-company-about">
            <span>Sobre la compa&ntilde;ia:</span>
            Nos dedicamos a la venta de productos inform&aacute;ticos.
        </p>

        <div class="footer-icons">

            <a href="#"><i class="fa fa-facebook"></i></a>
            <a href="#"><i class="fa fa-twitter"></i></a>
            <a href="#"><i class="fa fa-linkedin"></i></a>
            <a href="#"><i class="fa fa-github"></i></a>
        </div>
    </div>
</footer>