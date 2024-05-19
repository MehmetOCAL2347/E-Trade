<template>
  <div class="background-one">
    <card-type-one-vue></card-type-one-vue>
  </div>
</template>

<script>
import CardTypeOneVue from '@/components/common/UI/Users/CardTypeOne.vue';
export default {
    components: {
      CardTypeOneVue
    }
};
</script>

<style scoped>
.background-one{
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>
