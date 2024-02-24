<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-md-3 col-lg-2 d-md-block bg-light sidebar">
        <side-bar></side-bar>
      </div>

      <div class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
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
