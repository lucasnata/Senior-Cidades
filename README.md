#### Senior-Cidades [![CircleCI](https://circleci.com/gh/lucasnata/Senior-Cidades.svg?style=svg)](https://circleci.com/gh/lucasnata/Senior-Cidades)
##### Desafio de Desenvolvimento - Senior Sistemas
###### XPTO Systems - Cidades CSV
#
Link Swagger (Mapeia URLs com exemplos de chamadas)
http://localhost:8080/swagger-ui.html

Banco Mysql para rodar com a aplicação no Docker:
./docker/docker-compose.yml

#### Lista de URLs
|Method|URL|Requisitos|
|------|---|----------|
|POST|http://localhost:8080/cidades/importcsv|1. Ler o arquivo CSV das cidades para a base de dados;|
|GET|http://localhost:8080/estado/capitais|2. Retornar somente as cidades que são capitais ordenadas por nome;|
|GET|http://localhost:8080/estado/maior_menor|3. Retornar o nome do estado com a maior e menor quantidade de cidades e a quantidade de cidades;||
|GET|http://localhost:8080/estado/numero_cidades|4. Retornar a quantidade de cidades por estado;|
|GET|http://localhost:8080/cidades/{ibgeId}|5. Obter os dados da cidade informando o id do IBGE;|
|GET|http://localhost:8080/estado/{uf}|6. Retornar o nome das cidades baseado em um estado selecionado;|
|POST|http://localhost:8080/cidades|7. Permitir adicionar uma nova Cidade;|
|DELETE|http://localhost:8080/cidades|8. Permitir deletar uma cidade;|
|GET|http://localhost:8080/cidades/busca?coluna=nomeColuna&busca=valorColuna|9. Permitir selecionar uma coluna (do CSV) e através dela entrar com uma string para filtrar. retornar assim todos os objetos que contenham tal string;|
|GET|http://localhost:8080/cidades/total_coluna/{columName}|10. Retornar a quantidade de registro baseado em uma coluna. Não deve contar itens iguais;|
|GET|http://localhost:8080/cidades/total|11. Retornar a quantidade de registros total;|
|GET|http://localhost:8080/cidades/distancias|12. Dentre todas as cidades, obter as duas cidades mais distantes uma da outra com base na localização (distância em KM em linha reta);|