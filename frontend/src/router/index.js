import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("@/views/MainView.vue"),
  },
  {
    path: "/products",
    name: "products",
    component: () => import("@/components/pages/Products/ProductsPage.vue"),
  },
  {
    path: "/products/pd/:code",
    name: "productDetail",
    component: () =>
      import("@/components/pages/Products/ProductDetailPage.vue"),
  },
  {
    path: "/products/query",
    name: "filteredProducts",
    component: () => import("@/components/pages/Products/ProductsPage.vue")
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
