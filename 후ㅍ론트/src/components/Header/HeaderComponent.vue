<template>
  <header v-if="isLogin" class="header">
    <div class="header-content">
      <div class="header-buttons-left">
        <button @click="openFeedtModal">
            <font-awesome-layers full-width class="fa-3x">
              <font-awesome-icon icon="fa-regular fa-bell" style="cursor: pointer" />
              <font-awesome-layers-text id="alarm" counter :value="memberAction" position="top-right" v-if="memberAction != 0 && userRole==='TRAINER'" />
              <font-awesome-layers-text id="alarm" counter :value="myFeedBack" position="top-right" v-if="myFeedBack != 0 && userRole==='MEMBER'" />
            </font-awesome-layers>
        </button>
      </div>

      <h1 class="header-title cursor-pointer" @click="goHome">THE FIT</h1>

      <div class="header-buttons-right">
        <button class="header-button" @click="test()">알림테스팅</button>
        <button class="header-button" @click="logout">로그아웃</button>
      </div>
    </div>
  </header>
  <ModalComponent ref="modal" :feedback="feedback" :actions="actions" @clearFeedback="clearFeedbackHandler" @clearAction="clearAction"/>
</template>
  
<script>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import {EventSourcePolyfill } from 'event-source-polyfill';
import ModalComponent from './MyfeedModalComponent.vue';
import axios from 'axios';

export default {
  name: 'HeaderComponents',
  components: {
    ModalComponent,
  },
  data(){
    return{
      myFeedBack : 0,
      memberAction: 0,
      actions:[],
      isToggleDropdown: false,
      feedback:[],
    }
  },
  async created() {
    if(localStorage.getItem('token') != null){
      const token = localStorage.getItem('token');
      var sse = new EventSourcePolyfill('http://localhost:8080/connect', {
        headers: {
          Authorization: `Bearer ${token}`
        }
      });
    sse.addEventListener('connect', (e) => {
    const { data: receivedConnectData } = e;
    console.log('connect event data: ',receivedConnectData);  // "connected!"
    });
      sse.addEventListener('sendToMember', e => { 
        const obj = JSON.parse(e.data);
        this.myFeedBack = this.myFeedBack+1;
        this.feedback.push(obj) 
        console.log(this.feedback[0].type); 
    });
      sse.addEventListener('sendToTrainer', e => { 
        const obj = JSON.parse(e.data);
        this.memberAction = this.memberAction+1;
        this.actions.push(obj) 
        console.log(this.actions[0].type); 
        console.log(this.memberAction);
        });
    }
  },
  methods:{
    async test(){
      const token = localStorage.getItem('token');
      const refreshToken = localStorage.getItem('refreshToken');
      const headers = token ? {Authorization: `Bearer ${token}`,refreshToken:`${refreshToken}`}:{};
      await axios.get(`http://localhost:8080/calltest1`,{headers});
    },
    openFeedtModal() {
      this.$refs.modal.openModal();
      this.myFeedBack = 0;
      this.memberAction = 0;
   },
   clearFeedbackHandler(){
      this.feedback = [];
   },
   clearAction(){
      this.actions =[];
   }
  },
  setup() {
    const router = useRouter();
    const isLogin = ref(!!localStorage.getItem("token"));
    const userRole = ref(localStorage.getItem("role"));
    const isModalVisible = ref(false);

    function logout() {
      localStorage.removeItem("token");
      localStorage.removeItem("role");
      localStorage.removeItem("email");
      localStorage.removeItem("refreshToken");
      localStorage.removeItem("accessEmail");
      isLogin.value = false;
      userRole.value = null;
      window.location.href="/";
    }
          
    function goHome() {
      router.push('/');
      }

    function closeModal() {
      isModalVisible.value = false; // 모달 닫기
    }

    return {
      logout,
      closeModal,
      goHome,
      isLogin,
    };
  },
}
</script>
<style scoped>
.header {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: white;
  padding: 20px 0;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  max-width: 1200px;
}

.header-title {
  color: #BC96FB;
  font-size: 36px;
  /* Increased size for prominence */
  font-weight: bold;
  /* Bolder font weight */
  text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1);
  /* Soft shadow for depth */
}

.header-button {
  background-color: #BC96FB;
  /* Primary button color */
  color: white;
  border: none;
  padding: 10px 20px;
  margin: 0 10px;
  cursor: pointer;
  border-radius: 5px;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.header-button:hover {
  background-color: #A17FE0;
  /* Darker shade on hover */
  transform: translateY(-2px);
  /* Slight lift effect */
}

.header-buttons-left,
.header-buttons-right {
  display: flex;
}
</style>
  