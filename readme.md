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

**Caso de Uso:** Cadastro de Idade para Validação de Maioridade Simples  
**Regra de Negócio:** O sistema só deve permitir o cadastro de usuários que tenham entre **18 e 120 anos** (inclusive).

### 🔍 Mapeamento das Fronteiras
*   **Limite Mínimo (Mín):** 18
*   **Limite Máximo (Máx):** 120

### 🧪 Matriz de Cenários e Casos de Teste

| ID | Cenário de Entrada | Valor Testado | Tipo de Limite | Comportamento Esperado | Status Esperado |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **01** | Muito abaixo do limite | `0` | Fora do limite inferior | Mensagem de erro: "Idade inválida" | **Reprovado / Bloqueado** |
| **02** | Imediatamente antes do limite | `17` | Limite inferior externo | Mensagem de erro: "Apenas maiores de 18 anos" | **Reprovado / Bloqueado** |
| **03** | **Exatamente no limite mínimo** | `18` | **Limite inferior interno** | Cadastro realizado com sucesso | **Aprovado / Permitido** |
| **04** | Imediatamente após o limite | `19` | Limite inferior interno + 1 | Cadastro realizado com sucesso | **Aprovado / Permitido** |
| **05** | Valor nominal/médio | `45` | Dentro do escopo válido | Cadastro realizado com sucesso | **Aprovado / Permitido** |
| **06** | Imediatamente antes do máximo | `119` | Limite superior interno - 1 | Cadastro realizado com sucesso | **Aprovado / Permitido** |
| **07** | **Exatamente no limite máximo** | `120` | **Limite superior interno** | Cadastro realizado com sucesso | **Aprovado / Permitido** |
| **08** | Imediatamente além do máximo | `121` | Limite superior externo | Mensagem de erro: "Idade fora do limite" | **Reprovado / Bloqueado** |
| **09** | Muito acima do limite | `200` | Fora do limite superior | Mensagem de erro: "Idade fora do limite" | **Reprovado / Bloqueado** |

---

## 💡 Outros Exemplos Comuns de Limites

*   **Campos de Texto (Strings):** Se um campo de "Nome" aceita de **3 a 50 caracteres**, os testes limite devem incluir strings com exatamente 2, 3, 4, 49, 50 e 51 caracteres.
*   **Transações Financeiras:** Se o limite de transferência diária via Pix é de **R\$ 5.000,00**, os limites são R\$ 4.999,99 (passa), R\$ 5.000,00 (passa) e R\$ 5.000,01 (bloqueia).
*   **Carrinho de Compras:** Se um cupom dá desconto para compras *acima* de R\$ 100,00, o valor R\$ 100,00 não recebe o desconto, mas R\$ 100,01 recebe.


## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
