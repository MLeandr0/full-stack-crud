<script setup lang="ts">
const props = defineProps<{
  iconName: string
  buttonText: string
  iconColor: string
  buttonSize: string
}>()
</script>

<template>
  <v-btn :width="props.buttonSize" :color="props.iconColor" :prepend-icon="props.iconName">
    {{ props.buttonText }}
  </v-btn>
</template>
