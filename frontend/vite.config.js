import { defineConfig, loadEnv } from 'vite'

export default defineConfig(({ command, mode }) => {
  const env = loadEnv(mode, process.cwd(), '')
  
  return {
    server: {
      port: 5173,
      host: true
    },
    define: {
      // Make environment variables available globally
      __API_URL__: JSON.stringify(env.VITE_API_URL || 'https://textile-sahil-backend.onrender.com')
    }
  }
})
