// function to change main content background to fade and open subnav-content
document.addEventListener("DOMContentLoaded", function () {
  let subnavBtns = document.getElementsByClassName("subnavbtn");
  let mainContent = document.getElementsByClassName("main-content");
  for (let i = 0; i < subnavBtns.length; i++) {
    subnavBtns[i].addEventListener("click", function () {
      let subnavContent = this.nextElementSibling;

      // Close all other open subnav-content
      let allSubnavContents = document.querySelectorAll(".subnav-content");
      for (let j = 0; j < allSubnavContents.length; j++) {
        if (allSubnavContents[j] !== subnavContent) {
          allSubnavContents[j].style.display = "none";
        }
      }

      // Toggle the display of the clicked subnav-content
      if (subnavContent.style.display === "flex") {
        subnavContent.style.display = "none";
        mainContent[0].style.opacity = "1.0";
        mainContent[0].style.backgroundColor = "";
      } else {
        subnavContent.style.display = "flex";

        mainContent[0].style.backgroundColor = "rgba(0, 0, 0, 0.5)";
        mainContent[0].style.opacity = "0.5";
        subnavContent.style.backgroundColor = "white";
      }
    });
  }
});

// function to display subnav with mobile and tablet viewport
function toggleSubnav() {
  var subnavContents = document.querySelectorAll(".subnav-container");

  subnavContents.forEach(function (content) {
    if (content.style.display === "none") {
      content.style.display = "flex";
    } else {
      content.style.display = "none";
    }
  });
}

// Event listener for the navbar-mobile-navigate button
document.addEventListener("DOMContentLoaded", function () {
  var navbarMobileNavigate = document.querySelector(".navbar-mobile-navigate");

  if (navbarMobileNavigate) {
    navbarMobileNavigate.addEventListener("click", toggleSubnav);
  }
});

let slideIndex = 1;
showSlides(slideIndex);

// Next/previous controls
function plusSlides(n) {
  showSlides((slideIndex += n));
}

// Thumbnail image controls
function currentSlide(n) {
  showSlides((slideIndex = n));
}

function showSlides(n) {
  let i;
  let slides = document.getElementsByClassName("mySlides");
  let dots = document.getElementsByClassName("dot");
  if (n > slides.length) {
    slideIndex = 1;
  }
  if (n < 1) {
    slideIndex = slides.length;
  }
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex - 1].style.display = "block";
  dots[slideIndex - 1].className += " active";
}
