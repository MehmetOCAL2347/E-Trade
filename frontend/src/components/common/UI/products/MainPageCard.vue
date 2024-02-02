<template>
  <div class="card" style="width: 20rem">
    <img :src="product.url" class="card-img-top p-3" :alt="product.name" />
    <div class="card-body">
      <router-link :to="{ name: 'productDetail', params: { code: product.code } }">
        {{ product.name }}
      </router-link>
    </div>
    <div class="card-body">
      <span>{{ product.price }} {{ product.priceType }}</span>
      <!-- TODO priceType değişecek, sembol koncak -->
    </div>
    <div class="card-body">
      <!-- <star-rating :increment="1" read-only="true" v-model="product.starPoint"></star-rating> -->
      <span>{{ product.starPoint }}</span>
      <!-- TODO Yıldız eklencek -->
    </div>
    <div class="card-body">
      <span v-if="isWarnMessageShouldBeVisible()" class="warn-message">{{
        this.stockWarnMessage()
      }}</span>
      <router-link class="btn btn-primary w-100" to="#">Add to Chart</router-link>
      <!-- TODO To eklenecek -->
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      detailPath: "/dp/",
    };
  },
  props: {
    product: Object,
  },
  methods: {
    createLink() {
      return this.detailPath + this.product.code;
    },
    stockWarnMessage() {
      return "Only " + this.product.count + " in stock - order soon";
    },
    isWarnMessageShouldBeVisible() {
      if (this.product.count <= 15) {
        return true;
      } else {
        return false;
      }
    },
  },
};
</script>

<style scoped>
a:hover {
  color: var(--font-color-hover);
}
.warn-message {
  padding-left: 10px;
  color: var(--font-color-warn);
  font-size: var(--font-size-xSmall);
}
</style>
