console.log("ADMIN ORDER VIEW JS LOADED");
function editButton(){
console.log("ENABLED EDIT MODE");

    document.querySelectorAll(".view-mode").forEach(e => e.style.display = "none");
    document.querySelectorAll(".edit-mode").forEach(e => e.style.display = "block");
    document.getElementById("editButton").style.display = "none";
    document.getElementById("save-btn").style.display = "inline-block";
    document.getElementById("cancel-btn").style.display ="inline-block";
    }
    function cancelButton(){
        document.querySelectorAll(".view-mode").forEach(e => e.style.display = "inline");
        document.querySelectorAll(".edit-mode").forEach(e => e.style.display = "none");
        document.getElementById("editButton").style.display = "inline-block";
        document.getElementById("save-btn").style.display = "none";
        document.getElementById("cancel-btn").style.display = "none";
           }
         function deleteButton(){
            document.getElementById("deleteButton").addEventListener("click", function() {
            if(!confirm("Ar tikrai ištrinti užsakymą?")) return;
            const orderId = document.getElementById("orderId").value;
            fetch(`/api/admin/orders/${orderId}`, {
            method: 'DELETE'
            headers: csrfHeaders()
            })
            .then(r => {
            if (!r.ok) throw new Error ("Ištrinti nepavyko");
            window.location.href="/admin/orders";
            })
            .catch (err => alert("Klaida trinant uzsakyma"));
            })
            }
            function saveButton(){
                const orderId = document.getElementById("orderId").value;
                const data ={};
                document.querySelectorAll(".edit-mode").forEach(input => {
                data[input.name] = input.value;
                console.log("data:", data);
                });
                fetch(`/api/admin/orders/${orderId}`, {
                method: 'PUT',
                headers: {'content-type': 'application/json',
                ...csrfHeaders()},
                body: JSON.stringify(data)
                })

                .then(r => {
                  console.log("FETCH STATUS:", r.status);
                                console.log("Response:" , r);
                if(!r.ok) throw new Error("Saugojimas nepavyko");
                return r.json();
                 })
                 .then(updated => {
                 location.reload();
                 })
                 .catch (err => alert("Klaida saugojant"));
                }

                function collectFormData(){
                    const data = {};
                    document.querySelectorAll(".edit-mode[name]").forEach(input => {
                    data[input.name ] = input.value;
                    });
                    return data;
                    }
                    function updateViewMode(order){
                    document.querySelectorAll(".view-mode").forEach(span => {
                    const field = span.closest(".field");
                    const input = field.querySelector("[name]");
                    if(input&&order[input.name] !== undefined){
                    span.innerText = order[input.name];
                    }
                    });
                    }
document.addEventListener("DOMContentLoaded", function() {
console.log("DOM READY");
const saveBtn = document.getElementById("save-btn");
const editBtn = document.getElementById("editButton");
const deleteBtn = document.getElementById("deleteButton");
const cancelBtn = document.getElementById("cancel-btn");

if(!editBtn){
console.warn("Edit mygtukas nerastas!");
return;
}

saveBtn.addEventListener("click", saveButton);
editBtn.addEventListener("click", editButton);
deleteBtn.addEventListener("click", deleteButton);
cancelBtn.addEventListener("click", cancelButton);
})