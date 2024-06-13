<template>
  <div class="container">
    <div class="table">
      <div class="table-header">
        <div class="header__item">
          <a href="#" id="name" class="filter__link">Название породы</a>
        </div>
        <div class="header__item">
          <a href="#" id="count_cats" class="filter__link">Количество кошек</a>
        </div>
        <div class="header__item">
          <a href="#" id="actions" class="header__actions">Действия</a>
        </div>
      </div>
      <div class="table-content">
        <div class="table-row" v-for="breed in breeds" :key="breed.id">
          <div class="table-data">{{ breed.name }}</div>
          <div class="table-data">{{ breed.catCount }}</div>
          <div class="table-data">
            <button @click="deleteBreed(breed.id)" class="delete-button">
              <i class="fa-sharp fa-solid fa-trash fa-xs"></i>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      breeds: [],
    };
  },
  created() {
    this.fetchBreeds();
  },
  methods: {
    async fetchBreeds() {
      try {
        const response = await axios.get('/api/breeds');
        this.breeds = response.data;

        // Запрашиваем количество кошек для каждой породы
        for (const breed of this.breeds) {
          this.fetchCatsByBreed(breed.id);
        }
      } catch (error) {
        console.error("Ошибка при получении пород:", error);
      }
    },
    async fetchCatsByBreed(breedId) {
      try {
        const response = await axios.get(`/api/breeds/${breedId}/cats`);
        const breedIndex = this.breeds.findIndex(breed => breed.id === breedId);
        if (breedIndex !== -1) {
          this.$set(this.breeds[breedIndex], 'catCount', response.data.length);
        }
      } catch (error) {
        console.error("Ошибка при получении кошек породы:", error);
      }
    },
    async deleteBreed(id) {
      try {
        await axios.delete(`/api/breeds/${id}`);
        // Удаляем породу из локального состояния
        this.breeds = this.breeds.filter(breed => breed.id !== id);
      } catch (error) {
        console.error("Ошибка при удалении породы:", error);
      }
    },
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/styles/style.scss';
</style>