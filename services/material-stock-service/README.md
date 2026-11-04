# Material Stock Service

Bounded context: **Material Stock** — Guarded material stock per plant and the issues to production against it.

Alignment: ISA-95 material management posture. Core invariant, enforced in code and tests: **the guarded
balance/capacity never goes negative** — over-drawing operations are rejected with `422`
and nothing is recorded. All data is synthetic.

| Item | Value |
|---|---|
| Port | `8082` (`HTTP_PORT`) |
| Base path | `/api/v1/material-stock` |
| Owns data | MaterialStock accounts, MaterialIssue records |
| Publishes | `stock.consumed`, `stock.insufficient` |

Run locally: `mvn -pl services/material-stock-service quarkus:dev`.
