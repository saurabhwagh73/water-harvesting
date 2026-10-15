# Rainwater Harvesting Information Website
Java 21 + Spring Boot 3 + Maven backend, React 18 (Vite) frontend.
Content summarised from https://en.wikipedia.org/wiki/Rainwater_harvesting

## Run backend (IntelliJ: open /backend as Maven project, or terminal)
    cd backend
    mvn spring-boot:run        # http://localhost:8080/api/overview

## Run frontend
    cd frontend
    npm install
    npm run dev                # http://localhost:5173 (proxies /api to :8080)

## API
GET  /api/overview | benefits | components | history | regions | faqs
POST /api/calculate  {"roofAreaM2":100,"annualRainfallMm":800,"runoffCoefficient":0.8,"dailyDemandLitres":200,"dryDays":60}
