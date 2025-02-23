let localStorageTitle = "product-in-cart";
let discountCodeTitle = "discountCode"
export default {
  addToCart(state, payload) {
    // Retrieve the existing cart from local storage or initialize an empty array
    let existCart = JSON.parse(localStorage.getItem(localStorageTitle)) || [];

    // Find if the product already exists in the cart
    let product = existCart.find((item) => item.id === payload.id);

    if (product) {
      // If the product already exists, increase the quantity
      product.quantity += payload.quantity;
      state.cart = existCart;
    } else {
      // If the product does not exist, add it to the cart
      state.cart.push(payload);
    }
    localStorage.setItem(localStorageTitle, JSON.stringify(state.cart));
  },
  updateProductCount(state, payload) {
    let existCart = JSON.parse(localStorage.getItem(localStorageTitle)) || [];

    // Find if the product already exists in the cart
    let product = existCart.find((item) => item.id === payload.id);

    let newQuantity = product.quantity + payload.quantity;

    if (newQuantity > 0 && newQuantity <= 10) {
      product.quantity += payload.quantity;
      state.cart = existCart;
    } 
    localStorage.setItem(localStorageTitle, JSON.stringify(state.cart));
  },
  removeItem(state, payload){
    let existCart = JSON.parse(localStorage.getItem(localStorageTitle)) || [];

    existCart = existCart.filter(item => 
      item.id !== payload.id
    );

    state.cart = existCart;
    localStorage.setItem(localStorageTitle, JSON.stringify(state.cart));
    // cartProductDetail'de güncellenmeli

    state.cartProductDetail = state.cartProductDetail.filter(item => item.id !== payload.id);
  },
  setCartDetails(state, payload) {
    state.cartProductDetail = payload;
  },
  setDiscount(state, payload){
    state.discount = payload;
  },
  setDiscountCode(state, payload){
    state.discountCode = payload;
    localStorage.setItem(discountCodeTitle, JSON.stringify(payload));
  }
};
