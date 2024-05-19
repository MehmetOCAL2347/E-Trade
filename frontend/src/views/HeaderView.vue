<template>
  <nav id="custom-navbar" class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <router-link
        class="nav-link active"
        aria-current="page"
        :to="{ name: 'main' }"
        >Navbar</router-link
      >
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNavDropdown"
        aria-controls="navbarNavDropdown"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
          <li class="nav-item">
            <router-link
              class="nav-link active"
              aria-current="page"
              :to="{ name: 'all-products' }"
              >Ürünler</router-link
            >
          </li>
        </ul>
      </div>
      <div class="collapse navbar-collapse">
        <form class="d-flex" role="search">
          <input
            class="form-control me-2"
            type="search"
            placeholder="Ürün Ara"
            aria-label="Search"
          />
          <button class="btn btn-outline-success" type="submit">Ara</button>
        </form>
      </div>

      <div class="collapse navbar-collapse">
        <router-link :to="{name: 'login'}" class="btn btn-outline-success">Giriş Yap</router-link>
        <router-link :to="{name: 'register'}" class="btn btn-outline-success">Kayıt Ol</router-link>
      </div>
    </div>
  </nav>
</template>

<script>
export default {};
</script>

<style scoped>
#custom-navbar {
  height: var(--navbar-height);
}
</style>
