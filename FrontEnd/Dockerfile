FROM node:16.16.0 as builder

WORKDIR /app

COPY package.json .
COPY package-lock.json .

RUN npm install --silent

COPY . .

RUN npm run build

FROM nginx

COPY --from=builder /app/dist/ /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf
