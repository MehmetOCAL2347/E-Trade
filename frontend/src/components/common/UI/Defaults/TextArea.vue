<template>
  <div>
    <label :for="labelId" class="form-label"
      >{{ labelInfo }} <span class="span-warning"> * </span></label
    >
    <textarea :id="labelId" :placeholder="placeholder" class="form-control" rows="3" required></textarea>
  </div>
</template>

<script>
export default {
  props: {
    labelInfo: {
      type: String,
      required: true,
    },
    labelId: {
      type: String,
      required: true,
    },
    placeholder: {
      type: String,
      required: false
    }
  },
};
</script>

<style scoped>
.form-label {
  font-size: var(--font-size-xSmall);
  padding-left: 8px;
  margin-top: 10px;
  margin-bottom: 3px;
}

.span-warning {
  color: var(--color-2);
}

.form-control:focus {
  outline: none !important;
  box-shadow: none !important;
  border-color: #ccc !important;
}
</style>
