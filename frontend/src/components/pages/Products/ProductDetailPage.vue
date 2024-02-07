<template>
  <div class="container-md">
    <div class="row">
      <div class="col-sm custom-col">1</div>
      <div class="col-sm custom-col">2</div>
      <div class="col-sm custom-col">3</div>
    </div>
  </div>

  <div class="container-fluid">

    <div class="row">
      <div class="col-md-6">
        <image-carousel></image-carousel>
      </div>

      
      <div class="col-md-6">
        <product-title
          :title="detailProduct.name"
          :starPoint="detailProduct.starPoint"
          :count="detailProduct.count"
          :price="detailProduct.price"
          :priceType="detailProduct.priceType"
          :sellerName="detailProduct.sellerName"
          :categoryName="detailProduct.categoryName"
        ></product-title>
        <bullet-point :bulletPoints="detailProduct.bulletPoints"></bullet-point>
      </div>
    </div>
  </div>

  <div class="container-fluid">
    <similar-products></similar-products>
  </div>

  <div class="container-fluid">
    <product-comments></product-comments>
  </div>

</template>

<script>
import ProductTitle from "@/components/common/UI/ProductDetail/ProductTitle.vue";
import BulletPoint from "@/components/common/UI/ProductDetail/BulletPoint.vue";
import ImageCarousel from "@/components/common/UI/ProductDetail/ImageCarousel.vue";
import SimilarProducts from "@/components/common/UI/ProductDetail/SimilarProducts.vue";
import ProductComments from "@/components/common/UI/ProductDetail/ProductComments.vue";
export default {
  components: {
    ProductTitle,
    BulletPoint,
    ImageCarousel,
    SimilarProducts,
    ProductComments
  },
  data() {
    return {
      detailProduct: {},      
    };
  },
  methods: {
    async getDetailProduct(){
      await this.$store.dispatch('getDetailProduct', this.$route.params.code);
      this.detailProduct = this.$store.getters.getDetailProduct;
    }
  },
  created(){
    this.getDetailProduct();
  }
};
</script>

<style scoped>
.custom-col{
  padding: 1rem;
  background-color: #33b5e5;
  border: 2px solid #fff;
  text-align: center;
}
</style>
