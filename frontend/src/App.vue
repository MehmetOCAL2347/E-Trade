<template>
  <!-- <div id="app" class="d-flex flex-column min-vh-100">
    <the-header id="the-header" v-if="!$route.meta.hideNavbar"/>
    <the-main id="the-main" class="flex-grow-1"/>
    <the-footer id="the-footer" v-if="!$route.meta.hideNavbar" class="mt-auto"/>
  </div> -->

  <div id="app">
    <the-header id="the-header" v-if="!$route.meta.hideNavbar"/>
    <the-main id="the-main"/>
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

<style scoped>
#the-main{
  max-width: 2000px;
  margin: 0 auto;
}

/* #app {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

#the-footer {
  margin-top: auto;
} */
</style>
