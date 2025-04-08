const isRunningInCypress = window.location.hostname === "reactfrontend";
export const API_BASE = isRunningInCypress
  ? "http://springbackend:8080"         // for Cypress (Docker)
  : "http://localhost:8080";            // for host browser
