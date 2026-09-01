document.addEventListener("DOMContentLoaded", () => {
  const filters = document.querySelectorAll(".product-filter");
  const products = document.querySelectorAll(".product-item");

  filters.forEach(button => {
    button.addEventListener("click", () => {
      filters.forEach(btn => btn.classList.remove("active"));
      button.classList.add("active");

      const filter = button.dataset.filter;
      products.forEach(product => {
        const visible = filter === "all" || product.dataset.category === filter;
        product.classList.toggle("d-none", !visible);
      });
    });
  });

  const productModal = document.getElementById("productModal");
  productModal?.addEventListener("show.bs.modal", event => {
    const trigger = event.relatedTarget;
    const productName = trigger?.dataset.product || "Producto";
    document.getElementById("productModalTitle").textContent = productName;
  });

  const contactForm = document.getElementById("contactForm");
  contactForm?.addEventListener("submit", event => {
    event.preventDefault();
    const alert = document.getElementById("formAlert");
    alert.classList.remove("d-none");
    contactForm.reset();
    setTimeout(() => alert.classList.add("d-none"), 4000);
  });

  const navLinks = document.querySelectorAll("#navbarContent .nav-link");
  navLinks.forEach(link => {
    link.addEventListener("click", () => {
      const nav = document.getElementById("navbarContent");
      if (nav.classList.contains("show")) {
        bootstrap.Collapse.getOrCreateInstance(nav).hide();
      }
    });
  });
});
