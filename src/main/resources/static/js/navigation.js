document.addEventListener("DOMContentLoaded", () => {
const buttons = document.querySelectorAll("button[data-url]");

buttons.forEach(button => {
button.addEventListener("click", () => {
const target = button.getAttribute("data-url");
if(target){
window.location.href = target;
}
});
});

}
);