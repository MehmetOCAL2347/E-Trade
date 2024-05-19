<template>
  <div id="app">
    <the-header v-if="!$route.meta.hideNavbar"/>
    <the-main />
    <the-footer v-if="!$route.meta.hideNavbar"/>
  </div>
</template>

<script>
import TheHeader from './components/common/ThePage/TheHeader.vue';
import TheMain from './components/common/ThePage/TheMain.vue';
import TheFooter from './components/common/ThePage/TheFooter.vue';

export default {
  components: {
    TheHeader,
    TheMain,
    TheFooter
  }
};
</script>

<style scoped></style>
