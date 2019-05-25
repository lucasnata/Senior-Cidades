### Senior-Cidades

POST http://localhost:8080/cidades/importcsv
<br>Recebe: MultipartFile

GET http://localhost:8080/cidades
<br>Retorna: JSON Todas as cidades

GET http://localhost:8080/cidades/{ibgeId}
<br>Retorna: JSON Cidade por IbgeId

GET http://localhost:8080/estado/capitais
Retorna: JSON Capitais de Cada Estado

GET http://localhost:8080/estado/maior_menor
<br>Retorna: JSON Estado e Quantidade de Cidades

GET http://localhost:8080/estado/numero_cidades
<br>Retorna: JSON Numero de Cidades de Cada Estado

GET http://localhost:8080/estado/{uf}
<br>Retorno: JSON Cidades do Estado Solicitado

POST http://localhost:8080/cidades
<br>Retorno: HTTP Code 201