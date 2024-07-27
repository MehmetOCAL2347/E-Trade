<template>
  <div id="landing-page">
    <div id="main-carousel-component">
      <main-carousel></main-carousel>
    </div>
    <div id="featured-categories-component">
      <featured-categories></featured-categories>
    </div>
    <div id="popular-products-component">
      <popular-products></popular-products>
    </div>
    <div id="best-sellers-component">
      <best-sellers></best-sellers>
    </div>
  </div>
</template>

<script>
import MainCarousel from "@/components/common/UI/LandingPage/MainCarousel.vue";
import FeaturedCategories from "@/components/common/UI/LandingPage/FeaturedCategories.vue";
import PopularProducts from "@/components/common/UI/LandingPage/PopularProducts.vue";
import BestSellers from "@/components/common/UI/LandingPage/BestSellers.vue";
export default {
  components: {
    MainCarousel,
    FeaturedCategories,
    PopularProducts,
    BestSellers,
  },
  data() {
    return {
      slideInterval: 3000,
    };
  },
};
</script>

<style scoped>

</style>
