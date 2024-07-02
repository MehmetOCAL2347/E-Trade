import axios from "axios";
import config from "@/config";
const BASE_URL = config.baseUrl;
const userUrl = "/auth";
const loginUrl = "/login";
const registerUrl = "/register";

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
    },

    async register(params){
        try {
            const response = await axios.post(BASE_URL + userUrl + registerUrl, {
                email: params.email,
                password: params.password
            })
            
            return response;
        } catch (error) {
            console.log(error.response.data);
            if(error.response){
                return error.response;
            }else {
                throw error;
            }
        }
    }
}