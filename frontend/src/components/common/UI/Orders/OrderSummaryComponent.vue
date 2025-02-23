<template>
  <div class="container mt-5">
    <div class="steps-bar">
      <div class="step active">1</div>
      <div class="step inactive">2</div>
      <div class="step inactive">3</div>
      <div class="step inactive">4</div>
    </div>
  </div>
  <div id="step-content" class="mt-3">Sipariş Detayı</div>
</template>

<script></script>

<style scoped>
.steps-bar {
  display: flex;
  justify-content: space-between;
  position: relative;
  margin: 50px 0;
}
.steps-bar::before {
  content: "";
  position: absolute;
  top: 50%;
  left: 10px;
  right: 10px;
  height: 5px;
  background-color: #007bff;
  z-index: 1;
}
.step {
  position: relative;
  width: 30px;
  height: 30px;
  border-radius: 50%;
  background-color: #007bff;
  color: white;
  text-align: center;
  line-height: 30px;
  font-weight: bold;
  z-index: 2;
  transition: background-color 0.3s;
}
.step.active {
  background-color: #0056b3;
}
.step.inactive {
  background-color: #e9ecef;
  color: #6c757d;
}
</style>
