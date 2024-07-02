import UserService from "@/services/UserService";

export default {
  async login(context, params) {
    try {
      const response = await UserService.login(params);
      context.commit("setLoginUser", response);
    } catch (error) {
      console.log("Errorr: " + error);
    }
  },
  
  async register(context, params){
    try {
      const response = await UserService.register(params);
      context.commit("setRegisterUser", response);
    } catch (error) {
      console.log("Error: " + error);
    }
  }
};
