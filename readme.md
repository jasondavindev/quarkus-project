# Projeto final (Disciplina Lógica de Programação II)
## Quarkus
*A Kubernetes Native Java stack tailored for OpenJDK HotSpot and GraalVM, crafted from the best of breed Java libraries and standards.*

## Projeto
API Rest utilizando framework Java [Quarkus](https://quarkus.io/). A aplicação permite a submissão de scripts Python para resolução de determinados problemas computacionais. Quando submetido, o script é executado e avaliado pela aplicação, validando se a saída é idêntica à resposta esperada, que está localizada no diretório ```app/problems/expecteds```.

## Requisitos
- JDK >= 1.8
- Docker & Docker-compose >= 1.23.1

## Executando

1. Baixe todos pacotes do Maven
  ...

2. Navegue até a pasta do app e builde o script shell

```
cd app
source dev.sh
```

3. Builde a imagem da aplicação (você pode pular para etapa 4 direto)

```
dkbuild
```

4. Suba o container da aplicação

```
dkupa # para anexar logs no console
dkupd # sobe o container e desbloqueia o console
```

A aplicação estará rodando na porta ```3000```

## Endpoints
- **POST /maratona**: inserir nova solução
```
curl -X POST http://localhost:3000/maratona -H "Content-Type: application/json" -d '
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
