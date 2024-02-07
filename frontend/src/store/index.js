import { createStore } from "vuex";
import ProductModule from '@/store/Product/index';
import CatalogModule from '@/store/Catalog/index';

const store = createStore({
    modules: {
        prouductModule: ProductModule,
        catalogModule: CatalogModule
    }
})

export default store;