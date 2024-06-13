<template>
    <form class="form" @submit.prevent="handleSubmit">
      <div class="form-group">
        <label for="name">Название породы:</label>
        <input v-model="localBreed.name" id="name" type="text" required />
      </div>
      <button type="submit" class="button">{{ isEditing ? 'Сохранить изменения' : 'Добавить породу' }}</button>
    </form>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    props: {
      breed: {
        type: Object,
        default: () => ({
          name: ''
        })
      },
      isEditing: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        localBreed: { ...this.breed } // Создаем локальную копию породы
      };
    },
    watch: {
      breed(newBreed) {
        this.localBreed = { ...newBreed }; // Обновляем локальную копию, если prop изменяется
      }
    },
    methods: {
      handleSubmit() {
        if (this.isEditing) {
          axios.put(`/api/breeds/${this.localBreed.id}`, this.localBreed).then(() => {
            this.$emit('saved');
            this.localBreed = {};
          });
        } else {
          axios.post(`/api/breeds`, this.localBreed).then(() => {
            this.$emit('saved');
            this.localBreed = {};
          });
        }
      }
    }
  };
  </script>
  
  <style lang="scss" scoped>
    @import '../assets/styles/style.scss';
  </style>