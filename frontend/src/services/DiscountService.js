import axios from "axios";
import config from "@/config";
const BASE_URL = config.baseUrl;
const discountUrl = "/discount";
const queryUrl = "/query-check-discount"

export default {
  async getAndCheckDiscount(params) {
    let response =  await axios.post(BASE_URL + discountUrl + queryUrl, {
      discountCode: params.discountCode,
    });
    return response.data;
  },
};