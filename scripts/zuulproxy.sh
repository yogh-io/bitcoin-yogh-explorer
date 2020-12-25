conf=$(pwd)/conf/gateway-service.yml

cd ..
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.config.additional-location=$conf --server.port=8080" -pl :yogh-explorer-gateway
