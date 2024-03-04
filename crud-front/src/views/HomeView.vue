<script setup lang="ts">

  import { ref } from "vue";
  import Button from "@/components/Button.vue";

  const items = ref([{id: 1, name: "Montinho"}, {id: 2, name: "Freguês Carrancudo"}, {id: 3, name: "Agro"}]);
  //const items = [];
  
</script>

<template>
  <v-container fluid class="bg-#f3f8fa flex-nowrap flex-column align-center justify-center fill-height pa-ma-0">

    <v-row fluid align="center" justify="center">
      <v-col
        cols="auto"
        class="d-flex justify-center align-center flex-column"
      >
        <h1>Start saving your decks</h1>
        <Button
          class="ma-4"
          iconName="mdi-content-save"
          buttonText="Save Deck"
          iconColor="#6aa9fd"
          to="/save-deck"
        />
      </v-col>
    </v-row>

    <v-row class="ma-0" align="center" justify="center">
      <v-card variant="tonal" class="ma-0" min-width="550px" min-height="200px">
          <v-list 
            class="bg-#f3f8fa"
            lines="two"
            :items="items"
            item-title="name"
            item-value="id"
          >
          <template v-slot:append>
            <v-btn color="#F4511E" class="bg-#6aa9fd" icon="mdi-delete" to="/save-deck"></v-btn>  
          </template>
          </v-list>      
        </v-card>
    </v-row>

  </v-container>
</template>

<style scoped>
*{
  color: #051b22;
}
</style>