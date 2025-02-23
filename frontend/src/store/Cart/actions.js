import ProductService from "@/services/ProductService";
import DiscountService from "@/services/DiscountService";

export default {
  addToCart(context, params) {
    context.commit("addToCart", params);
  },
  updateProductCount(context, params) {
    context.commit("updateProductCount", params);
  },
  removeItem(context, params) {
    context.commit("removeItem", params);
  },
  async getCartDetails(context, params) {
    try {      
      const response = await ProductService.getCartDetails(params);      
      context.commit("setCartDetails", response);
    } catch (error) {
      console.log("Error: " + error);
    }
  },
  async getAndCheckDiscount(context, params) {
    try {
      const response = await DiscountService.getAndCheckDiscount(params);
      context.commit("setDiscount", response); // Params değerini almak istiyorum ve localstoraga kaydetcem
      context.commit("setDiscountCode", params);
    } catch (error) {
      context.commit("setDiscount", { discountValue: 0 });
      context.commit("setDiscountCode", { discountCode: 0 });
      console.log("E02: " + error);
    }
  },
};
