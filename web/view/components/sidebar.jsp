<%@page import="model.User"%>
<%

    User usuario = null;

    HttpSession sesionok = request.getSession();

    usuario = (User) sesionok.getAttribute("usuario");

    String rol = usuario.getRol_name();

%>

<div class="collapse collapse-horizontal show"  id="sidebar">
    <div  class="d-flex flex-column p-3 bg-light border-end h-100"  style="width: 250px;">
        <a class="align-self-end text-decoration-none text-primary" style="cursor: pointer" id="menu-btn" data-bs-toggle="collapse" data-bs-target="#sidebar"  aria-expanded="true" aria-controls="sidebar">
            <i class="fas fa-times"></i>
        </a>


        <a href="/" class="d-flex flex-column align-items-center justify-content-center gap-2 pb-3 link-dark text-decoration-none border-bottom">
            <img src="../../public/img/logo.png" alt="" width="40">

            <span class="fs-5 fw-semibold ">Techtint</span>

        </a>
        <div class="border-bottom py-3 text-center">
            <i class="far fa-user text-primary fs-4"></i>    
            <span class="fw-semibold text-primary" style="font-size: 15px"><%=usuario.getNombre()%> 

            </span>
            <span class="text-dark" style="font-size: 12px">
                (<%=usuario.getRol_name()%>)
            </span>

        </div>


        <nav  style="overflow-y: auto" class="h-100">
            <ul class="list-unstyled ps-0 my-3">
                <% if (rol.compareToIgnoreCase("super administrador") == 0) {%>
                <li class="mb-2">
                    <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#usuario" aria-expanded="false">
                        <i class="fas fa-address-book fs-2 me-3 fa-fw"></i>
                        <span class="text-start fs-6">Usuarios</span>
                    </button>
                    <div class="collapse" id="usuario">
                        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                            <li><a href="/usuario" class="link-dark rounded">Usuario</a></li>
                            <li><a href="/rol" class="link-dark rounded">Rol</a></li>
                        </ul>
                    </div>
                </li>
                <% }%>

                <li class="mb-2">
                    <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#conductor" aria-expanded="false">

                        <i class="fas fa-male fs-2 me-3 fa-fw"></i>
                        <span class="text-start fs-6">Conductor</span>
                    </button>
                    <div class="collapse" id="conductor">
                        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                            <li><a href="/conductor" class="link-dark rounded">Conductor</a></li>
                            <li><a href="/conductor/vehiculo" class="link-dark rounded">Vehiculos</a></li>
                            <li><a href="/conductor/proyecto" class="link-dark rounded">Proyectos</a></li>
                            <li><a href="/conductor/evaluacion" class="link-dark rounded">Evaluaciones</a></li>

                        </ul>
                    </div>
                </li>

                <% if (rol.compareToIgnoreCase("super administrador") == 0) {%>
                <li class="mb-2">
                    <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#vehiculo" aria-expanded="false">
                        <i class="fas fa-truck-moving fs-2 me-3 fa-fw"></i>
                        <span class="text-start fs-6">Vehiculo</span>
                    </button>
                    <div class="collapse" id="vehiculo">
                        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                            <li><a href="/vehiculo" class="link-dark rounded">Vehiculo</a></li>
                            <li><a href="/tipovehiculo" class="link-dark rounded">Tipo de vehiculos</a></li>
                        </ul>
                    </div>
                </li>
                <% }%>

                <% if (rol.compareToIgnoreCase("super administrador") == 0) {%>
                <li class="mb-2 me-auto">
                    <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#infraccion" aria-expanded="false">
                        <i class="fas fa-exclamation-triangle fs-2 me-3 fa-fw"></i>
                        <span class="text-start fs-6">Infraccion</span>
                    </button>
                    <div class="collapse" id="infraccion">
                        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                            <li><a href="#" class="link-dark rounded">Infraccion</a></li>
                            <li><a href="/tipoinfraccion" class="link-dark rounded">Tipo infraccion</a></li>

                        </ul>
                    </div>
                </li>
                <% }%>


                <li class="mb-2">
                    <button class="btn btn-toggle align-items-center rounded collapsed mt-auto" data-bs-toggle="collapse" data-bs-target="#proyecto" aria-expanded="false">
                        <i class="fas fa-clipboard-list fs-2 me-3 fa-fw"></i>
                        <span class="text-start fs-6">Proyecto</span>
                    </button>
                    <div class="collapse" id="proyecto">
                        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small ">
                            <li><a href="/proyecto" class="link-dark rounded">Proyecto</a></li>

                        </ul>
                    </div>
                </li>
                <% if (rol.compareToIgnoreCase("super administrador") == 0) {%>
                <li class="mb-2">
                    <button class="btn btn-toggle align-items-center rounded collapsed mt-auto" data-bs-toggle="collapse" data-bs-target="#informacion-general" aria-expanded="false">

                        <i class="fab fa-elementor fs-2 me-3 fa-fw"></i>
                        <span class="text-start fs-6">Informacion general</span>
                    </button>
                    <div class="collapse" id="informacion-general">
                        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small ">
                            <li><a href="/informaciongeneral/pais" class="link-dark rounded">Pais</a></li>
                            <li><a href="/informaciongeneral/gruposanguineo" class="link-dark rounded">Grupo sanguineo</a></li>
                            <li><a href="/informaciongeneral/empresa" class="link-dark rounded">Empresa</a></li>
                            <li><a href="/informaciongeneral/tipodocumentoidentidad" class="link-dark rounded">Tipos de documento de identidad</a></li>
                        </ul>
                    </div>
                </li>
                <% }%>


                <li class="mb-2">
                    <button class="btn btn-toggle align-items-center rounded collapsed mt-auto" data-bs-toggle="collapse" data-bs-target="#evaluacion" aria-expanded="false">



                        <i class="fas fa-clipboard fs-2 me-3 fa-fw"></i>
                        <span class="text-start fs-6">Evaluacion</span>
                    </button>
                    <div class="collapse" id="evaluacion">
                        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small ">
                            <li><a href="/informaciongeneral/pais" class="link-dark rounded">Pais</a></li>
                            <li><a href="/evaluador" class="link-dark rounded">Evaluador</a></li>
                            <li><a href="/informaciongeneral/empresa" class="link-dark rounded">Empresa</a></li>
                        </ul>
                    </div>
                </li>
                <% if (rol.compareToIgnoreCase("super administrador") == 0) {%>
                <li class="mb-2">
                    <button class="btn btn-toggle align-items-center rounded collapsed mt-auto" data-bs-toggle="collapse" data-bs-target="#licencia" aria-expanded="false">


                        <i class="fas fa-id-badge fs-2 me-3 fa-fw"></i>
                        <span class="text-start fs-6">Licencias</span>
                    </button>
                    <div class="collapse" id="licencia">
                        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small ">
                            <li><a href="/licencia/tipolicenciainterna" class="link-dark rounded">Tipos de licencia interna</a></li>
                            <li><a href="/licencia/tipolicenciaoficial" class="link-dark rounded">Tipos de licencia oficial</a></li>
                            <li><a href="/informaciongeneral/empresa" class="link-dark rounded">Empresa</a></li>
                        </ul>
                    </div>
                </li>
                <% }%>
            </ul>
        </nav>
    </div>
</div>
