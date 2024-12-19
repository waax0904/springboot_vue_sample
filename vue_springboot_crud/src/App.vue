<template>
  <img style="width:200px;height: 180px;" src="./assets/loading_cat.gif">
  <Items />
</template>

<script>
import Items from './components/Items.vue';
export default {
  name: 'App',
  components: {
    Items
  }
}
</script>

<style>
body {
  background-color: #e7dfdd;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
