cd ../..

cp yogh-explorer-server/target/bitcoin-explorer-server-0.0.1-SNAPSHOT.war docker/explorer-server/explorer.war

cd docker/explorer-server
docker build -t explorer-server:latest .
