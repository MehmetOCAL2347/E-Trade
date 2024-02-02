import ProductService from '@/services/ProductService';

export default {
  async getAllProducts(context) {
    try {
        const response = await ProductService.getAllProducts();
        context.commit('setAllProducts', response.data);
    } catch (error) {
        console.log(error);
    }
  },
  async getDetailProduct(context, params){
    try {
      const response = await ProductService.getDetailProduct(params);
      context.commit('setDetailProduct', response.data);
    } catch (error) {
      console.log(error);
    }
  }
};
