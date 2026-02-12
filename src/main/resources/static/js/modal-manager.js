console.log("ModalManager loaded");
const ModalManager = {
activeModal:null,
init(){
//open buttons
document.querySelectorAll("[data-open]").forEach(btn =>{
btn.addEventListener("click", e=>{
e.stopPropagation();
this.open(btn.dataset.open);
});
});
document.querySelectorAll("[data-close]").forEach(btn => {
btn.addEventListener("click", ()=>this.close());
});

document.querySelectorAll(".modal").forEach(modal =>{
modal.addEventListener("click", ()=> this.close());

const content =modal.querySelector(".modal-content");
if(content){
content.addEventListener("click", (e) => e.stopPropagation());
}
});
//esc
document.addEventListener("keydown", e =>{
if(e.key === "Escape") this.close();
});
},
open(name){
this.close();
const modal = document.querySelector(`.modal[data-modal="${name}"]`);
if(!modal) return;
modal.classList.remove("hidden");
this.activeModal = modal;
},
close() {
if(this.activeModal){
this.activeModal.classList.add("hidden");
this.activeModal=null;
}
}

}