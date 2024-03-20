<template>
  <title>oauth signup</title>
  <div>
    hello
  </div>
</template>
<script>

export default {

  name: 'app',
  components: {
  },
  created() {
    this.handleRedirectWithTokens();
  },
  methods: {
    handleRedirectWithTokens() {
      const urlParams = new URLSearchParams(window.location.search);
      const email = urlParams.get('email');
      if (email) {
        localStorage.setItem('email', email);
        localStorage.setItem('accessEmail', email);
        this.$router.push('/signupRole');

      }
    },
  },
}
</script>
