<template>
  <div class="container-fluid">
    <div class="row">
      <main-page-card
        class="m-1"
        v-for="(product, index) in products"
        :key="index"
        :product="product"
      />
    </div>
  </div>
</template>

<script>
import MainPageCard from "@/components/common/UI/products/MainPageCard.vue";
export default {
  components: {
    MainPageCard,
  },
  watch: {
    "$route.query": {
      handler() {
        this.getProductsWithParams();
      },
    },
    products() {},
  },
  data() {
    return {
      products: [],
    };
  },
  methods: {
    async getAllProducts() {
      await this.$store.dispatch("getAllProducts");
      this.products = this.$store.getters.getProducts;
    },

    async getProductsWithParams() {
      await this.$store.dispatch(
        "getProductsWithParams",
        this.createRequestParams()
      );
      this.products = this.$store.getters.getProducts;
    },

    createRequestParams() {
      let queryString = "?";
      let query = this.$route.query;

      // Method haline getirilebilir
      if (query.categoryName) {
        if (Array.isArray(query.categoryName)) {
          query.categoryName.forEach((category, index) => {
            queryString += (index > 0 ? "&" : "") + "categoryName=" + category;
          });
        } else {
          queryString +=
            (queryString.includes("=") ? "&" : "") +
            "categoryName=" +
            query.categoryName;
        }
      }

      // Method haline getirilebilir
      if (query.starPoint != null) {
        queryString +=
          (queryString.includes("=") ? "&" : "") +
          "starPoint=" +
          query.starPoint;
      }

      // Method haline getirilebilir
      if(query.minPriceValue){
        queryString += (queryString.includes("=") ? "&" : "") +
        "minPriceValue=" + 
        query.minPriceValue
      }

      if(query.maxPriceValue){
        queryString += (queryString.includes("=") ? "&" : "") +
        "maxPriceValue=" + 
        query.maxPriceValue
      }

      // Method haline getirilebilir
      if(query.isActive){
        queryString += (queryString.includes("=") ? "&" : "") +
        "isActive=" + 
        query.isActive
      }

      return queryString;
    },

    redirect() {
      if (Object.keys(this.$route.query).length === 0) {
        this.getAllProducts();
      } else {
        this.getProductsWithParams();
      }
    },
  },
  created() {
    this.redirect();
  },
};
</script>

<style scoped></style>
