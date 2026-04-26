console.log("ModalManager loaded");
window.modalManager = {
stack:[],
init(){
//open buttons
document.querySelectorAll("[data-open]").forEach(btn =>{
btn.addEventListener("click", e=>{
e.stopPropagation();
this.open(btn.dataset.open);
});
});
document.querySelectorAll("[data-close]").forEach(btn => {
btn.addEventListener("click", ()=>  modalManager.close());
});

document.querySelectorAll(".modal").forEach(modal =>{
modal.addEventListener("click", ()=> modalManager.close());

const content =modal.querySelector(".modal-content");
if(content){
content.addEventListener("click", (e) => e.stopPropagation());
}
});
//esc
document.addEventListener("keydown", e =>{
if(e.key === "Escape") modalManager.close();
});
},
open(name){
const modal = document.querySelector(`.modal[data-modal="${name}"]`);
if(!modal) return;
modal.classList.remove("hidden");
this.stack.push(modal);
},
close() {
const modal = this.stack.pop();
if(modal){
modal.classList.add("hidden");
}
console.log(window.modalManager);
}
}
