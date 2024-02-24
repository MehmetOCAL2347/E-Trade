<template>
  <div>
    <h3>Fiyat</h3>
    <span class="clear-filter hover-effect clear-button" @click="clearFilter()"
      >Temizle</span
    >
    <div v-for="(filter, index) in filterArray" :key="index" class="form-check">
      <input
        class="form-check-input"
        type="radio"
        :name="filter.id"
        :id="filter.id"
        :value="filter.value"
        v-model="selectedItem"
      />
      <label class="form-check-label hover-effect" :for="filter.id">
        {{ filter.description }}
      </label>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      selectedItem: {
        minPriceValue: undefined,
        maxPriceValue: undefined,
      },
      filterArray: [
        {
          id: "filter-1",
          description: "$25 ve altı",
          value: {
            minPriceValue: undefined,
            maxPriceValue: 25,
          },
        },
        {
          id: "filter-2",
          description: "$25 - $50",
          value: {
            minPriceValue: 25,
            maxPriceValue: 50,
          },
        },
        {
          id: "filter-3",
          description: "$50 - $100",
          value: {
            minPriceValue: 50,
            maxPriceValue: 100,
          },
        },
        {
          id: "filter-4",
          description: "$100 - $200",
          value: {
            minPriceValue: 100,
            maxPriceValue: 200,
          },
        },
        {
          id: "filter-5",
          description: "$200 ve üzeri",
          value: {
            minPriceValue: 200,
            maxPriceValue: undefined,
          },
        },
      ],
    };
  },
  watch: {
    selectedItem() {
      const queryParams = {
        minPriceValue: this.selectedItem.minPriceValue,
        maxPriceValue: this.selectedItem.maxPriceValue,
      };
      this.$router.push({
        name: "all-products",
        query: { ...this.$route.query, ...queryParams },
      });
    },
  },
  methods: {
    clearFilter() {
      this.selectedItem = {
        minPriceValue: undefined,
        maxPriceValue: undefined,
      };
    },
  },
};
</script>

<style scoped></style>
