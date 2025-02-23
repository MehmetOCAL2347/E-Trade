import actions from "./actions.js";
import getters from "./getters.js";
import mutations from "./mutations.js";

let localStorageTitle = "product-in-cart";

export default {
  state() {
    return {
      cart: JSON.parse(localStorage.getItem(localStorageTitle)) || [],
      cartProductDetail: null,
      discount: { discountValue: 0 },
      discountCode: { discountCode: "" }
    };
  },
  actions,
  getters,
  mutations,
};