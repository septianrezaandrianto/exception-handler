# exception-handler

This project using H2 database:
If you want to check the database,
run your service and open this url in browser 
http://localhost:{your-config-port}/h2-console -> http://localhost:8080/h2-console

## Create Api
curl --location --request POST 'http://localhost:8080/user/create' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name" : "Reza",
    "email" : "aaa@gmail.co.id",
    "phoneNumber" : "123456789012",
    "gender" : "F",
    "age" : 63,
    "address" : "Jogja"
}'

## Get List Data Api
curl --location --request GET 'http://localhost:8080/user/getUserList'

## Get Data By Name
curl --location --request GET 'http://localhost:8080/user/getUserByName/Reza'
