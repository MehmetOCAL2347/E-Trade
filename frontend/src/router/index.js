import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "/",
    name: "main",
    component: () => import("@/views/MainView.vue"),
    children: [
      {
        path: "products",
        name: "products",
        component: () => import("@/components/pages/Products/ProductsPage.vue"),
        children: [
          {
            path: "",
            name: "all-products",
            props: true,
            component: () =>
              import("@/components/pages/Products/AllProducts.vue"),
          },
          // {
          //   path: "query",
          //   name: "filtered-products",
          //   props: true,
          //   component: () =>
          //     import("@/components/pages/Products/AllProducts.vue"),
          // },
        ],
      },
    ],
  },

  {
    path: "/products/pd/:code",
    name: "productDetail",
    component: () =>
      import("@/components/pages/Products/ProductDetailPage.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
