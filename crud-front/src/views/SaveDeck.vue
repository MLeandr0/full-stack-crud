<script setup lang="ts">
import { useRouter } from "vue-router"
import { ref } from "vue"
import axios from "axios"
import InputText from "@/components/InputText.vue"
import Button from "@/components/Button.vue"

const router = useRouter()
const cards = ref<Card[]>([])
const cardCost = ref<number | string>()
const cardDefense = ref<number | string>()
const cardName = ref<string>()
const cardLife = ref<number | string>()
const deckName = ref<string>()

interface Props {
  deckName?: string
  deckId?: string
  isItSaving?: number
}

const props = defineProps<Props>()
deckName.value = props.deckName == undefined ? '' : props.deckName

interface Card {
  cardId: number
  cardName: string
  cardLife: string
  cardDefense: number
  cardMana: number
  deck: Deck
}

interface Deck {
  deckId: number
  deckName: string
  cards: Card[]
}

const currentDeck = ref<Deck>({
  deckName: "",
  cards: [],
  deckId: 0
})

async function addCard(deckId: number) {
  try {
    const response = await axios.post(`http://localhost:8081/api/decks/${deckId}/cards`, {
      cardName: cardName.value,
      cardMana: cardCost.value,
      cardShield: cardCost.value,
      cardLife: cardLife.value
    })

    currentDeck.value.cards.push(response.data)
    clearCardInfoInputs()
  } catch (error) {
    console.error('Error adding card to deck', error)
    throw error
  }
}

function clearCardInfoInputs() {
  cardCost.value = ''
  cardDefense.value = ''
  cardName.value = ''
  cardLife.value = ''
}

async function createDeck() {
  try {
    const response = await axios.post('http://localhost:8081/api/decks', {
      deckName: deckName.value,
      cards: currentDeck.value.cards
    })

    currentDeck.value.deckName = ''
    currentDeck.value.cards = []
    navigateToSaveDeck()

    console.log('Deck created:', response.data)
  } catch (error) {
    console.error('Error creating deck', error)
    throw error
  }
}

function navigateToSaveDeck() {
  router.push({
    name: 'home'
  })
}
</script>

<template>
  <v-container
    fluid
    class="bg-#f7fafc flex-nowrap flex-column align-start justify-start fill-height pa-ma-0"
  >
    <v-container fluid class="pa-ma-0">
      <v-row align="center" justify="center">
        <v-col cols="8" sm="4">
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
          <InputText label="Cost" iconName="mdi-diamond-stone" v-model="cardCost" />
        </v-col>

        <v-col cols="1">
          <InputText label="Life" iconName="mdi-heart" v-model="cardLife" />
        </v-col>

        <v-col cols="1">
          <InputText label="Defense" iconName="mdi-shield" v-model="cardDefense" />
        </v-col>

        <v-col cols="1">
          <InputText label="Name" iconName="mdi-rename" v-model="cardName" />
        </v-col>

        <v-col cols="1">
          <Button iconColor="#6aa9fd" iconName="mdi-plus" buttonText="Add Card" @click="addCard" />
        </v-col>
      </v-row>
    </v-container>

    <v-container fluid class="flex-1-1 pa-md-0">
      <v-row class="fill-height" justify="center">
        <v-col class="fill-height" cols="6">
          <v-card variant="tonal" class="fill-height" min-width="300px" min-height="50px">
            <v-row class="ma-6">
              <v-col v-for="(currentDeck, index) in cards" :key="index" cols="12" md="6" lg="4">
                <v-card v-if="cards" color="#6a94a2" class="ma-0">
                  <v-card-title>{{ cards.at[index].name }}</v-card-title>
                  <v-card-text>
                    <div>{{ cards.at[index].mana }}</div>
                    <div>{{ cards.at[index].life }}</div>
                    <div>{{ cards.at[index].defense }}</div>
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
            iconColor="#6aa9fd"
            @click="createDeck()"
          />
        </v-col>
        <v-col cols="1">
          <Button iconName="mdi-delete" buttonText="Cancel" iconColor="#6aa9fd" to="/" />
        </v-col>
      </v-row>
    </v-container>
  </v-container>
</template>

<style>
* {
  color: #051b22;
}
</style>
