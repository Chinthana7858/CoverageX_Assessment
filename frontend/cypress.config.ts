// cypress.config.ts
const { defineConfig } = require('cypress');

module.exports = defineConfig({
  e2e: {
     baseUrl: 'http://reactfrontend:3000',
    specPattern: 'src/cypress/e2e/**/*.cy.{js,ts,jsx,tsx}',
    supportFile: false,
  },
});
