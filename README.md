# Safe Work API (Quarkus)

API RESTful desenvolvida em Java com Quarkus para o projeto **Safe Work**, solução focada em reduzir o no-show e melhorar o bem-estar dos times por meio de check-ins regulares, indicadores de saúde do time e recomendações de ações.

A API expõe endpoints para autenticação, gestão de times, registro de check-ins e consulta de indicadores consolidados, sendo consumida por um front-end web.

---

## ✅ Tecnologias Utilizadas

- **Linguagem:** Java 17
- **Framework:** Quarkus 3.15.1
- **Banco de Dados:** H2 em memória
- **JPA / ORM:** Hibernate ORM
- **Validação:** Bean Validation (Hibernate Validator)
- **Documentação:** OpenAPI 3 + Swagger UI
- **Build:** Maven
- **Deploy:** Render (Docker)

---

## 🌐 URL de Produção

- **Base da API:**  
  `https://safework-quarkus-api.onrender.com/api`

- **Swagger UI (documentação interativa):**  
  `https://safework-quarkus-api.onrender.com/q/swagger-ui`

---

## 🧱 Arquitetura (camadas)

Pacote base: `com.safework`

- `domain.model` – Entidades de domínio (Usuario, Time, Checkin, Recomendacao, AcaoAplicada, IndicadorTimeDTO).
- `infra.repo` – Repositórios JPA responsáveis pelo acesso ao banco.
- `domain.service` – Regras de negócio (cálculo de indicadores, validações, orquestração de repositórios).
- `web.resource` – Endpoints REST expostos para o front-end.
- `web.exception` – Tratamento de exceções de negócio e erros de validação.

---

## 🔌 Endpoints Principais

### Auth Resource

- `POST /api/auth/login`  
  Recebe credenciais e realiza autenticação simples do usuário, retornando os dados básicos para o front.

### Checkin Resource

- `GET /api/checkins`  
  Lista check-ins registrados pelos colaboradores, com informações de humor, data, time etc.

- `POST /api/checkins`  
  Registra um novo check-in do colaborador, incluindo nível de bem-estar e comentários.

### Time Resource

- `GET /api/teams`  
  Lista todos os times cadastrados e seus líderes.

- `POST /api/teams`  
  Cadastra um novo time.

### Indicador Resource

- `GET /api/indicadores/teams/{timeId}`  
  Retorna indicadores consolidados de bem-estar para o time (média de humor, tendência, alertas, etc.).

---

## ⚙️ Como rodar localmente (dev)

Pré-requisitos:

- Java 17
- Maven 3.8+
- (Opcional) Docker, se quiser rodar via container

2. Rodar em modo dev (Quarkus)
mvn quarkus:dev

A API ficará acessível em:
```
http://localhost:8080
```

Swagger:
```
http://localhost:8080/q/swagger-ui
```

O banco H2 roda em memória e é configurado no application.properties.

🐋 Rodando via Docker (local)

Dentro da pasta `safework-quarkus`:

### Build da imagem

```bash
docker build -t safework-quarkus-api .
```

Subir o container
```
docker run -p 8080:8080 safework-quarkus-api
```

🌍 Deploy na Render

`O deploy é realizado na plataforma Render utilizando um Dockerfile multi-stage presente na raiz do módulo safework-quarkus.`

Configuração principal do serviço:

```Runtime: Docker

Root Directory: safework-quarkus

Porta: 8080 (detectada automaticamente pelo Render)

Deploy automático a cada git push na branch main.

URL pública da API:
https://safework-quarkus-api.onrender.com


```
