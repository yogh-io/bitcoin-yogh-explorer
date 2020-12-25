# Build artifacts
cd ..
if [ "$1" = "rebuild" ]; then
mvn clean
mvn clean install
fi
cd docker

# Build images
cd explorer-server
sh build.sh
cd ../apache
sh build.sh
cd ..

# Stop existing
sh stop.sh

# Deploy containers
cd explorer-server
sh deploy.sh
cd ../apache
sh deploy.sh
