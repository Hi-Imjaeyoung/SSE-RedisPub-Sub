<template>
<title>LOGIN SUCCESS</title>
</template>
<script>
export default {
  created() {
    this.handleRedirectWithTokens();
  },
  methods: {
    handleRedirectWithTokens() {
      const urlParams = new URLSearchParams(window.location.search);
      const accessToken = urlParams.get('accessToken');
      const refreshToken = urlParams.get('refreshToken');
      const role = urlParams.get('role');
      const email = urlParams.get('email');
      if (accessToken && refreshToken) {
        localStorage.setItem('token', accessToken);
        localStorage.setItem('refreshToken', refreshToken);
        localStorage.setItem('role',role);
        localStorage.setItem('email', email);
        localStorage.setItem('accessEmail', email);
        window.location.href="/";
      }
    },
  },
}
</script>