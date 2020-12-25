cd ..

mkdir -p target/gwt/launcherDir > /dev/null

while true; do

if [ "$1" = "reload" ]; then
  mvn jetty:run -Djetty.http.port=8081 -pl :bitcoin-explorer-server -Denv=dev-reload
else
  mvn jetty:run -Djetty.http.port=8081 -pl :bitcoin-explorer-server -Denv=dev
fi
sleep 1s

done
