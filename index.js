// Smooth scrolling for navbar links
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
  anchor.addEventListener('click', function (e) {
    e.preventDefault();
    const target = document.querySelector(this.getAttribute('href'));
    if (target) {
      window.scrollTo({
        top: target.offsetTop - 70, // offset for sticky navbar
        behavior: 'smooth'
      });
    }
  });
});

// Mobile menu toggle (if you later add a hamburger icon)
const navToggle = document.createElement("div");
navToggle.classList.add("nav-toggle");
navToggle.innerHTML = "&#9776;"; // hamburger icon
document.querySelector(".nav-container").prepend(navToggle);

const navLinks = document.querySelector(".nav-links");

navToggle.addEventListener("click", () => {
  navLinks.classList.toggle("active");
});

// Close menu on link click (mobile view)
navLinks.querySelectorAll("a").forEach(link => {
  link.addEventListener("click", () => {
    navLinks.classList.remove("active");
  });
});
