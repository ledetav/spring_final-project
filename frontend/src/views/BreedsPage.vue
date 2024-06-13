<template>
    <div class="breeds-page">
        <main class="main-content">
            <div class="table-container">
                <BreedsTable ref="breedsTable"/>
            </div>
            <div class="form-container">
                <BreedForm @saved="fetchBreeds"/>
            </div>
        </main>
    </div>
</template>

<script>
import BreedForm from '../components/BreedForm.vue';
import BreedsTable from '../components/BreedsTable.vue';

export default {
    components: {
        BreedForm,
        BreedsTable
    },
    methods: {
        fetchBreeds(){
            this.$refs.breedsTable.fetchBreeds();
        }
    }
};
</script>

<style scoped>
/* BEM styles */
.main-content {
    display: flex;
}

.form-container,
.table-container {
    flex: 1;
}
</style>