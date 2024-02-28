<script setup lang="ts">

  const items = [{id: 1, name: "pedro"}, {id: 2, name: "Sus"}, {id: 3, name: "julius"}, {id: 4, name: "julius"}, {id: 5, name: "julius"}];
  const cards = [{mana: 10, defense: 12, name: "Murloc", life: 8, id: 1}];
  //const items = [];
</script>

<template>
  <v-app class="container">
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

.container {
  display: flex;
  height: 100vh;
  align-items: center;
  flex-direction: column;
}

.container-main {
  display: flex;
  flex-direction: column;
  justify-content: center;
  /*background-color: green;*/
  gap: 100px;
}

.container-top {
  display: flex;
  /*background-color: red;*/
  flex-direction: column;
  align-items: center;
  justify-content: space-evenly;
  width: 1000px;
  height: 250px;
}

.container-decks {
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
  /*background-color: blueviolet;*/
}
</style>