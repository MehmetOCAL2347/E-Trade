<template>
  <div class="forgot-password">
    <form @submit.prevent id="forgot-password-form">
      <h3>{{ tradeName }}</h3>
      <h2>{{ forgotPasswordText }}</h2>
      <div class="form-group">
        <label for="email">Email</label>
        <input
          class="input"
          placeholder="Email Adresinizi Giriniz"
          type="email"
          id="email"
          v-model="email"
          required
        />
      </div>
    </form>
    <div>
      <button id="forgot-password-button" class="button">
        {{ forgotPasswordText }}
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: "",
      tradeName: "mZone",
      forgotPasswordText: "Şifremi Unuttum",
    };
  },
};
</script>

<style scoped>
#forgot-password-form {
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: space-around;
  height: 100%;
  width: 100%;
}

.forgot-password {
  min-height: 400px;
  min-width: 576px;
  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  padding: 2rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.form-group {
  margin-bottom: 1rem;
  width: 100%;
}

.input {
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 10px;
  width: 100%;
}

.button {
  width: 100%;
  border-radius: 10px;
  padding: 0.75rem;
  color: #fff;
  margin-bottom: 20px;
  border: none;
  cursor: pointer;
}

.button:hover {
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

#forgot-password-button {
  background-color: #244855;
}
</style>
