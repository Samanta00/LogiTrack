/** @type {import('tailwindcss').Config} */
export default {
    content: [
      "./index.html",
      "./src/**/*.{js,ts,jsx,tsx}",
    ],
    theme: {
      extend: {
        colors: {
          'logi-bg': '#f4f7f6',        // Fundo acinzentado da imagem
          'logi-green-line': '#6ba26b', // Linha verde do gráfico
          'logi-green-area': '#a8d1a8', // Preenchimento verde
          'logi-beige-line': '#b09c7a', // Linha bege do gráfico
          'logi-beige-area': '#c9bfa9', // Preenchimento bege
          'logi-blue': '#6495ed',       // Azul das barras
          'logi-red': '#b22222',        // Vermelho das barras
        },
        borderRadius: {
          'logi': '12px',
        }
      },
    },
    plugins: [],
  }