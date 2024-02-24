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
      <div>
        <input
          class="form-check-input"
          type="checkbox"
          :id="category.code"
          :value="category.name"
          v-model="filters"
        />
        <label class="form-check-label hover-effect" :for="category.code">{{
          category.name
        }}</label>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      filters: [],
      categories: [], // Ekrandaki DB'den gelen bilgi
      isComponentVisible: false,
    };
  },

  watch: {
    filters() {
      const queryParams = { categoryName: this.filters };
      this.$router.push({
        name: "all-products",
        query: { ...this.$route.query, ...queryParams },
      });
    },
  },

  methods: {
    clearFilter() {
      if (this.filters.length !== 0) {
        this.filters = [];
      }
    },
    checkParamsExist() {
      let query = this.$route.query.categoryName;

      if (query) {
        if (Array.isArray(query)) {
          this.filters = query;
        } else {
          this.filters.push(query);          
        }
      }
    },

    async getAllCategories() {
      await this.$store.dispatch("getAllCategories");
      this.categories = this.$store.getters.getAllCategories;
      if (this.categories.length !== 0) {
        this.isComponentVisible = true;
      }
    },
  },
  mounted() {
    if (this.$store.getters.getAllCategories.length === 0) {
      this.getAllCategories();
    } else {
      this.isComponentVisible = true;
      this.categories = this.$store.getters.getAllCategories;
    }
  },
  created() {
    this.checkParamsExist();
  },
};
</script>

<style scoped></style>
