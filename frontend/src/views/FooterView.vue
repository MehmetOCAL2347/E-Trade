<template>
  <footer id="footer-view" class="bg-dark text-white py-4">
    <div class="container custom-container">
      <div class="row">
        <div class="col-md-4">
          <h5>İletişim</h5>
          <p>
            Email:
            <a href="mailto:ocal.mehmet@outlook.com.tr" class="text-white"
              >ocal.mehmet@outlook.com.tr</a
            >
          </p>
          <p>Phone: (+90) 555-312-22-11</p>
        </div>
        <div class="col-md-4">
          <h5>Sosyal Medya</h5>
          <p><i class="bi bi-instagram mr"></i>  Instagram</p>
          <p><i class="bi bi-facebook mr"></i>  Facebook</p>
          <p><i class="bi bi-pinterest mr"></i>  Pinterest</p>
          <p><i class="bi bi-tiktok mr"></i>  Tiktok</p>
          <p><i class="bi bi-whatsapp mr"></i>  Whatsapp</p>
          <p><i class="bi bi-twitter-x mr"></i>  Twitter</p>
        </div>
        <div class="col-md-4">
          <h5>Hızlıca Ulaş</h5>
          <ul class="list-unstyled">
            <li><a href="#" class="text-white">Hakkımızda</a></li>
            <li><a href="#" class="text-white">Tüm Ürünler</a></li>
            <li><a href="#" class="text-white">Satış Sözleşmesi</a></li>
            <li><a href="#" class="text-white">KVKK</a></li>
          </ul>
        </div>
      </div>
      <div class="text-center mt-3">
        <p>&copy; 2024 Copyright MANDE. All rights reserved.</p>
      </div>
    </div>
  </footer>
</template>

<script>
export default {};
</script>

<style scoped>
#footer-view {
  min-height: 600px;
  background-color: var(--color-1);
}

.custom-container {
  padding-top: 50px;
  width: 100%;
  min-height: 600px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

a {
  text-decoration: none;
}
a:hover {
  text-decoration: underline;
}
</style>
