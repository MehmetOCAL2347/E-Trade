<template>
  <div class="container">
    <div class="row">
      <div class="container mt-4">
        <div class="address-form">
          <h5 class="mt-3">
            <i class="bi bi-geo-alt-fill"></i> {{ titleOfAdressSummary }}
          </h5>

          <!-- Adres ve Kişisel Bilgiler Form Başlangıç-->
          <form @submit.prevent="handleSubmit" class="mt-3">
            <div class="row mb-3 container-custom-seperate">
              <h5 class="alert alert-secondary">{{ titleOfUserInfo }}</h5>

              <!-- Kişisel Bilgiler Başlangıç-->
              <div class="col-md-6">
                <div class="form-group">
                  <floating-input
                    inputId="name-input"
                    placeholder="İsim"
                    typeOfInput="text"
                    iconClass="bi bi-person-fill"
                    messageText=""
                    :showMessageText="false"
                    v-model="orderDetail.userInformation.name"
                  ></floating-input>
                </div>
              </div>

              <div class="col-md-6">
                <div class="form-group">
                  <floating-input
                    inputId="surname-input"
                    placeholder="Soyisim"
                    typeOfInput="text"
                    iconClass="bi bi-person-bounding-box"
                    messageText=""
                    :showMessageText="false"
                    v-model="orderDetail.userInformation.surName"
                  ></floating-input>
                </div>
              </div>

              <div class="col-md-6">
                <div class="form-group">
                  <floating-input
                    inputId="email-input"
                    placeholder="E-mail Adresi"
                    typeOfInput="email"
                    iconClass="bi bi-envelope-at-fill"
                    messageText=""
                    :showMessageText="false"
                    v-model="orderDetail.userInformation.email"
                  ></floating-input>
                </div>
              </div>

              <div class="col-md-6">
                <div class="form-group">
                  <floating-input
                    inputId="email-input"
                    placeholder="Telefon Numaranız"
                    typeOfInput="number"
                    iconClass="bi bi-telephone"
                    messageText=""
                    :showMessageText="false"
                    v-model="orderDetail.userInformation.phoneNumber"
                  ></floating-input>
                </div>
              </div>
            </div>
            <!-- Kişisel Bilgiler Bitiş-->

            <!-- Adres Başlangıç-->
            <div class="row mb-3 container-custom-seperate">
              <h5 class="alert alert-secondary">{{ titleOfAdress }}</h5>

              <div class="col-md-6">
                <combobox-input
                  :labelInfo="labelInfoForProvince"
                  :labelId="labelIdForProvince"
                  :optionList="provinceList"
                  @selection-change="onProvinceSelected"
                  v-model="orderDetail.addressInformation.province"
                ></combobox-input>
              </div>

              <div class="col-md-6">
                <combobox-input
                  :labelInfo="labelInfoForDistrict"
                  :labelId="labelIdForDistrict"
                  :optionList="districtList"
                  v-model="orderDetail.addressInformation.district"
                ></combobox-input>
              </div>

              <div class="col-md-6">
                <combobox-input
                  :labelInfo="labelInfoForPaymentMethod"
                  :labelId="labelIdForPaymentMethod"
                  :optionList="paymentMethodList"
                  v-model="orderDetail.paymentInformation.paymentMethod"
                ></combobox-input>
              </div>

              <div class="mb-3">
                <text-area
                  :labelInfo="labelInfoForAdress"
                  :labelId="labelIdForAdress"
                  :placeholder="placeholderForTextArea"
                  v-model="orderDetail.addressInformation.address"
                  :isNecessary="true"
                ></text-area>
              </div>
            </div>
            <!-- Adres Bitiş-->

            <!-- Sipariş Oluştur butonu ve hata mesajı, başarılı mesajın gözüktüğü kısım Başlangıç-->
            <div>
              <button type="submit" class="btn btn-primary">
                {{ titleOfCreateOrderButton }}
              </button>

              <!-- Hata mesajı varsa gösterilir-->
              <div
                v-if="errorMessage !== ''"
                class="alert alert-danger mt-2"
                role="alert"
              >
                <p class="p-custom">{{ errorMessage }}</p>
              </div>

              <!-- Başarılı olundu mesajı gösterilir-->
              <div
                v-if="successMessage !== ''"
                class="alert alert-success mt-2"
                role="alert"
              >
                <p class="p-custom">{{ successMessage }}</p>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import TextArea from "../Defaults/TextArea.vue";

import { CreateOrderRequest } from "@/utils/Order/CreateOrderRequest";

export default {
  components: { TextArea },
  data() {
    return {
      provinceBaseUrl: "https://turkiyeapi.dev/api/v1/",
      provinceEndPoint: "provinces",
      districtBaseUrl: "https://turkiyeapi.dev/api/v1/provinces",
      districtEndPoint: "",

      placeholderForTextArea: "Lütfen Açık Adresinizi Giriniz",

      labelInfoForProvince: "Şehir Seçiniz",
      labelInfoForDistrict: "İlçe Seçiniz",
      labelInfoForAdress: "Adresinizi Giriniz",
      labelInfoForPaymentMethod: "Ödeme Yöntemini Seçiniz",

      labelIdForProvince: "province",
      labelIdForDistrict: "district",
      labelIdForAdress: "adress",
      labelIdForPaymentMethod: "payment-method",

      titleOfPaymentHistory: "Ödeme Bilgileri",
      titleOfAdress: "Adres",
      titleOfUserInfo: "Kişisel Bilgiler",
      titleOfCreateOrderButton: "Sipariş Oluştur",
      titleOfOrderSummary: "Sipariş Özeti",
      titleOfAdressSummary: "Adres ve Kişisel Bilgiler",

      errorMessage: "",
      successMessage: "",

      countryList: ["", "Türkiye"],
      provinceList: [],
      districtList: [],
      paymentMethodList: [
        { id: "0", name: "Kapıda Kredi Kartı İle Ödeme" },
        { id: "1", name: "Kapıda Nakit Ödeme" },
      ],

      orderDetail: {
        userInformation: {
          name: "",
          surName: "",
          email: "",
          phoneNumber: "",
        },
        addressInformation: {
          province: "",
          district: "",
          address: "",
        },
        paymentInformation: {
          paymentMethod: "",
        },
      },
    };
  },
  methods: {
    async onProvinceSelected(selectedProvince) {
      await this.getDistrictsFromProvince(selectedProvince);
    },

    async getDistrictsFromProvince(selectedProvince) {
      this.districtEndPoint = "?name=";
      let endPoint = (this.districtEndPoint += selectedProvince);

      await this.$store.dispatch("getDistrictsFromProvince", {
        baseUrl: this.districtBaseUrl,
        endPoint: endPoint,
      });
      this.districtList = this.$store.getters.getDistrictList.districts;
    },
    async getProvinceList() {
      try {
        await this.$store.dispatch("getAllProvinces", {
          baseUrl: this.provinceBaseUrl,
          endPoint: this.provinceEndPoint,
        });
        this.provinceList = [
          ...this.provinceList,
          ...this.$store.getters.getProvinceList,
        ];
      } catch (error) {
        console.log(error);
      }
    },

    checkEmptyAreas() {
      if (
        !this.orderDetail.userInformation.name ||
        !this.orderDetail.userInformation.surName ||
        !this.orderDetail.userInformation.email ||
        !this.orderDetail.userInformation.phoneNumber ||
        !this.orderDetail.addressInformation.province ||
        !this.orderDetail.addressInformation.district ||
        !this.orderDetail.addressInformation.address ||
        !this.orderDetail.paymentInformation.paymentMethod
      ) {
        this.errorMessage = "Tüm alanların dolu olması gerekmektedir";
        return false;
      } else {
        return true;
      }
    },

    checkPhoneNumber() {
      const phoneRegex = /^\d{10}$/;
      if (!phoneRegex.test(this.orderDetail.userInformation.phoneNumber)) {
        this.errorMessage = "Telefon numarası 10 haneden oluşmalıdır";
        return false;
      } else {
        return true;
      }
    },

    checkEmailAdress() {
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!emailRegex.test(this.orderDetail.userInformation.email)) {
        this.errorMessage = "Geçerli mail adresi giriniz";
        return false;
      } else {
        return true;
      }
    },

    handleSubmit() {
      let valueEmptyArea = this.checkEmptyAreas();
      let valuePhoneNumber = this.checkPhoneNumber();
      let valueEmailAdress = this.checkEmailAdress();

      if (valueEmptyArea && valuePhoneNumber && valueEmailAdress) {
        this.createOrder();
      } else {
        console.log(this.errorMessage);
      }
    },

    createRequestObject() {
      console.log(this.$store.getters.getCart);

      return new CreateOrderRequest({
        name: this.orderDetail.userInformation.name,
        surName: this.orderDetail.userInformation.surName,
        email: this.orderDetail.userInformation.email,
        phoneNumber: this.orderDetail.userInformation.phoneNumber,
        province: this.orderDetail.addressInformation.province,
        district: this.orderDetail.addressInformation.district,
        address: this.orderDetail.addressInformation.address,
        paymentMethod: this.orderDetail.paymentInformation.paymentMethod,
        discountCode: this.$store.getters.getDiscountCode.discountCode,
        cartItemList: [
          // Bunu da almamız lazım
          { id: "66c5d0e1f9dda962b32d92d8", quantity: 4 },
          { id: "66c5d2acf9dda962b32d92dc", quantity: 3 },
          { id: "ID-B0CH8H1TT8", quantity: 5 },
        ],
      });
    },

    async createOrder() {
      const createOrderRequest = this.createRequestObject();

      console.log(createOrderRequest);

      try {
        await this.$store.dispatch("createOrder", createOrderRequest);
      } catch (error) {
        console.log(error);
      }

      this.successMessage = "Sipariş Başarılı Şekilde oluşturuldu";
      this.errorMessage = "";
      /*
      Product listesini ve discount code değerini localstora'dan silmek lazım
      */

      setTimeout(() => {
        this.successMessage = "";
      }, 3000);
    },
  },

  mounted() {
    this.getProvinceList();
  },
};
</script>

<style scoped>
.container {
  max-width: 800px;
  padding: 20px 0px;
}

.tabs-button {
  width: 50%;
}

.address-form {
  border: 1px solid #ccc;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.5);
  border-radius: 5px;
  padding: 20px;
}

.my-line {
  border: 0;
  border-top: 2px solid #ccc; /* You can adjust the thickness and color */
  margin-bottom: 0;
}

.container-custom-seperate {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 10px;
  margin: 2px 0px;
}

.p-custom {
  margin-bottom: 0;
  font-size: var(--font-size-xSmall);
}
</style>
