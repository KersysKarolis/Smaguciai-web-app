
const emblaNode = document.querySelector('.embla');
const gallery = document.querySelector('.personage__gallery__container');
const carousel = document.querySelector('.carousel__background');
//const viewport = emblaNode.querySelector('.embla__viewport');
//const emblaApi = EmblaCarousel(viewport, { align: 'center' })
let embla = null;
let wheelAccum = 0;
function initEmbla(){
if(window.innerWidth <= 1250){
gallery.style.display = "none";
carousel.style.display = "flex";
if(!embla){
embla = EmblaCarousel(emblaNode,{
loop:true,
align: 'start',
dragFree: true,
containScroll: false
});

emblaNode.addEventListener('wheel', wheelHandler,{passive:false});
}
}else{
gallery.style.display = "flex";
carousel.style.display = "none";
if(embla){
emblaNode.removeEventListener('wheel', wheelHandler);
embla.destroy();
embla=null;
}
}
}
function wheelHandler(e){
    if(Math.abs(e.deltaY)> Math.abs(e.deltaX)){
        e.preventDefault();
        wheelAccum += e.deltaY;
        if(wheelAccum > 100){
            embla.scrollNext();
            wheelAccum=0;
        }  else if(wheelAccum < -100){
                embla.scrollPrev();
                   wheelAccum=0;
               }
        }
}
initEmbla();
window.addEventListener('resize', initEmbla);