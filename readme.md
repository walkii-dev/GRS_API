# GRS_API (API de Gerenciamento de Reservas de Salas)

![Java](https://shields.io)
![Spring](https://shields.io)
![Docker](https://shields.io)

Esta é uma API RESTful desenvolvida com **Spring Boot** que fornece operações completas de CRUD. O projeto conta com persistência de dados, cobertura de testes unitários e está totalmente containerizado com Docker para facilitar a execução.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17 / 21** (ou a versão que você usou)
- **Spring Boot 3.x** (Web, Data JPA, Validation)
- **Banco de Dados:** PostgreSQL / MySQL / H2
- **Testes:** JUnit 5 & Mockito
- **Containerização:** Docker & Docker Compose

---

## 🚀 Como Executar o Projeto

Você pode rodar a aplicação localmente de duas formas: utilizando o Docker (recomendado) ou direto pelo Maven.

### Pré-requisitos
Antes de começar, você vai precisar ter instalado em sua máquina:
- [Git](https://git-scm.com)
- [Docker](https://docker.com) e Docker Compose (se optar por rodar via container)
- [JDK 17+](https://adoptium.net) e [Maven](https://apache.org) (se optar por rodar localmente)

### 🐋 Opção 1: Rodando com Docker (Mais rápido)

1. Clone o repositório:
   ```bash
   git clone https://github.com
   cd seu-repositorio
   ```

2. Suba os containers do banco de dados e da API:
   ```bash
   docker-compose up -d --build
   ```
   *A API estará disponível em `http://localhost:8080`.*

### 💻 Opção 2: Rodando Localmente (Sem Docker para a API)

1. Clone o repositório e navegue até a pasta.
2. Certifique-se de configurar as credenciais do seu banco de dados no arquivo `src/main/resources/application.properties`.
3. Instale as dependências e rode a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```

---

## 🧪 Como Rodar os Testes

O projeto possui **testes unitários** implementados com JUnit 5 e Mockito para garantir a consistência das regras de negócio.

Para executar a suíte de testes, rode o comando:
```bash
./mvnw test
```

Ao final da execução, o Maven exibirá o sumário com o total de testes executados e o status de sucesso.

---

## 🔌 Endpoints da API

Abaixo estão as principais rotas configuradas para os CRUDs (exemplo com uma entidade "Users"):

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| **GET** | `/api/users` | Lista todos os registros |
| **GET** | `/api/users/{id}` | Busca um registro por ID |
| **POST** | `/api/users` | Cria um novo registro |
| **PUT** | `/api/users/{id}` | Atualiza um registro existente |
| **DELETE** | `/api/users/{id}` | Remove um registro do sistema |

*(Opcional: Se você usou Swagger/OpenAPI, adicione: "A documentação interativa da API pode ser acessada em `http://localhost:8080/swagger-ui.html` com o app rodando".)*

---

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
