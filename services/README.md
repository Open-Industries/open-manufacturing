# Services

One independently deployable Quarkus microservice per bounded context. Every service
follows the same hexagonal layout, exposes `/openapi`, `/q/health` and `/q/metrics`,
returns RFC 9457 problem details, and is configured only through environment variables
(Twelve-Factor). Lifecycle transitions take no body; `@Consumes` appears only on
endpoints that read one.

**Implementation order** (see `../docs/implementation-roadmap.md`):
Production Lines → Material Stock → Quality Rules → Open APIs.

| Service | Port | Alignment |
|---|---|---|
| `production-line-service` | 8081 | ISA-95 equipment hierarchy posture |
| `material-stock-service` | 8082 | ISA-95 material management posture |
| `quality-rules-service` | 8083 | ISO 9001 / ISO 22400 quality discipline |
| `equipment-catalog-service` | 8084 | IEC 63278 AAS identification posture |
| `notification-service` | 8085 | Platform support (shared pattern) |

Run the pilot service:

```bash
mvn -pl services/production-line-service quarkus:dev
# OpenAPI UI: http://localhost:8081/docs — health: http://localhost:8081/q/health
```
