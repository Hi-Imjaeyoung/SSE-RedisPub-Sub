<template>
  <div id="app">
    <HeaderComponent />
    <router-view/>
  </div>
</template>
<script>
import HeaderComponent from './components/Header/HeaderComponent.vue' // HeaderComponent의 정확한 경로로 수정해주세요.

export default {
  name: 'App',
  components: {
    HeaderComponent
  }
}

</script>