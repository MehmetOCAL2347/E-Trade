import { createStore } from "vuex";
import ProductModule from '@/store/Product/index';

const store = createStore({
    modules: {
        prouductModule: ProductModule
    }
})

export default store;