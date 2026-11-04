# Equipment Catalog Service

Bounded context: **Equipment** — Equipment registry (Asset Administration Shell posture).

Alignment: IEC 63278 AAS identification posture. Lifecycle transitions take no body. All data is synthetic.

| Item | Value |
|---|---|
| Port | `8084` (`HTTP_PORT`) |
| Base path | `/api/v1/equipment` |
| Owns data | Equipment records |

Run locally: `mvn -pl services/equipment-catalog-service quarkus:dev`.
