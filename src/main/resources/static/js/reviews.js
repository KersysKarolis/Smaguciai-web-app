document.addEventListener("DOMContentLoaded", () => {
const reviewsNode = document.querySelector('.reviews-embla');
if(!reviewsNode) return;
const reviewsEmbla = EmblaCarousel(reviewsNode, {
loop:true,
align:'start',
dragFree: true
});

const previousBtn = document.querySelector('.reviews-prev');
const nextBtn = document.querySelector('.reviews-next');

previousBtn?.addEventListener('click', ()=> reviewsEmbla.scrollPrev());
nextBtn?.addEventListener('click', ()=> reviewsEmbla.scrollNext());

});



