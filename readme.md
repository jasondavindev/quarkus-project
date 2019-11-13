# Projeto final (Disciplina Lógica de Programação II)
API Rest utilizando framework Java [Quarkus](https://quarkus.io/). A aplicação permite você submeter scripts na linguagem Python e é retornado uma resposta dizendo se houve êxito ou não, na execução.

## Fluxo
Os inputs dos scripts em Python estão localizados na pasta ```app/problems/inputs```, e as saídas esperadas estão na pasta ```app/problems/expecteds```. Quando um script Python é executado, a aplicação Java gera arquivos contendo o output da execução, que são gravados na pasta ```app/problems/outputs```. Ao final, é feito um de-para entre o arquivo esperado e o arquivo de saída.

## Endpoints
**POST /maratona**: inserir nova solução
```
curl -X POST http://localhost:3000/maratona -d '
{
  "sourcecode": "cHJpbnQoaW50KGlucHV0KCkpICogMik=",
  "filename": "script.py",
  "problem": "a"
}'

# resposta
[
  {
    "sourcecode": "cHJpbnQoaW50KGlucHV0KCkpICogMik=",
    "filename": "script.py",
    "timestamp": 1573663020520,
    "problem": "a",
    "status": "SUCCESS"
  }
]
```

- **GET /maratona**: listar todas soluções
```
curl http://localhost:3000/maratona
# resposta
[
  {
    "sourcecode": "cHJpbnQoaW50KGlucHV0KCkpICogMik=",
    "filename": "script.py",
    "timestamp": 1573663020520,
    "problem": "a",
    "status": "SUCCESS"
  }
]
```

- **GET /maratona/status/{success|fail}**: listar soluções com o status passado
- **GET /maratona/problem/{a-z}**: listar soluções com o ID do problema passado
- **GET /maratona/date/{timestamp}**: listar soluções com timestamp maior ou igual ao timestamp passado

## Requisitos
- JDK >= 1.8
- Docker & Docker-compose >= 1.23.1

## Executando

1. Baixe todos pacotes do Maven
  ...

2. Navegue até a pasta do app e build o script shell

```
cd app
source dev.sh
```

3. Build a imagem da aplicação (você pode pular para etapa 4 direto)

```
dkbuild
```

4. Suba o container da aplicação

```
dkupa # para anexar logs no console
dkupd # sobe o container e desbloqueia o console
```

A aplicação estará rodando na porta ```3000```
