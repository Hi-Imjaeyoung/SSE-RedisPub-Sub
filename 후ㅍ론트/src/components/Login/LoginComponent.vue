<template>
    <div class="relative w-full h-screen bg-gradient-to-r from-purple-300 to-blue-300">
        <BackgroundComponent></BackgroundComponent>
        <section class="relative flex items-center justify-center h-full">
            <div class="w-full max-w-md p-8 space-y-4 bg-white rounded-lg shadow-md dark:bg-gray-800">
                <h1 class="text-2xl font-bold text-center text-gray-900 dark:text-white">
                    로그인
                </h1>
                <form class="space-y-4 md:space-y-6" @submit.prevent="doLogin">
                    <div class="form-group">
                        <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">이메일</label>
                        <input v-model="email" type="email" name="email" id="email" class="form-control bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg
                 focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
                 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                            placeholder="이메일을 입력하세요!" required="">
                    </div>
                    <div>
                        <label for="password"
                            class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">비밀번호</label>
                        <input v-model="password" type="password" name="password" id="password" placeholder="••••••••"
                            class="form-control bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                            required="">
                    </div>
                    <button type="submit"
                        class="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg 
            text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800">로그인하기</button>

                </form>
                <button @click="googleLogin"
                    class="px-4 py-2 border flex gap-2 border-slate-200 dark:border-slate-700 rounded-lg text-slate-700 dark:text-slate-200 hover:border-slate-400 dark:hover:border-slate-500 hover:text-slate-900 dark:hover:text-slate-300 hover:shadow transition duration-150">
                    <img class="w-6 h-6" src="https://www.svgrepo.com/show/475656/google-color.svg" loading="lazy"
                        alt="google logo">
                    <span>구글로 로그인하기</span>
                </button>
            </div>
        </section>
    </div>
</template>

<script>
import BackgroundComponent from '../BackgroundComponent.vue';
import axios from 'axios';
import { jwtDecode } from 'jwt-decode';

export default {
    name: 'app',
    components: {
        BackgroundComponent
    },
    data() {
        return {
            email: "",
            password: "",
        }
    },
    created() {
    },
    methods: {
        onSignInSuccess(googleUser) {
            const profile = googleUser.getBasicProfile()
            console.log('SUCCESS', profile)

        },
        onSignInError(error) {
            console.log('ERROR', error)
        },

        googleLogin() {
            window.location.href =
                "https://accounts.google.com/o/oauth2/auth?" +
                "scope=email%20profile%20https://www.googleapis.com/auth/cloud-platform%20openid%20" +
                "https://www.googleapis.com/auth/drive.metadata.readonly%20" +
                "https://www.googleapis.com/auth/userinfo.profile%20" +
                "https://www.googleapis.com/auth/userinfo.email&access_type=offline" +
                "&include_granted_scopes=true&response_type=code&state=state_parameter_passthrough_value" +
                "&redirect_uri=http://localhost:8080/auth/google/callback" +
                "&client_id=625185374022-79lf2b3q4m7214nvj5jo5g6pm1icl9na.apps.googleusercontent.com" +
                "&approval_prompt=force";
        },
        async doLogin() {
            try {
                const loginData = { email: this.email, password: this.password };
                const response = await axios.post("http://localhost:8080/dologin", loginData);
                const token = response.data.result.token;
                const refreshToken = response.data.result.refreshToken;
                if (token) {
                    const decoded = jwtDecode(token);
                    localStorage.setItem("token", token);
                    localStorage.setItem("refreshToken", refreshToken);
                    localStorage.setItem("role", decoded.role);
                    localStorage.setItem("email", decoded.sub);
                    localStorage.setItem("accessEmail", decoded.sub);
                    alert("환영합니다, " + decoded.userName + " 회원님!");
                    window.location.href = "/";
                }
                else {
                    console.log("200 ok but not a valid token");
                    alert("로그인 실패!");
                }
            }
            catch (error) {
                const error_message = error.response.data.message;
                if (error_message) {
                    console.log(error_message);
                    alert(error_message);
                }
                else {
                    console.log(error);
                    alert("로그인 실패!");
                }
            }
        }
    },

}
</script>
