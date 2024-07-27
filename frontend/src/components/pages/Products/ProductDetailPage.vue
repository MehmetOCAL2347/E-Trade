<template>
  <!-- <div class="container-md">
    <div class="row">
      <div class="col-sm custom-col">1</div>
      <div class="col-sm custom-col">2</div>
      <div class="col-sm custom-col">3</div>
    </div>
  </div> 

  <div class="container-fluid">

    <div class="row">
      <div class="col-md-6">
        <image-carousel></image-carousel>
      </div>

      
      <div class="col-md-6">
        <product-title
          :title="detailProduct.name"
          :starPoint="detailProduct.starPoint"
          :count="detailProduct.count"
          :price="detailProduct.price"
          :priceType="detailProduct.priceType"
          :sellerName="detailProduct.sellerName"
          :categoryName="detailProduct.categoryName"
        ></product-title>
        <bullet-point :bulletPoints="detailProduct.bulletPoints"></bullet-point>
      </div>
    </div>
  </div>

  <div class="container-fluid">
    <similar-products></similar-products>
  </div>

  <div class="container-fluid">
    <product-comments></product-comments>
  </div> -->

  <!-- <div class="modal-content container">
        <div class="row">

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
                v-for="(photo, index) in photos"
                :key="index"
                :src="photo"
                @click="updateMainPhoto(photo)"
                class="small-photo"
              />
            </div>
          </div>

          <div class="col-lg-6 col-md-12">
            <h2>Name</h2>
            <p>description</p>
            <p><strong>Price:</strong>15 TL</p>
            <p><strong>Category:</strong> Araba</p>
            <button class="btn btn-primary">Add to Cart</button>
          </div>
        </div>
      </div> -->

  <div class="container custom-container">
    <div class="row">
      <div class="col-md-6">
        <div
          class="main-photo-wrapper"
          @mousemove="zoom"
          @mouseleave="resetZoom"
          :style="zoomStyles"
        >
          <!-- <img :src="product.image" class="img-fluid" alt="Product Image" /> -->
        </div>

        <div class="d-flex justify-content-around mt-3">
          <img
            v-for="(thumbnail, index) in this.detailProduct.images"
            :key="index"
            :src="thumbnail.url"
            class="img-thumbnail"
            alt="Product Thumbnail"
            @click="updateMainPhoto(thumbnail.url)"
          />
        </div>
      </div>
      <div class="col-md-6">
        <h1>{{ this.detailProduct.name }}</h1>
        <div class="d-flex align-items-center">
          <!-- <span class="text-muted me-2">{{ product.reviews }} reviews</span> -->
          <span class="badge bg-success">{{ this.detailProduct.starPoint }}</span>
        </div>
        <div class="my-3">
          <span class="h4 me-2"
            >{{ this.detailProduct.price }} {{ this.detailProduct.priceType }}</span
          >
          <!-- <span class="h4 text-danger">${{ product.discountedPrice }}</span> -->
          <!-- <span class="text-success ms-2">{{ product.discount }}% Off</span> -->
        </div>
        <!-- <div>
          <label for="size" class="form-label">Size</label>
          <div class="btn-group" role="group">
            <button
              type="button"
              class="btn btn-outline-primary"
              v-for="size in product.sizes"
              :key="size"
            >
              {{ size }}
            </button>
          </div>
        </div> -->
        <!-- <div class="input-group my-3">
          <button
            class="btn btn-outline-secondary"
            type="button"
            @click="decreaseQuantity"
          >
            -
          </button>
          <input type="number" class="form-control" v-model="quantity" />
          <button
            class="btn btn-outline-secondary"
            type="button"
            @click="increaseQuantity"
          >
            +
          </button>
        </div> -->
        <div class="mt-3">
          <p><strong>Ürün Kodu:</strong> {{ this.detailProduct.code }}</p>
          <p><strong>Stok:</strong> {{ this.detailProduct.count }}</p>
          <p><strong>Kategori:</strong> {{ this.detailProduct.categoryName }}</p>
          <p><strong>Satıcı:</strong> {{ this.detailProduct.sellerName }}</p>
        </div>
        <button class="btn btn-success btn-lg w-100" @click="addToCart">
          + Sepete Ekle
        </button>
        <bullet-point :bulletPoints="detailProduct.bulletPoints" class="mt-5"></bullet-point>
      </div>
    </div>
  </div>
</template>

<script>
// import ProductTitle from "@/components/common/UI/ProductDetail/ProductTitle.vue";
import BulletPoint from "@/components/common/UI/ProductDetail/BulletPoint.vue";
// import ImageCarousel from "@/components/common/UI/ProductDetail/ImageCarousel.vue";
// import SimilarProducts from "@/components/common/UI/ProductDetail/SimilarProducts.vue";
// import ProductComments from "@/components/common/UI/ProductDetail/ProductComments.vue";
export default {
  components: {
    // ProductTitle,
    BulletPoint,
    // ImageCarousel,
    // SimilarProducts,
    // ProductComments
  },
  data() {
    return {
      detailProduct: {},
      zoomStyles: {
        backgroundImage: "",
        backgroundSize: "100%",
        backgroundPosition: "center",
      },


      // product: {
      //   image:
      //     "https://m.media-amazon.com/images/I/810P97qy2cL._AC_SL1500_.jpg",
      //   thumbnails: [
      //     "https://m.media-amazon.com/images/I/810P97qy2cL._AC_SL1500_.jpg",
      //     "https://m.media-amazon.com/images/I/81iEWYaaGzL._AC_SL1500_.jpg",
      //     "https://m.media-amazon.com/images/I/71ixQn0+etL._AC_SL1500_.jpg",
      //     "https://m.media-amazon.com/images/I/71b7mIU+5xL._AC_SL1500_.jpg",
      //   ],
      //   name: "Napolitanke Ljesnjak",
      //   reviews: 30,
      //   rating: 4.5,
      //   originalPrice: 35,
      //   discountedPrice: 32,
      //   discount: 26,
      //   sizes: ["250g", "500g", "1kg"],
      //   code: "FBB00255",
      //   availability: "In Stock",
      //   type: "Fruits",
      //   shipping: "01 day shipping. (Free pickup today)",
      // },


      // quantity: 1,
    };
  },
  methods: {
    async getDetailProduct() {
      await this.$store.dispatch("getDetailProduct", this.$route.params.code);
      this.detailProduct = this.$store.getters.getDetailProduct;
      this.zoomStyles.backgroundImage= `url(${this.detailProduct.images[0].url})`;
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
      this.product.image = photo;
      this.zoomStyles.backgroundImage = `url(${photo})`;
    },
  },
  created() {
    this.getDetailProduct();
  },
};
</script>

<style scoped>
.img-thumbnail {
  width: 150px;
  height: 150px;
}

.modal-content {
  margin-top: 50px;
  margin-bottom: 50px;
  width: 100%;
}

.custom-container {
  margin-top: 100px;
  margin-bottom: 100px;
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
  object-fit: contain;
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

@media (max-width: 1400px) {
  .img-thumbnail {
    width: 120px;
    height: 120px;
  }
}

@media (max-width: 1200px) {
  .img-thumbnail {
    width: 100px;
    height: 100px;
  }
}

@media (max-width: 992px) {
  .img-thumbnail {
    width: 80px;
    height: 80px;
  }
}

@media (max-width: 768px) {
  .img-thumbnail {
    width: 100px;
    height: 100px;
  }
}

@media (max-width: 576px) {
  .img-thumbnail {
    width: 80px;
    height: 80px;
  }
}

/*  */

/* @media (max-width: 1328px) {
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
} */
</style>
