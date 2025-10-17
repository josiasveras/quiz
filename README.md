# 🧠 Quiz App (em construção...)

Aplicação **backend** desenvolvida em **Groovy** com **Spring Boot**, criada para gerenciar perguntas e respostas em formato de quiz.
O projeto está em fase de desenvolvimento inicial e **atualmente utiliza dados mockados**, sem integração real com banco de dados.

---

## 💻 Tecnologias Principais

| Categoria                   | Tecnologias                             |
| --------------------------- | --------------------------------------- |
| **Linguagem**               | Groovy 4.0.14                           |
| **Framework**               | Spring Boot 3.x                         |
| **Banco de Dados (futuro)** | MySQL *(integração em desenvolvimento)* |
| **Build Tool**              | Gradle                                  |
| **Testes**                  | Spock Framework                         |
| **Documentação**            | OpenAPI / Swagger                       |
| **Client API**              | Postman                                 |
| **IDE**                     | IntelliJ IDEA                           |

---

## ⚙️ Funcionalidades Atuais

* 📋 **Listagem de perguntas (mockadas)**
  As perguntas e respostas são carregadas a partir de dados simulados.

* ✅ **Validação de respostas (mock)**
  O sistema compara a resposta enviada com o valor correto definido no mock.

* 🧩 **Arquitetura organizada**
  Estruturada em camadas de *Controller*, *Service*, *DTO* e *Model*.

* 🧪 **Testes automatizados**
  Cobertura das rotas principais e regras de negócio utilizando o *Spock Framework*.

---

## 📁 Estrutura do Projeto

```
📦 quiz-app
├── 📁 src
│   ├── 📁 main
│   │   ├── 📁 groovy
│   │   │   └── 📁 com/app/quiz
│   │   │       ├── 📁 controller
│   │   │       │   └── QuestionController.groovy          # Endpoints REST
│   │   │       ├── 📁 dto
│   │   │       │   ├── AnswerRequest.groovy               # Request model (POST /answer)
│   │   │       │   ├── AnswerResponse.groovy              # Response model (POST /answer)
│   │   │       │   └── QuestionResponse.groovy            # Response model (GET /question/{id})
│   │   │       ├── 📁 model
│   │   │       │   └── Question.groovy                    # Entidade base (mock)
│   │   │       ├── 📁 service
│   │   │       │   └── QuestionService.groovy             # Lógica de negócio e validação de respostas
│   │   │       └── 📄 QuizApiApplication.groovy            # Classe principal
│   │   └── 📁 resources
│   │       └── application.yml                            # Configurações da aplicação
│   └── 📁 test
│       └── 📁 groovy/com/app/quiz
│           ├── 📁 controller/QuestionControllerSpec.groovy # Testes dos endpoints
│           └── 📁 utils/TestUtils.groovy                   # Utilitários de teste
├── 📄 build.gradle
├── 📄 settings.gradle
├── 📄 .gitignore
├── 📄 gradlew / gradlew.bat
└── 📄 README.md
```

---

## 🚀 Como Executar o Projeto

### 1️⃣ Clonar o repositório

```bash
git clone https://github.com/josiasveras/quiz.git
cd quiz-app
```

### 2️⃣ Executar com Gradle

```bash
./gradlew bootRun
```

A aplicação iniciará em:
👉 [http://localhost:8080/quiz](http://localhost:8080/quiz)

---

## 🧪 Testes

Para executar todos os testes automatizados:

```bash
./gradlew test
```

O relatório será gerado em:

```
build/reports/tests/test/index.html
```

---

## 🧭 Endpoints Principais

| Método | Endpoint              | Descrição                            |
| ------ | --------------------- | ------------------------------------ |
| `GET`  | `/quiz/question/{id}` | Retorna uma pergunta mockada pelo ID |
| `POST` | `/quiz/answer`        | Valida a resposta enviada (mock)     |

---

## 🔄 Próximas Etapas

* [ ] Integrar com banco de dados **MySQL**
* [ ] Implementar camada de persistência (JPA / Hibernate)
* [ ] Adicionar autenticação com **JWT**
* [ ] Criar endpoints para CRUD completo de perguntas
* [ ] Implementar CI/CD e badges de build
* [ ] Documentação da API

---

## 🧑‍💻 Autor

**Jorzias Veras**
Desenvolvedor Backend | Estudante de Software Development
📧 Contato: josias_veras@hotmail.com

---
