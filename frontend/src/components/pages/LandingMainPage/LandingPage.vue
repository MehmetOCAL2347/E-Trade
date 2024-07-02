<template>
  <div id="landing-page">
    <div
      id="carouselExampleDark"
      class="carousel carousel-dark slide"
      data-bs-ride="carousel"
      :data-bs-interval="slideInterval"
    >
      <div class="carousel-indicators">
        <button
          type="button"
          data-bs-target="#carouselExampleDark"
          data-bs-slide-to="0"
          class="active"
          aria-current="true"
          aria-label="Slide 1"
        ></button>
        <button
          type="button"
          data-bs-target="#carouselExampleDark"
          data-bs-slide-to="1"
          aria-label="Slide 2"
        ></button>
        <button
          type="button"
          data-bs-target="#carouselExampleDark"
          data-bs-slide-to="2"
          aria-label="Slide 3"
        ></button>
      </div>
      <div class="carousel-inner custom-rounded">
        <div class="carousel-item active" :data-bs-interval="slideInterval">
          <img
            src="https://ideacdn.net/idea/hb/38/myassets/std_theme_files/tpl-marqett/assets/uploads/slider_6.png?revision=7.2.8.2-1-1719149728"
            class="d-block w-100"
            alt="#"
          />
          <div class="carousel-caption d-none d-md-block">
            <h5>First slide label</h5>
            <p>Some representative placeholder content for the first slide.</p>
            <button>Tıkla</button>
          </div>
        </div>
        <div class="carousel-item" :data-bs-interval="slideInterval">
          <img
            src="https://ideacdn.net/idea/hb/38/myassets/std_theme_files/tpl-marqett/assets/uploads/slider_9.png?revision=7.2.8.2-1-1719149728"
            class="d-block w-100"
            alt="#"
          />
          <div class="carousel-caption d-none d-md-block">
            <h5>Second slide label</h5>
            <p>Some representative placeholder content for the second slide.</p>
          </div>
        </div>
        <div class="carousel-item" :data-bs-interval="slideInterval">
          <img
            src="https://ideacdn.net/idea/hb/38/myassets/std_theme_files/tpl-marqett/assets/uploads/slider_10.png?revision=7.2.8.2-1-1719149728"
            class="d-block w-100"
            alt="#"
          />
          <div class="carousel-caption d-none d-md-block">
            <h5>Third slide label</h5>
            <p>Some representative placeholder content for the third slide.</p>
          </div>
        </div>
      </div>
      <button
        class="carousel-control-prev"
        type="button"
        data-bs-target="#carouselExampleDark"
        data-bs-slide="prev"
      >
        <!-- <span class="carousel-control-prev-icon" aria-hidden="true"></span> -->
        <i class="bi bi-arrow-left-circle-fill icon-custom"></i>
      </button>
      <button
        class="carousel-control-next"
        type="button"
        data-bs-target="#carouselExampleDark"
        data-bs-slide="next"
      >
        <!-- <span class="carousel-control-next-icon" aria-hidden="true"></span> -->
        <i class="bi bi-arrow-right-circle-fill icon-custom"></i>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      slideInterval: 3000,
    };
  },
};
</script>

<style scoped>
#landing-page {
  width: var(--width-main);
  margin: 0 auto;
  padding-top: 1rem;
  padding-bottom: 1rem;
  min-height: 100vh;
  padding-top: 150px;
}

img {
  max-width: 100%;
  height: auto;
}

.custom-rounded {
  border-radius: 10px;
}

.icon-custom {
  opacity: 100%;
  color: red;
  border-radius: 50%;
  padding: 10px;
}

</style>
