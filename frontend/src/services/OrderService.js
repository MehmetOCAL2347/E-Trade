import axios from "axios";
import config from "@/config";
const BASE_URL = config.baseUrl;
const orderUrl = "/order";
const createOrderUrl = "/create-order";

export default {
    async createOrder(params) {
        return await axios.post(BASE_URL + orderUrl + createOrderUrl, params)
    }
}