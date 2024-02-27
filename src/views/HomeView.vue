<script setup>
  //let items = data: () => ({[{name: "oi"}, {name: "tchau"}]})
  /*export default {
    data: () => ({
      items: [
        {
          title: 'Item #1',
          value: 1,
        },
        {
          title: 'Item #2',
          value: 2,
        },
        {
          title: 'Item #3',
          value: 3,
        },
        {
          title: 'Item #4',
          value: 4,
        },
      ],
    }),
  }*/
</script>

<template>
  <main class="container">
    <h1>Start Saving your Decks</h1>

    <div class="container-decks">

      <p>Your deck list is empty</p>

      <v-card max-width="1500" min-width="300">
        <v-list lines="two">

          <v-list-item
            :items="items"
            :key="n"
            :title="'Item ' + n"
            subtitle="Lorem ipsum dolor sit amet consectetur adipisicing elit"
          >
          </v-list-item>

        </v-list>      
      </v-card>

      <!-- 
      <v-card max-width="1500" min-width="300">
        <v-list :items="items"></v-list>
      </v-card>
      -->
    </div>

    <v-btn prepend-icon="$vuetify"> Add Deck </v-btn>
  </main>
</template>

<style scoped>
.container {
  display: flex;
  height: 100vh;
  justify-content: space-evenly;
  align-items: center;
  flex-direction: column;
}
.container-decks {
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  align-items: center;
}
</style>