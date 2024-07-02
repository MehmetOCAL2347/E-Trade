<template>
  <div class="register-container">
    <form @submit.prevent="register" id="register-form">
      <h3>{{ tradeName }}</h3>
      <h2>{{ registerText }}</h2>
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
        <!-- TODO- Ekleme yapılcak-->
        <div class="form-alert-container">
          <p class="form-alert" v-if="alertEmail">{{ messageEmail }}</p>
        </div>
      </div>
      <div class="form-group">
        <label for="password">Şifre</label>
        <input
          class="input"
          placeholder="Şifrenizi Giriniz"
          type="password"
          id="password"
          v-model="password"
          required
        />
      </div>
      <div class="form-group">
        <label for="password-retry">Şifre Doğrulama</label>
        <input
          class="input"
          placeholder="Şifrenizi Doğrulayınız"
          type="password"
          id="password-retry"
          v-model="passwordRetry"
          required
        />
      </div>
      <div class="register-button-container">
        <button id="register-button" class="button" type="submit">
          {{ registerText }}
        </button>
      </div>
    </form>

    <div class="to-login-page-container">
      Zaten Hesabınız Var mı?
      <router-link to="/login" class="to-login-page">Giriş Yap</router-link>
    </div>
    <div class="line-with-text">Veya</div>
    <div>
      <button id="sign-with-google" class="button">Google İle Giriş Yap</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: "",
      password: "",
      passwordRetry: "",
      loading: false,
      registerText: "Kayıt Ol",
      tokenName: "token-mande",
      error: null,
      tradeName: "mZone",
      messageEmail: "Hatalı Email",
      alertEmail: true,
      registeredUser: {}
    };
  },
  methods: {
    async register(){
      // Şifrelerin eşleşmesi lazım - ErrorHandler yapısı kurulmalı
      if(this.isPasswordsEquals()){
        
        try {
          await this.$store.dispatch("register", {
            email: this.email,
            password: this.password
          });

          // Burda kaldımm
        } catch (error) {
          console.log("Error: " + error);
        }

      }
    },

    isPasswordsEquals(){
      if (this.password===this.passwordRetry) {
        return true;
      }else {
        return false;
      }
    }
  }
};
</script>

<style scoped>
#register-form {
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: space-around;
  height: 100%;
  width: 100%;
}

.register-container {
  min-height: 600px;
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
  margin-left: 10px;
  margin-bottom: 1rem;
  width: 100%;
}

.input {
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 10px;
  width: 100%;
}

.register-button-container{
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

.form-alert-container {
  padding-top: 3px;
  padding-left: 7px;
}

.form-alert {
  color: red;
}

.button:hover {
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

#register-button {
  background-color: #244855;
}

#sign-with-google {
  background-color: #e64833;
}

.to-login-page-container {
  text-align: center;
}

.to-login-page {
  text-decoration: underline;
  color: #874f41;
}

.line-with-text {
  display: flex;
  align-items: center;
  text-align: center;
  margin: 20px 0;
}

.line-with-text::before,
.line-with-text::after {
  content: "";
  flex: 1;
  border-bottom: 1px solid #000;
}

.line-with-text::before {
  margin-right: 10px;
}

.line-with-text::after {
  margin-left: 10px;
}

label {
  margin-bottom: 0.5rem;
}
</style>
