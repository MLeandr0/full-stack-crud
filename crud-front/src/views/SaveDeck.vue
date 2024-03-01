<script setup lang="ts">

  import { ref } from "vue";
  
  const cards = ref([]);
  const cardCost = ref("");
  const cardDefense = ref("");
  const cardName = ref("");
  const cardLife = ref("");
  const deckName = ref("");

  function ddCard() {
    let id = 3000;
    cards.value.push({mana: cardCost.value, defense: cardDefense.value, name: cardName.value, life: cardLife.value, id: 3000})
    cardCost.value = "";
    cardDefense.value = "";
    cardName.value = "";
    cardLife.value = "";
  }

</script>

<template>

  <v-container fluid class="fill-height  pa-md-0" >

    <v-container fluid class="pa-md-0">

      <v-row align="center" justify="center">
          <v-col
            cols="8"
            sm="4"
          >
            <h1>Create your deck</h1>
            <v-text-field
              v-model="deckName"
              counter="20"
              hint="Dont type a word too long"
              label="Deck Name"
            ></v-text-field>
          </v-col>
      </v-row>

    </v-container>
    
    <v-container fluid class="pa-md-0">

      <v-row align="center" justify="center">
          <v-col cols="1">
            <v-text-field label="Card Cost" clearable v-model="cardCost"></v-text-field>
          </v-col>

          <v-col cols="1" >
            <v-text-field label="Card Life" v-model="cardLife"></v-text-field>
          </v-col>
          
          <v-col cols="1">
            <v-text-field label="Card Defense" v-model="cardDefense"></v-text-field>
          </v-col>
          
          <v-col cols="1">
            <v-text-field label="Card Name" v-model="cardName"></v-text-field>
          </v-col>

          <v-col cols="1">
            <v-btn prepend-icon="$vuetify" @click="ddCard"> Add Card </v-btn>  
          </v-col>
          
      </v-row>

    </v-container>

    <v-container fluid class="pa-md-0">

      <v-row align="center" justify="center">
        <v-col cols="6">
          <v-card class="savedeck" min-width="300px" min-height="650px">
            <v-list 
              :lines="false"
            >
              <v-list-item
                v-for="(card, i) in cards"
                :key="i"
                :value="card"
              >
                <v-list-item-title v-text="card.name"></v-list-item-title>
                <v-list-item-subtitle>{{card.mana}}</v-list-item-subtitle>
                <v-list-item-subtitle>{{card.life}}</v-list-item-subtitle>
                <v-list-item-subtitle>{{card.defense}}</v-list-item-subtitle>
              </v-list-item>
            </v-list>      
          </v-card>
        </v-col>
      </v-row>

    </v-container>

    <v-container fluid class="pa-md-0">

      <v-row align="center" justify="center">
          <v-col cols="1">
            <v-btn prepend-icon="$vuetify" to="/"> Done </v-btn>
          </v-col>
          <v-col cols="1">
            <v-btn prepend-icon="$vuetify" to="/"> Cancel </v-btn>
          </v-col>
      </v-row>

    </v-container>

  </v-container>

</template>

<style>

.container-save_deck {
  display: flex;
  height: 100vh;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

.container-main-save_deck{
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
