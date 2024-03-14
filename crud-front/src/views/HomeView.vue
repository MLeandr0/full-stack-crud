<script setup lang="ts">
import { useRouter } from 'vue-router'
import { onMounted, ref } from 'vue'
import axios from 'axios'
import Button from '../components/Button.vue'

interface Card {
  cardId: number
  cardName: string
  cardLife: number
  cardDefense: number
  cardMana: number
  deckId: number
}

interface Deck {
  deckId: number
  deckName: string
  cards: Card[]
}

const decks = ref<Deck[]>([])
const router = useRouter()

async function getDecks(): Promise<void> {
  try {
    const deckData = await getData('http://localhost:8081/api/decks')
    decks.value = deckData
  } catch (error) {
    console.error('Error getting data from the database')
    throw error
  }
}

async function getData(url: string): Promise<any> {
  try {
    const response = await axios.get(url)
    return response.data
  } catch (error) {
    console.error(`Error fetching data from ${url}:`, error)
    throw error
  }
}

async function deleteDeck(deckId: number) {
  try {
    await axios.delete(`http://localhost:8081/api/decks/${deckId}`)
    getDecks()
  } catch (error) {
    console.error(`Error deleting deck ${deckId}`, error)
  }
}

function navigateToEditDeck(deck: Deck) {
  router.push({
    name: 'save',
    params: {
      deckName: deck.deckName,
      deckId: deck.deckId
    },
    query: {
      isItSaving: '1'
    }
  })
}

function navigateToSaveDeck() {
  router.push({
    name: 'save',
    params: undefined
  })
}

onMounted(() => {
  getDecks()
})
</script>

<template>
  <v-container
    fluid
    class="flex-nowrap flex-column align-center justify-center fill-height pa-ma-0"
  >
    <v-row fluid align="center" justify="center">
      <v-col cols="auto" class="d-flex justify-center align-center flex-column">
        <h1>Start saving your decks</h1>
        <Button
          class="ma-4"
          iconName="mdi-content-save"
          buttonText="Save Deck"
          iconColor="#6aa9fd"
          @click="navigateToSaveDeck()"
        />
      </v-col>
    </v-row>

    <v-row class="ma-0" align="center" justify="center">
      <v-card
        class="pa-2 ma-0 d-flex flex-column ga-4 rounded"
        min-width="550px"
        min-height="200px"
      >
        <v-list-item
          class="bg-grey-lighten-4 rounded pa-3"
          v-for="deck in decks"
          :key="deck.deckId"
        >
          <v-list-item-title>
            {{ deck.deckName }}
          </v-list-item-title>
          <template v-slot:append>
            <v-btn
              width="40px"
              height="40px"
              color="#F4511E"
              class="bg-#6aa9fd rounded"
              icon="mdi-delete"
              @click="deleteDeck(deck.deckId)"
            >
            </v-btn>
            <v-btn
              width="40px"
              height="40px"
              color="#6aa9fd"
              icon="mdi-pencil"
              class="ml-2 rounded"
              @click="navigateToEditDeck(deck)"
            >
            </v-btn>
          </template>
        </v-list-item>
      </v-card>
    </v-row>
  </v-container>
</template>

<style scoped>
* {
  color: #051b22;
}
</style>
