import { defineConfig } from 'vitest/config';
import vue from "@vitejs/plugin-vue";

export default defineConfig({
    // ...
    test: {
        // enable jest-like global test APIs
        globals: true,
        // simulate DOM with happy-dom
        // (requires installing happy-dom as a peer dependency)
        environment: 'happy-dom',
    },
    plugins: [vue()]
})