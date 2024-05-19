import axios from "axios";
import config from "@/config";
const BASE_URL = config.baseUrl;
const userUrl = "/auth";
const loginUrl = "/login";

export default {
    async login (params){
        try {
            const response = await axios.post(BASE_URL + userUrl + loginUrl, {
                email: params.email,
                password: params.password
            });
            return response;
        } catch (error) {
            if(error.response){
                return error.response;
            }else {
                throw error;
            }
        }
    }
}