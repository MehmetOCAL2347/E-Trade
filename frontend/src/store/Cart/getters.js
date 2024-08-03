export default {
    getCartCount(state){
        return state.cart.length;
    },
    getExistCart(state){
        return state.cart;
    },
    getDetailCartProducts(state){
        return state.cartProductDetail;
    },
    getDiscount(state){
        return state.discount;
    }
}