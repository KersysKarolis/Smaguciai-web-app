function generateDecor(containerId, count){
const container = document.getElementById(containerId);
const colors = [
        "#e91e63", // pink
        "#4b5ca8", // blue
        "#8e44ad", // purple
        "#ef4444", // red
        "#f79a0b"  // orange
];
for(let i = 0; i< count; i++){
const isStar = Math.random()> 0.7;
if(isStar){
const star = document.createElement("div");
star.classList.add("star");
star.innerHTML = "*";

star.style.left = Math.random() *100 + "%";
star.style.top = Math.random() *100 + "%";
star.style.color = colors[Math.floor(Math.random() * colors.length)];
star.style.fontSize = (15 + Math.random()*25) + "px";
container.appendChild(star);
} else{
const dot = document.createElement("div");
dot.classList.add("dot");
const size = 8 + Math.random() *20;
dot.style.width = size + "px";
dot.style.height = size + "px";
dot.style.left = Math.random() * 100 + "%";
dot.style.top = Math.random() * 100 +"%";
dot.style.backgroundColor = colors[Math.floor(Math.random() * colors.length)];

container.appendChild(dot);
}
}
}
document.addEventListener("DOMContentLoaded", function (){
generateDecor("hero-decor", 25);
generateDecor("about-decor", 20);
})