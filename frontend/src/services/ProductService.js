import axios from "axios";
import config from "@/config";
const BASE_URL = config.baseUrl;
const productUrl = "/products";
const cartDetailUrl = "/cartDetail";
const detailProductUrl = "/pd/";

export default {
  async getAllProducts() {
    return await axios.get(BASE_URL + productUrl);
  },
  async getDetailProduct(code) {
    return await axios.get(BASE_URL + productUrl + detailProductUrl + code);
  },
  async getProductsWithParams(params) {
    return await axios.get(BASE_URL + productUrl + params);
  },
  async getCartDetails(params) {
    let response =  await axios.post(BASE_URL + productUrl + cartDetailUrl, {
      codes: params.codes,
    });
    return response.data;
  },
};
