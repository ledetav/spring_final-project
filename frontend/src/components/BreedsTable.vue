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
            <button @click="deleteBreed(breed.id)" class="delete-button"><i
                class="fa-sharp fa-solid fa-trash fa-xs"></i></button>
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
    fetchBreeds() {
      axios.get('/api/breeds').then(response => {
        this.breeds = response.data;
        // Для каждой породы запрашиваем количество кошек
        this.breeds.forEach(breed => {
          this.fetchCatsByBreed(breed.id);
        });
      });
    },
    fetchCatsByBreed(breedId) {
      axios.get(`/api/breeds/${breedId}/cats`).then(response => {
        // Обновите свойство породы, добавив количество кошек
        const breedIndex = this.breeds.findIndex(breed => breed.id === breedId);
        if (breedIndex !== -1) {
          this.breeds[breedIndex].catCount = response.data.length;
        }
      });
    },
    deleteBreed(id) {
      axios.delete(`/api/breeds/${id}`).then(() => {
        // Обновление списка пород и кошек после удаления
        this.fetchBreeds();
      }).catch(error => {
        console.error("Ошибка при удалении кошек породы:", error);
      });
    },
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/styles/style.scss';
</style>