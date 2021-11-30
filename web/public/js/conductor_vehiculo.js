const modalTabForm = document.querySelector('#modalTabForm');

const inputsModal = modalTabForm.getElementsByTagName('input');

const selectModal = modalTabForm.getElementsByTagName('select');

const modaltitle = document.getElementById('modaltitle');

const modalTab = new bootstrap.Modal(document.getElementById('modalTab'), {
    keyboard: false
});

window.onload = async () => {

    await updateTable();
    await updateConductorSelect();
    await updateVehiculoSelect();
};

on(document, 'keyup', '#filtro', e => {

    filterTable();

});

const editBtn = async(btn) => {

    modalTabForm.reset();

    modaltitle.innerHTML = 'MODIFICAR USUARIO';

    fila = btn.parentNode.parentNode.parentNode;

    const elemento = fila.children;

    const id = elemento[0].innerHTML;

    const nombre = elemento[1].innerHTML;

    const apellido = elemento[2].innerHTML;

    const empresa_id = elemento[3].innerHTML;

    const grupoSanguineo_id = elemento[5].innerHTML;

    const tipoDocumento_id = elemento[7].innerHTML;

    const numero_documento = elemento[9].innerHTML;

    const pais_id = elemento[10].innerHTML;

    const activo = elemento[12].innerHTML;



    inputsModal['user_id'].value = id;



    inputsModal['nombre'].value = nombre;

    inputsModal['apellido'].value = apellido;

    selectModal['empresa_id'].value = empresa_id;

    selectModal['grupoSanguineo_id'].value = grupoSanguineo_id;

    selectModal['tipoDocumento_id'].value = tipoDocumento_id;

    selectModal['pais_id'].value = pais_id;

    inputsModal['documento'].value = numero_documento;

    inputsModal['checkBox_active'].checked = activo.toLowerCase() === 'true' ? true : false;

    inputsModal['tipo'].value = 'actualizar';

    modalTab.toggle();

};




on(document, 'click', '#addBtn', e => {

    modaltitle.innerHTML = 'AGREGAR USUARIO';

    modalTabForm.reset();
    modalTab.toggle();
    inputsModal['tipo'].value = 'crear';

});

on(document, 'submit', '#modalTabForm', async e => {

    e.preventDefault();

    const tipo = inputsModal['tipo'].value;



    if (tipo === 'crear') {


        const data = {

            conductor: selectModal['conductor_id'].value,
            vehiculo: selectModal['vehiculo_id'].value


        };


        if (verifyCreate()) {

            return;

        }

        await createItem(data);

        await updateTable();

        modalTab.toggle();

        modalTabForm.reset();

    } else if (tipo === 'actualizar') {

        const data = {

            id: inputsModal['user_id'].value,
            empresa_id: selectModal['empresa_id'].value,
            grupoSanguineo_id: selectModal['grupoSanguineo_id'].value,
            tipoDocumentoIdentidad_id: selectModal['tipoDocumento_id'].value,
            pais_id: selectModal['pais_id'].value,
            documento: inputsModal['documento'].value,
            nombre: inputsModal['nombre'].value,
            apellido: inputsModal['apellido'].value,
            activo: inputsModal['checkBox_active'].checked

        };

        if (verifyUpdate()) {

            return;

        }

        await updateItem(data);

        await updateTable();

        modalTab.toggle();

        modalTabForm.reset();

    }

});

async function deleteBtn(btn) {

    fila = btn.parentElement.parentElement.parentElement;

    const elemento = fila.children;

    const conductor_id = elemento[0].innerHTML;
    const vehiculo_id = elemento[3].innerHTML;

    if (confirm('ESTA SEGURO DE ELIMINAR?')) {

        await deleteItem(conductor_id,vehiculo_id);

    }

    await updateTable();

}
;

//on(document, 'click', '.btn-eliminar', async e => {
//
//    const fila = e.target.parentNode.parentNode;
//
//    const elemento = fila.children;
//
//    const user_id = elemento[0].innerHTML;
//
//    await deleteItem(user_id);
//
//    await updateTable();
//
//});

const createItem = async (body) => {

    const data = await fetch('/api/conductor/vehiculo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)

    });

    const datajson = await data.json();

};

const updateItem = async (body) => {

    const data = await fetch('/api/conductor', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)

    });

    const datajson = await data.json();



};

const deleteItem = async (conductor, vehiculo) => {

    const data = await fetch(`/api/conductor/vehiculo?conductor=${conductor}&vehiculo=${vehiculo}`, {
        method: 'DELETE'
    });

    const datajson = await data.json();

};


const updateTable = async () => {

    const data = await fetch(`/api/conductor/vehiculo`, {
        method: 'GET'
    });

    const datajson = await data.json();


    const tablaFilas = document.querySelector('#tablaFilas');

    if (datajson.length === 0) {

        return `<tr>
                    <td colspan="7">No hay usuarios</td>
                </tr>`;
    }
    ;

    let contentUpdated = ``;

    for (let i = 0; i < datajson.length; i++) {

        contentUpdated += `<tr class="align-middle">
                                <td>${datajson[i].conductor_id}</td>
                                <td>${datajson[i].conductor_nombre}</td>
                                <td>${datajson[i].conductor_apellido}</td>
                                <td>${datajson[i].vehiculo_id}</td>
                                <td>${datajson[i].vehiculo_placa}</td>
                                <td>${datajson[i].tipoVehiculo_id}</td>
                                <td>${datajson[i].tipoVehiculo_nombre}</td>
                                
         
                                <td>
        <div class="d-flex justify-content-evenly">
        
                                    <button class=" btn btn-link p-0" onClick="deleteBtn(this)">
                                        <i class="fas fa-trash text-danger"></i> 
                                    </button>  
                                 
                                </td>
                            </tr>`;

    }
    ;

    tablaFilas.innerHTML = contentUpdated;

};

const verifyCreate = () => {

    if (
            selectModal['conductor_id'].value,
            selectModal['vehiculo_id'].value
            ) {

        return false;

    } else {

        return true;

    }

};

const verifyUpdate = () => {

    if (
            inputsModal['user_id'].value,
            selectModal['empresa_id'].value,
            selectModal['grupoSanguineo_id'].value,
            selectModal['tipoDocumento_id'].value,
            selectModal['pais_id'].value,
            inputsModal['documento'].value,
            inputsModal['nombre'].value,
            inputsModal['apellido'].value
            ) {

        return false;

    } else {

        return true;

    }

};


//ROL


const updateConductorSelect = async () => {

    const data = await fetch(`/api/conductor`, {
        method: 'GET'
    });

    const datajson = await data.json();



    const selectrol = document.getElementById('select_conductor');

    if (datajson.length === 0) {

        return `<option value="">NO HAY CONDUCTORES</option>`;
    }
    ;

    let contentUpdated = `<option value=""> SELECCIONE CONDUCTOR</option>`;

    for (let i = 0; i < datajson.length; i++) {

        contentUpdated += `<option value="${datajson[i].id}"> ${datajson[i].nombre} ${datajson[i].apellido}</option>`;

    }
    ;

    selectrol.innerHTML = contentUpdated;

};

const updateVehiculoSelect = async () => {

    const data = await fetch(`/api/vehiculo`, {
        method: 'GET'
    });

    const datajson = await data.json();



    const selectrol = document.getElementById('select_vehiculo');

    if (datajson.length === 0) {

        return `<option value="">NO HAY VEHICULOS</option>`;
    }
    ;

    let contentUpdated = `<option value=""> SELECCIONE VEHICULO</option>`;

    for (let i = 0; i < datajson.length; i++) {

        contentUpdated += `<option value="${datajson[i].id}"> ${datajson[i].detalle}</option>`;

    }
    ;

    selectrol.innerHTML = contentUpdated;

};

