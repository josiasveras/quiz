# 🧠 Quiz App (em construção...)

Aplicação **backend** desenvolvida em **Groovy** com **Spring Boot**, criada para gerenciar perguntas e respostas em formato de quiz.
O projeto está em fase de desenvolvimento inicial e **atualmente utiliza dados mockados**, sem integração real com banco de dados.

---

## 💻 Tecnologias Principais

| Categoria          | Tecnologias       |
|--------------------|-------------------|
| **Linguagem**      | Groovy 4.0.14     |
| **Framework**      | Spring Boot 3.x   |
| **Banco de Dados** | MySQL             |
| **Build Tool**     | Gradle            |
| **Testes**         | Spock Framework   |
| **Documentação**   | OpenAPI / Swagger |
| **Client API**     | Postman           |
| **IDE**            | IntelliJ IDEA     |

---

## ⚙️ Funcionalidades Atuais

* 📋 **Listagem de perguntas**
  As perguntas e respostas são carregadas a partir de Banco de Dados.

* ✅ **Validação de respostas**
  O sistema compara a resposta enviada com o valor correto definido no Banco de Dados.

* 🧩 **Arquitetura organizada**
  Estruturada em camadas de *Controller*, *DTO*, *Model*, *Repository*,  e *Service*.

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
│   │   │       │   ├── AnswerController.groovy            # Endpoints REST
│   │   │       │   └── QuestionController.groovy          # Endpoints REST
│   │   │       ├── 📁 dto
│   │   │       │   ├── AnswerRequest.groovy               # Request model (POST /answers)
│   │   │       │   ├── AnswerResponse.groovy              # Response model (POST /answers)
│   │   │       │   ├── QuestionRequest.groovy             # Response model (POST /questions)
│   │   │       │   └── QuestionResponse.groovy            # Response model (GET POST /questions)
│   │   │       ├── 📁 exception
│   │   │       │   ├── GlobalExceptionHandler.groovy      # Classe global tratamento centralizado de erros e exceções
│   │   │       │   └── QuestionNotFoundException.groovy
│   │   │       ├── 📁 model
│   │   │       │   ├── Answer.groovy                      # Entidade para Pergunta
│   │   │       │   └── Question.groovy                    # Entidade para Questão
│   │   │       ├── 📁 repository
│   │   │       │   ├── AnswerRepository.groovy            # Interface para Persistência em Banco de Dados
│   │   │       │   └── QuestionRepository.groovy          # Interface para Persistência em Banco de Dados
│   │   │       ├── 📁 service
│   │   │       │   ├── AnswerService.groovy               # Lógica de negócio
│   │   │       │   └── QuestionService.groovy             # Lógica de negócio
│   │   │       └── 📄 QuizApiApplication.groovy           # Classe principal
│   │   └── 📁 resources
│   │       └── application.yml                            # Configurações da aplicação
│   └── 📁 test
│       └── 📁 groovy/com/app/quiz
│           ├── 📁 controller
│           │   ├── AnswerControllerSpec.groovy            # Testes dos endpoints
│           │   └── QuestionControllerSpec.groovy          # Testes dos endpoints
│           ├── 📁 service
│           │   ├── AnswerServiceSpec.groovy               # Testes regras de negócio
│           │   └── QuestionServiceSpec.groovy             # Testes regras de negócio
│           └── 📁 utils/TestUtils.groovy                  # Utilitários de teste
├── 📄 .gitignore
├── 📄 build.gradle
├── 📄 gradlew
├── 📄 gradlew.bat
├── 📄 README.md
└── 📄 settings.gradle

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

| Método | Endpoint              | Descrição                                         |
|--------| --------------------- |---------------------------------------------------|
| `GET`  | `/quiz/question/{id}` | Retorna uma pergunta por ID do Banco de Dados     |
| `POST` | `/quiz/question`      | Cria uma pergunta no Banco de Dados               |
| `POST` | `/quiz/answer`        | Valida a resposta enviada com a do Banco de Dados |

---

## 🔄 Próximas Etapas

* [x] Integrar com banco de dados **MySQL**
* [x] Implementar camada de persistência (JPA / Hibernate)
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
