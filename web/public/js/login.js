const formLogin = document.querySelector("#login");

const on = async (element, event, selector, handler) => {

    element.addEventListener(event, async e => {
        if (e.target.closest(selector)) {
            await handler(e);
        }
    });

};

on(document, 'submit', '#createuser', async e => {

    e.preventDefault();

   
    if (verifyCreate()) {

        return;

    };

    await createItem(data);

    await updateTable();

    createTab.toggle();

    createForm.reset();

});