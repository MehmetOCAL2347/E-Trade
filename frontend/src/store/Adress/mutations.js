let localStorageTitle = "full-adress";
export default {
    setProvinceList(state, payload){
        state.provinceList = payload;
    },
    setDistrictList(state, payload){
        state.districtList = payload;
    },
    setFullAdress(state, payload){
        
    //let existFullAdress = JSON.parse(localStorage.getItem(localStorageTitle)) || [];

    state.existFullAdress = payload.fullAdress;
    localStorage.setItem(localStorageTitle, JSON.stringify(state.existFullAdress));

    }
}