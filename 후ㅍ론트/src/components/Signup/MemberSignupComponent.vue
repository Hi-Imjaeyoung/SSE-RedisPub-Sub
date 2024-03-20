<template>
    <div class="relative w-full h-screen bg-gradient-to-r from-purple-300 to-blue-300 overflow-hidden">
        <BackgroundComponent></BackgroundComponent>
        <section class="relative flex items-center justify-center h-full">
            <div class="w-full max-w-md px-6 py-8 mx-auto bg-white rounded-lg shadow-md dark:bg-gray-800">
                <h1 class="text-2xl font-bold text-center text-gray-900 dark:text-white">
                    회원가입
                </h1>
                <div class="overflow-auto max-h-[calc(100vh-200px)]">
                    <form class="space-y-4 md:space-y-6" @submit.prevent="userCreate">
                        <div>
                            <label for="name"
                                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">성함</label>
                            <input type="text" name="name" id="name"
                                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                required="" v-model="name">
                        </div>
                        <div>
                            <label for="email"
                                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">이메일</label>
                            <input type="email" name="email" id="email" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg
             focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 
             dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required=""
                                v-model="email" :disabled="isDisabled">
                        </div>
                        <div>
                            <label for="password"
                                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">비밀번호</label>
                            <input type="password" name="password" id="password"
                                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                required="" v-model="password" :disabled="isDisabled">
                        </div>
                        <div>
                            <label for="phoneNumber"
                                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">전화번호</label>
                            <input type="number" name="phoneNumber" id="phoneNumber"
                                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                required="" v-model="phoneNumber">
                        </div>
                        <div>
                            <label for="cmHeight" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">신체정보
                                -
                                키</label>
                            <input type="number" name="cmHeight" id="cmHeight"
                                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                required="" v-model="cmHeight">
                        </div>
                        <div>
                            <label for="kgWeight" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">신체정보
                                -
                                몸무게</label>
                            <input type="number" name="kgWeight" id="kgWeight"
                                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                required="" v-model="kgWeight">
                        </div>

                        <div>
                            <label for="gender" class="block mb-2 text-sm font-medium text-gray-900">성별</label>
                            <select type="dropdown" name="gender" id="gender" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg
                         focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5
                          dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 
                          dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" v-model="gender">
                                <option value="genderchoice">선택</option>
                                <option value="MALE">남성</option>
                                <option value="FEMALE">여성</option>
                            </select>
                        </div>
                        <div class="form-group block mb-2 text-sm font-medium text-gray-900">
                            <p>
                                <label for="image">
                                    프로필 사진
                                </label>
                            </p>
                            <input type="file" name="image" id="image" @change="handleFileUpload" accept="image/*">
                        </div>
                        <div>
                            <label for="trainerId" class="block mb-2 text-sm font-medium text-gray-900">담당 트레이너 선택</label>
                            <select type="dropdown" name="trainerId" id="trainerId" class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg
 focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5
 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 
 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" v-model="trainerId">
                                <option value="">담당 트레이너 선택</option>
                                <option v-for="trainer in trainersList" :value="trainer.id" :key="trainer.id">{{
                                    trainer.name }}</option>
                            </select>
                        </div>

                        <button type="submit"
                            class="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg 
        text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800">환영합니다!
                        </button>
                    </form>
                </div>
            </div>
        </section>
    </div>
</template>
<script>
import BackgroundComponent from '../BackgroundComponent.vue';
import axios from 'axios';

export default {
    name: 'MemberCreate',
    components: {
        BackgroundComponent
    },
    data() {
        return {
            trainersList: [],
            name: "",
            email: "",
            password: null,
            phoneNumber: "",
            cmHeight: null,
            kgWeight: null,
            gender: "genderchoice",
            role: "MEMBER",
            trainerId: "trainerchoice",
            profileImage:"",
        }
    },
    created() {
        this.loadTrainers();
        if (localStorage.getItem("email")) {
            this.email = localStorage.getItem("email");
        }
    },
    computed: {
        isDisabled() {
            return !!localStorage.getItem('email');
        }
    },
    methods: {
        handleFileUpload(event) {
            this.profileImage = event.target.files[0];
        },
        async loadTrainers() {
            try {
                const response = await axios.get("http://localhost:8080/trainer/available/list");
                this.trainersList = response.data.result;
                console.log(this.trainersList);
            }
            catch (error) {
                console.log(error);
            }
        },
        async userCreate() {
            const registerData = new FormData();
            registerData.append("name",this.name);
            registerData.append("email",this.email);
            registerData.append("password",this.password);
            registerData.append("phoneNumber",this.phoneNumber);
            registerData.append("cmHeight",this.cmHeight);
            registerData.append("kgWeight",this.kgWeight);
            registerData.append("gender",this.gender);
            registerData.append("role",this.role);
            registerData.append("profileImage",this.profileImage);
            registerData.append("trainerId", this.trainerId);
            try {
                await axios.post("http://localhost:8080/member/create", registerData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                });
                alert("회원님 가입 완료!");
                window.location.href = "/";

            } catch (error) {
                console.log(error);
                console.log(this.name);
                console.log(this.email);
                console.log(this.password);
                console.log(this.phoneNumber);
                console.log(this.cmHeight);
                console.log(this.kgWeight);
                console.log(this.role);
                console.log(this.gender);
                alert(error.response.data.message);
            }
        }
    },
}
</script>

