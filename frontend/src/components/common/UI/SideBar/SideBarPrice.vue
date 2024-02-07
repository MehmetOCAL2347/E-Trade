<template>
  <div>
    <h3>Fiyat</h3>
    <span class="clear-filter hover-effect clear-button" @click="clearFilter()">Temizle</span>
    <div class="form-check">
      <input
        class="form-check-input"
        type="radio"
        name="exampleRadios"
        id="option1"
        value="option1"
        v-model="selectedItem"
      />
      <label class="form-check-label hover-effect" for="option1"> $25 altında </label>
    </div>

    <div class="form-check">
      <input
        class="form-check-input"
        type="radio"
        name="exampleRadios"
        id="option2"
        value="option2"
        v-model="selectedItem"
      />
      <label class="form-check-label hover-effect" for="option2"> $25 to $50 </label>
    </div>

    <div class="form-check">
      <input
        class="form-check-input"
        type="radio"
        name="exampleRadios"
        id="option3"
        value="option3"
        v-model="selectedItem"
      />
      <label class="form-check-label hover-effect" for="option3"> $50 to $100 </label>
    </div>

    <div class="form-check">
      <input
        class="form-check-input"
        type="radio"
        name="exampleRadios"
        id="option4"
        value="option4"
        v-model="selectedItem"
      />
      <label class="form-check-label hover-effect" for="option4"> $100 to $200 </label>
    </div>

    <div class="form-check">
      <input
        class="form-check-input"
        type="radio"
        name="exampleRadios"
        id="option5"
        value="option5"
        v-model="selectedItem"
      />
      <label class="form-check-label hover-effect" for="option5"> $200 ve Üzeri </label>
    </div>

    <form class="d-flex">
      <div class="form-group">
        <input
          type="number"
          id="minPrice"
          name="minPrice"
          class="form-control"
          placeholder="$ Min"
          v-model="minValue"
        />
      </div>
      <div class="form-group">
        <input
          type="number"
          id="maxPrice"
          name="maxPrice"
          class="form-control"
          placeholder="$ Maks"
          v-model="maxValue"
        />
      </div>

      <button type="submit" class="btn btn-primary">Filtrele</button>
    </form>
  </div>
</template>

<script>
export default {
  data(){
    return {
      minValue: null,
      maxValue: null,
      selectedItem: null
    }
  },
  watch: {
    selectedItem(){
      console.log(this.selectedItem);
    },
    minValue(){
      console.log(this.minValue);
    },
    maxValue(){
      console.log(this.maxValue);
    }
  },
  methods: {
    clearFilter(){
      this.selectedItem = null;
      this.minValue = null;
      this.maxValue = null;
    }
  }
};
</script>

<style scoped></style>
