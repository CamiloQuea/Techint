
<jsp:include page="components/head.jsp" >
    <jsp:param  name="nombre" value="Login"/>
</jsp:include>
<div class="d-flex justify-content-center align-items-center vh-100 login-page">

    <div class="card card-outline card-primary login-box">
        <div class="card-header text-center">
            <p class="h1"><b>Techint</b></p>
        </div>
        <div class="card-body">
            <p class="login-box-msg fw-bold">Iniciar Sesión</p>
            <form action="#" id="formLogin" method="post">
                <div class="input-group mb-3 flex-nowrap">
                    <input type="email" name="correo" id="correo" class="form-control" placeholder="Correo">
                    <span class="fas fa-at input-group-text pt-2"></span>
                </div>
                <div class="input-group mb-3 flex-nowrap">
                    <input type="password" name="contra" id="contra" class="form-control" placeholder="Contraseña">
                    <span class="fas fa-key input-group-text pt-2"></span>
                </div>
                <div class="social-auth-links text-center mt-2 mb-3">
                    <input type="hidden" name="action" value="adminLogin">
                    <input type="submit" name="login" class="btn btn-block btn-primary" value="Ingresar">
                </div>
            </form>
            <p class="mb-1">
                <span>Tienes problemas para ingresar? </span><a href="#" class="text-center fw-bold text-black-50">Comunicate con el soporte</a>
            </p>
        </div>
    </div>

</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>