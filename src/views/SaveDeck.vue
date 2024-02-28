<script setup lang="ts">

  /*import { ref } from "vue";
  const cards = ref("");*/
  const cards = [{mana: 10, defense: 12, name: "Murloc", life: 8, id: 1}];
  //const teste = [{id: 1, name: "pedro"}, {id: 2, name: "Sus"}, {id: 3, name: "julius"}, {id: 4, name: "julius"}, {id: 5, name: "julius"}];
  const teste = [];

</script>

<template>
  <v-app class="container">
    <v-main class="container-main">
      <div class="container-top">
        <h1>Deck creation</h1>
        <v-text-field
          label="Deck Name"
          hide-details="auto"
        >
        </v-text-field>
      </div>

      <div class="container-card">
        <div class="container-card-input">
          <v-text-field label="Card Cost"></v-text-field>
          <v-text-field label="Card Life"></v-text-field>
          <v-text-field label="Card Defense"></v-text-field>
          <v-btn prepend-icon="$vuetify" to="/"> Add Card </v-btn>  
        </div>

        <p v-if="teste.length == 0">Start adding your cards</p>
        <v-card v-else max-width="10" min-width="300">
          <v-list 
            lines="two"
            :items="teste"
            items-title="name"
            items-value="id"
          >
          </v-list>      
        </v-card>
      </div>  



      <div class = "container-button">
        <v-btn prepend-icon="$vuetify" to="/"> Done </v-btn>
        <v-btn prepend-icon="$vuetify" to="/"> Cancel </v-btn>
      </div>
  
    </v-main>
  </v-app>
</template>



<style>

.container {
  display: flex;
  height: 100vh;
  align-items: center;
  flex-direction: column;
}

.container-main{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  gap: 100px;
}

.container-card{
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 100px;
  width: 100%;
  justify-content: space-between;
}

.container-card-input{
  display: flex;
  flex-direction: row;
  align-items: center;
  height: 100px;
  width: 750px;
  justify-content: space-evenly;
}

.container-button{
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: row;
  gap: 10px;
}

</style>
