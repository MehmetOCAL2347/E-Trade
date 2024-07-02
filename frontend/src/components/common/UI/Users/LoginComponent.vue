<template>
  <div class="login-container">
    <form @submit.prevent="login" id="login-form">
      <h3>{{ tradeName }}</h3>
      <h2>{{ loginText }}</h2>
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
      <div id="remind-forgot">
        <div>
          <input id="remind-me" type="checkbox" />
          <label for="remind-me">Beni Hatırla</label>
        </div>
        <div>
          <router-link to="forgot-password" class="to-forget-password"
            >Şifremi Unuttum</router-link
          >
        </div>
      </div>
      <button id="submit-button" class="button" type="submit">
        <span
          class="spinner-border spinner-border-sm"
          aria-hidden="true"
          v-if="loading"
        ></span>
        <span role="status">{{ loginText }}</span>
      </button>
      <div class="to-register-page-container">
        Henüz Üye Değil Misiniz?
        <router-link to="/register" class="to-register-page"
          >Kayıt Ol</router-link
        >
      </div>
    </form>
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
      loginUser: {},
      loading: false,
      loginText: "Giriş Yap",
      tokenName: "token-mande",
      error: null,
      tradeName: "mZone",
    };
  },
  methods: {
    async login() {
      this.loading = true;
      this.error = null;
      this.loginText = "Yükleniyor...";

      try {
        await this.$store.dispatch("login", {
          email: this.email,
          password: this.password,
        });

        this.loginUser = this.$store.getters.getLoginUser;

        if (this.loginUser.status === 200) {
          localStorage.setItem(this.tokenName, this.loginUser.data.jwt);
          this.$router.push({ name: "main" });
        } else if (this.loginUser.status === 404) {
          localStorage.setItem(this.tokenName, this.loginUser.data.jwt);
          console.log("Kullanıcı Bulunamadı");
        }

        // Redirect To main page
        // if(){

        // }
        // this.$router.push({ name: "main" });

        //
      } catch (error) {
        this.error = error + " Login Failed";
        // Uyarı mesajı verilebilir
      } finally {
        this.loading = false;
        this.loginText = "Giriş Yap";
      }
    },
    isTokenExist() {
      const token = localStorage.getItem(this.tokenName);
      if (token !== null && token !== "undefined") {
        // Expire olup olmadığını kontrol edicez
        return true;
      } else {
        return false;
      }
    },
  },
  created() {
    if (this.isTokenExist()) {
      console.log("Daha önce giriş yapılmış");
    }
  },
};
</script>

<style scoped>
#login-form {
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: space-around;
  height: 100%;
  width: 100%;
}

.login-container {
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
  margin-bottom: 1rem;
  width: 100%;
}

.input {
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 10px;
  width: 100%;
}

#remind-forgot {
  width: 100%;
  display: flex;
  justify-content: space-between;
  flex-direction: row;
  padding-top: 20px;
  padding-bottom: 20px;
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

#submit-button {
  background-color: #244855;
}

#sign-with-google {
  background-color: #e64833;
}

#register-button {
  background-color: #90aead;
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

.to-forget-password {
  text-decoration: underline;
  color: #874f41;
}

.to-register-page-container {
  text-align: center;
}

.to-register-page {
  text-decoration: underline;
  color: #874f41;
}

label {
  margin-bottom: 0.5rem;
}
</style>
