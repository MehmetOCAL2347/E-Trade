import actions from "./actions.js";
import getters from "./getters.js";
import mutations from "./mutations.js";

let localStorageTitle = "full-adress";

export default {
  state() {
    return {
      provinceList: null,
      districtList: null,
      fullAdress: JSON.parse(localStorage.getItem(localStorageTitle)) || {
        userInformation: {
          name: "",
          surName: "",
          email: "",
          phoneNumber: "",
        },
        addressInformation: {
          province: "",
          district: "",
          address: "",
        },
      },
    };
  },
  actions,
  getters,
  mutations,
};
