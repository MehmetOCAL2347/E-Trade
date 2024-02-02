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
        ></product-title>
        <bullet-point :bulletPoints="detailProduct.bulletPoints"></bullet-point>
      </div>
    </div>
  </div>
</template>

<script>
import ProductTitle from "@/components/common/UI/ProductDetail/ProductTitle.vue";
import BulletPoint from "@/components/common/UI/ProductDetail/BulletPoint.vue";
import ImageCarousel from "@/components/common/UI/ProductDetail/ImageCarousel.vue";
export default {
  components: {
    ProductTitle,
    BulletPoint,
    ImageCarousel,
  },
  data() {
    return {
      detailProduct: {},
      // detailProduct: {
      //   code: "PRD001",
      //   name: "Mueller Pro-Series All-in-One, 12 Blade Mandoline Slicer, Vegetable Spiralizer, Cutter, Dicer, Food Chopper, Grater, Kitchen Gadgets Sets with Container",
      //   starPoint: 4.5,
      //   count: 20,
      //   price: 23.99,
      //   priceType: "$",
      //   categoryName: "Home & Kitchen",
      //   sellerName: "MANDE",
      //   bulletPoints: [
      //     "Professional Design: Our designers dismantled dozens of kinds of vegetable chopper on the market, and more customers did research, we understand the needs of customers, Designed this all-star vegetable chopper. This vegetable chopper in the major kitchen product exhibition received orders from all over the world, now we put it to the market.",
      //     "Smart and Effective Design: Safety lock design, can better protect your fingers. High-quality knife holder boxes can be stored, saving more kitchen space. The simplest and most efficient shaft design is our patented, allowing for simpler and less effort disassembly while maintaining better stability. The detachable hand guard is also our highlight, making it easier and cleaner to clean. The Grid is also a snap design for easy and effortless disassembly. A variety of safety signs and instructions, so that you in the use of the process more safe.",
      //     "Save time and effort: Make a meal for yourself and your family after work , versatile blade design allows you to cut the perfect Onions, tomatoes and peppers to make salsa in a short time, while also handling garlic, ginger, radish, cheese and other various vegetables. Life is always about enjoyment, so owning this vegetable chopper will save your time and enjoy life better for your family",
      //     "Material safety: Comfortable handle using TPU material, safe and comfortable. 430J2 stainless steel blades are more durable than 304 blades and will not rust. The ABS base is designed to withstand even greater pressure and is also suitable for the upper part of the dishwasher, making cleaning easier. The non-slip silicone ring at the bottom increases friction and is suitable for dry or wet desktops. All materials are BPA-free, so you and your family can use them safely",
      //     "The perfect kitchen gadget collection (18 in 1) : The vegetable chopper consists of 18 accessories, 2 dicing blades and 6 cutting blades, and comes with 2 knife holder boxes for storage. Offering you a variety of ways to slice, chop, grate and chop, the high-quality stainless steel blades cut perfect Onions, tomatoes and peppers for salsa in no time. 2L containers can hold more vegetables. Safe hand guards make you safer during use, save time and protect you from injury. Save more kitchen space",
      //   ],
      // },
      
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
