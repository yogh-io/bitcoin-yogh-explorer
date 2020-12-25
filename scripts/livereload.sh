cd ..

echo "Waiting for file changes.. $(pwd)"
ulimit -n 16384

while true; do
  # Entr quits when the directory/file structure changes, so we can sneakishly trigger a license update build for new files (if indicated through --invasive)
  if [ "$1" = "--invasive" ]; then
    sh scripts/update_license.sh ..
  fi

  #find */src -regextype posix-egrep -regex ".*View.*|.*Component.*|.*scss$" | entr -d sh scripts/reload.sh target/gwt/launcherDir/reload
  ag -l | entr -d sh scripts/reload.sh target/gwt/launcherDir/reload
done
