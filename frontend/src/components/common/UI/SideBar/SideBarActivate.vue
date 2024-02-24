<template>
  <div>
    <h4 id="activation-title">{{ title }}</h4>
    <div class="form-check form-switch">
      <input
        class="form-check-input"
        type="checkbox"
        role="switch"
        id="flexSwitchCheckChecked"
        :value="isActive"
        v-model="isActive"
      />
      <label class="form-check-label" for="flexSwitchCheckChecked"
        >{{ switchExplain }}</label
      >
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isActive: true,
      title: "Satışta Olmayan Ürünler",
      switchExplain: "Gösterme"
    };
  },
  watch: {
    isActive() {
      if (!this.isActive) {
        this.createParams(false);
        this.switchExplain = "Göster"
      } else {
        this.createParams(undefined);
        this.switchExplain = "Gösterme"
      }
    },
  },
  methods: {
    createParams(value) {
      const queryParams = { isActive: value };
      this.$router.push({
        name: "all-products",
        query: { ...this.$route.query, ...queryParams },
      });
    },
    checkParamExist(){
      let query = this.$route.query.isActive;
      if(query){
        this.isActive = false;
      }else {
        this.isActive = true;
      }
    }
  },
  created(){
    this.checkParamExist();
  }
};
</script>

<style scoped></style>
