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
  computed: {},
  components: { MainPageCard },
  data() {
    return {
      products: [],
    };
  },
  methods:{
    async getAllProducts(){
      await this.$store.dispatch('getAllProducts');
      this.products = this.$store.getters.getProducts;
    }
  },
  created() {
    // Eğer products boşsa bir kontrol yapılabilir
    this.getAllProducts();
  }

};
</script>

<style scoped></style>
