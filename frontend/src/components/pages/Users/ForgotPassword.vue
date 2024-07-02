<template>
  <div id="forgot-password-component">
    <forgot-password-component></forgot-password-component>
  </div>
</template>

<script>
import ForgotPasswordComponent from "@/components/common/UI/Users/ForgotPasswordComponent.vue";
export default {
    components: {
        ForgotPasswordComponent
    }
};
</script>

<style scoped>
#forgot-password-component{
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>
