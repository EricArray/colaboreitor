if [ $# -ne 4 ]; then
    echo "usage: user pass cp localidad"
    exit 1
fi

user="$1"
pass="$2"
cp="$3"
localidad="$4"

server="http://localhost:8080"
auth="X-Authorization: $user:$pass"
content_type="Content-Type: application/json"

data="{ \"cp\":\"$cp\", \"localidad\":\"$localidad\", \"lat\":\"1.0\", \"lng\":\"1.0\" }"

curl -X POST -H "$auth" -H "$content_type" --data "$data" "$server/cp"
echo
