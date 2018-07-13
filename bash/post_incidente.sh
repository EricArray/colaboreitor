if [ $# -ne 5 ]; then
    echo "usage: user pass nombre cp fotos"
    echo "  fotos: ej. '[\"https://imgur.com/iBRE7Dj\"]'"
    exit 1
fi

user="$1"
pass="$2"
nombre="$3"
cp="$4"
fotos="$5" # ej: '["https://imgur.com/iBRE7Dj"]'

server="http://localhost:8080"
auth="X-Authorization: $user:$pass"
content_type="Content-Type: application/json"

data="{ \"nombre\":\"$nombre\", \"cp\": \"$cp\", \"lat\":\"1.0\", \"lng\":\"1.0\", \"fotos\": $fotos }"

curl -X POST -H "$auth" -H "$content_type" --data "$data" "$server/incidente"
echo
