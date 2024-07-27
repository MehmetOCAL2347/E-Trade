<template>
  <div class="container-fluid">
    <div class="row">
      <!-- <div class="col-md-3 col-lg-2 d-md-block sidebar custom-sidebar"> -->
      <div class="col-lg-3 col-md-4 mb-6 mb-md-0 sidebar custom-sidebar">
        <side-bar></side-bar>
      </div>

      <!-- <div class="col-md-9 ml-sm-auto col-lg-10 px-md-4"> -->
      <div class="col-lg-9 col-md-12">
        <div class="row">
          <router-view></router-view>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import SideBar from "@/components/common/UI/SideBar/SideBar.vue";
export default {
  components: {
    SideBar
  },
};
</script>

<style scoped>
.custom-sidebar{
  padding: 0;
}
</style>
