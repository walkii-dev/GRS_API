# GRS_API (API de Gerenciamento de Reservas de Salas)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%234479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-%23C71A36.svg?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit5-%23f5f5f5.svg?style=for-the-badge&logo=junit5&logoColor=dc524a)


Este projeto tem como objetivo fornecer uma API simples para gerenciamento de reserva de salas, utilizando usuários 
reais para solicitar horários em salas em diversas datas, a fim de executar as mais diversas atividades.

---

## 🛠️ Tecnologias Utilizadas

- ☕ **Java 21** (Utilizando a versão JDK Temurin 21.0.12) 
- 🍃 **Spring Boot 4.0.8** (+ dependências )
- 💾 **Banco de Dados:** MySQL
- 🧪 **Testes:** JUnit 5 | Mockito
- 🐋 **Containerização:** Docker & Docker Compose

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

<!-- uma coisa importante é que eu posso fazer validação de domínio das duas formas. -->

*(Opcional: Se você usou Swagger/OpenAPI, adicione: "A documentação interativa da API pode ser acessada em `http://localhost:8080/swagger-ui.html` com o app rodando".)*

# 📋 Documentação de Exemplos Limite (Boundary Values)

**Caso de Uso:** Verificação de Sobreposição de Reserva  
**Regra de Negócio:** O sistema só deve permitir uma reserva nova ser adicionada caso ela não esteja sobrepondo 
(ou 'invadindo') o horário de uma outra, seja a nova sobrepondo no início ou o final da anterior.

### 🔍 Mapeamento das Fronteiras

Dado uma Reserva qualquer que **já esteja no banco de dados**, que tenha dados **válidos de usuário e sala**
(existentes no banco de dados) e que esteja **ativa**,
tendo os horários:

*   **Início da Reserva:** 10:00
*   **Final da Reserva:** 13:00

### 🧪 Matriz de Cenários e Casos de Teste

| ID | Cenário de Entrada | Valor Testado (Início/Fim) | Status Code Esperado | Mensagem esperada | Tipo de Limite |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **01** | Data de fim antecede a de início | `14:00/10:00` | _400_ | --- | **Reprovada / Bloqueada** |
| **02** | Antecedendo com tempo o horário anterior | `07:00/09:00` | _201_ | --- | **Aprovada / Permitida** |
| **03** | **Antecedendo integralmente o horário anterior** | `08:00/10:00` | _**201**_ | --- | **Aprovada / Permitida** |
| **04** | Sobrepondo o início do horário anterior | `09:00/11:00` | _409_ | --- | **Reprovada / Bloqueada** |
| **05** | Dentro do intervalo do horário anterior | `11:00/12:00` | _409_ | --- | **Reprovada / Bloqueada** |
| **06** | Sobrepondo completamente o horário anterior | `10:00/13:00` | _409_ | --- | **Reprovada / Bloqueada** |
| **07** | Sobrepondo o final do horário anterior | `12:00/14:00` | _409_ | --- | **Reprovada / Bloqueada** |
| **08** | **Sucedendo integralmente o horário anterior** | `13:00/15:00` | _**201**_ | --- | **Aprovada / Permitida** |
| **09** | Sucedendo com tempo a frente do horário anterior | `14:00/16:00` | _201_ | --- | **Aprovada / Permitida** |

---

## 💡 Outros Exemplos Comuns de Limites

*   **Nomes das Salas (String):** Por mais que os nomes das salas pareçam números, a numeração foi elaborada para que siga um padrão, como "001", "004" em diante.
*   **Nomes de usuários:** ao cadastrar um usuário, ele deverá ter uma espécie de apelido , o qual tendo entre 8 e 24 caracteres.


## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
