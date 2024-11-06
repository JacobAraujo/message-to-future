import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // URL where your API is running
        changeOrigin: true,
        secure: false,
      },
    },
  },
});
