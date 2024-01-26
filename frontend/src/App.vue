<template>
  <the-header />
  <the-main />
  <the-footer />
</template>

<script>
import TheFooter from "./components/common/TheFooter.vue";
import TheHeader from "./components/common/TheHeader.vue";
import TheMain from "./components/common/TheMain.vue";

export default {
  name: "App",
  components: {
    TheHeader,
    TheMain,
    TheFooter,
  },
};
</script>
<style></style>
