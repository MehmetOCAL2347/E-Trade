export default {
    setAllProducts(state, payload){
        console.log(payload);
        state.products = payload;
    },
    setDetailProduct(state, payload){
        state.detailProduct = payload;
    }
}