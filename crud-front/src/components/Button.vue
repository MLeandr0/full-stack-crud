<script setup lang="ts">

    const props = defineProps<{
        iconName: string;
        buttonText: string;
    }>();

</script>

<template>
    <v-btn class="bg-blue-lighten-3" :prepend-icon="props.iconName"> {{ props.buttonText }} </v-btn>  
</template>