import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "/",
    name: "main",
    component: () => import("@/views/MainView.vue"),
    children: [
      {
        path: "",
        name: "landing-page",
        component: () =>
          import("@/components/pages/LandingMainPage/LandingPage.vue"),
      },
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
        ],
      },
      {
        path: "login",
        name: "login",
        component: () => import("@/components/pages/Users/UserLogin.vue"),
        meta: { hideNavbar: true },
      },
      {
        path: "register",
        name: "register",
        component: () => import("@/components/pages/Users/UserRegister.vue"),
        meta: { hideNavbar: true },
      },
      {
        path: "cart",
        name: "cart",
        component: () => import("@/components/pages/Cart/CartPage.vue"),
        meta: { hideNavbar: false },
      },
      {
        path: "create-order",
        name: "create-order",
        component: () => import("@/components/pages/Order/OrderPage.vue"),
        meta: { hideNavbar: false },
      },
      {
        path: "forgot-password",
        name: "forgot-password",
        component: () => import("@/components/pages/Users/ForgotPassword.vue"),
        meta: { hideNavbar: true },
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
