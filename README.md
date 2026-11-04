# Open Manufacturing — Reference Manufacturing Core Platform

A **standard, generic manufacturing core platform** that any organization in this vertical
can deploy and adapt: built as **microservices and serverless functions** on
**Java 21 + Quarkus**, aligned with **The Twelve-Factor App**, documented under the ISO
architecture and quality standards, and aligned with the domain standards of the
vertical (see the traceability matrix). Everything is *aligned to* the cited standards —
**no certification, conformance or regulatory-compliance claim is made**. All data is
**synthetic**.

> Reference implementation: small, complete and runnable. The rules that matter in this
> domain are enforced in code and covered by tests — start with the invariant:
> material issued to production never exceeds available stock — an issue that would overrun stock is rejected (422) and records nothing, so work orders never run on phantom material.

## Standards alignment

| Standard / framework | What it governs here | Where |
|---|---|---|
| ISO/IEC/IEEE **42010** | Architecture description | `docs/architecture/architecture-description.md` |
| ISO/IEC **25010** (SQuaRE) | Product quality model and scenarios | `docs/architecture/quality-attributes.md` |
| ISO/IEC/IEEE **15288 & 12207** | Life-cycle processes | `docs/lifecycle/software-lifecycle.md` |
| ISO/IEC **27001 & 27002** | Information security | `docs/security/` |
| **The Twelve-Factor App** | Cloud-native methodology | `docs/architecture/twelve-factor.md` |
| ISA-95 / IEC 62264 | Enterprise–control integration (levels 3/4) | `docs/domain/domain-standards.md` level mapping |
| OPC UA / IEC 62541 | Industrial interoperability | Boundary: machine data integrates via OPC UA at the edge |
| ISO 22400 | Manufacturing operations KPIs | OEE and KPI evaluator posture |
| IEC 63278 (AAS) | Asset Administration Shell | Equipment digital-twin posture |
| ISO 9001 | Quality management | Quality gates in the life cycle |

Full traceability: `docs/compliance/standards-traceability.md`.

## The platform at a glance

### Microservices (Quarkus, `services/`)

| Service | Port | Alignment | Responsibility |
|---|---|---|---|
| `production-line-service` | 8081 | ISA-95 equipment hierarchy posture | Production line registry per plant. |
| `material-stock-service` | 8082 | ISA-95 material management posture | Guarded material stock per plant and the issues to production against it. |
| `quality-rules-service` | 8083 | ISO 9001 / ISO 22400 quality discipline | Decision rules binding product families to batch-size and defect envelopes. |
| `equipment-catalog-service` | 8084 | IEC 63278 AAS identification posture | Equipment registry (Asset Administration Shell posture). |
| `notification-service` | 8085 | Platform support (shared pattern) | Channel-agnostic dispatch of domain notifications from events. |

### Serverless functions (Quarkus Funqy + Knative, `functions/`)

| Function | Trigger | Responsibility |
|---|---|---|
| `downtime-risk-scorer` | Event / HTTP | Scores equipment risk signals (vibration, temperature, error rate) with an auditable rationale. |
| `oee-evaluator` | Event / HTTP | Compares unplanned stops and OEE against line targets (ISO 22400 KPI discipline); one finding per breach. |

## Design principles

1. **One bounded context per service**, identical hexagonal layout everywhere.
2. **The domain invariant is code, not prose** — material issued to production never exceeds available stock — an issue that would overrun stock is rejected (422) and records nothing, so work orders never run on phantom material.
3. **Privacy by design** — Production data is commercially sensitive operational data; worker personal data stays in adopter systems. All data here is synthetic.
4. **Database per service, API-first (OpenAPI), events as CloudEvents.**
5. **Adapt by configuration** — rules, thresholds, tariffs/rates and providers are data
   and configuration, not code forks (see *Adaptation guide*).

## Quick start

Prerequisites: Java 21, Maven 3.9+, optionally Docker/Podman and Knative/OpenShift.

```bash
mvn verify                                    # build and test everything
mvn -pl services/production-line-service quarkus:dev
docker compose up -d                          # local PostgreSQL, Kafka, Keycloak
```

## Deployment

- **Kubernetes / OpenShift**: `kubectl apply -k deploy/kubernetes/`.
- **Serverless**: Knative Services in `deploy/knative/` (scale to zero).
- **Local**: `docker-compose.yml` for dependencies; services run with `quarkus:dev`.

## Adaptation guide

| You want to… | Change only… |
|---|---|
| Adjust domain rules and thresholds | Rule data in the `quality-rules-service` context and function configuration |
| Change the guarded capacity semantics | The `material-stock-service` context (invariant stays: never negative) |
| Plug your IdP / providers | OIDC at the gateway; provider adapters at the boundary |
| Replace in-memory persistence | The repository port's JPA adapter on the service's own database (`DB_KIND`, `DB_URL`) |

## Documentation map

- Architecture (42010): `docs/architecture/` · Decisions: `docs/adr/`
- Domain standards: `docs/domain/domain-standards.md`
- Quality (25010), Security (27001/27002), Life cycle (15288/12207), API, Data, Operations: under `docs/`

## License

Apache-2.0 — see `LICENSE`.
