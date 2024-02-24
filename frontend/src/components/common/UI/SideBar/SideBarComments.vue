<template>
  <!-- Müşteri Yorumları -->
  <div>
    <h4 id="categorie-title">Yorumlar</h4>
    <!-- v-for kullanılcak, yıldız şeklinde olacak-->
    <span class="clear-filter hover-effect clear-button" @click="clearFilter()"
      >Temizle</span
    >
    <div class="form-check">
      <input
        class="form-check-input"
        type="radio"
        name="exampleRadios"
        id="four-star-up"
        value="4"
        v-model="selectedItem"
      />
      <label class="form-check-label hover-effect" for="four-star-up">
        4 ve üzeri
      </label>
    </div>

    <div class="form-check">
      <input
        class="form-check-input"
        type="radio"
        name="exampleRadios"
        id="three-star-up"
        value="3"
        v-model="selectedItem"
      />
      <label class="form-check-label hover-effect" for="three-star-up">
        3 ve üzeri
      </label>
    </div>

    <div class="form-check">
      <input
        class="form-check-input"
        type="radio"
        name="exampleRadios"
        id="two-star-up"
        value="2"
        v-model="selectedItem"
      />
      <label class="form-check-label hover-effect" for="two-star-up">
        2 ve üzeri
      </label>
    </div>

    <div class="form-check">
      <input
        class="form-check-input"
        type="radio"
        name="exampleRadios"
        id="one-star-up"
        value="1"
        v-model="selectedItem"
      />
      <label class="form-check-label hover-effect" for="one-star-up">
        1 ve üzeri
      </label>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      selectedItem: "",
    };
  },
  watch: {
    selectedItem() {
      const queryParams = { starPoint: this.selectedItem };
      this.$router.push({
        name: "all-products",
        query: { ...this.$route.query, ...queryParams },
      });
    },
  },
  methods: {
    clearFilter() {
      this.selectedItem = undefined; // "" -> bu şekilde yaptığımızda url'de ?starPoint= şeklinde kalıyor. O sebeple undefined tanımlanmalı
    },
    checkParamsExist() {
      let query = this.$route.query.starPoint;
      if (query) {
        this.selectedItem = query;
      }
    },
  },
  created() {
    this.checkParamsExist();
  },
};
</script>

<style scoped></style>
