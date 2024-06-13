<template>
  <form class="form" @submit.prevent="handleSubmit">
    <div class="form-group">
      <label for="name">Имя</label>
      <input v-model="formCat.name" id="name" type="text" required>
    </div>
    <div class="form-group">
      <label>Пол</label>
      <div style="display: flex; justify-content: space-evenly;">
        <div><input type="radio" id="male" value="male" v-model="formCat.gender">
          <label for="male">Самец</label>
        </div>
        <div><input type="radio" id="female" value="female" v-model="formCat.gender">
          <label for="female">Самка</label>
        </div>
      </div>
    </div>
    <div class="form-group">
      <div><label for="neutered">Кастрирован/стерилизован</label>
        <input type="checkbox" id="neutered" v-model="formCat.neutered">
      </div>
    </div>
    <div class="form-group">
      <label for="breed">Порода</label>
      <select v-model="formCat.breed" id="breed" required>
        <option value="" disabled="disabled" selected="selected">Выберите породу</option>
        <option v-for="breed in breeds" :key="breed.id" :value="breed">{{ breed.name }}</option>
      </select>
    </div>
    <div class="form-group">
      <div><label for="purebred">Чистокровен</label>
        <input type="checkbox" id="purebred" v-model="formCat.purebred">
      </div>
    </div>
    <div class="form-group">
      <label for="weight">Вес (кг)</label>
      <input v-model="formCat.weight" id="weight" type="number" step="0.1" required>
    </div>
    <div class="form-group">
      <label for="birthDate">Дата рождения</label>
      <input v-model="formCat.birthDate" id="birthDate" type="date" required>
    </div>
    <button type="submit" class="button">{{ isEditing ? 'Сохранить изменения' : 'Добавить кота' }}</button>
  </form>
</template>

<script>
import axios from 'axios';

export default {
  props: {
    cat: {
      type: Object,
      default: () => ({
        name: '',
        gender: 'male',
        neutered: false,
        breed: {},
        purebred: false,
        weight: 0,
        birthDate: ''
      })
    },
    isEditing: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      formCat: { ...this.cat, breed: this.cat?.breed || {} },
      breeds: []
    };
  },
  watch: {
    cat: {
      immediate: true,
      handler(newVal) {
        this.formCat = { ...newVal };
      }
    }
  },
  created() {
    this.fetchBreeds();
  },
  methods: {
    fetchBreeds() {
      axios.get('/api/breeds').then(response => {
        this.breeds = response.data;
      });
    },
    handleSubmit() {
      const url = this.isEditing ? `/api/cats/${this.formCat.id}` : '/api/cats';
      const method = this.isEditing ? 'put' : 'post';

      axios({
        method,
        url,
        data: this.formCat
      })
        .then(() => {
          this.$emit('saved');
          this.formCat = { ...this.catDefault }; // Возвращаем formCat к начальным значениям после сохранения
        })
        .catch(error => {
          console.error('Error:', error);
        });
    }
  }
};
</script>

<style lang="scss" scoped>
@import '../assets/styles/style';
</style>