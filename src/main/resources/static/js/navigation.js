
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

const menuToggle = document.querySelector('.mobile-menu-toggle');
const mobileNav = document.getElementById('mobileNav');
const overlay = document.querySelector('.overlay');
// Mobile menu toggle function
window.toggleMobileMenu = function() {



if (!menuToggle || !mobileNav || !overlay) {
console.error('Mobile menu elements not found');
return;
}
const isActive = mobileNav.classList.contains('active');
menuToggle.classList.toggle('active');
mobileNav.classList.toggle('active');
overlay.classList.toggle('active');
if(!isActive){
document.body.style.overflow = 'hidden';
document.body.style.position = '';
} else{
document.body.style.overflow = '';
document.body.style.position = '';
}

if(!isActive){
setTimeout(() =>{
const closeButton = mobileNav.querySelector('.mobile-nav-close');
if(closeButton) closeButton.focus();
}, 100);
}
};


// Close mobile menu when clicking outside
document.addEventListener('click', (e) => {
if (!mobileNav || !menuToggle) return;
if (
!mobileNav.contains(e.target) &&
!menuToggle.contains(e.target) &&
mobileNav.classList.contains('active')) {
toggleMobileMenu();
}
});

// Close mobile menu on escape key
document.addEventListener('keydown', (e) => {
if (e.key === 'Escape'&& mobileNav.classList.contains('active')) {
toggleMobileMenu();
}
});
});
