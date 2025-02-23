import OrderService from "@/services/OrderService";

export default {
  async createOrder(context, params) {
    try {      
      const response = OrderService.createOrder(params);
      context.commit("setOrderId", response.data);
    } catch (error) {
        console.log(error);        
    }
  },
};
