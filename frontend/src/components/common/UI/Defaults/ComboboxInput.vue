<template>
  <div>
    <label :for="labelId" class="form-label"
      >{{ labelInfo }} <span class="span-warning"> * </span></label
    >
    <select
      :id="labelId"
      class="form-select"
      @change="onSelectionChange"
      :value="value"
    >
      <option v-for="(option, index) in optionList" :key="index">
        {{ option.name }}
      </option>
    </select>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  props: {
    labelInfo: {
      type: String,
      required: true,
    },
    optionList: {
      type: Array,
      required: true,
    },
    labelId: {
      type: String,
      required: true,
    },
    value: [String, Number],
  },
  methods: {
    onSelectionChange(event) {      
      const selectedValue = event.target.value;
      this.$emit("selection-change", selectedValue);
    },
  },
  computed: {
    selectedValue() {
      return this.value;
    },
  },
};
</script>

<style scoped>
select:focus {
  outline: none !important;
  box-shadow: none !important;
  border-color: #ccc !important;
}

.form-label {
  font-size: var(--font-size-xSmall);
  padding-left: 8px;
  margin-top: 10px;
  margin-bottom: 3px;
}

.span-warning {
  color: var(--color-2);
}
</style>
