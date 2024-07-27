<template>
  <div class="my-lg-14 my-8 mt-5">
    <div class="container-fluid custom-container">
      <div class="row">
        <div class="col-12 mb-6">
          <h1 class="mb-0">{{ title }}</h1>
        </div>
      </div>
      <div class="row row-custom">
        <div
          class="cart-container m-2 cart-custom pb-5 pt-5"
          v-for="(product, index) in products"
          :key="index"
          @mouseover="product.showIcon = true"
          @mouseleave="product.showIcon = false"
        >
          <div class="product-image-container">
            <img class="product-image" :src="product.url" alt="" />
            <div
              class="icon-container"
              v-if="product.showIcon"
              @click="showQuickView(product.code)"
            >
              <i
                class="bi bi-eye icon-class"
                :class="{ 'show-icon': showIcon }"
              ></i>
            </div>
          </div>

          <div class="product-description mt-3 p-1">

            <router-link
              :to="{ name: 'productDetail', params: { code: product.code } }"
            >
              {{ product.name }}
            </router-link>
            <p>{{ product.categoryName }}</p>
            <p>{{ product.starPoint }}</p>

            <div class="price-addToCart">
              <p class="mb-0">{{ product.price }} {{ product.priceType }}</p>
              <button
                v-if="product.isActive"
                type="button"
                class="btn btn-success"
              >
                <i class="bi bi-plus"></i> Ekle
              </button>
              <span class="out-of-stock" v-else>{{ stockMessage }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <product-quick-view
    :isVisible="this.isQuickViewVisible"
    @close-quick-view="closeQuickView"
    @open="disableScroll"
    @close="enableScroll"
    :detailProduct="this.detailProduct"
    :zoomStyle="this.zoomStyles"
  ></product-quick-view>
</template>

<script>
export default {
  props: {
    products: [],
    title: String,
  },
  data() {
    return {
      isQuickViewVisible: false,
      showIcon: false,
      stockMessage: "Stok Tükenmiştir",
      detailProduct: {},
      zoomStyles: {
        backgroundImage: "",
        backgroundSize: "100%",
        backgroundPosition: "center",
      },
    };
  },
  methods: {
    async getDetailProduct(code) {
      await this.$store.dispatch("getDetailProduct", code);
      this.detailProduct = this.$store.getters.getDetailProduct;
      this.zoomStyles.backgroundImage= `url(${this.detailProduct.images[0].url})`;
    },
    showQuickView(code) {
      this.isQuickViewVisible = true;
      this.getDetailProduct(code); 
    },
    closeQuickView() {
      this.isQuickViewVisible = false;
    },
    disableScroll() {
      document.body.style.overflow = "hidden";
    },
    enableScroll() {
      document.body.style.overflow = "";
    },
  },
};
</script>

<style scoped>
a:hover {
  color: var(--color-2);
}
.custom-container {
  width: var(--width-main);
  margin: 0 auto;
}

.cart-container {
  border: 1px solid var(--color-4);
  border-radius: 10px;
  transition: 0.3s ease;
  margin-top: 15px;
  margin-bottom: 15px;
  width: 400px;
  min-height: 400px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  overflow: hidden;
}

.cart-container:hover {
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
  border: 1px solid var(--color-2);
}

.row-custom {
  display: flex;
  justify-content: center;
}

.cart-custom {
  min-height: 450px;
}

.product-description {
  width: 90%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.price-addToCart {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.product-image-container{
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
}

i:hover {
  cursor: pointer;
}

.product-image {
  max-width: 100%;
  max-height: 100%;
}

.icon-container {
  position: absolute;
  top: 90%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  color: var(--color-2);
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}

.icon-container:hover {
  background-color: var(--color-2);
  color: white;
}

.icon-class {
  font-size: 24px;
  color: #000;
  padding-left: 15px;
  padding-right: 15px;
}

.show-icon {
  display: block;
}

.out-of-stock{
    color: var(--color-2);
}
</style>
