<template>
  <transition name="modal">
    <div v-if="isVisible" class="modal">
      <div class="modal-content container">
        <div class="row">
          <div>
            <span class="close" @click="closeDetails">&times;</span>
          </div>

          <div class="col-lg-6 col-md-12 product-view">
            <div
              class="main-photo-wrapper"
              @mousemove="zoom"
              @mouseleave="resetZoom"
              :style="zoomStyles"
            >
              <img :src="mainPhoto" class="main-photo" />
            </div>
            <div class="small-photos">
              <img
                v-for="(photo, index) in this.detailProduct.images"
                :key="index"
                :src="photo.url"
                @click="updateMainPhoto(photo.url)"
                class="small-photo"
              />
            </div>
          </div>

          <div class="col-lg-6 col-md-12">
            <h1>{{ this.detailProduct.name }}</h1>
            <div class="d-flex align-items-center">
              <span class="badge bg-success">{{
                this.detailProduct.starPoint
              }}</span>
            </div>
            <div class="my-3">
              <span class="h4 me-2"
                >{{ this.detailProduct.price }}
                {{ this.detailProduct.priceType }}</span
              >
            </div>
            <div class="mt-3">
              <p><strong>Ürün Kodu:</strong> {{ this.detailProduct.code }}</p>
              <p><strong>Stok:</strong> {{ this.detailProduct.count }}</p>
              <p>
                <strong>Kategori:</strong> {{ this.detailProduct.categoryName }}
              </p>
              <p>
                <strong>Satıcı:</strong> {{ this.detailProduct.sellerName }}
              </p>
            </div>
            <button class="btn btn-success btn-lg w-100" @click="addToCart">
              + Sepete Ekle
            </button>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  props: {
    isVisible: Boolean,
    detailProduct: {},
    zoomStyle: Array,
  },
  data() {
    return {
      zoomStyles: {},
    };
  },
  methods: {
    closeDetails() {
      this.$emit("close-quick-view");
    },
    zoom(event) {
      const { offsetX, offsetY, target } = event;
      const { offsetWidth, offsetHeight } = target;

      const x = (offsetX / offsetWidth) * 100;
      const y = (offsetY / offsetHeight) * 100;

      this.zoomStyles.backgroundPosition = `${x}% ${y}%`;
      this.zoomStyles.backgroundSize = "250%";
    },
    resetZoom() {
      this.zoomStyles.backgroundPosition = "center";
      this.zoomStyles.backgroundSize = "100%";
    },
    updateMainPhoto(photo) {
      this.zoomStyles.backgroundImage = `url(${photo})`;
    },
  },
  watch: {
    isVisible(newValue) {
      if (newValue) {
        this.$emit("open");
      } else {
        this.$emit("close");
      }
    },
  },
  created() {
    this.zoomStyles = this.zoomStyle; // Props'tan gelen değer değişkene atandı
  },
};
</script>

<style scoped>
.modal {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
  background-color: #fefefe;
  padding: 20px;
  border: 1px solid #888;
  width: 80%;
  max-width: 1200px;
  min-height: 680px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

.modal-enter-active,
.modal-leave-active {
  transition: opacity 0.5s;
}

.modal-enter,
.modal-leave-to {
  opacity: 0;
}

.product-view {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
}

.image-and-description {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
}

.main-photo-wrapper {
  position: relative;
  width: 100%;
  max-width: 600px;
  height: 0;
  padding-bottom: 100%; /* Maintain aspect ratio */
  overflow: hidden;
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  cursor: zoom-in;
}

.main-photo {
  width: 100%;
  height: 100%;
  object-fit: contain; /* Ensures the entire image is visible */
  opacity: 0; /* Hide the original image */
}

.small-photos {
  width: 100%;
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
}

.small-photo {
  width: 100px;
  height: 100px;
  object-fit: cover;
  margin: 0 5px;
  cursor: pointer;
  transition: transform 0.2s ease;
}

.small-photo:hover {
  transform: scale(1.1);
}

@media (max-width: 1328px) {
  .small-photo {
    width: 100px;
    height: 100px;
  }
}

@media (max-width: 996px) {
  .small-photos {
    justify-content: space-around;
  }

  .small-photo {
    width: 60px;
    height: 60px;
  }
}

@media (max-width: 768px) {
  .main-photo-wrapper {
    max-width: 100%;
  }
  .small-photo {
    width: 80px;
    height: 80px;
  }
}
</style>
