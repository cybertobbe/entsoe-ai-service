# ENTSO-E AI Service

An intelligent electricity price analysis service that leverages AI to provide insights and recommendations about Swedish electricity prices (SE3 region).

## Overview

This Spring Boot application integrates with an electricity price analyzer service and uses AI (via Ollama) to provide natural language analysis and recommendations about electricity prices. The service helps households optimize their electricity consumption by identifying the cheapest and most expensive hours of the day.

## Features

- **AI-Powered Analysis**: Uses Ollama with Llama3 model to generate human-readable insights
- **Price Statistics**: Fetches daily electricity price data including averages, min/max values
- **Smart Recommendations**: Provides actionable advice on when to use high-consumption appliances
- **Swedish Market Focus**: Tailored for the Swedish electricity market (SE3 zone)
- **VAT-Inclusive Pricing**: Shows both base prices and VAT-inclusive prices

