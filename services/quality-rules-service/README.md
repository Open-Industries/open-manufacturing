# Quality Rules Service

Bounded context: **Quality Rules** — Decision rules binding product families to batch-size and defect envelopes.

Alignment: ISO 9001 / ISO 22400 quality discipline. Decisions are `ALLOWED` / `DEGRADED` / `DENIED`, always
with the matched rule and a human-readable reason for audit. All data is synthetic.

| Item | Value |
|---|---|
| Port | `8083` (`HTTP_PORT`) |
| Base path | `/api/v1/quality-rules` |
| Owns data | QualityRule rules |
| Publishes | `quality.activated` |

Run locally: `mvn -pl services/quality-rules-service quarkus:dev`.
