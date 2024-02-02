import { createApp } from "vue";
import App from "./App.vue";
import "bootstrap/dist/css/bootstrap.min.css";
import 'bootstrap/dist/js/bootstrap.min.js';
import '@popperjs/core/dist/umd/popper.min.js';
import "@/styles/main.css";
import router from "./router";
import store from './store/index';


createApp(App)
.use(router)
.use(store)
.mount("#app");