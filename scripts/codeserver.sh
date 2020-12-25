log=$(pwd)/conf/logback.xml

cd ..
echo "Starting in:"
pwd
mvn gwt:codeserver -pl :bitcoin-explorer-client -am -Dlogback.configurationFile=$log
