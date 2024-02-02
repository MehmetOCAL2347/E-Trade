<template>
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <!-- <p id="product-title">{{ bulletPoints[0] }}</p> -->
        <ul>
          <li v-for="(bulletPoint, index) in bulletPoints" :key="index">
            {{ bulletPoint }}
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["bulletPoints"],
  created() {},
};
</script>

<style scoped>
</style>
