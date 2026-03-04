# ENTSO-E AI Service

An intelligent electricity price analysis service that leverages AI to provide insights and recommendations about Swedish electricity prices (SE3 region).

## Overview

This Spring Boot application integrates with an electricity price analyzer service and uses AI (via Ollama) to provide natural language analysis and recommendations about electricity prices. The service helps households optimize their electricity consumption by identifying the cheapest and most expensive hours of the day.

## Features

- **AI-Powered Analysis**: Uses Ollama with Llama3 model to generate human-readable insights
- **Price Statistics**: Fetches daily electricity price data including averages, min/max values
- **Smart Recommendations**: Provides actionable advice on when to use high-consumption appliances
- 🇸**Swedish Market Focus**: Tailored for the Swedish electricity market (SE3 zone)
- **VAT-Inclusive Pricing**: Shows both base prices and VAT-inclusive prices

## Prerequisites

- Java 21 or higher
- Maven 3.6+
- Ollama running locally with Llama3 model installed
- ENTSO-E Price Analyzer service running (see configuration)

## Installation

### 1. Clone the repository

```bash
git clone <repository-url>
cd entsoe-ai-service
```

### 2. Install and configure Ollama

Install Ollama from [ollama.ai](https://ollama.ai) and pull the Llama3 model:

```bash
ollama pull llama3
```

Make sure Ollama is running on `http://localhost:11434` (default).

### 3. Configure the application

Edit `src/main/resources/application.properties`:

```properties
# Server configuration
server.port=8090

# Ollama AI configuration
spring.ai.ollama.base-url=http://localhost:11434
spring.ai.ollama.chat.model=llama3

# Price Analyzer service URL
entsoe.price-analyzer.url=http://192.168.0.6:8088
```

Update the `entsoe.price-analyzer.url` to point to your ENTSO-E Price Analyzer service.

### 4. Build the project

```bash
./mvnw clean install
```

## Running the Application

### Using Maven

```bash
./mvnw spring-boot:run
```

### Using Java

```bash
java -jar target/entsoe-ai-service-0.0.1-SNAPSHOT.jar
```

The application will start on port 8090 (configurable in application.properties).

## API Endpoints

### Get Price Analysis

Returns AI-generated analysis of today's electricity prices with recommendations.

**Endpoint:** `GET /api/analysis`

**Response:**
```json
{
  "analysis": "Baserat på dagens elpriser för SE3...\n\n1. Billigaste timmarna...\n2. Dyraste timmarna...\n3. Rekommendation..."
}
```

**Example using curl:**
```bash
curl http://localhost:8090/api/analysis
```

## Architecture

### Components

- **PriceAnalysisController**: REST API controller exposing the analysis endpoint
- **PriceAnalysisService**: Core service that fetches price data and generates AI analysis
- **RestTemplateConfig**: Configuration for HTTP client
- **DailySummary**: Model for daily price statistics
- **PriceAnalysis**: Model for individual price points

### External Dependencies

1. **ENTSO-E Price Analyzer Service**: Provides electricity price data
   - `/api/analysis/today` - Returns hourly prices for today
   - `/api/analysis/summary/today` - Returns daily statistics

2. **Ollama**: Local AI inference engine
   - Runs Llama3 model for natural language generation
   - Processes prompts and returns Swedish language analysis

## Technology Stack

- **Spring Boot 4.0.3**: Application framework
- **Spring AI 2.0.0-M2**: AI integration framework
- **Ollama**: Local AI model runtime
- **Java 21**: Programming language
- **Maven**: Build tool

## Configuration Options

| Property | Default | Description |
|----------|---------|-------------|
| `server.port` | 8090 | HTTP server port |
| `spring.ai.ollama.base-url` | http://localhost:11434 | Ollama service URL |
| `spring.ai.ollama.chat.model` | llama3 | AI model to use |
| `entsoe.price-analyzer.url` | - | Price analyzer service URL |

## Development

### Project Structure

```
src/
├── main/
│   ├── java/tech/sjostrom/entsoeaiservice/
│   │   ├── EntsoeAiServiceApplication.java
│   │   ├── PriceAnalysisController.java
│   │   ├── PriceAnalysisService.java
│   │   ├── RestTemplateConfig.java
│   │   ├── DailySummary.java
│   │   └── PriceAnalysis.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/tech/sjostrom/entsoeaiservice/
        └── EntsoeAiServiceApplicationTests.java
```

### Running Tests

```bash
./mvnw test
```

## Troubleshooting

### Common Issues

1. **Ollama not responding**
   - Ensure Ollama is running: `ollama serve`
   - Check if Llama3 is installed: `ollama list`
   - Verify the base URL in application.properties

2. **Price Analyzer service unavailable**
   - Verify the Price Analyzer service is running
   - Check network connectivity to the configured URL
   - Update `entsoe.price-analyzer.url` if needed

3. **No price data available**
   - The service depends on external price data
   - Check the Price Analyzer service logs
   - Ensure ENTSO-E API is accessible from the Price Analyzer

## Future Enhancements

- [ ] Support for multiple Swedish price zones (SE1, SE2, SE3, SE4)
- [ ] Historical price analysis and trends
- [ ] Price forecasting
- [ ] Webhook notifications for price thresholds
- [ ] Web UI for visualization
- [ ] Docker containerization
- [ ] Multi-language support



For issues and questions, please [open an issue](https://github.com/your-repo/issues) on GitHub.

