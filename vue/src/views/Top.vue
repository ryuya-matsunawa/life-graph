<template>
  <div class="top">
    <Header />
    <router-link to="/">
      Home
    </router-link>
    <router-link to="/search">
      Search
    </router-link>
    <router-link to="/edit">
      Edit
    </router-link>
    <router-link to="/show">
      Show
    </router-link>
    <h1>This is an about page</h1>
  </div>
</template>

<script>
import Header from '../views/Header.vue'

export default {
  components: {
    Header
  }
}
</script>
