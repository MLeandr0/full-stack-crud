<script setup lang="ts">
import { useRouter } from 'vue-router'
import { ref, defineProps, onMounted } from 'vue'
import axios from 'axios'
import InputText from '@/components/InputText.vue'
import Button from '@/components/ButtonComponent.vue'

const router = useRouter()
const cardCost = ref<number | string>()
const cardDefense = ref<number | string>()
const cardName = ref<string>()
const cardLife = ref<number | string>()
const localDeckName = ref<string>()
const url = import.meta.env.VITE_API_URL

interface Props {
  deckName: string
  deckId: string
}

const props = defineProps<Props>()
const isItSavingQuery = ref(
  Array.isArray(router.currentRoute.value.query.isItSaving)
    ? router.currentRoute.value.query.isItSaving[0]
    : router.currentRoute.value.query.isItSaving || '0'
)

localDeckName.value = props.deckName === undefined ? '' : props.deckName

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

const currentDeck = ref<Deck>({
  deckName: '',
  cards: [],
  deckId: 0
})

const displayCardCollection = ref<Card[]>([])
const tempCards = ref<Card[]>([])

async function addCardLocally() {
  try {
    const manaValue = Number(cardCost.value)
    const defenseValue = Number(cardDefense.value)
    const lifeValue = Number(cardLife.value)

    if (isNaN(manaValue) || isNaN(defenseValue)) {
      throw new Error('Invalid input for mana or defense.')
    }

    tempCards.value.push({
      cardId: 0,
      cardName: cardName.value || '',
      cardMana: manaValue,
      cardDefense: defenseValue,
      cardLife: lifeValue,
      deckId: 0
    })

    displayCardCollection.value.push({
      cardId: 0,
      cardName: cardName.value || '',
      cardMana: manaValue,
      cardDefense: defenseValue,
      cardLife: lifeValue,
      deckId: 0
    })

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
    const response = await axios.post(`${url}/decks`, {
      deckName: localDeckName.value
    })
    await insertCardToDeck(response.data.deckId, tempCards.value)
  } catch (error) {
    console.error('Error creating deck', error)
    throw error
  }
  navigateToHomeScreen()
}

async function insertCardToDeck(deckId: number, temp: Card[]) {
  try {
    const response = await axios.post(`${url}/decks/${deckId}/cards`, temp)
    if (response.status === 201) {
      currentDeck.value.cards = []
      tempCards.value = []
      currentDeck.value.deckName = ''
      currentDeck.value.deckId = 0
      return { success: true }
    } else {
      return { success: false, error: response.statusText }
    }
  } catch (error) {
    console.error('Error inserting cards to deck', error)
    return { success: false, error }
  }
}

async function editDeck(deckId: number) {
  try {
    await axios.put(`${url}/decks/${deckId}`, {
      deckName: localDeckName.value,
      deckId: deckId
    })

    await insertCardToDeck(deckId, tempCards.value)

    currentDeck.value.deckName = ''
    currentDeck.value.deckId = 0
    currentDeck.value.cards = []
    tempCards.value = []
    navigateToHomeScreen()
    return { success: true }
  } catch (error) {
    return { success: false, error }
  }
}

async function getDeckCards(deckId: number): Promise<any> {
  if (props.deckId !== undefined && isItSavingQuery.value == '1') {
    try {
      const response = await axios.get(`${url}/decks/${deckId}/cards`)
      displayCardCollection.value = response.data
      console.log(tempCards.value.length)
      if (tempCards.value.length > 0) {
        displayCardCollection.value = displayCardCollection.value.concat(tempCards.value)
      }
    } catch (error) {
      console.error('Error displaying cards of the deck', error)
      throw error
    }
  }
}

async function decideDeckAction() {
  if (isItSavingQuery.value === '1') {
    await editDeck(Number(props.deckId))
  } else if (isItSavingQuery.value === '0') {
    currentDeck.value.cards = tempCards.value
    await createDeck()
  } else throw 'Error unknown value'
}

function removeFromArray(cardId: number) {
  const index = tempCards.value.findIndex((card) => card.cardId === cardId)
  if (index !== -1) {
    tempCards.value.splice(index, 1)
  }
}

async function deleteCardFromDeck(cardId: number) {
  try {
    await axios.delete(`${url}/decks/${props.deckId}/cards/${cardId}`)
    getDeckCards(Number(props.deckId))
  } catch (error) {
    removeFromArray(cardId)
    getDeckCards(Number(props.deckId))
  }
}

function navigateToHomeScreen() {
  router.push({
    name: 'home'
  })
}

onMounted(() => {
  getDeckCards(+props.deckId)
})
</script>

<template>
  <v-container fluid class="flex-nowrap flex-column align-start justify-start fill-height pa-ma-0">
    <v-container fluid class="pa-ma-0">
      <v-row align="center" justify="center">
        <v-col cols="8" sm="4">
          <h1 class="text-center">Work on your deck</h1>
          <v-text-field
            variant="underlined"
            v-model="localDeckName"
            counter="20"
            hint="Dont type a word too long"
            label="Deck Name"
          ></v-text-field>
        </v-col>
      </v-row>
    </v-container>

    <v-container fluid class="pa-ma-0">
      <v-row align="center" justify="center">
        <v-col cols="2">
          <InputText label="Cost" iconName="mdi-diamond-stone" v-model="cardCost" />
        </v-col>

        <v-col cols="2">
          <InputText label="Life" iconName="mdi-heart" v-model="cardLife" />
        </v-col>

        <v-col cols="2">
          <InputText label="Defense" iconName="mdi-shield" v-model="cardDefense" />
        </v-col>

        <v-col cols="2">
          <InputText label="Name" iconName="mdi-rename" v-model="cardName" />
        </v-col>

        <v-col cols="2">
          <Button
            iconColor="#6aa9fd"
            iconName="mdi-plus"
            buttonText="Add Card"
            @click="addCardLocally"
          />
        </v-col>
      </v-row>
    </v-container>

    <v-container fluid class="flex-1-1 pa-md-0">
      <v-row class="fill-height" justify="center">
        <v-col class="fill-height" cols="6">
          <v-card class="fill-height bg-grey-lighten-4" min-width="300px" min-height="50px">
            <v-row class="ma-6">
              <v-col
                v-for="(cards, index) in displayCardCollection"
                :key="index"
                cols="12"
                md="6"
                lg="4"
              >
                <v-card v-if="cards" class="ma-0 bg-grey-lighten-5">
                  <v-card-title>{{ cards.cardName }}</v-card-title>
                  <v-card-text>
                    <div>{{ cards.cardMana }}</div>
                    <div>{{ cards.cardLife }}</div>
                    <div>{{ cards.cardDefense }}</div>
                  </v-card-text>
                  <v-card-actions>
                    <v-icon @click="deleteCardFromDeck(cards.cardId)">mdi-delete</v-icon>
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
        <v-col cols="2">
          <Button
            iconName="mdi-check-bold"
            buttonText="Done"
            iconColor="#6aa9fd"
            @click="decideDeckAction()"
          />
        </v-col>
        <v-col cols="2">
          <Button iconName="mdi-cancel" buttonText="Cancel" iconColor="#F4511E" to="/" />
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
