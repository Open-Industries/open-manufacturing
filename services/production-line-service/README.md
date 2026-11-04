# Production Line Service

Bounded context: **Production Lines** — Production line registry per plant.

Alignment: ISA-95 equipment hierarchy posture. Lifecycle transitions take no body; secret identifiers,
where the domain has them, are masked at creation and never stored or returned in clear.
All data is synthetic.

| Item | Value |
|---|---|
| Port | `8081` (`HTTP_PORT`) |
| Base path | `/api/v1/production-lines` |
| Owns data | ProductionLine records |
| Publishes | `line.created`, lifecycle events (see `contracts/asyncapi.yaml`) |

Run locally: `mvn -pl services/production-line-service quarkus:dev`.
