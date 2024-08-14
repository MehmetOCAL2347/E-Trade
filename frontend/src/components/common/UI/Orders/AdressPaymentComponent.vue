<template>
  <div class="container">
    <div class="row">
      <div class="container mt-4">
        <div class="d-flex mb-1">
          <div
            class="btn-group"
            role="group"
            aria-label="Basic radio toggle button group"
          >
            <input
              type="radio"
              class="btn-check input-custom"
              name="btnradio"
              id="btnradio1"
              autocomplete="off"
              checked
              @click="updateCurrentTab('order-summary')"
            />
            <label class="btn btn-outline-primary" for="btnradio1"
              ><i class="bi bi-geo-alt-fill"></i> Sipariş Özeti</label
            >

            <input
              type="radio"
              class="btn-check input-custom"
              name="btnradio"
              id="btnradio2"
              autocomplete="off"
              checked
              @click="updateCurrentTab('address')"
            />
            <label class="btn btn-outline-primary" for="btnradio2"
              ><i class="bi bi-geo-alt-fill"></i> Adres Bilgileri</label
            >

            <input
              type="radio"
              class="btn-check input-custom"
              name="btnradio"
              id="btnradio3"
              autocomplete="off"
              @click="updateCurrentTab('payment')"
            />
            <label class="btn btn-outline-primary" for="btnradio3"
              ><i class="bi bi-credit-card-fill"></i>
              {{ titleOfPaymentHistory }}</label
            >
          </div>
        </div>

        <div class="address-form" v-if="currentTab === 'order-summary'">
          Sipariş Özeti
        </div>

        <!-- Address Form -->
        <div class="address-form" v-else-if="currentTab === 'address'">
          <h5 class="mt-3">
            <i class="bi bi-geo-alt-fill"></i> {{ titleOfAddNewAdress }}
          </h5>

          <form @submit.prevent="saveAddress" class="mt-3">
            <div class="row mb-3 container-custom-seperate">
              <h5 class="alert alert-secondary">{{ titleOfUserInfo }}</h5>

              <div class="col-md-6">
                <div class="form-group">
                  <floating-input
                    inputId="name-input"
                    placeholder="İsim"
                    :requiredArea="true"
                    typeOfInput="text"
                    iconClass="bi bi-person-fill"
                    messageText=""
                    :showMessageText="false"
                    v-model="fullAddress.userInformation.name"
                  ></floating-input>
                </div>
              </div>

              <div class="col-md-6">
                <div class="form-group">
                  <floating-input
                    inputId="surname-input"
                    placeholder="Soyisim"
                    :requiredArea="true"
                    typeOfInput="text"
                    iconClass="bi bi-person-bounding-box"
                    messageText=""
                    :showMessageText="false"
                    v-model="fullAddress.userInformation.surName"
                  ></floating-input>
                </div>
              </div>

              <div class="col-md-6">
                <div class="form-group">
                  <floating-input
                    inputId="email-input"
                    placeholder="E-mail Adresi"
                    :requiredArea="true"
                    typeOfInput="email"
                    iconClass="bi bi-envelope-at-fill"
                    messageText=""
                    :showMessageText="false"
                    v-model="fullAddress.userInformation.email"
                  ></floating-input>
                </div>
              </div>

              <div class="col-md-6">
                <div class="form-group">
                  <floating-input
                    inputId="email-input"
                    placeholder="Telefon Numarası"
                    :requiredArea="true"
                    typeOfInput="email"
                    iconClass="bi bi-telephone"
                    messageText=""
                    :showMessageText="false"
                    v-model="fullAddress.userInformation.phoneNumber"
                  ></floating-input>
                </div>
              </div>
            </div>

            <div class="row mb-3 container-custom-seperate">
              <h5 class="alert alert-secondary">{{ titleOfAdress }}</h5>

              <div class="col-md-6">
                <combobox-input
                  :labelInfo="labelInfoForProvince"
                  :labelId="labelIdForProvince"
                  :optionList="provinceList"
                  @selection-change="onProvinceSelected"
                  v-model="fullAddress.addressInformation.province"
                ></combobox-input>
              </div>

              <div class="col-md-6">
                <combobox-input
                  :labelInfo="labelInfoForDistrict"
                  :labelId="labelIdForDistrict"
                  :optionList="districtList"
                  v-model="fullAddress.addressInformation.district"
                ></combobox-input>
              </div>

              <div class="mb-3">
                <text-area
                  :labelInfo="labelInfoForAdress"
                  :labelId="labelIdForAdress"
                  :placeholder="placeholderForTextArea"
                ></text-area>
              </div>
            </div>

            <button @click="saveAdress" class="btn btn-primary">
              {{ titleOfSaveAdressButton }}
            </button>
          </form>
        </div>

        <!-- -->
        <div v-else>
          <h4>Ödeme Bilgileri</h4>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import TextArea from "../Defaults/TextArea.vue";
export default {
  components: { TextArea },
  data() {
    return {
      currentTab: "address",
      provinceBaseUrl: "https://turkiyeapi.dev/api/v1/",
      provinceEndPoint: "provinces",
      districtBaseUrl: "https://turkiyeapi.dev/api/v1/provinces",
      districtEndPoint: "",

      fullAddress: {
        // userInformation: {
        //   name: "",
        //   surName: "",
        //   email: "",
        //   phoneNumber: "",
        // },
        // addressInformation: {
        //   province: "",
        //   district: "",
        //   address: "",
        // },
      },

      placeholderForTextArea: "Lütfen Açık Adresinizi Giriniz",

      labelInfoForProvince: "Şehir Seçiniz",
      labelInfoForDistrict: "İlçe Seçiniz",
      labelInfoForAdress: "Adresinizi Giriniz",

      labelIdForProvince: "province",
      labelIdForDistrict: "district",
      labelIdForAdress: "adress",

      titleOfPaymentHistory: "Ödeme Bilgileri",
      titleOfAddNewAdress: "Yeni Adres Ekle",
      titleOfAdress: "Adres",
      titleOfUserInfo: "Kişisel Bilgiler",
      titleOfSaveAdressButton: "Adresi Kaydet",
      titleOfCreateOrderButton: "Sipariş Oluştur",

      countryList: ["", "Türkiye"],
      provinceList: [{ id: "", name: "Şehir Seçiniz" }],
      districtList: [{ id: "", name: "İlçe Seçiniz" }],
    };
  },
  methods: {
    updateCurrentTab(tabName) {
      this.currentTab = tabName;
    },
    async onProvinceSelected(selectedProvince) {
      await this.getDistrictsFromProvince(selectedProvince);
    },

    async getDistrictsFromProvince(selectedProvince) {
      console.log(selectedProvince);

      this.districtEndPoint = "?name=";
      let endPoint = (this.districtEndPoint += selectedProvince);
      console.log(endPoint);

      await this.$store.dispatch("getDistrictsFromProvince", {
        baseUrl: this.districtBaseUrl,
        endPoint: endPoint,
      });
      this.districtList = this.$store.getters.getDistrictList.districts;
      console.log(this.districtList);

      /*
      this.districtList = [...this.districtList, ...this.$store.getters.getDistrictList];
      console.log(this.districtList);
      */
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

    saveAdress() {
      /*
      0. Uygulama açılırken localstorage'da adres varsa o bilgiler ile doldurulcak
      1. Adres localstorage'a kaydedilcek
      2. Bir daha gelindiğinde ilgili adres getirilcek
      3. Eğer adres yok ise kaydedilcek
      4. Eğer adres var ve değişiklik yapılmışsa güncellencek
*/
      this.$store.dispatch("saveFullAdress", {
        fullAdress: this.fullAddress,
      });
    },

    cretateOrder() {},

    getExistFullAdress() {
      this.fullAddress = this.$store.getters.getExistFullAdress;
      console.log("Başlangıç");
      //console.log(this.fullAddress.userInformation.name);
    },
  },
  mounted() {
    this.getProvinceList();
    this.getExistFullAdress();
  }
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
</style>
