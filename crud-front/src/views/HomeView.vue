<script setup>

</script>

<template>
  <main class = "container">
    
    <h1>Start Saving your Decks</h1>

    <div class = "container-decks">
      <p>Your deck list is empty</p>
      <v-list lines="two">
        <v-list-item
          v-for="n in 3"
          :key="n"
          :title="'Item ' + n"
          subtitle="Lorem ipsum dolor sit amet consectetur adipisicing elit"
        ></v-list-item>
      </v-list>            
    </div>

    <v-btn prepend-icon="$vuetify">
      Add Deck
    </v-btn>

  </main>
</template>

<style scoped>
.container {
  display: flex;
  height: 100vh;
  background-color: lightblue;
  margin: 0px;
  padding: 0px;
  justify-content: space-evenly;
  align-items: center;
  flex-direction: column;
}
.container-decks {
  display:flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>


