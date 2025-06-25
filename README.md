# Kafka Confluent Replicator Orchestrator

## Overview
This project provides a small Spring Boot service used to orchestrate Kafka topic replication. It stores information about which Kafka Connect worker is responsible for replicating each topic in a cluster and exposes endpoints to manage that information.

## Prerequisites
- **JDK** 20 or newer
- **Maven**
- **Docker** (for running with `docker-compose`)

## Running
Run the application directly from the sources:

```bash
mvn spring-boot:run
```

Alternatively, start the service together with its PostgreSQL database using Docker:

```bash
docker-compose -f docker/docker-compose.yml up
```

The compose file will start both the application and a Postgres instance required by the service.

## Example API usage
A sample request payload is available in [template/request.json](template/request.json). Use it as a reference when sending POST requests to `/api/topic`.
The `action` field only accepts the values `increment` or `redistribute`.
