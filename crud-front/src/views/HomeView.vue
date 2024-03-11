<script setup lang="ts">
import { useRouter } from "vue-router"
import { onMounted, ref} from "vue"
import axios from "axios"
import Button from "@/components/Button.vue"

interface Deck {
  deckId: number
  deckName: string
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
      deckId: deck.deckId,
      isItSaving: 1
    }
  })
}

function navigateToSaveDeck() {
  router.push({
    name: 'save',
    params: {},
  })
}

onMounted(() => {
  getDecks()
})
</script>

<template>
  <v-container
    fluid
    class="bg-#f3f8fa flex-nowrap flex-column align-center justify-center fill-height pa-ma-0"
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
      <v-card class="ma-0" min-width="550px" min-height="200px">
        <v-list-item v-for="deck in decks" :key="deck.deckId">
          <v-list-item-title>
            {{ deck.deckName }}
          </v-list-item-title>
          <template v-slot:append>
            <v-btn
              color="#F4511E"
              class="bg-#6aa9fd"
              icon="mdi-delete"
              @click="deleteDeck(deck.deckId)"
            >
            </v-btn>
            <v-btn color="#6aa9fd" icon="mdi-pencil" @click="navigateToEditDeck(deck)">
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
