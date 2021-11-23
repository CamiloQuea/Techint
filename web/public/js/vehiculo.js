const modalTabForm = document.querySelector('#modalTabForm');

const inputsModal = modalTabForm.getElementsByTagName('input');

const selectModal = modalTabForm.getElementsByTagName('select');

const textAreaModal = modalTabForm.getElementsByTagName('textarea');

const modaltitle = document.getElementById('modaltitle');

const modalTab = new bootstrap.Modal(document.getElementById('modalTab'), {
    keyboard: false
});

window.onload = async () => {

    await updateTable();
    await updateTipoVehiculoSelect();

};

on(document, 'keyup', '#filtro', e => {

    filterTable();

});

const editBtn = async(btn) => {

    modalTabForm.reset();

    modaltitle.innerHTML = 'MODIFICAR TIPO DE VEHICULO';

    fila = btn.parentNode.parentNode.parentNode;

    const elemento = fila.children;

    const id = elemento[0].innerHTML;

    const tipo = elemento[1].innerHTML;

    const placa = elemento[2].innerHTML;


    inputsModal['id'].value = id;

    selectModal['select_tipo_vehiculo'].value = tipo;

    inputsModal['placa'].value = placa;

    inputsModal['tipo'].value = 'actualizar';

    modalTab.toggle();

};




on(document, 'click', '#addBtn', e => {

    modaltitle.innerHTML = 'AGREGAR TIPO DE VEHICULO';

    modalTabForm.reset();
    modalTab.toggle();
    inputsModal['tipo'].value = 'crear';

});

on(document, 'submit', '#modalTabForm', async e => {

    e.preventDefault();

    const tipo = inputsModal['tipo'].value;



    if (tipo === 'crear') {



        const data = {

            placa: inputsModal['placa'].value,
            tipo_vehiculo: selectModal['tipoVehiculo_id'].value


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

            id: inputsModal['id'].value,
            placa: inputsModal['placa'].value,
            tipo_vehiculo: selectModal['tipoVehiculo_id'].value

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

    const user_id = elemento[0].innerHTML;

    if (confirm('ESTA SEGURO DE ELIMINAR?')) {

        await deleteItem(user_id);

    }

    await updateTable();

}
;

const createItem = async (body) => {

    const data = await fetch('/api/vehiculo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)

    });

    const datajson = await data.json();

};

const updateItem = async (body) => {

    const data = await fetch('/api/vehiculo', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)

    });

    const datajson = await data.json();



};

const deleteItem = async (id) => {

    const data = await fetch(`/api/vehiculo?id=${id}`, {
        method: 'DELETE'
    });

    const datajson = await data.json();

};


const updateTable = async () => {

    const data = await fetch(`/api/vehiculo`, {
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
                                <td>${datajson[i].id}</td>
                                <td>${datajson[i].codigo}</td>
                                <td>${datajson[i].detalle}</td>

                                <td>
        <div class="d-flex justify-content-evenly">
        
                                    <button class=" btn btn-link p-0" onClick="deleteBtn(this)">
                                        <i class="fas fa-trash text-danger"></i> 
                                    </button>  
                                    <button class=" btn btn-link p-0" onClick="editBtn(this)">
                                        <i class="fas fa-pen text-warning"></i>
                                    </button>
        </div>
                                </td>
                            </tr>`;

    }
    ;

    tablaFilas.innerHTML = contentUpdated;

};

const verifyCreate = () => {

    if (
//            selectModal['rol_id'].value !== "" &&
            inputsModal['placa'].value,
            selectModal['tipoVehiculo_id'].value
            ) {

        return false;

    } else {

        return true;

    }

};

const verifyUpdate = () => {

    if (
            inputsModal['id'].value &&
            inputsModal['placa'].value &&
            selectModal['tipoVehiculo_id'].value
            ) {

        return false;

    } else {

        return true;

    }

};


//ROL

const updateTipoVehiculoSelect = async () => {

    const data = await fetch(`/api/tipovehiculo`, {
        method: 'GET'
    });

    const datajson = await data.json();



    const selectrol = document.getElementById('select_tipo_vehiculo');

    if (datajson.length === 0) {

        return `<option value="">NO HAY </option>`;
    }
    ;

    let contentUpdated = `<option value=""> SELECCIONE ROL</option>`;

    for (let i = 0; i < datajson.length; i++) {

        contentUpdated += `<option value="${datajson[i].id}"> ${datajson[i].detalle}</option>`;

    }
    ;



    selectrol.innerHTML = contentUpdated;

};


