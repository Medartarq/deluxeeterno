document.addEventListener("DOMContentLoaded", () => {
  const nav = document.getElementById("navbarContent");
  const adminNav = document.getElementById("adminNavbarContent");
  const navLinks = document.querySelectorAll("#navbarContent .nav-link, #adminNavbarContent .nav-link");

  navLinks.forEach(link => {
    link.addEventListener("click", () => {
      const target = link.closest(".navbar-collapse");
      if (target?.classList.contains("show")) {
        bootstrap.Collapse.getOrCreateInstance(target).hide();
      }
    });
  });

  document.querySelectorAll("[data-confirm]").forEach(form => {
    form.addEventListener("submit", event => {
      if (!window.confirm(form.dataset.confirm)) {
        event.preventDefault();
      }
    });
  });

  if (nav && adminNav) {
    return;
  }
});
