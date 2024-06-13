<template>
  <div class="container">
    <div class="cats-table">
      <div class="table-header">
        <div class="header__item">
          <a href="#" id="name" class="filter__link">Имя</a>
        </div>
        <div class="header__item">
          <a href="#" id="gender" class="filter__link">Пол</a>
        </div>
        <div class="header__item">
          <a href="#" id="neutered" class="filter__link">Кастрация</a>
        </div>
        <div class="header__item">
          <a href="#" id="breed" class="filter__link">Порода</a>
        </div>
        <div class="header__item">
          <a href="#" id="purebred" class="filter__link">Чистокровен</a>
        </div>
        <div class="header__item">
          <a href="#" id="weight" class="filter__link filter__link--float-number">Вес</a>
        </div>
        <div class="header__item">
          <a href="#" id="age" class="filter__link filter__link--age">Возраст</a>
        </div>
        <div class="header__item">
          <a href="#" id="actions" class="header__actions">Действия</a>
        </div>
      </div>
      <div class="table-content">
        <div class="table-row" v-for="cat in cats" :key="cat.id">
          <div class="table-data">{{ cat.name }}</div>
          <div class="table-data">{{ cat.gender === 'male' ? 'Самец' : 'Самка' }}</div>
          <div class="table-data">{{ cat.neutered ? 'Да' : 'Нет' }}</div>
          <div class="table-data">{{ cat.breed.name }}</div>
          <div class="table-data">{{ cat.purebred ? 'Да' : 'Нет' }}</div>
          <div class="table-data">{{ cat.weight }}</div>
          <div class="table-data">{{ calculateAge(cat.birthDate) }}</div>
          <div class="table-data">
            <button @click="editCat(cat)" class="button"><i class="fa-sharp fa-solid fa-pencil fa-xs"></i></button>
            <button @click="deleteCat(cat.id)" class="delete-button"><i class="fa-sharp fa-solid fa-trash fa-xs"></i></button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import '../assets/scripts/cats-filter.js';
import axios from 'axios';

export default {
  data() {
    return {
      cats: []
    };
  },
created() {
    this.fetchCats();
  },
   methods: {
    fetchCats() {
      axios.get('/api/cats').then(response => {
        this.cats = response.data;
      });
    },
    deleteCat(id) {
      axios.delete(`/api/cats/${id}`).then(() => {
        this.fetchCats();
      });
    },
    editCat(cat) {
      this.$emit('edit-cat', cat);
    },
    calculateAge(birthDate) {
      const now = new Date();
      const birth = new Date(birthDate);
      
      let years = now.getFullYear() - birth.getFullYear();
      let months = now.getMonth() - birth.getMonth();
      
      if (months < 0) {
        years--;
        months += 12;
      } else if (months === 0 && now.getDate() < birth.getDate()) {
        years--;
        months = 11;
      }

      return `${years} лет и ${months} месяцев`;
    }
  }
};

</script>

<style lang="scss" scoped>
@import '../assets/styles/style';
</style>