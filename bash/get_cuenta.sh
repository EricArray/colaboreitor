if [ $# -ne 2 ] then
    echo "usage: user pass"
    exit 1
fi

user="$1"
pass="$2"

server="http://localhost:8080"
auth="X-Authorization: $user:$pass"
content_type="Content-Type: application/json"

curl -X GET -H "$auth" "$server/cuenta"
echo
