import { createApp } from "vue";
import App from "./App.vue";
import "bootstrap";
import "bootstrap/dist/js/bootstrap";
import "bootstrap/dist/css/bootstrap.css";
import "@popperjs/core/dist/umd/popper.min.js";
import "bootstrap/dist/js/bootstrap.bundle.min";
import "bootstrap-icons/font/bootstrap-icons.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import "@/styles/main.css";
import router from "./router";
import store from "./store/index";
import ProductQuickView from "@/components/common/UI/ProductDetail/ProductQuickView.vue";
import FloatingInput from "@/components/common/UI/Defaults/FloatingInput.vue";
import ComboboxInput from "@/components/common/UI/Defaults/ComboboxInput.vue";
import TextArea from "@/components/common/UI/Defaults/TextArea.vue";
import Toast, { POSITION } from "vue-toastification";
import "vue-toastification/dist/index.css";

const app = createApp(App);

const options = {
  position: POSITION.TOP_RIGHT,
  timeout: 5000,
  hideProgressBar: false,
  closeOnClick: true,
  pauseOnHover: true,
  draggable: true,
  draggablePercent: 0.6,
  showCloseButtonOnHover: false,
  icon: true,
  rtl: false,
  type: 'info'
};

app.use(router);
app.use(store);
app.use(Toast, options);

app.component("product-quick-view", ProductQuickView);
app.component("floating-input", FloatingInput);
app.component("combobox-input", ComboboxInput);
app.component("text-area",TextArea)

app.mount("#app");
