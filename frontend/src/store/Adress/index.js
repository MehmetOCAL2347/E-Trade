import actions from "./actions.js";
import getters from "./getters.js";
import mutations from "./mutations.js";

// let localStorageTitle = "full-adress";

export default {
  state() {
    return {
      provinceList: null,
      districtList: null,
      // fullAdress: JSON.parse(localStorage.getItem(localStorageTitle)) || {
      orderDetail: null,
    };
  },
  actions,
  getters,
  mutations,
};
