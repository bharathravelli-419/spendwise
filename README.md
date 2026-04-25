# Spendwise

Personal finance app built to level up engineering skills across different trending technologies

**Live:** Coming soon

**Design Docs:** docs folder

**Journal:** journal folder

**ADRs:** docs/adr folder

## Tech Stack

**Backend :** Spring Boot >3 (Java 21) - PostgreSQL 16 - Redis - Kafka

**Frontend:** React 19 - Vite - TanStack Query - Tailwind

**Infra:** Docker - Kubernetes - Terraform - Jenkins - Github Actions - ArgoCD

**Observability:** OpenTelemetry - Prometheus - Grafana - Loki - Tempo

**AI:** Ollama - LangChain4j - pgvector - MCP


## Quick start

```bash
docker compose up -d
./gradlew :apps:transaction-service:bootRun
cd apps/web && pnpm install && pnpm dev
```
