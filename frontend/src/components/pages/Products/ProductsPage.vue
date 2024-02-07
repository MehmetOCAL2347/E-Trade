<template>
  <div class="container-fluid">
    <div class="row">
      <!-- Sidebar -->
      <div class="col-md-3 col-lg-2 d-md-block bg-light sidebar">
        <side-bar></side-bar>
      </div>

      <!-- All Products -->
      <div class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
        <div class="row">
          <main-page-card
            class="m-1"
            v-for="(product, index) in products"
            :key="index"
            :product="product"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MainPageCard from "@/components/common/UI/products/MainPageCard.vue";
import SideBar from "@/components/common/UI/SideBar/SideBar.vue";
export default {
  computed: {},
  components: {
    MainPageCard,
    SideBar,
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
  },
  created() {
    // Eğer products boşsa bir kontrol yapılabilir
    this.getAllProducts();
  },
};
</script>

<style scoped></style>
