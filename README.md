# Distributed tracing samples

```
./build.sh
docker-compose up --build

curl localhost:8080/api/v1/sample -H "X-client-trace-id: foo" -X POST
```

Observe Zipkin at localhost:9411
