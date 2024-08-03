import { createStore } from "vuex";
import ProductModule from '@/store/Product/index';
import CatalogModule from '@/store/Catalog/index';
import UserModule from '@/store/User/index';
import CartModule from '@/store/Cart/index';

const store = createStore({
    modules: {
        prouductModule: ProductModule,
        catalogModule: CatalogModule,
        userModule: UserModule,
        cartModule: CartModule
    }
})

export default store;