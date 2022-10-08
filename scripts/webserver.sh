cd ..

mkdir -p target/gwt/launcherDir > /dev/null

mvn jetty:run -Djetty.http.port=8081 -pl :bitcoin-explorer-server -Denv=dev
