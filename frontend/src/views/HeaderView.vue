<template>
  <div id="the-header-component" class="container-fluid px-0">
    <header
      id="header-1"
      class="d-flex flex-wrap justify-content-start align-items-center pt-3 header"
    >
      <i class="bi bi-instagram mr"></i>
      <i class="bi bi-facebook mr"></i>
      <i class="bi bi-pinterest mr"></i>
      <i class="bi bi-tiktok mr"></i>
      <i class="bi bi-whatsapp mr"></i>
      <i class="bi bi-twitter-x mr"></i>
    </header>
    <header
      id="header-2"
      class="d-flex flex-wrap justify-content-between align-items-center pb-3 header"
    >
      <div class="container-box">
        <router-link
          to="/"
          class="d-flex align-items-center justify-content-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none"
          >{{ title }}
          <!-- TODO-1 Logo Eklencek
          <svg class="bi me-2" width="40" height="32">
            <use xlink:href="#bootstrap" />
          </svg> -->
          <!-- <span>{{ title }}</span> -->
        </router-link>
      </div>
      <div class="container-box">
        <ul class="nav nav-pills">
          <li
            class="d-flex flex-wrap justify-content-center align-items-center"
          >
            <router-link aria-current="page" :to="{ name: 'all-products' }">{{
              navItems[0]
            }}</router-link>
          </li>
          <li
            class="d-flex flex-wrap justify-content-center align-items-center"
            v-for="(category, index) in categories"
            :key="index"
          >
            <div class="dropdown">
              <button
                class="btn dropdown-toggle"
                type="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                {{ category.title }}
              </button>
              <ul class="dropdown-menu">
                <li v-for="(item, index) in category.subTitles" :key="index">
                  <router-link class="dropdown-item" :to="item.to">{{
                    item.name
                  }}</router-link>
                </li>
              </ul>
            </div>
          </li>
        </ul>
      </div>
      <div
        class="container-box d-flex justify-content-center align-items-center"
      >
        <div class="icon-with-text m-2 p-2">
          <router-link :to="{ name: 'login' }"
            ><i class="bi bi-box-arrow-in-right fs-4"></i>
            {{ navItems[1] }}
          </router-link>
        </div>
        <div class="icon-with-text m-2 p-2">
          <router-link :to="{ name: 'register' }"
            ><i class="bi bi-person-add fs-4"></i>
            {{ navItems[2] }}
          </router-link>
        </div>
        <div class="icon-with-text m-2 p-2">
          <router-link :to="{ name: 'cart' }">
            <div class="cart-icon">
              <i class="bi bi-cart3 fs-4"></i>
              <span class="badge">{{ cartCount }}</span>
            </div>
          </router-link>
        </div>
      </div>

      <!-- <div class="container-box">
        Yeni bir alan eklenmek istendiğinde bu şekilde eklenebilir
      </div> -->
    </header>
  </div>
</template>

<script>
export default {
  data() {
    return {
      title: "Mande",
      productCartCount: 0,
      localStorageTitle: "product-in-cart",
      navItems: ["Tüm Ürünler", "Giriş Yap", "Üye Ol", "Sepetim"],
      categories: [
        {
          title: "Aksesuar",
          subTitles: [
            { name: "Güneşlik", to: "/guneslik" },
            { name: "İç Aydınlatma", to: "/ic-aydinlatma" },
          ],
        },
        {
          title: "Yedek Parça",
          subTitles: [{ name: "Kol Dayama", to: "/kol-dayama" }],
        },
        {
          title: "Bakım",
          subTitles: [
            { name: "Pasta Cila", to: "/pasta-cila" },
            { name: "Lastik Parlatıcı", to: "/lastik-parlatici" },
            { name: "Koku", to: "/koku" },
          ],
        },
        {
          title: "Far",
          subTitles: [
            { name: "Led Far", to: "/led-far" },
            { name: "Zenon Far", to: "/zenon-far" },
          ],
        },
      ],
    };
  },
  // mounted(){
  //   let cart = JSON.parse(localStorage.getItem(this.localStorageTitle));
  //   this.productCartCount = cart.length;
  // },
  computed: {
    cartCount(){
      return this.$store.getters.getCartCount;
    }
  },
};
</script>

<style scoped>
#the-header-component {
  background-color: var(--color-5);
  color: var(--color-3);
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}
.header {
  width: var(--width-main);
  margin: 0 auto;
}
a,
button {
  font-size: 1.1rem;
  color: var(--color-3) !important;
}
.icon-with-text {
  text-align: center;
}
.icon-with-text p {
  margin: 0;
  font-size: 1.1rem;
}

.mr {
  margin-right: 3rem;
}
.cart-icon {
  position: relative;
  display: inline-block;
}
.cart-icon .badge {
  position: absolute;
  top: 0;
  right: 0;
  transform: translate(50%, -50%);
  background-color: var(--color-2);
  color: white;
  border-radius: 50%;
  padding: 0.25em 0.5em;
  font-size: 0.75em;
}
</style>
