<template>
  <!-- <div class="container-fluid">
    <div class="row">
      <main-page-card
        class="m-1"
        v-for="(product, index) in products"
        :key="index"
        :product="product"
      />
    </div>
  </div> -->

  <div>
    <new-main-page-card
      :products="products"
      :title="title"
    ></new-main-page-card>
  </div>
  <div></div>
</template>

<script>
// import MainPageCard from "@/components/common/UI/products/MainPageCard.vue";
import NewMainPageCard from "@/components/common/UI/products/NewMainPageCard.vue";
export default {
  components: {
    // MainPageCard,
    NewMainPageCard,
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
      title: "Tüm Ürünler",
      products: [],
      // products1: [
      //   {
      //     code: "PRD001",
      //     url: "https://freshcart.codescandy.com/assets/images/products/product-img-1.jpg",
      //     name: "Ürün Başlığı merhaba nasılsın",
      //     categoryName: "Technology",
      //     price: 34,
      //     priceType: "TL",

      //     count: 25,
      //     isActive: true,
      //     starPoint: 4.5,
      //     showIcon: false,
      //   },
      //   {
      //     code: "PRD002",
      //     url: "https://freshcart.codescandy.com/assets/images/products/product-img-2.jpg",
      //     name: "Ürün Başlığı merhaba nasılsın",
      //     categoryName: "Technology",
      //     price: 29,

      //     priceType: "TL",
      //     count: 25,
      //     isActive: true,
      //     starPoint: 4.5,
      //     showIcon: false,
      //   },
      //   {
      //     code: "PRD003",
      //     url: "https://freshcart.codescandy.com/assets/images/products/product-img-3.jpg",
      //     name: "Ürün Başlığı merhaba nasılsın",
      //     categoryName: "Technology",
      //     price: 60,

      //     priceType: "TL",
      //     count: 25,
      //     isActive: true,
      //     starPoint: 4.5,
      //     showIcon: false,
      //   },
      //   {
      //     code: "PRD004",
      //     url: "https://freshcart.codescandy.com/assets/images/products/product-img-4.jpg",
      //     name: "Ürün Başlığı merhaba nasılsın",
      //     categoryName: "Technology",
      //     price: 38,

      //     priceType: "TL",
      //     count: 25,
      //     isActive: true,
      //     starPoint: 4.5,
      //     showIcon: false,
      //   },
      //   {
      //     code: "PRD005",
      //     url: "https://freshcart.codescandy.com/assets/images/products/product-img-5.jpg",
      //     name: "Ürün Başlığı merhaba nasılsın",
      //     categoryName: "Technology",
      //     price: 66,

      //     priceType: "TL",
      //     count: 25,
      //     isActive: true,
      //     starPoint: 4.5,
      //     showIcon: false,
      //   },
      //   {
      //     code: "PRD006",
      //     url: "https://freshcart.codescandy.com/assets/images/products/product-img-6.jpg",
      //     name: "Ürün Başlığı merhaba nasılsın",
      //     categoryName: "Technology",
      //     price: 33,

      //     priceType: "TL",
      //     count: 25,
      //     isActive: true,
      //     starPoint: 4.5,
      //     showIcon: false,
      //   },
      //   {
      //     code: "PRD007",
      //     url: "https://freshcart.codescandy.com/assets/images/products/product-img-7.jpg",
      //     name: "Ürün Başlığı merhaba nasılsın",
      //     categoryName: "Technology",
      //     price: 42,

      //     priceType: "TL",
      //     count: 25,
      //     isActive: true,
      //     starPoint: 4.5,
      //     showIcon: false,
      //   },
      //   {
      //     code: "PRD008",
      //     url: "https://freshcart.codescandy.com/assets/images/products/product-img-8.jpg",
      //     name: "Ürün Başlığı merhaba nasılsın",
      //     categoryName: "Technology",
      //     price: 12,

      //     priceType: "TL",
      //     count: 25,
      //     isActive: true,
      //     starPoint: 4.5,
      //     showIcon: false,
      //   },
      //   {
      //     code: "PRD009",
      //     url: "https://freshcart.codescandy.com/assets/images/products/product-img-9.jpg",
      //     name: "Ürün Başlığı merhaba nasılsın",
      //     categoryName: "Technology",
      //     price: 18,
      //     priceType: "TL",
      //     count: 25,
      //     isActive: true,
      //     starPoint: 4.5,
      //     showIcon: false,
      //   },
      // ],
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
      if (query.minPriceValue) {
        queryString +=
          (queryString.includes("=") ? "&" : "") +
          "minPriceValue=" +
          query.minPriceValue;
      }

      if (query.maxPriceValue) {
        queryString +=
          (queryString.includes("=") ? "&" : "") +
          "maxPriceValue=" +
          query.maxPriceValue;
      }

      // Method haline getirilebilir
      if (query.isActive) {
        queryString +=
          (queryString.includes("=") ? "&" : "") + "isActive=" + query.isActive;
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
