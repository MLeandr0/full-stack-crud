<script setup lang="ts">

  import { ref } from "vue";
  
  const cards = ref([]);
  const cardCost = ref("");
  const cardDefense = ref("");
  const cardName = ref("");
  const cardLife = ref("");
  const deckName = ref("");

  function ddCard() {
    //let id = 3000;
    cards.value.push({mana: cardCost.value, defense: cardDefense.value, name: cardName.value, life: cardLife.value, id: 3000})
    cardCost.value = "";
    cardDefense.value = "";
    cardName.value = "";
    cardLife.value = "";
  }

</script>

<template>

  <v-container fluid class="flex-nowrap flex-column align-start justify-start fill-height pa-ma-0" >

    <v-container fluid class="pa-ma-0">

      <v-row align="center" justify="center">
          <v-col
            cols="8"
            sm="4"
          >
            <h1 class="text-center">Save your deck</h1>
            <v-text-field
              v-model="deckName"
              counter="20"
              hint="Dont type a word too long"
              label="Deck Name"
            ></v-text-field>
          </v-col>
      </v-row>

    </v-container>
    
    <v-container fluid class="pa-ma-0">

      <v-row align="center" justify="center">
          <v-col cols="1">
            <v-text-field label="Cost" clearable v-model="cardCost">
              <template v-slot:prepend-inner>
                <v-icon icon="mdi-diamond-stone"></v-icon>
              </template>
            </v-text-field>
          </v-col>

          <v-col cols="1" >
            <v-text-field label="Life" v-model="cardLife">
              <template v-slot:prepend-inner>
                <v-icon icon="mdi-heart"></v-icon>
              </template>
            </v-text-field>
          </v-col>
          
          <v-col cols="1">
            <v-text-field label="Defense" v-model="cardDefense">
              <template v-slot:prepend-inner>
                <v-icon icon="mdi-shield"></v-icon>
              </template>
            </v-text-field>
          </v-col>
          
          <v-col cols="1">
            <v-text-field label="Name" v-model="cardName">
              <template v-slot:prepend-inner>
                <v-icon icon="mdi-rename"></v-icon>
              </template>
            </v-text-field>
          </v-col>

          <v-col cols="1">
            <v-btn prepend-icon="mdi-plus" @click="ddCard"> Add Card </v-btn>  
          </v-col>
          
      </v-row>

    </v-container>

    <v-container fluid class="flex-1-1 pa-md-0">

      <v-row class="fill-height" justify="center">
        <v-col class="fill-height" cols="6">
          <v-card class="savedeck fill-height" min-width="300px" min-height="50px">
            <v-row class="ma-6">
            <v-col v-for="(card, index) in cards" :key="index" cols="12" md="6" lg="4">
              <v-card v-if="card" class="ma-0">
                <v-card-title>{{ card.name }}</v-card-title>
                <v-card-text>
                  <div>{{ card.life }}</div>
                  <div>{{ card.mana }}</div>
                  <div>{{ card.defense }}</div>
                </v-card-text>
                <v-card-actions>
                  <!-- Trash icon for deleting the card -->
                  <v-icon @click="deleteCard(index)">mdi-delete</v-icon>
                </v-card-actions>
              </v-card>
            </v-col>
          </v-row>

          </v-card>
        </v-col>
      </v-row>

    </v-container>

    <v-container fluid class="pa-ma-0">

      <v-row align="center" justify="center">
          <v-col cols="1">
            <v-btn prepend-icon="mdi-check-bold" to="/"> Done </v-btn>
          </v-col>
          <v-col cols="1">
            <v-btn prepend-icon="mdi-delete" to="/"> Cancel </v-btn>
          </v-col>
      </v-row>

    </v-container>

  </v-container>

</template>

<style>



</style>
