const modalTabForm = document.querySelector('#modalTabForm');

const inputsModal = modalTabForm.getElementsByTagName('input');

const selectModal = modalTabForm.getElementsByTagName('select');

const modaltitle = document.getElementById('modaltitle');


const modalTab = new bootstrap.Modal(document.getElementById('modalTab'), {
    keyboard: false
});

window.onload = async () => {

    await updateTable();

};

on(document, 'keyup', '#filtro', e => {

    filterTable();

});

const editBtn = async(btn) => {

    modalTabForm.reset();

    modaltitle.innerHTML = 'EDITAR ROL';

    fila = btn.parentNode.parentNode.parentNode;

    const elemento = fila.children;

    const id = elemento[0].innerHTML;

    const nombre = elemento[1].innerHTML;

    const ruc = elemento[2].innerHTML;

    inputsModal['id'].value = id;

    inputsModal['nombre'].value = nombre;

    inputsModal['ruc'].value = ruc;

    inputsModal['tipo'].value = 'actualizar';

    modalTab.toggle();

};

on(document, 'click', '#addBtn', e => {

    modalTabForm.reset();

    modaltitle.innerHTML = 'AGREGAR ROL';

    inputsModal['tipo'].value = 'crear';

    modalTab.toggle();

});

on(document, 'submit', '#modalTabForm', async e => {

    e.preventDefault();

    const tipo = inputsModal['tipo'].value;



    if (tipo === 'crear') {
        const data = {

            nombre: inputsModal['nombre'].value,
            ruc: inputsModal['ruc'].value

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
            ruc: inputsModal['ruc'].value

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

    const id = elemento[0].innerHTML;


    if (confirm('ESTA SEGURO DE ELIMINAR?')) {

        await deleteItem(id);

    }

    await updateTable();

}
;

const createItem = async (body) => {

    const data = await fetch('/api/empresa', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)

    });

    const datajson = await data.json();

};

const updateItem = async (body) => {

    const data = await fetch('/api/empresa', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)

    });

    const datajson = await data.json();



};

const deleteItem = async (id) => {

    const data = await fetch(`/api/empresa?id=${id}`, {
        method: 'DELETE'
    });

    const datajson = await data.json();

};


const updateTable = async () => {

    const data = await fetch(`/api/empresa`, {
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
                                <td>${datajson[i].nombre}</td>
        <td>${datajson[i].ruc}</td>
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
            inputsModal['ruc'].value

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
            inputsModal['ruc'].value

            ) {

        return false;

    } else {

        return true;

    }

};
