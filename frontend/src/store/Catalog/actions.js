import CatalogService from "@/services/CatalogService";

export default {
  async getAllCategories(context) {
    try {
        const response = await CatalogService.getAllCategories();
        context.commit('setAllCategories', response.data);
    } catch (error) {
        console.log(error);
    }
  }
};
