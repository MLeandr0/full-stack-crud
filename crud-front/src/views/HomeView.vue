<script setup lang="ts">

  import { ref } from "vue";
  const items = ref([{id: 1, name: "Montinho"}, {id: 2, name: "Freguês Carrancudo"}, {id: 3, name: "Agro"}]);
  //const items = [];
  
</script>

<template>
  <v-app class="container-home">
    <v-main class="container-main">

      <div class="container-top">

        <h1>Start Saving your Decks</h1>
        <v-btn prepend-icon="$vuetify" to="/save-deck"> Save Deck </v-btn>

      </div>

      <div class="container-decks">

        <p v-if="items.length == 0">Your deck list is empty</p>

        <v-card class="container-deck-list" v-else max-width="10" min-width="300">
          <v-list 
            lines="two"
            :items="items"
            item-title="name"
            item-value="id"
          >
          </v-list>      
        </v-card>

      </div>

    </v-main>
  </v-app>

</template>

<style scoped>



</style>