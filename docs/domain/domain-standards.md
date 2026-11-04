# Domain Standards — Manufacturing

Alignment mapping for this core. *Aligned to* throughout.

## Concept mapping

| Platform concept | Domain standard | Mapping |
|---|---|---|
| Lines, stock, work-order issues | ISA-95 / IEC 62264 (Level 3–4 boundary) | Contexts sit at MES/ERP boundary; Level 0–2 control stays in OT |
| Machine integration | OPC UA (IEC 62541) | Telemetry and commands integrate at the edge via OPC UA; not re-implemented here |
| Equipment identity | IEC 63278 Asset Administration Shell | Equipment registry follows AAS identification posture |
| KPIs | ISO 22400 | OEE evaluator definitions follow ISO 22400 KPI discipline |
| Quality | ISO 9001 | Quality rules and gates |

## Scope boundaries

No real-time machine control, no safety functions: this is the operations-data plane
around certified OT.
