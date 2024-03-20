<template>
    <div class="w-[1440px] h-[1024px] relative overflow-hidden animated-background">
        <BackgroundComponent></BackgroundComponent>
        <button @click="warnDisabled"
            class="bebas-neue-regular absolute left-[27px] top-[411px] text-[250px] text-left animated-text"
            :disabled="isDisabled">
            The FIT</button>
        <div :class="{ shake: disabled }">
            <p class="bebas-neue-regular absolute right-[500px] top-[492px] text-3xl text-left">
                <a href="/login" v-if="disabled" class="jua-regular relative inline-flex items-center justify-center p-0.5 mb-2 me-2 
                overflow-hidden  text-3xl  text-gray-900 rounded-lg group bg-gradient-to-br from-purple-400
                 to-blue-400 group-hover:from-purple-300 group-hover:to-blue-300 hover:text-white 
                 dark:text-white focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800">
                    <span class="relative px-5 py-2.5 transition-all ease-in duration-75
                     bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
                        로그인
                    </span>
                </a>

                <br>
                <br>

                <a href="/signupRole" v-if="disabled" class="jua-regular relative inline-flex items-center justify-center p-0.5 mb-2 me-2 
                overflow-hidden text-3xl  text-gray-900 rounded-lg group bg-gradient-to-br from-purple-400
                 to-blue-400 group-hover:from-purple-300 group-hover:to-blue-300 hover:text-white 
                 dark:text-white focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800">
                    <span class="relative px-5 py-2.5 transition-all ease-in duration-75
                     bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
                        회원가입
                    </span>
                </a>
            </p>
        </div>
    </div>
</template>


<script>
import { ref } from 'vue'
import BackgroundComponent from '../BackgroundComponent.vue';

export default {


    name: 'app',
    components: {
        BackgroundComponent

    },
    computed: {
        isDisabled() {
            return localStorage.getItem('token');
        }
    },

    setup() {
        const disabled = ref(false)
        function warnDisabled() {
            disabled.value = true
        }

        return {
            disabled,
            warnDisabled
        }
    },
}
</script>

<style>
.jua-regular {
    font-family: "Jua", sans-serif;
    font-weight: 400;
    font-style: normal;
}

.bebas-neue-regular {
    font-family: "Bebas Neue", sans-serif;
    font-weight: 400;
    font-style: normal;
}

.shake {
    animation: shake 0.82s cubic-bezier(0.36, 0.07, 0.19, 0.97) both;
    transform: translate3d(0, 0, 0);
}

@keyframes shake {

    10%,
    90% {
        transform: translate3d(-1px, 0, 0);
    }

    20%,
    80% {
        transform: translate3d(2px, 0, 0);
    }

    30%,
    50%,
    70% {
        transform: translate3d(-4px, 0, 0);
    }

    40%,
    60% {
        transform: translate3d(4px, 0, 0);
    }
}

.animated-background {
    animation: colorShift 10s infinite alternate;
    background: linear-gradient(45deg, #BC96FB, #ABECD6);
}

@keyframes colorShift {
    0% {
        background-position: 0% 50%;
    }

    100% {
        background-position: 100% 50%;
    }
}

.animated-text {
    animation: pulse 1s infinite alternate;
}

@keyframes pulse {
    0% {
        transform: scale(1);
    }

    100% {
        transform: scale(1.05);
    }
}
</style>
