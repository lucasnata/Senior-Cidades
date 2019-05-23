### Senior-Cidades

POST http://localhost:8080/cidades/importcsv
Recebe: MultipartFile

GET http://localhost:8080/cidades
Retorna: JSON Todas as cidades

http://localhost:8080/cidades/{ibgeId}
Retorna: JSON Cidade por IbgeId

http://localhost:8080/estado/capitais
Retorna: JSON Capitais de Cada Estado

http://localhost:8080/estado/maior_menor
Retorna: JSON Estado e Quantidade de Cidades

http://localhost:8080/estado/numero_cidades
Retorna: JSON Numero de Cidades de Cada Estado

http://localhost:8080/estado/{uf}
Retorno: JSON Cidades do Estado Solicitado