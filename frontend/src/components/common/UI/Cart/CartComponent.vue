<template>
  <div class="cart-page container mt-5">
    <h3 v-if="products.length !== 0" class="mb-4">
      Sepetinde {{ products.length }} ürün bulunmaktadır
    </h3>
    <h3 v-else class="mb-4">Sepetinde ürün bulunmamaktadır</h3>
    <div class="row">
      <div class="col-md-8">
        <div
          class="cart-item mb-3 p-3 border"
          v-for="(product, index) in products"
          :key="index"
        >
          <div class="row">

            <div class="col-lg-8">
              <div class="d-flex align-products-center">
                <img
                  :src="product.url"
                  alt="Product"
                  class="img-thumbnail mr-3"
                />
                <div class="product-detail-cart">
                  <div>
                    <strong>{{ product.name }}</strong>
                  </div>
                  <div class="text-muted">
                    <p class="mb-0">
                      Adet Fiyatı: {{ product.price }} {{ product.priceType }}
                    </p>
                  </div>
                </div>
              </div>
            </div>

            <div class="col-lg-4 custom-total-container">
              <div class="custom-cart-total">
                <div class="input-group">
                  <div class="input-group-prepend">
                    <button
                      @click="decrement(product)"
                      class="btn btn-outline-danger"
                      :disabled="product.quantity <= 1"
                    >
                      -
                    </button>
                  </div>
                  <div class="quantity-area">
                    <p class="form-control text-center mb-0">
                      {{ product.quantity }}
                    </p>
                  </div>

                  <div class="input-group-append">
                    <button
                      @click="increment(product)"
                      class="btn btn-outline-success"
                      :disabled="product.quantity >= 10"
                    >
                      +
                    </button>
                  </div>

                  <div class="input-group-append delete-button">
                    <button @click="removeItem(item)" class="btn btn-danger">
                      <i class="bi bi-trash3"></i>
                    </button>
                  </div>
                </div>

                <div class="text-right custom-total-price">
                  <p class="mb-0">
                    Toplam {{ product.price * product.quantity }}
                    {{ product.priceType }}
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div v-if="products.length !== 0" class="col-md-4">
        <div class="cart-summary border p-3">
          <h4>{{ titleOfSelectedProducts }}</h4>

          <div class="summary-container">
            <p style="text-align: left">{{ titleOfTotalProductCount }}</p>
            <p style="text-align: right">3 Adet</p>
          </div>

          <div class="summary-container">
            <p style="text-align: left">{{ titleOfShipmentPrice }}</p>
            <p style="text-align: right">19 TL</p>
          </div>

          <div class="summary-container">
            <p style="text-align: left">{{ titleOfTotalProductPrice }}</p>
            <p style="text-align: right">500 TL</p>
          </div>

          <hr class="my-line" />

          <div class="summary-container">
            <p style="text-align: left">Sepet Toplamı</p>
            <p style="text-align: right">519 TL</p>
          </div>

          <div>
            <button
              v-if="!isDiscountAreaActive"
              @click="discountAreaActive"
              class="btn btn-outline-success custom-order-btn"
            >
              İndirim Kodu Gir
            </button>

            <div v-else class="input-group mb-3">
              <button
                class="btn btn-success"
                type="button"
                id="button-addon"
                :disabled="!discountCode"
                :class="{ enabled: discountCode }"
              >
                {{ titleOfDiscountButton }}
              </button>
              <input
                type="text"
                class="form-control"
                id="discount-input"
                v-model="discountCode"
                :placeholder="placeHolderOfDiscount"
                aria-label="Example text with button addon"
                aria-describedby="button-addon"
              />
            </div>
          </div>

          <router-link
            to="/create-order"
            class="btn btn-success mt-3 custom-order-btn"
          >
            <i class="bi bi-credit-card"></i> {{ titleOfCreateOrderButton }}
          </router-link>
        </div>
      </div>
    </div>

    <div class="recommended-products mt-5">
      <popular-products></popular-products>
      <!-- <h4>En Çok Satan Ürünler</h4>
      <div class="d-flex">
        <div
          v-for="product in recommendedProducts"
          :key="product.id"
          class="card mr-3"
          style="width: 18rem"
        >
          <img :src="product.image" class="card-img-top" :alt="product.name" />
          <div class="card-body">
            <h5 class="card-title">{{ product.name }}</h5>
            <p class="card-text">%0 faizli 1000 TL</p>
          </div>
        </div>
      </div> -->
    </div>
  </div>
</template>

<script>
import PopularProducts from "@/components/common/UI/LandingPage/PopularProducts.vue";
export default {
  components: {
    PopularProducts
  },
  data() {
    return {
      titleOfSelectedProducts: "Sipariş Özeti",
      titleOfCreateOrderButton: "Sepeti Onayla",
      titleOfTotalProductCount: "Toplam Ürün",
      titleOfShipmentPrice: "Kargo Toplam",
      titleOfTotalProductPrice: "Ürünlerin Toplamı",
      titleOfTotalCartPrice: "Sepet Toplamı",
      placeHolderOfDiscount: "İndirim Kodunu Gir",
      titleOfDiscountButton: "Uygula",
      isDiscountAreaActive: false,
      discountCode: "",
      products: [
        {
          id: 1,
          name: "Ürün-1 deneme başlık responsive olarak design edilecek",
          url: "https://via.placeholder.com/100",
          price: 100.0,
          priceType: "TL",
          quantity: 1,
          seller: "Yds",
          deliveryDate: "31 Temmuz Çarşamba",
        },
        {
          id: 2,
          name: "Ürün-2 deneme başlık ",
          url: "https://via.placeholder.com/100",
          price: 223.0,
          priceType: "TL",
          quantity: 1,
          seller: "Yds",
          deliveryDate: "31 Temmuz Çarşamba",
        },
        {
          id: 3,
          name: "Ürün-3 deneme başlık responsive olarak design edilecek",
          url: "https://via.placeholder.com/100",
          price: 12.5,
          priceType: "TL",
          quantity: 1,
          seller: "Yds",
          image: "https://via.placeholder.com/100",
          deliveryDate: "31 Temmuz Çarşamba",
        },
      ],
      recommendedProducts: [
        { id: 1, name: "Product 1", image: "https://via.placeholder.com/150" },
        { id: 2, name: "Product 2", image: "https://via.placeholder.com/150" },
        { id: 3, name: "Product 3", image: "https://via.placeholder.com/150" },
        { id: 4, name: "Product 4", image: "https://via.placeholder.com/150" },
      ],
    };
  },
  methods: {
    increment(item) {
      item.quantity++;
    },
    decrement(item) {
      if (item.quantity > 1) {
        item.quantity--;
      }
    },
    removeItem(item) {
      this.products = this.products.filter((i) => i.id !== item.id);
    },
    discountAreaActive() {
      this.isDiscountAreaActive = !this.isDiscountAreaActive;
    },
  },
};
</script>

<style scoped>
.cart-page {
  /* max-width: 1200px; */
  margin: 0 auto;
}

.cart-item {
  background-color: white;
  border-radius: 5px;
}

.product-detail-cart {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  margin-left: 10px;
  padding-top: 5px;
}

.padding-custom {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding-top: 5px;
}

.custom-total-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;
}

.custom-cart-total {
  display: flex;
  flex-direction: column;
  align-items: space-around;
  justify-content: space-around;
}

.input-group {
  height: 50px;
}

#discount-input:focus {
  outline: none !important;
  box-shadow: none !important;
  border-color: #ccc !important; /* Set to your desired border color */
}

#discount {
  flex: 1;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px 0 0 5px;
  font-size: 14px;
}

#discount-apply-button {
  padding: 10px 20px;
  border: none;
  background-color: #ccc;
  color: white;
  font-weight: bold;
  border-radius: 0 5px 5px 0;
  cursor: not-allowed;
  transition: background-color 0.3s;
}

#discount-apply-button.enabled {
  background-color: #4caf50;
  cursor: pointer;
}

.quantity-area {
  height: 38px;
}

.delete-button {
  padding-left: 10px;
}
.cart-summary {
  background-color: white;
  border-radius: 5px;
}

.summary-container {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.custom-order-btn {
  width: 100%;
}

.recommended-products .card {
  border: none;
}

.discount-code {
  display: flex;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 15px;
  width: 100%;
  margin: 50px auto;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.my-line {
  border: 0;
  border-top: 2px solid black; /* You can adjust the thickness and color */
  margin-bottom: 0;
}

@media (max-width: 992px) {
  .custom-total-container {
    flex-direction: column;
    justify-content: space-evenly;
  }
  .custom-cart-total {
    width: 100%;
    margin-top: 15px;
    flex-direction: row;
    justify-content: space-evenly;
    align-items: center;
  }
}
</style>
