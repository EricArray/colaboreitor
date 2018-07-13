if [ $# -ne 4 ]; then
    echo "usage: user pass incidente reaccion"
    exit 1
fi

user="$1"
pass="$2"
incidente="$3"
reaccion="$4"

server="http://localhost:8080"
auth="X-Authorization: $user:$pass"
content_type="Content-Type: application/json"

data="{ \"incidente\":\"$incidente\", \"reaccion\": $reaccion }"

curl -X POST -H "$auth" -H "$content_type" --data "$data" "$server/reaccion"
echo
