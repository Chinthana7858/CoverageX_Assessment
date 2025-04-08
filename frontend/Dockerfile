# frontend/Dockerfile
FROM node:18-alpine

WORKDIR /app
COPY . .

ARG VITE_API_BASE
ENV VITE_API_BASE=$VITE_API_BASE

RUN npm install
RUN VITE_API_BASE=$VITE_API_BASE npm run build

RUN npm install -g serve
EXPOSE 3000
CMD ["serve", "-s", "dist", "--listen", "3000"]
