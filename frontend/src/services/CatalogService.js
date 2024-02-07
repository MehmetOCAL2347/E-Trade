import axios from "axios";
import config from "@/config";
const BASE_URL = config.baseUrl;
const catagoriesUrl = "/categories";

export default {
    async getAllCategories(){
        return await axios.get(BASE_URL + catagoriesUrl);
    }
}