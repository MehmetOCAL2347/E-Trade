import axios from "axios";
import config from "@/config";
const BASE_URL = config.baseUrl;
const getAllProvincesUrl = "/fetch-data/getAllProvinces";
const getDistrictsFromProvinceUrl = "/fetch-data/getDistrictsFromProvince";

export default {
    async getAllProvinces(params){
        return await axios.post(BASE_URL + getAllProvincesUrl, {
            baseUrl: params.baseUrl,
            endPoint: params.endPoint
        });
        
        
    },
    async getDistrictsFromProvince(params){
        return await axios.post(BASE_URL + getDistrictsFromProvinceUrl, {
            baseUrl: params.baseUrl,
            endPoint: params.endPoint
        });
    }
}