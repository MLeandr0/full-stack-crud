<script setup lang="ts">
const props = defineProps<{
  iconName: string
  buttonText: string
  iconColor: string
}>()
</script>

<template>
  <v-btn width="550px" :color="props.iconColor" :prepend-icon="props.iconName">
    {{ props.buttonText }}
  </v-btn>
</template>
