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
                <img :src="product.url" alt="Product" class="img-thumbnail mr-3" />
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
                      @click="updateProductCount(product, -1)"
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
                      @click="updateProductCount(product, 1)"
                      class="btn btn-outline-success"
                      :disabled="product.quantity >= 10"
                    >
                      +
                    </button>
                  </div>

                  <div class="input-group-append delete-button">
                    <button @click="removeItem(product)" class="btn btn-danger">
                      <i class="bi bi-trash3"></i>
                    </button>
                  </div>
                </div>

                <div class="text-right custom-total-price">
                  <p class="mb-0">
                    Toplam
                    {{ parseFloat(product.price * product.quantity).toFixed(2) }}
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
            <p style="text-align: right">{{ cartCount }} Adet</p>
          </div>

          <div class="summary-container">
            <p style="text-align: left">{{ titleOfShipmentPrice }}</p>
            <p style="text-align: right">{{ titleOfShipmentCost }}</p>
          </div>

          <div class="summary-container">
            <p style="text-align: left">{{ titleOfTotalProductPrice }}</p>
            <p style="text-align: right">{{ totalPrice }} TL</p>
          </div>

          <hr class="my-line" />

          <div class="summary-container">
            <p style="text-align: left">{{ titleOfMiddlePrice }}</p>
            <p style="text-align: right">{{ totalPrice }} TL</p>
          </div>

          <div class="summary-container">
            <p style="text-align: left">
              Toplam İndirim Miktarı
              <span v-if="totalDiscountValue !== 0">(%{{ totalDiscountValue }})</span>
            </p>
            <p style="text-align: right">{{ totalDiscount }} TL</p>
          </div>

          <hr class="my-line" />

          <div class="summary-container">
            <p style="text-align: left">Toplam Ödenecek Ücret</p>
            <p style="text-align: right">{{ totalAllPrice }} TL</p>
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
                @click="getAndCheckDiscount(discountCode)"
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

          <router-link to="/create-order" class="btn btn-success mt-3 custom-order-btn">
            <i class="bi bi-credit-card"></i> {{ titleOfCreateOrderButton }}
          </router-link>
        </div>
      </div>
    </div>

    <div class="recommended-products mt-5">
      <popular-products></popular-products>
    </div>
  </div>
</template>

<script>
import PopularProducts from "@/components/common/UI/LandingPage/PopularProducts.vue";
export default {
  components: {
    PopularProducts,
  },

  data() {
    return {
      titleOfSelectedProducts: "Sipariş Özeti",
      titleOfCreateOrderButton: "Sepeti Onayla",
      titleOfTotalProductCount: "Toplam Ürün",
      titleOfShipmentPrice: "Kargo Toplam",
      titleOfShipmentCost: "Ücretsiz Kargo",
      titleOfTotalProductPrice: "Ürünlerin Toplamı",
      titleOfMiddlePrice: "Ara Toplam",
      titleOfTotalCartPrice: "Sepet Toplamı",
      placeHolderOfDiscount: "İndirim Kodunu Gir",
      titleOfDiscountButton: "Uygula",
      isDiscountAreaActive: false,
      discountCode: "",
      totalPriceValue: 0.0,
      totalDiscountValue: 0, // % olarak girilir
      totalAllPriceValue: 0.0,
      products: [],
    };
  },
  methods: {
    updateProductCount(product, value) {
      this.$store.dispatch("updateProductCount", {
        id: product.code,
        quantity: value,
      });
      product.quantity += value;
    },
    removeItem(product) {
      this.$store.dispatch("removeItem", {
        id: product.code,
      });
      this.getCartDetailProducts();
    },
    discountAreaActive() {
      this.isDiscountAreaActive = !this.isDiscountAreaActive;
    },
    addQuantityToProducts() {
      let products = this.$store.getters.getDetailCartProducts;

      const productQuantity = this.existCart.map((item) => item.quantity);

      console.log(productQuantity);

      let updatedProducts = products.map((product) => {
        let storedProduct = this.existCart.find((item) => item.id === product.code);

        return {
          ...product,
          quantity: storedProduct ? storedProduct.quantity : 0,
        };
      });
      this.products = updatedProducts;
    },

    async getCartDetailProducts() {
      const productCodes = this.existCart.map((item) => item.id);
      try {
        await this.$store.dispatch("getCartDetails", {
          codes: productCodes,
        });
      } catch (error) {
        console.log(error);
      }

      this.addQuantityToProducts();
    },
    async getAndCheckDiscount(value) {
      value = value.trim().toUpperCase();
      try {
        await this.$store.dispatch("getAndCheckDiscount", {
          discountCode: value,
        });
        this.totalDiscountValue = this.$store.getters.getDiscount.discountValue;
      } catch (error) {
        console.log("E01" + error);
      }
    },
  },
  mounted() {
    this.getCartDetailProducts();
  },
  watch: {
    existCart(newCart) {
      console.log("Cart updated", newCart);
    },
    totalPrice(newValue) {
      this.totalPriceValue = newValue;
      console.log(this.totalPriceValue);
    },
  },
  computed: {
    existCart() {
      console.log(this.$store.getters.getExistCart);
      return this.$store.getters.getExistCart;
    },
    cartCount() {
      return this.$store.getters.getCartCount;
    },
    totalPrice() {
      // Kargo ücreti olacaksa onuda burada hesaplatmak gerek!!
      let totalPrice = 0;

      const quantities = this.products.map((product) => product.quantity * product.price);
      quantities.map((price) => (totalPrice += price));

      return parseFloat(totalPrice).toFixed(2);
    },
    totalDiscount() {
      return parseFloat((this.totalDiscountValue * this.totalPriceValue) / 100).toFixed(
        2
      );
      //return parseFloat((this.totalDiscountValue * this.totalPriceValue) / 100).toFixed(2);
    },
    totalAllPrice() {
      return parseFloat(
        this.totalPriceValue - (this.totalDiscountValue * this.totalPriceValue) / 100
      ).toFixed(2);
      //return parseFloat(this.totalPriceValue - this.totalDiscountValue).toFixed(2);
    },
  },
};
</script>

<style scoped>
.cart-page {
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
