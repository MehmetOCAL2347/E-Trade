<template>

  <!-- Belki Markaya göre de filtre uygulanabilir -->

  <div class="sidebar-sticky custom-sticky">
    <div id="side-bar-categories" class="form-container">
      <side-bar-categories></side-bar-categories>
    </div>

    <div id="side-bar-comments" class="form-container">
      <side-bar-comments></side-bar-comments>
    </div>

    <div id="side-bar-price" class="form-container">
      <side-bar-price></side-bar-price>
    </div>

    <div id="side-bar-activate" class="form-container">
      <side-bar-activate></side-bar-activate>
    </div>
  </div>
</template>

<script>
import SideBarActivate from "./SideBarActivate.vue";
import SideBarCategories from "./SideBarCategories.vue";
import SideBarComments from "./SideBarComments.vue";
import SideBarPrice from "./SideBarPrice.vue";
export default {
  components: {
    SideBarComments,
    SideBarCategories,
    SideBarPrice,
    SideBarActivate,
  },
};
</script>

<style scoped>
.custom-sticky{
  padding: 25px;
}
.form-container {
  margin-top: 15px;
  margin-bottom: 15px;
}
</style>
