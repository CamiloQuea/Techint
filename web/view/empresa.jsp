
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
               Empresas
            </h2>

            <h2 class="fw-light fs-6 ms-auto">
                /Informacion General/Empresas
            </h2>

        </div>

        <div> 
            <button type="button" class="btn btn-outline-success mb-2"  id="addBtn">
                AGREGAR
            </button>
            <div class="d-inline-flex float-xl-end mb-2">
                <input class="form-control me-2" type="search" placeholder="Buscar" aria-label="Search" id="filtro">
            </div>
        </div>



        <div class="card table-responsive-xl">
            <div class="card-body ">
                <table class="table mx-auto container-fluid" id="datatable">
                    <thead>
                        <tr>
                            <th scope="col" onclick="sortTable(0)">ID</th>
                            <th scope="col" onclick="sortTable(1)">NOMBRE</th>
                            <th scope="col" onclick="sortTable(2)">RUC</th>
                            <th scope="col">FUNCIONES</th>
                        </tr>
                    </thead>
                    <tbody id="tablaFilas">






                    </tbody>
                </table>
            </div>
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
            <form id="modalTabForm">
                <div class="modal-body">
                    <div class="row g-3">
                        <div class="form-floating mb-3 col">

                            <input type="text" class="form-control"  placeholder="Ingresar nombre" name="nombre">
                            <label for="floatingInput">Nombre</label>
                        </div>
                    </div>
                    <div class="row g-3">
                        <div class="form-floating mb-3 col">

                            <input type="number" class="form-control"  placeholder="Ingresar ruc" name="ruc">
                            <label for="floatingInput">RUC</label>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
                <input type="hidden" name="id" id="id">
                <input type="hidden" name="tipo"/>
            </form>
        </div>
    </div>
</div>


<%@include  file="components/contentfooter.jsp"  %>


<jsp:include page="components/foot.jsp">
    <jsp:param  name="js" value="<script src=\"../../public/js/empresa.js\"></script>"/>
</jsp:include>