if [ $# -ne 5 ]; then
    echo "usage: user pass nombre cp dni"
    exit 1
fi

user="$1"
pass="$2"
nombre="$3"
cp="$4"
dni="$5"

server="http://localhost:8080"
#auth="X-Authorization: user:pass"
content_type="Content-Type: application/json"

data="{ \"username\":\"$user\", \"password\": \"$pass\", \"nombre\":\"$nombre\", \"cp\":\"$cp\", \"dni\": \"$dni\", \"lat\":\"1.0\", \"lng\":\"1.0\" }"

curl -X POST -H "$content_type" --data "$data" "$server/cuenta"
echo
