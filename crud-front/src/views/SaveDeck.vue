<script setup lang="ts">

  import { ref } from "vue";
  import InputText from "@/components/InputText.vue";
  import Button from "@/components/Button.vue";
  
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

  <v-container fluid class="bg-blue-lighten-4 flex-nowrap flex-column align-start justify-start fill-height pa-ma-0" >

    <v-container fluid class="pa-ma-0">

      <v-row align="center" justify="center">
          <v-col
            cols="8"
            sm="4"
          >
            <h1 class="text-center">Work on your deck</h1>
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
            <InputText 
              label="Cost"
              iconName="mdi-diamond-stone" 
              v-model="cardCost"
            />
          </v-col>

          <v-col cols="1" >
            <InputText
              label="Life"
              iconName="mdi-heart"
              v-model="cardLife"
            />
          </v-col>
          
          <v-col cols="1">
            <InputText
              label="Defense"
              iconName="mdi-shield"
              v-model="cardDefense"
            />
          </v-col>
          
          <v-col cols="1">
            <InputText
              label="Name"
              iconName="mdi-rename"
              v-model="cardName"
            />
          </v-col>

          <v-col cols="1">
            <Button iconName="mdi-plus" buttonText="Add Card" @click="ddCard"/>
          </v-col>
          
      </v-row>

    </v-container>

    <v-container fluid class="flex-1-1 pa-md-0">

      <v-row class="fill-height" justify="center">
        <v-col class="fill-height" cols="6">
          <v-card class="fill-height" min-width="300px" min-height="50px">
            <v-row class="ma-6">
            <v-col v-for="(card, index) in cards" :key="index" cols="12" md="6" lg="4">
              <v-card v-if="card" class="ma-0">
                <v-card-title>{{ card.name }}</v-card-title>
                <v-card-text>
                  <div>{{ card.mana }}</div>
                  <div>{{ card.life }}</div>
                  <div>{{ card.defense }}</div>
                </v-card-text>
                <v-card-actions>
                  <v-icon @click="">mdi-delete</v-icon>
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
            <Button
              iconName="mdi-check-bold"
              buttonText="Done"
              to="/"
            />
          </v-col>
          <v-col cols="1">
            <Button
              iconName="mdi-delete"
              buttonText="Cancel"
              to="/"
            />
          </v-col>
      </v-row>

    </v-container>

  </v-container>

</template>

<style>



</style>
