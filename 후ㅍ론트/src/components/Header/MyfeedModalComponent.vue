<template>
    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <span class="close" @click="closeModal">&times;</span>
        <h2>나의 피드</h2>
        <p></p>
        <div>
        <template v-if="feedback.length === 0 && actions.length ===0">
          <p class="default-message">피드가 없어요.</p>
        </template>
            <tbody v-if="feedback.length != 0">
              <tr v-for="(obj,index) in feedback" :key="index">
                <td class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">[ {{obj.date}} ] {{obj.name}} 트레이너님이 {{obj.type}} 피드백을 했어요</td>
              </tr>
            </tbody>
            <tbody v-if="actions.length != 0">
              <tr v-for="(obj,index) in actions" :key="index">
                <td class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">[ {{obj.date}} ] {{obj.name}} 회원님이 {{obj.type}} 등록 했어요</td>
              </tr>
            </tbody>
        </div>
      </div>
    </div>
  </template>

  
    
  <script>

  export default {
    props: {
    feedback: Array,
    actions: Array
    },
    data() {
      return {
        showModal: false,
      };
    },
    methods: {
      openModal() {
        this.showModal = true;
      },
      closeModal() {
        this.showModal = false;
        this.$emit('clearFeedback');
        this.$emit('clearAction')
      },
    }
  };
  </script>
    
  <style scoped>
  .modal {
    position: fixed;
    z-index: 1000;
    /* Ensure modal is above other content */
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: rgba(0, 0, 0, 0.5);
    /* Semi-transparent background */
  }
  
  .modal-content {
    padding: 20px;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    width: auto;
    min-width: 300px;
    text-align: center;
  }
  
  .close {
    float: right;
    font-size: 1.5rem;
    font-weight: bold;
    cursor: pointer;
  }
  
  .close:hover,
  .close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
  }</style>