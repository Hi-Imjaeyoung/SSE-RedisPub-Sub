<template>
    <title>SignUP Role</title>
    <div class="relative w-full h-screen bg-gradient-to-r from-purple-300 to-blue-300 overflow-hidden">
        <BackgroundComponent></BackgroundComponent>
        <div class="w-full h-full relative overflow-hidden animated-background">
            <div class="button-container flex justify-center items-center h-full space-x-4">
                <a href="/signupTrainer" class="jua-regular relative inline-flex items-center justify-center p-0.5 mb-2 me-2 
                overflow-hidden  text-3xl  text-gray-900 rounded-lg group bg-gradient-to-br from-purple-400
                 to-blue-400 group-hover:from-purple-300 group-hover:to-blue-300 hover:text-white 
                 dark:text-white focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800">
                    <span class="relative px-10 py-3 transition-all ease-in duration-75
                 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">트레이너로 회원가입하기</span></a>
                <a href="/signupMember" class="jua-regular relative inline-flex items-center justify-center p-0.5 mb-2 me-2 
                overflow-hidden  text-3xl  text-gray-900 rounded-lg group bg-gradient-to-br from-purple-400
                 to-blue-400 group-hover:from-purple-300 group-hover:to-blue-300 hover:text-white 
                 dark:text-white focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800">
                    <span class="relative px-10 py-3 transition-all ease-in duration-75
                 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">회원으로 회원가입하기</span></a>
            </div>
        </div>
    </div>
</template>
<script>
import BackgroundComponent from '../BackgroundComponent.vue';

export default {

    name: 'app',
    components: {
        BackgroundComponent
    }
}
</script>