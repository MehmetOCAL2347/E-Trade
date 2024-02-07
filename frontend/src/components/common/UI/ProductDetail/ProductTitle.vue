<template>
  <!-- <div>
    <p id="product-title">{{ title }}</p>
  </div> -->

  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <p id="product-title">{{ title }}</p>
      </div>

      <div class="col-md-12">
        <router-link class="link" to="#">{{ getSeller }}</router-link>
      </div>

      <div class="col-md-12">
        <router-link class="link" to="#">{{ categoryName }}</router-link>
      </div>

      <!-- Bu da ayrı bir komponent olabilir-->
      <div class="col-md-12">
        <p class="star-point">{{ starPoint }}</p>
      </div>

      <!-- <div class="col-md-12">
        <p class="first-price">
          {{ priceType }} {{ priceFirstIndex }}
          <span class="second-price"> {{ priceSecondIndex }}</span>
        </p>
      </div> -->
    </div>
  </div>
</template>

<script>
export default {
  props: ["title", "starPoint", "count", "price", "priceType", "sellerName", "categoryName"],
  computed: {
    getSeller() {
      return "Visit the " + this.sellerName;
    },
    priceFirstIndex() {
      let value = this.price.toString().split(".");
      return value[0].trim();
    },
    priceSecondIndex() {
      let value = this.price.toString().split(".");
      return value[1].trim();
    },
  },
};
</script>

<style scoped>
#product-title {
  font-size: var(--font-size-medium);
}
.link {
  font-size: var(--font-size-xSmall);
  color: var(--a-color-blue);
  padding-left: 5px;
  font-weight: 500;
}
.link:hover {
  text-decoration: underline;
  color: var(--font-color-hover);
}
.star-point {
  padding-left: 5px;
}
.first-price {
  font-size: var(--font-size-large);
  padding-left: 5px;
}
.second-price {
  font-size: var(--font-size-xSmall);
}
</style>
