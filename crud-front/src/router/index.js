import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import SaveDeck from '../views/SaveDeck.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/save-deck/:deckName?/:deckId?',
      name: 'save',
      component: SaveDeck,
      props: true
    }
  ]
})

export default router
