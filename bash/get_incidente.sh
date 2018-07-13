if [ $# -ne 2 ] && [ $# -ne 3 ]; then
    echo "usage: user pass [id]"
    exit 1
fi

user="$1"
pass="$2"
id="$3"

server="http://localhost:8080"
auth="X-Authorization: $user:$pass"
content_type="Content-Type: application/json"

if [ -z "$id" ]
  then
    curl -X GET -H "$auth" "$server/incidente"
  else
    curl -X GET -H "$auth" "$server/incidente/$id"
fi
echo
