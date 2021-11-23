
<%@page session="true" %>



<jsp:include page="components/head.jsp">
    <jsp:param  name="nombre" value="Usuario"/>
</jsp:include>

<%@include  file="components/sidebar.jsp"  %>


<%@include  file="components/contentheader.jsp"  %>



<div class="mb-auto">

    <div class="p-2 mb-auto">
        <div class="d-flex mb-3">
            <h2 class="fw-light fs-4">
                Usuario
            </h2>

            <h2 class="fw-light fs-6 ms-auto">
                /Usuario
            </h2>

        </div>

        <div> 
            <button type="button" class="btn btn-outline-success mb-2"  id="addBtn">
                AGREGAR
            </button>
            <div class="d-inline-flex float-xl-end mb-2">
                <input class="form-control me-2" type="search" placeholder="Buscar" aria-label="Search" id="filtro">
                <!--<button class="btn btn-outline-success" onclick="filterTable()">Buscar</button>-->
            </div>
        </div>




        <div class="table-responsive" style="overflow: scroll">
            <table class="table mx-auto container-fluid" id="datatable">
                <thead>
                    <tr>
                        <th scope="col" onclick="sortTable(0)">ID</th>
                        <th scope="col" onclick="sortTable(1)">NOMBRE</th>
                        <th scope="col" onclick="sortTable(2)">APELLIDO</th>
                        <th scope="col" onclick="sortTable(3)">EMAIL</th>
                        <th scope="col" onclick="sortTable(4)">ROL ID</th>
                        <th scope="col" onclick="sortTable(5)">ROL</th>
                        <th scope="col">FUNCIONES</th>
                    </tr>
                </thead>
                <tbody id="tablaFilas">






                </tbody>
            </table>
        </div>

    </div>
</div>

<div class="modal fade" id="modalTab" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modaltitle"></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form method="POST" id="modalTabForm">
                <div class="modal-body">
                    <div class="row g-3">
                        <div class="form-floating mb-3 col">

                            <input type="text" class="form-control"  placeholder="Ingresar nombre" name="nombre">
                            <label for="floatingInput">Nombre</label>
                        </div>

                        <div class="form-floating mb-3 col">

                            <input type="text" class="form-control"  placeholder="Ingresar apellido" name="apellido">
                            <label for="floatingInput">Apellido</label>
                        </div>
                    </div>


                    <div class="row g-3">
                        <div class="form-floating mb-3 col">

                            <input type="email" class="form-control" placeholder="Ingresar correo" name="correo">
                            <label for="floatingInput">Correo</label>
                        </div>

                        <div class="form-floating mb-3 col">

                            <input type="password" class="form-control" placeholder="Ingresar contraseña" name="contra">
                            <label for="floatingInput">Contraseña</label>
                        </div>
                    </div>

                    <div class="row g-3 mb-3">

                        <select class="form-select" name="rol_id" id="select_rol">

                        </select>

                    </div>




                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
                <input type="hidden" name="user_id" id="user_id">
                <input type="hidden" name="tipo"/>
            </form>
        </div>
    </div>
</div>


<%@include  file="components/contentfooter.jsp"  %>


<jsp:include page="components/foot.jsp">
    <jsp:param  name="js" value="<script src=\"../../public/js/user.js\"></script>"/>
</jsp:include>