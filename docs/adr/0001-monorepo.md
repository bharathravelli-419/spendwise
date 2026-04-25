### **ADR 001: Single monorepo for all Spendwise code**

* Status: **Accepted**
* Date: 2026-04-25
* Deciders: bharathravelli-419

Context

Spendwise will have ~6 backend services, a React Frontend, IaaC, Jenkins pipeline, Kubernetes manifests, Docs. I am one person with time constraints. I need frictionless cross-cutting changes.

### Decision

Single Git repository spendwise containing:

1. apps/*  - each service & frontend (web)
2. infra/*   - Docker, K8s, Terraform, jenkins
3. docs/*   - ADRs, runbooks, design docs
4. journal/*- daily notes

### Alternatives considered

* polyrepo (one per service) - rejected for now changes spread accross PRs, overhead of complex management.
* Turborepo-managed monorepo - rejected for noe
