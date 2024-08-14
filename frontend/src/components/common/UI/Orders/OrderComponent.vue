<template>
  <div class="payment-form">
    <form @submit.prevent="submitForm">
      <h3>{{ titleOfComponent }}</h3>

      <div id="user-information">
        <h4>{{ titleOfUserInformation }}</h4>

        <div class="form-group">
          <floating-input
            inputId="name-input"
            :placeholder="titleOfName"
            :requiredArea="true"
            typeOfInput="text"
            iconClass="bi bi-person-fill"
            messageText=""
            :showMessageText="false"
          ></floating-input>
        </div>

        <div class="form-group">
          <floating-input
            inputId="surname-input"
            :placeholder="titleOfSurname"
            :requiredArea="true"
            typeOfInput="text"
            iconClass="bi bi-person-bounding-box"
            messageText=""
            :showMessageText="false"
          ></floating-input>
        </div>

        <div class="form-group">
          <floating-input
            inputId="email-input"
            :placeholder="titleOfEmail"
            :requiredArea="true"
            typeOfInput="email"
            iconClass="bi bi-envelope-at-fill"
            messageText="E-Arşiv Fatura İçin E-Posta Adresi Gereklidir"
            :showMessageText="false"
          ></floating-input>
        </div>

        <!-- <div class="form-group">
          <label for="cardName"
            >{{ titleOfSurname }}
            <strong class="strong-custom">*</strong></label
          >
          <input type="text" id="cardName" v-model="form.cardName" required />
        </div> -->
      </div>

      <button class="btn btn-success" type="submit">Submit Payment</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      titleOfComponent: "Sipariş Bilgi Formu",
      titleOfUserInformation: "Kişisel Bilgiler",
      titleOfName: "İsim",
      titleOfSurname: "Soyisim",
      titleOfEmail: "E-posta",
      form: {
        cardNumber: "",
        cardName: "",
        expiryDate: "",
        cvv: "",
      },
    };
  },
  methods: {
    submitForm() {
      // Handle form submission
      console.log(this.form);
      // Add your payment processing logic here
    },
  },
};
</script>

<style scoped>
.payment-form {
  padding: 60px 30px;
  width: 50%;
  margin: 0 auto;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-top: 50px;
  margin-bottom: 50px;
}
.form-group {
  margin-bottom: 15px;
}
form label {
  display: block;
  margin-bottom: 5px;
}
form input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.strong-custom {
  color: var(--color-2);
}
</style>
