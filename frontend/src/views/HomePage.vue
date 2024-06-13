<template>
  <div class="home-page">
    <main class="main-content">
      <div class="table-container">
        <CatsTable ref="catsTable" @edit-cat="selectCatForEdit" />
      </div>
      <div class="form-container">
        <CatForm :cat="selectedCat" :isEditing="isEditing" @saved="fetchCats" />
      </div>
    </main>
  </div>
</template>

<script>
import CatForm from '../components/CatForm.vue';
import CatsTable from '../components/CatsTable.vue';

export default {
  components: {
    CatForm,
    CatsTable
  },
  data() {
    return {
      selectedCat: null,
      isEditing: false
    };
  },
  methods: {
    fetchCats() {
      this.$refs.catsTable.fetchCats();
    },
    selectCatForEdit(cat) {
      this.selectedCat = cat;
      this.isEditing = true;
    }
  }
};
</script>

<style scoped>
/* BEM styles */
.main-content {
  display: flex;
}
.form-container, .table-container {
  flex: 1;
}
</style>