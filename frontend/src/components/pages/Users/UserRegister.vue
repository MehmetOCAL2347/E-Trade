<template>
  <div id="register-component">
    <register-component></register-component>
  </div>
</template>

<script>
import RegisterComponent from '@/components/common/UI/Users/RegisterComponent.vue';
export default {
    components: {
        RegisterComponent
    }
}
</script>

<style scoped>
#register-component{
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>
