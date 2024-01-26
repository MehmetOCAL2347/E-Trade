import { createApp } from "vue";
import App from "./App.vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "@/styles/main.css";
import router from "./router";
import store from "./store";
import api from "@/services/api.js"

// import axios from "axios";
// import config from "@/config";

// axios.create({
//   baseURL: config.baseUrl,
// });

createApp(App).use(store).use(router).use(api).mount("#app");
