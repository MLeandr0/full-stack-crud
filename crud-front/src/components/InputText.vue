<script setup lang="ts">
const props = defineProps<{
  iconName: string
  label: string
}>()
</script>

<template>
  <v-text-field width="50px" variant="underlined" :label="props.label" clearable>
    <template v-slot:prepend-inner>
      <v-icon :icon="props.iconName"></v-icon>
    </template>
  </v-text-field>
</template>
