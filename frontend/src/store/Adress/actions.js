import AdressService from "@/services/AdressService";

export default {
    async getAllProvinces(context, params){
        try {
            const response = AdressService.getAllProvinces(params);            
            context.commit('setProvinceList', (await response).data.data);
        } catch (error) {
            console.log("Errorr: " + error);
        }
    },

    async getDistrictsFromProvince(context, params){
        try {
            const response = AdressService.getDistrictsFromProvince(params);
            context.commit('setDistrictList', (await response).data);
        } catch (error) {
            console.log("Errorr: " + error);
        }
    },

    saveFullAdress(context, params){
        context.commit("setFullAdress",params);
    }
}
