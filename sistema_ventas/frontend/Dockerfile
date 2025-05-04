# Construye la app con Node
FROM node:18-alpine AS builder
WORKDIR /app
COPY . .
RUN npm install && npm run build

# Sirve la app con nginx
FROM nginx:alpine
COPY --from=builder /app/build /usr/share/nginx/html

# Copia configuración personalizada de Nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
