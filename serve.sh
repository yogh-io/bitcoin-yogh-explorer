# Serve the entire project in a single tmux session. Has some dependencies on tools.
echo "Using root dir: $1"

# Check if the necessary tools are installed
command -v tmux >/dev/null 2>&1 || { echo >&2 "I require tmux but it's not installed.  Aborting."; exit 1; }
command -v entr >/dev/null 2>&1 || { echo >&2 "I require entr but it's not installed.  Aborting."; exit 1; }
command -v lsof >/dev/null 2>&1 || { echo >&2 "I require lsof but it's not installed.  Aborting."; exit 1; }

# Kill existing session of the same name, and sleep a while to let threads die. Do this before port checks.
tmux kill-session -t bitcoin-serve && { echo "Waiting for existing session to close"; sleep 2; }

# Check if 'lsof' is available, and if so do port checks
lsof -i:9876 >/dev/null 2>&1 && { echo "Need port 9876 to be free. (perhaps another tmux session is hogging it? Try 'tmux kill-server'.)"; exit 1; }
lsof -i:8080 >/dev/null 2>&1 && { echo "Need port 8080 to be free. (perhaps another tmux session is hogging it? Try 'tmux kill-server'.)"; exit 1; }
lsof -i:8081 >/dev/null 2>&1 && { echo "Need port 8081 to be free. (perhaps another tmux session is hogging it? Try 'tmux kill-server'.)"; exit 1; }

# Navigate to scripts, they work with relative paths
cd scripts

# Launch a tmux session
echo "Launching tmux session 'bitcoin-serve'"
tmux new-session -d -s bitcoin-serve ./codeserver.sh

# Let panes remain on exit, so that any crashes remain visible
tmux set-option remain-on-exit

tmux split-window -v -p 66 ./zuulproxy.sh
tmux split-window -v -t 0 ./webserver.sh $2
tmux split-window -v -t 0 ./bitcoind.sh
tmux split-window -h ./electr.sh

# Attach to the session
tmux -2 attach-session -d

###
# Note for prosperity: Exit tmux through 'ctrl + b, d'
###
