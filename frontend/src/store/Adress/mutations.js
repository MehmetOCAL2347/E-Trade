let localStorageTitle = "full-adress";
export default {
  setProvinceList(state, payload) {
    state.provinceList = payload;
  },
  setDistrictList(state, payload) {
    state.districtList = payload;
  },
  setOrderDetail(state, payload) {
    //let existFullAdress = JSON.parse(localStorage.getItem(localStorageTitle)) || [];

    state.orderDetail = payload.orderDetail;
    localStorage.setItem(
      localStorageTitle,
      JSON.stringify(state.orderDetail)
    );
  },
};
