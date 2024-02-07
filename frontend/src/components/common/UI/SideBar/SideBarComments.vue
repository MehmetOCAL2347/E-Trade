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
        value="four-star-up"
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
        value="three-star-up"
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
        value="two-star-up"
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
        value="one-star-up"
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
      selectedItem: null,
    };
  },
  watch: {
    selectedItem() {
      // SelectedItem'da bir değişiklik olduğu zaman aksiyon alınabilir
      //console.log(this.selectedItem);
    },
  },
  methods: {
    clearFilter() {
      this.selectedItem = null;
    },
  },
};
</script>

<style scoped></style>
