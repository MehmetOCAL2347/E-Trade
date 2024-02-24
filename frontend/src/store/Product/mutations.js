export default {
    setAllProducts(state, payload){
        state.products = payload;
    },
    setDetailProduct(state, payload){
        state.detailProduct = payload;
    },
    setProductsWithParams(state, payload){
        state.products = payload;
    }
}