# OEE Evaluator (serverless function)

Quarkus Funqy function on Knative (scale to zero). Compares unplanned stops and OEE against line targets (ISO 22400 KPI discipline); one finding per breach.

Invoke: `POST /oeeEvaluator` with the KPI payload.
