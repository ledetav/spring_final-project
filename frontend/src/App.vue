<template>
  <nav>
    <router-link to="/">Коты и кошки питомника</router-link> |
    <router-link to="/breeds">Породы кошек</router-link>
  </nav>
  <router-view/>
</template>

<style lang="scss">
@import 'assets/styles/_variables';

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: $primary-color;

    &.router-link-exact-active {
      color: lighten($primary-color, 20%);
    }
  }
}
</style>
