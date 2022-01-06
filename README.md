1. RUN mvn clean install
2. inside target run java -jar rockpaperscissor-0.0.1-SNAPSHOT.jar 
3. default server is 9090

Important links

1. http://localhost:9090/start 

Response

{
"token": "MTY0MTUwNDUxNjIxOQ==",
"message": "READY"
}

2. http://localhost:9090/v1/MTY0MTUwNDUxNjIxOQ==/PAPER

Response

{
"playerMove": "PAPER",
"serverMove": "ROCK",
"token": "MTY0MTUwNDUxNjIxOQ==",
"serverScore": 0,
"playerScore": 1,
"winner": null
}

3. for db

http://localhost:9090/h2-console

no password
