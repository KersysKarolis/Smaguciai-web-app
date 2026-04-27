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
// Mobile menu toggle function
window.toggleMobileMenu = function() {
const menuToggle = document.querySelector('.mobile-menu-toggle');
const mobileNav = document.querySelector('.mobile-nav');
const overlay = document.querySelector('.overlay');

menuToggle.classList.toggle('active');
mobileNav.classList.toggle('active');
overlay.classList.toggle('active');

// Prevent body scroll when menu is open
if (mobileNav.classList.contains('active')) {
document.body.style.overflow = 'hidden';
} else {
document.body.style.overflow = '';
}
};

// Close mobile menu when clicking outside
document.addEventListener('click', (e) => {
const mobileNav = document.querySelector('.mobile-nav');
const menuToggle = document.querySelector('.mobile-menu-toggle');
const overlay = document.querySelector('.overlay');

if (!mobileNav.contains(e.target) && !menuToggle.contains(e.target) && mobileNav.classList.contains('active')) {
toggleMobileMenu();
}
});

// Close mobile menu on escape key
document.addEventListener('keydown', (e) => {
if (e.key === 'Escape') {
const mobileNav = document.querySelector('.mobile-nav');
if (mobileNav.classList.contains('active')) {
toggleMobileMenu();
}
}
}
);