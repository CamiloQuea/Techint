const modalTabForm = document.querySelector('#modalTabForm');

const inputsModal = modalTabForm.getElementsByTagName('input');

const selectModal = modalTabForm.getElementsByTagName('select');

const modaltitle = document.getElementById('modaltitle');

const modalTab = new bootstrap.Modal(document.getElementById('modalTab'), {
    keyboard: false
});

window.onload = async () => {

    await updateTable();
    await updateRolSelect();

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

    inputsModal['id'].value = id;

    inputsModal['nombre'].value = nombre;

    inputsModal['apellido'].value = apellido;

    selectModal['empresa_id'].value = empresa_id;

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

            nombre: inputsModal['nombre'].value,
            apellido: inputsModal['apellido'].value,
            empresa_id: selectModal['empresa_id'].value

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
            nombre: inputsModal['nombre'].value,
            apellido: inputsModal['apellido'].value,
            empresa_id: selectModal['empresa_id'].value

        };
        
        console.log(data);

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

    const data = await fetch('/api/evaluador', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)

    });

    const datajson = await data.json();

};

const updateItem = async (body) => {

    const data = await fetch('/api/evaluador', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)

    });

    const datajson = await data.json();



};

const deleteItem = async (id) => {

    const data = await fetch(`/api/evaluador?id=${id}`, {
        method: 'DELETE'
    });

    const datajson = await data.json();

};


const updateTable = async () => {

    const data = await fetch(`/api/evaluador`, {
        method: 'GET'
    });

    const datajson = await data.json();

    console.log(datajson)

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
                                <td>${datajson[i].nombre}</td>
                                <td>${datajson[i].apellido}</td>
                                <td>${datajson[i].empresa_id}</td>
                                <td>${datajson[i].empresa_name}</td>
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
            inputsModal['nombre'].value,
            inputsModal['apellido'].value,
            selectModal['empresa_id'].value
            ) {

        return false;

    } else {

        return true;

    }

};

const verifyUpdate = () => {

    if (
            inputsModal['id'].value,
            inputsModal['nombre'].value,
            inputsModal['apellido'].value,
            selectModal['empresa_id'].value
            ) {

        return false;

    } else {

        return true;

    }

};


//ROL


const updateRolSelect = async () => {

    const data = await fetch(`/api/empresa`, {
        method: 'GET'
    });

    const datajson = await data.json();



    const selectrol = document.getElementById('select_empresa');

    if (datajson.length === 0) {

        return `<option value="">NO HAY </option>`;
    }
    ;

    let contentUpdated = `<option value=""> SELECCIONE EMPRESA</option>`;

    for (let i = 0; i < datajson.length; i++) {

        contentUpdated += `<option value="${datajson[i].id}"> ${datajson[i].nombre}</option>`;

    }
    ;

    selectrol.innerHTML = contentUpdated;

};



