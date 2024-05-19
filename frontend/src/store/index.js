import { createStore } from "vuex";
import ProductModule from '@/store/Product/index';
import CatalogModule from '@/store/Catalog/index';
import UserModule from '@/store/User/index';

const store = createStore({
    modules: {
        prouductModule: ProductModule,
        catalogModule: CatalogModule,
        userModule: UserModule
    }
})

export default store;