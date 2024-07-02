import { createApp } from "vue";
import App from "./App.vue";
import 'bootstrap'
import 'bootstrap/dist/js/bootstrap';
import 'bootstrap/dist/css/bootstrap.css'
import '@popperjs/core/dist/umd/popper.min.js';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import 'bootstrap-icons/font/bootstrap-icons.css';
import "@/styles/main.css";
import router from "./router";
import store from './store/index';


const app = createApp(App);

app.use(router);
app.use(store);


app.mount("#app");