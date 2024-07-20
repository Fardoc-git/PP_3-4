async function theModal(form, modal, id){
    modal.show();
    let user = await getUserId(id);
    form.id.value = user.id;
    form.firstName.value = user.firstName;
    form.lastName.value = user.lastName;
    form.roles.value = user.roles;
}