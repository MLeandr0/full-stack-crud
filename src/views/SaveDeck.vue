<script setup lang="ts">

  import { ref } from "vue";
  
  const cards = ref([]);
  const cardCost = ref("");
  const cardDefense = ref("");
  const cardName = ref("");
  const cardLife = ref("");

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
  <v-app class="container-save_deck">
    <v-main class="container-main-save_deck">

      <div class="container-top2">
        <h1>Deck creation</h1>
        <v-text-field
          label="Deck Name"
          hide-details="auto"
        >
        </v-text-field>
      </div>

      <div class="container-card">
        
        <div class="container-card-input">
          <v-text-field label="Card Cost" clearable v-model="cardCost"></v-text-field>
          <v-text-field label="Card Life" v-model="cardLife"></v-text-field>
          <v-text-field label="Card Defense" v-model="cardDefense"></v-text-field>
          <v-text-field label="Card Name" v-model="cardName"></v-text-field>
          <v-btn prepend-icon="$vuetify" @click="ddCard"> Add Card </v-btn>  
        </div>

        <div>
          <p v-if="cards.length == 0">Start adding your cards</p>
          <v-card class="savedeck" v-else max-width="700" min-width="300" min-height="100">
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
        </div>

      </div>  

      <div class = "container-button">
        <v-btn prepend-icon="$vuetify" to="/"> Done </v-btn>
        <v-btn prepend-icon="$vuetify" to="/"> Cancel </v-btn>
      </div>
      
    </v-main>
  </v-app>
</template>

<style>

.container-save_deck {
  display: flex;
  height: 100vh;
  align-items: center;
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
