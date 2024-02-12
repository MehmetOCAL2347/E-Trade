<template>
  <!-- Kategoriler -->
  <div v-if="isComponentVisible">
    <h4 id="categorie-title">Kategoriler</h4>
    <span class="clear-filter hover-effect clear-button" @click="clearFilter()"
      >Temizle</span
    >
    <div
      class="form-check"
      v-for="(category, index) in categories"
      :key="index"
    >
      <input
        class="form-check-input"
        type="checkbox"
        :id="category.code"
        :value="category.code"
        v-model="selectedCheckboxes"
      />
      <label
        class="form-check-label hover-effect"
        :for="category.code"
        @click="addFilter(category.name)"
        >{{ category.name }}</label
      >
      <!-- <label class="form-check-label hover-effect" :for="category.code">{{
        category.name
      }}</label> -->
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      selectedCheckboxes: [],
      categories: [],
      isComponentVisible: false,
    };
  },
  watch: {
    selectedCheckboxes() {
      console.log(this.selectedCheckboxes);
    },
  },
  methods: {
    clearFilter() {
      if (this.selectedCheckboxes.length !== 0) {
        this.selectedCheckboxes = [];
      }
    },

    async getAllCategories() {
      await this.$store.dispatch("getAllCategories");
      this.categories = this.$store.getters.getAllCategories;
      if (this.categories.length !== 0) {
        this.isComponentVisible = true;
      }
    },

    addFilter(categoryName) {
      this.$router.push({ path: "/products/query", query: { categoryName } });
    },
  },
  created() {
    if (this.$store.getters.getAllCategories.length === 0) {
      this.getAllCategories();
    } else {
      this.isComponentVisible = true;
      this.categories = this.$store.getters.getAllCategories;
    }
  },
};
</script>

<style scoped></style>
