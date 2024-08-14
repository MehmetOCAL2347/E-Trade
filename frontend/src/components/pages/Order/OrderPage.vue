<template>
  <div id="order-component">
    <!-- <order-component></order-component> -->
    <adress-payment-component></adress-payment-component>
  </div>
</template>

<script>
// import OrderComponent from "@/components/common/UI/Orders/OrderComponent.vue";
import AdressPaymentComponent from "@/components/common/UI/Orders/AdressPaymentComponent.vue";
export default {
  components: {
    // OrderComponent,
    AdressPaymentComponent,
  },
  data() {
    return {};
  },
};
</script>

<style scoped></style>
