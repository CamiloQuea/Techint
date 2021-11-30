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
    await updateEvaluadorSelect();
};

on(document, 'keyup', '#filtro', e => {

    filterTable();

});

const editBtn = async(btn) => {

    modalTabForm.reset();

    modaltitle.innerHTML = 'MODIFICAR EVALUACION';

    fila = btn.parentNode.parentNode.parentNode;

    const elemento = fila.children;

    const id = elemento[0].innerHTML;

    const conductor_id = elemento[1].innerHTML;

    const evaluador_id = elemento[4].innerHTML;

    const notaExamTeo = elemento[7].innerHTML;

    const notaExamPrac = elemento[8].innerHTML;

    inputsModal['id'].value = id;

    selectModal['conductor_id'].value = conductor_id;

    selectModal['evaluador_id'].value = evaluador_id;

    inputsModal['notaExamTeo'].value = notaExamTeo;

    inputsModal['notaExamPrac'].value = notaExamPrac;

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
            evaluador: selectModal['evaluador_id'].value,
            notaExamTeo: parseFloat(inputsModal['notaExamTeo'].value),
            notaExamPrac: parseFloat(inputsModal['notaExamPrac'].value)


        };

        console.log(data);


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
            conductor: selectModal['conductor_id'].value,
            evaluador: selectModal['evaluador_id'].value,
            notaExamTeo: parseFloat(inputsModal['notaExamTeo'].value),
            notaExamPrac: parseFloat(inputsModal['notaExamPrac'].value)

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


    if (confirm('ESTA SEGURO DE ELIMINAR?')) {

        await deleteItem(conductor_id);

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

    const data = await fetch('/api/conductor/evaluacion', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)

    });

    const datajson = await data.json();

};

const updateItem = async (body) => {

    const data = await fetch('/api/conductor/evaluacion', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)

    });

    const datajson = await data.json();



};

const deleteItem = async (id) => {

    const data = await fetch(`/api/conductor/evaluacion?id=${id}`, {
        method: 'DELETE'
    });

    const datajson = await data.json();

};


const updateTable = async () => {

    const data = await fetch(`/api/conductor/evaluacion`, {
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
                                <td>${datajson[i].evaluacion_id}</td>
                                <td>${datajson[i].conductor_id}</td>
                                <td>${datajson[i].conductor_nombre}</td>
                                <td>${datajson[i].conductor_apellido}</td>
                                <td>${datajson[i].evaluador_id}</td>
                                <td>${datajson[i].evaluador_nombre}</td>
                                <td>${datajson[i].evaluador_apellido}</td>
                                <td>${datajson[i].notaExamTeo}</td>
                                <td>${datajson[i].notaExamPrac}</td>
                                
         
                                <td>
        <div class="d-flex justify-content-evenly">
        
                                    <button class=" btn btn-link p-0" onClick="deleteBtn(this)">
                                        <i class="fas fa-trash text-danger"></i> 
                                    </button>  
                                    <button class=" btn btn-link p-0" onClick="editBtn(this)">
                                        <i class="fas fa-pen text-warning"></i>
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
            selectModal['evaluador_id'].value,
            inputsModal['notaExamTeo'].value,
            inputsModal['notaExamPrac'].value
            ) {

        return false;

    } else {

        return true;

    }

};

const verifyUpdate = () => {

    if (
            inputsModal['id'].value,
            selectModal['conductor_id'].value,
            selectModal['evaluador_id'].value,
            inputsModal['notaExamTeo'].value,
            inputsModal['notaExamPrac'].value
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

const updateEvaluadorSelect = async () => {

    const data = await fetch(`/api/evaluador`, {
        method: 'GET'
    });

    const datajson = await data.json();



    const selectrol = document.getElementById('select_evaluador');

    if (datajson.length === 0) {

        return `<option value="">NO HAY EVALUADORES</option>`;
    }
    ;

    let contentUpdated = `<option value=""> SELECCIONE EVALUADOR</option>`;

    for (let i = 0; i < datajson.length; i++) {

        contentUpdated += `<option value="${datajson[i].id}"> ${datajson[i].nombre} ${datajson[i].apellido}</option>`;

    }
    ;

    selectrol.innerHTML = contentUpdated;

};

