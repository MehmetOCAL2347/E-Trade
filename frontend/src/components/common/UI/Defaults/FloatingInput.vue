<template>
  <div class="input-group">
    <span class="input-group-text text-bg-danger"
      ><i :class="iconClass"></i
    ></span>
    <div class="form-floating">
      <input
        :type="typeOfInput"
        class="form-control custom-input-group"
        :id="inputId"
        :placeholder="placeholder"
        :value="modelValue"
        @input="$emit('update:modelValue', $event.target.value)"
        :required="isNecessary"
      />
      <label :for="inputId" class="label-for-input"
        >{{ placeholder }}
        <strong v-if="isNecessary" class="strong-custom">*</strong></label
      >
    </div>
  </div>
  <p class="message-text">{{ messageText }}</p>
</template>

<script>
export default {
  props: {
    iconClass: {
      type: String,
      required: true,
    },
    placeholder: {
      type: String,
      required: true,
    },
    typeOfInput: {
      type: String,
      required: true,
    },
    messageText: {
      type: String,
      required: true
    },
    showMessageText: {
      type: Boolean,
      required: true,
      default: false
    },
    inputId: {
      type: String,
      required: true
    },
    modelValue: {
      type: String,
      required: true
    },
    isNecessary: {
      type: Boolean,
      required: false,
      default: true
    }
  },
};
</script>

<style scoped>
.custom-input-group {
  width: 100%;
}
input:focus {
  outline: none !important;
  box-shadow: none !important;
  border-color: #ccc !important; /* Set to your desired border color */
}

.strong-custom {
  color: var(--color-2);
}

.message-text{
  font-size: var(--font-size-xSmall);
  color: var(--color-2);
  padding-left: 40px;
}

.label-for-input{
  font-size: var(--font-size-xSmall);
}
</style>
