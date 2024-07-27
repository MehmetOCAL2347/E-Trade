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
            <img class="product-image" :src="product.imageSrc" alt="" />
            <div
              class="icon-container"
              v-if="product.showIcon"
              @click="showQuickView"
            >
              <i
                class="bi bi-eye icon-class"
                :class="{ 'show-icon': showIcon }"
              ></i>
            </div>
          </div>

          <div class="product-description mt-3 p-1">
            <h4>{{ product.title }}</h4>
            <p>{{ product.category }}</p>
            <div class="price-addToCart">
              <p class="mb-0">{{ product.price }} ₺</p>
              <button type="button" class="btn btn-success">
                <i class="bi bi-plus"></i> Ekle
              </button>
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
  ></product-quick-view>
</template>

<script>
export default {
  data() {
    return {
      title: "Popüler Ürünler",
      isQuickViewVisible: false,
      showIcon: false,
      products: [
        {
          imageSrc:
            "https://freshcart.codescandy.com/assets/images/products/product-img-1.jpg",
          title: "Ürün Başlığı merhaba nasılsın",
          category: "Kategori İsmi merhaba nasılsınız inşallah",
          price: 32,
          showIcon: false,
        },
        {
          imageSrc:
            "https://freshcart.codescandy.com/assets/images/products/product-img-2.jpg",
          title: "Ürün Başlığı merhaba nasılsın",
          category: "Kategori İsmi merhaba nasılsınız inşallah",
          price: 32,
          showIcon: false,
        },
        {
          imageSrc:
            "https://freshcart.codescandy.com/assets/images/products/product-img-3.jpg",
          title: "Ürün Başlığı merhaba nasılsın",
          category: "Kategori İsmi merhaba nasılsınız inşallah",
          price: 32,
          showIcon: false,
        },
        {
          imageSrc:
            "https://freshcart.codescandy.com/assets/images/products/product-img-4.jpg",
          title: "Ürün Başlığı merhaba nasılsın",
          category: "Kategori İsmi merhaba nasılsınız inşallah",
          price: 32,
          showIcon: false,
        },
        {
          imageSrc:
            "https://freshcart.codescandy.com/assets/images/products/product-img-5.jpg",
          title: "Ürün Başlığı merhaba nasılsın",
          category: "Kategori İsmi merhaba nasılsınız inşallah",
          price: 32,
          showIcon: false,
        },
        {
          imageSrc:
            "https://freshcart.codescandy.com/assets/images/products/product-img-6.jpg",
          title: "Ürün Başlığı merhaba nasılsın",
          category: "Kategori İsmi merhaba nasılsınız inşallah",
          price: 32,
          showIcon: false,
        },
        {
          imageSrc:
            "https://freshcart.codescandy.com/assets/images/products/product-img-7.jpg",
          title: "Ürün Başlığı merhaba nasılsın",
          category: "Kategori İsmi merhaba nasılsınız inşallah",
          price: 32,
          showIcon: false,
        },
        {
          imageSrc:
            "https://freshcart.codescandy.com/assets/images/products/product-img-8.jpg",
          title: "Ürün Başlığı merhaba nasılsın",
          category: "Kategori İsmi merhaba nasılsınız inşallah",
          price: 32,
          showIcon: false,
        },
        {
          imageSrc:
            "https://freshcart.codescandy.com/assets/images/products/product-img-9.jpg",
          title: "Ürün Başlığı merhaba nasılsın",
          category: "Kategori İsmi merhaba nasılsınız inşallah",
          price: 32,
          showIcon: false,
        },
      ],
    };
  },
  methods: {
    showQuickView() {
      this.isQuickViewVisible = true;
    },
    closeQuickView() {
      this.isQuickViewVisible = false;
    },
    disableScroll(){
      document.body.style.overflow='hidden';
    },
    enableScroll(){
      document.body.style.overflow = '';
    }
  },
};
</script>

<style scoped>
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
  height: 400px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  overflow: hidden;
}

.cart-container:hover {
  cursor: pointer;
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

.product-image-container {
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
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
</style>
