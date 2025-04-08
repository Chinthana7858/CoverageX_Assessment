/// <reference types="cypress" />

describe('Todo App E2E', () => {
    beforeEach(() => {
      // Intercept API calls
      cy.intercept('POST', '**/tasks').as('createTask');
      cy.intercept('GET', '**/tasks').as('getTasks');
      cy.intercept('PATCH', '**/tasks/*/done').as('markDone');

      // Visit the app
      cy.visit('http://reactfrontend:3000');


    });

    it('adds a task and marks it as done', () => {
      // Fill in the task form using data-testid attributes
      cy.get('[data-testid="task-title-input"]').type('E2E Task');
      cy.get('[data-testid="task-desc-input"]').type('Test this task');

      // Submit the form
      cy.get('[data-testid="add-task-btn"]').click();

      // Wait for backend responses
      cy.wait('@createTask');
      cy.wait('@getTasks');

      // Confirm the task appears
      cy.contains('E2E Task').should('exist');

      // Click the "Done" button inside the same card
      cy.contains('E2E Task')
        .closest('div.bg-gray-200')
        .within(() => {
          cy.contains('Done').click();
        });

      // Wait for the mark-done request and refreshed task list
      cy.wait('@markDone');
      cy.wait('@getTasks');

      // Verify task is removed
      cy.contains('E2E Task').should('not.exist');
    });
  });
