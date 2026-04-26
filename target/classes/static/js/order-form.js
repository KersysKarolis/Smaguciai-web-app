document.addEventListener("DOMContentLoaded", () =>{
const form = document.querySelector("form");
const startInput = document.querySelector("#startTime");
const characterSelect = document.querySelector("[name='character']");
const genreSelect = document.querySelector("[name='orderGenre']");
const phoneInput = document.querySelector("#phoneNumber");
const addressInput = document.querySelector("[name='location']");
const email = document.querySelector("[name='email']");
const childName = document.querySelector("[name='childName']");
if(startInput){
const now = new Date();
const formatted = now.toISOString().slice(0, 16);
startInput.min = formatted;
}
if(phoneInput){
phoneInput.addEventListener("input", function(){
this.value= this.value.replace(/[^0-9+]/g, "");
});
}

if(form){
form.addEventListener("submit", function (e){
let hasError = false;
const now = new Date();
if(startInput){
const selected = new Date(startInput.value);
 clearFieldError(startInput);
 if(!startInput.value){
  showFieldError(startInput, "Pasirinkite laiką");
 hasError=true
 }
 else if(selected < now){
 showFieldError(startInput, "Laikas pasibaigė");
hasError=true;
 }
}
if(childName){
clearFieldError(childName);
if(!childName.value){
showFieldError(childName, "Įveskite vaiko vardą")
hasError=true;
}}
 if(characterSelect){
 clearFieldError(characterSelect);
 if(!characterSelect.value){
 showFieldError(characterSelect, "Pasirinkite personažą");
 hasError = true;
 }
 }
 if(genreSelect){
 clearFieldError(genreSelect);
 if(!genreSelect.value){
 showFieldError(genreSelect, "Pasirinkite šventės žanrą");
 hasError=true;
 }
 }
 if(addressInput){
 clearFieldError(addressInput);
 const regex= /^[^,]+,\s*[^,]+\s+\d+/;
 if(!addressInput.value.trim()){
 showFieldError(addressInput, "Iveskite adresa");
 hasError=true;
 } else if(!regex.test(addressInput.value)){
 showFieldError(addressInput, "Formatas: Miestas, Gatvė, pastato nr.");
 hasError=true;
 }
 }
 if(phoneInput){
 clearFieldError(phoneInput);
 const regexp = /^\+?[0-9]{9,15}$/;
 if(!phoneInput.value.trim()){
 showFieldError(phoneInput, "Įveskite telefono numerį");
 hasError=true;
 } else if (!regexp.test(phoneInput.value)){
 showFieldError(phoneInput, "Numeris turi buti bent 9 skaitmenu");
 hasError=true;
 }
 }
 if(email){
 clearFieldError(email);
 const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
 if(!email.value.trim()){
 showFieldError(email, "Įveskite elektroninį paštą");
 hasError=true;
 }else if(!emailRegex.test(email.value)){
 showFieldError(email, "Neteisingai ivestas el pastas");
 hasError=true;
 }
 }
 if (hasError){
 e.preventDefault();
 }
})};
document.querySelectorAll("input, select").forEach(e1 => {
e1.addEventListener("input", () => clearFieldError(e1));
e1.addEventListener("change", () => clearFieldError(e1));
});
highlightServerErrors();
});

function showFieldError (input, message){
input.classList.add("error");
let errorDiv = document.querySelector(`.field-error[data-for='${input.name}']`);
if(!errorDiv){
errorDiv = document.createElement("div");
errorDiv.className ="field-error";
errorDiv.dataset.for = input.name;
input.insertAdjacentElement("afterend", errorDiv);
}
errorDiv.textContent = message;
}
function clearFieldError (input){
input.classList.remove("error");
const errorDiv = document.querySelector(`.field-error[data-for='${input.name}']`);
if(errorDiv){
errorDiv.remove();
}
}
function highlightServerErrors (){
document.querySelectorAll(".field-error").forEach(e1 => {
const input = e1.previousElementSibling;
if(input){
input.classList.add("error");
}
});
}


