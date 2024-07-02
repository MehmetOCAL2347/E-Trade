<template>
  <div id="login-component">
    <login-component></login-component>
  </div>
</template>

<script>
import LoginComponent from '@/components/common/UI/Users/LoginComponent.vue';
export default {
    components: {
      LoginComponent
    }
};
</script>

<style scoped>
#login-component{
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>
