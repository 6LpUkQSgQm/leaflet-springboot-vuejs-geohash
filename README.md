# leaflet-springboot-vuejs-geohash

## Setup Vue.js & Spring Boot

### Prerequisites

#### Linux

```
sudo apt update
sudo apt install node
npm install -g @vue/cli
```

## First App run

Inside the root directory, do a: 

```
mvn clean install
```

Run our complete Spring Boot App:

```
cd backend/
mvn spring-boot:run
```

```
cd frontend/
npm install
npm install --save-dev  --unsafe-perm node-sass
npm run serve
```

