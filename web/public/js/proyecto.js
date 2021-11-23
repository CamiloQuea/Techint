const modalTabForm = document.querySelector('#modalTabForm');

const inputsModal = modalTabForm.getElementsByTagName('input');

const textAreaModal = modalTabForm.getElementsByTagName('textarea');

const selectModal = modalTabForm.getElementsByTagName('select');

const modaltitle = document.getElementById('modaltitle');

const modalTab = new bootstrap.Modal(document.getElementById('modalTab'), {
    keyboard: false
});

window.onload = async () => {

    await updateTable();
    await updateUsuarioSelect();

};

on(document, 'keyup', '#filtro', e => {

    filterTable();

});

const editBtn = async(btn) => {

    modalTabForm.reset();

    modaltitle.innerHTML = 'MODIFICAR PROYECTO';

    fila = btn.parentNode.parentNode.parentNode;

    const elemento = fila.children;

    const id = elemento[0].innerHTML;

    const codigo = elemento[1].innerHTML;

    const usuario_id = elemento[2].innerHTML;

    const nombre = elemento[3].innerHTML;


    inputsModal['id'].value = id;

    inputsModal['codigo'].value = codigo;

    selectModal['select_usuario'].value = usuario_id;

    inputsModal['nombre'].value = nombre;

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

            codigo: inputsModal['codigo'].value,
            usuario: selectModal['select_usuario'].value,
            nombre: inputsModal['nombre'].value


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
            codigo: inputsModal['codigo'].value,
            usuario: selectModal['select_usuario'].value,
            nombre: inputsModal['nombre'].value

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

    const data = await fetch('/api/proyecto', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)

    });

    const datajson = await data.json();

};

const updateItem = async (body) => {

    const data = await fetch('/api/proyecto', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)

    });

    const datajson = await data.json();

};

const deleteItem = async (id) => {

    const data = await fetch(`/api/proyecto?id=${id}`, {
        method: 'DELETE'
    });

    const datajson = await data.json();

};


const updateTable = async () => {

    const data = await fetch(`/api/proyecto`, {
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
                                <td>${datajson[i].usuario}</td>
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
            inputsModal['codigo'].value,
            selectModal['select_usuario'].value,
            inputsModal['nombre'].value
            ) {

        return false;

    } else {

        return true;

    }

};

const verifyUpdate = () => {

    if (
            inputsModal['id'].value !== "",
            inputsModal['codigo'].value,
            selectModal['select_usuario'].value,
            inputsModal['nombre'].value
            ) {

        return false;

    } else {

        return true;

    }

};


//Usuario

const updateUsuarioSelect = async () => {

    const data = await fetch(`/api/user`, {
        method: 'GET'
    });

    const datajson = await data.json();



    const selectusuario = document.getElementById('select_usuario');

    if (datajson.length === 0) {

        return `<option value="">NO HAY </option>`;
    }
    ;

    let contentUpdated = `<option value=""> SELECCIONE USUARIO</option>`;

    for (let i = 0; i < datajson.length; i++) {

        contentUpdated += `<option value="${datajson[i].id}"> ${datajson[i].nombre}</option>`;

    }
    ;

    selectusuario.innerHTML = contentUpdated;

};




