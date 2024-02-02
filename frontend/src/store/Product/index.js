import actions from "./actions.js";
import getters from "./getters.js";
import mutations from "./mutations.js";

export default {
  state() {
    return {
      products: [],
      detailProduct: {}
    };
  },
  actions,
  getters,
  mutations,
};
