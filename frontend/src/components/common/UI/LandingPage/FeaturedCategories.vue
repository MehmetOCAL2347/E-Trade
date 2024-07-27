<template>
  <div id="featured-categories" class="container-fluid">
    <h1>{{ title }}</h1>
    <Swiper
      :modules="modules"
      :slidesPerView="slidesPerView"
      :spaceBetween="spaceBetween"
      :navigation="true"
      :autoplay="{
        delay: 2500,
        disableOnInteraction: false,
      }"
      ref="mySwiper"
    >
      <SwiperSlide v-for="(photo, index) in photos" :key="index">
        <div class="cart-container">
          <img :src="photo" alt="" />
          <p>Kategori İsmi</p>
        </div>
      </SwiperSlide>
    </Swiper>
  </div>
</template>

<script>
import { Swiper, SwiperSlide } from "swiper/vue";
import { Autoplay, Navigation } from "swiper/modules";
import "swiper/css/bundle";
import "swiper/css";

export default {
  components: {
    Swiper,
    SwiperSlide,
  },
  data() {
    return {
      title: "Kategoriler",
      slidesPerView: 1,
      spaceBetween: 20,
      photos: [
        "https://img.freepik.com/free-photo/isolated-happy-smiling-dog-white-background-portrait-2_1562-691.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/Drawing-of-a-Dog-Home-400-x-400-1.webp/400px-Drawing-of-a-Dog-Home-400-x-400-1.webp.png?20211022070032",
        "https://img.freepik.com/free-photo/isolated-happy-smiling-dog-white-background-portrait-2_1562-691.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/Drawing-of-a-Dog-Home-400-x-400-1.webp/400px-Drawing-of-a-Dog-Home-400-x-400-1.webp.png?20211022070032",
        "https://img.freepik.com/free-photo/isolated-happy-smiling-dog-white-background-portrait-2_1562-691.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/Drawing-of-a-Dog-Home-400-x-400-1.webp/400px-Drawing-of-a-Dog-Home-400-x-400-1.webp.png?20211022070032",
        "https://img.freepik.com/free-photo/isolated-happy-smiling-dog-white-background-portrait-2_1562-691.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/Drawing-of-a-Dog-Home-400-x-400-1.webp/400px-Drawing-of-a-Dog-Home-400-x-400-1.webp.png?20211022070032",
      ],
      mediaQueries: {
        xs: window.matchMedia("(max-width: 576px)"),
        sm: window.matchMedia("(min-width: 576px) and (max-width: 768px)"),
        md: window.matchMedia("(min-width: 768px) and (max-width: 992px)"),
        lg: window.matchMedia("(min-width: 992px) and (max-width: 1200px)"),
        xl: window.matchMedia("(min-width: 1200px)"),
        xxl: window.matchMedia("(min-width: 1400px)"),
      },
    };
  },
  mounted() {
    this.updateSwiperSettings();
    window.addEventListener("resize", this.updateSwiperSettings);
  },
  beforeUnmount() {
    window.removeEventListener("resize", this.updateSwiperSettings);
  },
  methods: {
    updateSwiperSettings() {
      if (window.innerWidth >= 1490) {
        this.slidesPerView = 5;
      } else if (window.innerWidth >= 1200) {
        this.slidesPerView = 4;
      } else if (window.innerWidth >= 910) {
        this.slidesPerView = 3;
      } else if (window.innerWidth >= 612) {
        this.slidesPerView = 2;
      } else {
        this.slidesPerView = 1;
      }
    },
  },
  setup() {
    return {
      modules: [Autoplay, Navigation],
    };
  },
};
</script>

<style scoped>
#featured-categories {
  width: var(--width-main);
}
.swiper-slide {
  text-align: center;
  font-size: 18px;
  background: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
}

.cart-container {
  border: 1px solid var(--color-4);
  border-radius: 10px;
  transition: 0.3s ease;
  margin-top: 15px;
  margin-bottom: 15px;
  width: 400px;
  height: 400px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center; /* Center align text inside the paragraph */
  overflow: hidden; /* Ensure content does not overflow */
}

.cart-container:hover {
  cursor: pointer;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
  border: 1px solid var(--color-2);
}

.cart-container img {
  /* padding: 5px; */
  max-width: 100%; /* Ensure image fits within the container */
  max-height: 70%; /* Adjust based on your needs */
  object-fit: contain; /* Preserve aspect ratio and fit image */
  border-radius: 10px;
}

.cart-container p {
  margin: 0;
  padding: 10px 0; /* Optional padding for spacing */
  overflow: hidden;
  text-overflow: ellipsis; /* Add ellipsis if text overflows */
  word-wrap: break-word; /* Allow text to wrap to the next line */
}
</style>
