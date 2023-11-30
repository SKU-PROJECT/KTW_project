const navMenu = document.getElementById('nav-menu');
const navToggle = document.getElementById('nav-toggle');
const navClose = document.getElementById('nav-close');

/* SHOW MENU */


/* MENU SHOW */
if(navToggle){
    navToggle.addEventListener('click', ()=> {
        navMenu.classList.add('show-menu')
    })
}

/* MENU HIDDEN */
if(navClose){
    navClose.addEventListener('click', ()=>{
        navMenu.classList.remove('show-menu')
    })
}

/* REMOVE MENU MOBILE */
const navLink = document.querySelectorAll('.nav__link')

function linkAction(){
    const navMenu = document.getElementById('nav-menu')
    navMenu.classList.remove('show-menu')
}
navLink.forEach(n=>n.addEventListener('click', linkAction))

/* CHANGE BACKGROUND HEADER */
function scrollHeader(){
    const nav = document.getElementById('header')
    if (this.scrollY >= 200) header.classList.add('scroll-header'); else header.classList.remove('scroll-header')
}
window.addEventListener('scroll', scrollHeader)

/* SWIPER DISCOVER */
var swiper = new Swiper(".discover__container", {
    effect: "coverflow",
    grabCursor: true,
    centeredSlides: true,
    loop: true,
    slidesPerView: "auto",
    coverflowEffect: {
        rotate: 50,
        stretch: 0,
        depth: 100,
        modifier: 1,
        slideShadows: true,
    },
    pagination: {
        el: ".swiper-pagination",
    },
});

/* SWIPER DISCOVER */
// var swiper = new Swiper(".discover__container", {
//     effect: "coverflow",
//     grabCursor: true,
//     centeredSlides: true,
//     slidesPerView: "auto",
//     loop: true,
//     spaceBetween: 32,
//     coverflowEffect: {
//       rotate: 0,
//     },

//   });

/* VIDEO */
const videoFile = document.getElementById('video-file');
const videoButton = document.getElementById('video-button');
const videoIcon = document.getElementById('video-icon');

function playPause(){
    if(videoFile.paused){
        videoFile.play()
        videoIcon.classList.add('ri-pause-line')
        videoIcon.classList.remove('ri-play-line')
    }
    else{
        videoFile.pause()
        videoIcon.classList.add('ri-play-line')
        videoIcon.classList.remove('ri-pause-line')
    }
}
videoButton.addEventListener('click', playPause)

function finalVideo(){
    videoIcon.classList.add('ri-play-line')
    videoIcon.classList.remove('ri-pause-line')
}
videoFile.addEventListener('ended', finalVideo)