if [ $# -ne 2 ] && [ $# -ne 3 ]; then
    echo "usage: user pass [cp]"
    exit 1
fi

user="$1"
pass="$2"
cp="$3"

server="http://localhost:8080"
auth="X-Authorization: $user:$pass"
content_type="Content-Type: application/json"

if [ -z "$cp" ]
  then
    curl -X GET -H "$auth" "$server/cp"
  else
    curl -X GET -H "$auth" "$server/cp/$cp"
fi
echo
