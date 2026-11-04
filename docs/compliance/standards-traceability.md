# Standards Traceability Matrix

Every standard or framework claimed by this platform and where its evidence lives.
"Aligned to" throughout — no certification or regulatory-compliance claim is made.

| Standard / framework | Area | Evidence |
|---|---|---|
| ISO/IEC/IEEE 42010 | Architecture description | `docs/architecture/architecture-description.md`; `docs/adr/` |
| ISO/IEC 25010 | Quality model | `docs/architecture/quality-attributes.md` (QS-01…QS-08); tests; SLOs |
| ISO/IEC/IEEE 15288 / 12207 | Life-cycle processes | `docs/lifecycle/software-lifecycle.md` |
| ISO/IEC 27001 / 27002 | ISMS controls | `docs/security/` |
| Twelve-Factor App | Cloud methodology | `docs/architecture/twelve-factor.md` |
| OpenAPI 3.1 / AsyncAPI 2.6 / CloudEvents 1.0 | Contracts & events | `contracts/` |
| RFC 9457 | Error format | `Problem.java` in every service |
| ISA-95 / IEC 62264 | Enterprise–control integration (levels 3/4) | `docs/domain/domain-standards.md` level mapping |
| OPC UA / IEC 62541 | Industrial interoperability | Boundary: machine data integrates via OPC UA at the edge |
| ISO 22400 | Manufacturing operations KPIs | OEE and KPI evaluator posture |
| IEC 63278 (AAS) | Asset Administration Shell | Equipment digital-twin posture |
| ISO 9001 | Quality management | Quality gates in the life cycle |
