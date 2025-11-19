# PathCat - Plataforma de Upskilling/Reskilling

## 📋 Descrição

PathCat é uma API RESTful desenvolvida em Java com Spring Boot para gerenciamento de uma plataforma de Upskilling/Reskilling. A aplicação permite o cadastro de usuários, trilhas de aprendizado, competências e matrículas, facilitando o desenvolvimento profissional contínuo.

## 🎯 Problema que Resolve

No cenário atual de rápida evolução tecnológica, profissionais precisam constantemente atualizar suas habilidades (upskilling) ou mudar de área (reskilling). O PathCat oferece:

- Gestão centralizada de usuários e suas trilhas de desenvolvimento
- Organização de competências técnicas e comportamentais
- Acompanhamento de matrículas e progresso em trilhas
- Categorização por níveis de carreira e dificuldade

## 🚀 Tecnologias Utilizadas

- **Java 17** - Linguagem de programação (LTS Version)
- **Spring Boot 3.2.0** - Framework para aplicações Java
- **Spring Data JPA** - Persistência de dados
- **H2 Database** - Banco de dados em memória (perfil de desenvolvimento)
- **MySQL 8.0** - Banco de dados relacional (perfil de produção)
- **Spring Validation** - Validação de dados com Bean Validation
- **Lombok** - Redução de código boilerplate (getters, setters, construtores)
- **Maven** - Gerenciamento de dependências
- **Docker Compose** - Orquestração de containers para MySQL

### Dependências Principais

```xml
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-validation
- h2 (runtime - perfil desenvolvimento)
- mysql-connector-j (runtime - perfil produção)
- lombok (optional)
- spring-boot-starter-test
```

## 📦 Estrutura do Projeto

```
br.com.pathcat/
├── domain/              # Entidades JPA e Enums
│   ├── Usuario.java
│   ├── Trilha.java
│   ├── Competencia.java
│   ├── Matricula.java
│   ├── NivelCarreira.java
│   ├── NivelTrilha.java
│   ├── StatusMatricula.java
│   └── CategoriaCompetencia.java
├── dto/                 # Data Transfer Objects
│   ├── UsuarioRequestDTO.java
│   ├── UsuarioResponseDTO.java
│   ├── TrilhaRequestDTO.java
│   ├── TrilhaResponseDTO.java
│   ├── CompetenciaDTO.java
│   ├── MatriculaRequestDTO.java
│   └── MatriculaResponseDTO.java
├── repository/          # Interfaces JPA Repository
│   ├── UsuarioRepository.java
│   ├── TrilhaRepository.java
│   ├── CompetenciaRepository.java
│   └── MatriculaRepository.java
├── service/             # Lógica de negócio
│   ├── UsuarioService.java
│   ├── TrilhaService.java
│   ├── CompetenciaService.java
│   └── MatriculaService.java
├── controller/          # REST Controllers
│   ├── UsuarioController.java
│   ├── TrilhaController.java
│   ├── CompetenciaController.java
│   └── MatriculaController.java
├── exception/           # Tratamento de exceções
│   ├── GlobalExceptionHandler.java
│   ├── UsuarioNaoEncontradoException.java
│   ├── TrilhaNaoEncontradaException.java
│   ├── CompetenciaNaoEncontradaException.java
│   └── ErrorResponse.java
├── config/              # Configurações
│   └── DataSeeder.java
└── PathCatApplication.java
```

## 🔧 Configuração e Instalação

### Pré-requisitos

- Java 17 ou superior (Java 17 LTS recomendado)
- Maven 3.6 ou superior
- Docker e Docker Compose (opcional, apenas para perfil MySQL)

### Perfis de Configuração

A aplicação suporta dois perfis de execução:

#### 1. Perfil H2 (Desenvolvimento - Padrão)
- Banco de dados em memória
- Ideal para desenvolvimento rápido e testes
- Console H2 habilitado em `/h2-console`
- **Perfil ativado por padrão**

#### 2. Perfil MySQL (Produção)
- Banco de dados MySQL persistente
- Requer MySQL rodando (Docker ou instalação local)
- Ideal para ambientes de produção

### Como Executar

#### Opção 1: Com H2 (Desenvolvimento - Padrão)

1. **Clone o repositório:**
```bash
git clone https://github.com/M-Hisamoto/GS-java-PathCat.git
cd GS-java-PathCat
```

2. **Compile o projeto:**
```bash
mvn clean install
```

3. **Execute a aplicação:**
```bash
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

##### Console H2

Acesse o console do banco de dados H2 em: `http://localhost:8080/h2-console`

- **JDBC URL:** `jdbc:h2:mem:pathcatdb`
- **Username:** `sa`
- **Password:** (deixe em branco)

#### Opção 2: Com MySQL (Produção)

1. **Inicie o MySQL via Docker Compose:**
```bash
docker-compose up -d
```

Isso iniciará um container MySQL com as seguintes configurações:
- **Host:** localhost
- **Porta:** 3306
- **Database:** pathcatdb
- **Usuário:** pathcat
- **Senha:** pathcat123

2. **Execute a aplicação com perfil MySQL:**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```

Ou configure a propriedade no `application.properties`:
```properties
spring.profiles.active=mysql
```

3. **Para parar o MySQL:**
```bash
docker-compose down
```

4. **Para remover os dados persistidos:**
```bash
docker-compose down -v
```

### Alternativa: Executando o JAR

```bash
# Compilar
mvn clean package

# Executar com H2
java -jar target/pathcat-1.0.0.jar

# Executar com MySQL
java -jar target/pathcat-1.0.0.jar --spring.profiles.active=mysql
```

## 📚 Documentação da API

### Usuários

#### Criar Usuário
```bash
POST /api/usuarios
Content-Type: application/json

{
  "nome": "João Silva",
  "email": "joao.silva@example.com",
  "areaAtuacao": "Desenvolvimento",
  "nivelCarreira": "PLENO"
}
```

#### Listar Todos os Usuários
```bash
GET /api/usuarios
```

#### Buscar Usuário por ID
```bash
GET /api/usuarios/{id}
```

#### Atualizar Usuário
```bash
PUT /api/usuarios/{id}
Content-Type: application/json

{
  "nome": "João Silva",
  "email": "joao.silva@example.com",
  "areaAtuacao": "Desenvolvimento Full Stack",
  "nivelCarreira": "SENIOR"
}
```

#### Deletar Usuário
```bash
DELETE /api/usuarios/{id}
```

### Trilhas

#### Criar Trilha
```bash
POST /api/trilhas
Content-Type: application/json

{
  "nome": "Trilha DevOps",
  "descricao": "Aprenda DevOps do zero ao avançado",
  "nivel": "INTERMEDIARIO",
  "cargaHoraria": 50,
  "focoPrincipal": "DevOps",
  "competenciasIds": [1, 2]
}
```

#### Listar Todas as Trilhas
```bash
GET /api/trilhas
```

#### Buscar Trilha por ID
```bash
GET /api/trilhas/{id}
```

#### Atualizar Trilha
```bash
PUT /api/trilhas/{id}
Content-Type: application/json

{
  "nome": "Trilha DevOps Avançado",
  "descricao": "DevOps para profissionais experientes",
  "nivel": "AVANCADO",
  "cargaHoraria": 60,
  "focoPrincipal": "DevOps",
  "competenciasIds": [1, 2, 3]
}
```

#### Deletar Trilha
```bash
DELETE /api/trilhas/{id}
```

### Competências

#### Criar Competência
```bash
POST /api/competencias
Content-Type: application/json

{
  "nome": "Docker",
  "categoria": "TECNOLOGIA",
  "descricao": "Containerização de aplicações com Docker"
}
```

**Categorias válidas:** `TECNOLOGIA`, `HUMANA`, `GESTAO`

#### Listar Todas as Competências
```bash
GET /api/competencias
```

#### Buscar Competência por ID
```bash
GET /api/competencias/{id}
```

#### Atualizar Competência
```bash
PUT /api/competencias/{id}
Content-Type: application/json

{
  "nome": "Docker e Kubernetes",
  "categoria": "TECNOLOGIA",
  "descricao": "Containerização e orquestração de aplicações"
}
```

#### Deletar Competência
```bash
DELETE /api/competencias/{id}
```

**Resposta:** 204 No Content

### Matrículas

#### Criar Matrícula (Inscrever Usuário em Trilha)
```bash
POST /api/matriculas
Content-Type: application/json

{
  "usuarioId": 1,
  "trilhaId": 2
}
```

#### Listar Trilhas de um Usuário
```bash
GET /api/matriculas/usuarios/{usuarioId}/trilhas
```

#### Listar Usuários de uma Trilha
```bash
GET /api/matriculas/trilhas/{trilhaId}/usuarios
```

## 🎲 Dados de Exemplo (Seeds)

A aplicação vem pré-configurada com dados de exemplo:

### Usuários:
- Ana Silva (ana.silva@example.com) - Área: Dados, Nível: JUNIOR
- Bruno Costa (bruno.costa@example.com) - Área: IA, Nível: PLENO
- Carla Mendes (carla.mendes@example.com) - Área: Gestão, Nível: SENIOR

### Competências:
- Machine Learning (Tecnologia)
- Empatia (Humana)
- Python (Tecnologia)
- Comunicação Efetiva (Humana)
- Liderança (Gestão)

### Trilhas:
- Trilha Inteligência Artificial (Intermediário, 40h)
- Trilha Soft Skills Colaborativas (Iniciante, 20h)
- Trilha Liderança e Gestão (Avançado, 30h)

### Matrículas:
- Ana → Trilha Soft Skills
- Bruno → Trilha IA
- Carla → Trilha Liderança

## 📋 Validações

O sistema implementa validações automáticas com Bean Validation:

### Usuário
- **Nome**: Obrigatório e não pode ser vazio (`@NotBlank`)
- **Email**: Obrigatório, deve ser válido (`@Email`) e único no sistema
- **Nível de Carreira**: JUNIOR, PLENO, SENIOR ou TRANSICAO

### Trilha
- **Nome**: Obrigatório e não pode ser vazio (`@NotBlank`)
- **Nível**: Obrigatório (`@NotNull`) - INICIANTE, INTERMEDIARIO ou AVANCADO
- **Carga Horária**: Obrigatória e mínimo de 1 hora (`@Min(1)`, `@NotNull`)

### Competência
- **Nome**: Obrigatório e não pode ser vazio (`@NotBlank`)
- **Categoria**: Obrigatória (`@NotNull`) - TECNOLOGIA, HUMANA ou GESTAO

### Matrícula
- **ID do Usuário**: Obrigatório (`@NotNull`)
- **ID da Trilha**: Obrigatório (`@NotNull`)
- **Status**: ATIVA, CONCLUIDA ou CANCELADA (definido automaticamente como ATIVA se não especificado)

## ⚠️ Tratamento de Erros

A API retorna respostas padronizadas para diferentes tipos de erro:

### 400 - Bad Request
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Erro de validação",
  "path": "/api/usuarios",
  "fieldErrors": [
    {
      "field": "email",
      "message": "Email deve ser válido"
    }
  ]
}
```

### 404 - Not Found
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Usuário não encontrado com o ID: 999",
  "path": "/api/usuarios/999"
}
```

### 422 - Unprocessable Entity
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 422,
  "error": "Unprocessable Entity",
  "message": "Email já cadastrado no sistema",
  "path": "/api/usuarios"
}
```

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas com uso extensivo de Lombok para reduzir boilerplate:

1. **Controller** (`@RestController`, `@RequiredArgsConstructor`): Recebe requisições HTTP e retorna respostas
2. **Service** (`@Service`, `@RequiredArgsConstructor`): Contém a lógica de negócio
3. **Repository** (`@Repository`): Interface de acesso aos dados (Spring Data JPA)
4. **Domain** (`@Entity`, `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`): Entidades JPA do domínio
5. **DTO** (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`): Objetos de transferência de dados com validações
6. **Exception** (`@RestControllerAdvice`): Tratamento centralizado de exceções

### Padrões Utilizados

- **Constructor Injection**: Todas as dependências são injetadas via construtor usando `@RequiredArgsConstructor` do Lombok
- **DTO Pattern**: Separação clara entre entidades de domínio e objetos de transferência
- **Repository Pattern**: Abstração de acesso a dados com Spring Data JPA
- **Exception Handling**: Tratamento global de exceções com `@RestControllerAdvice`
- **Transaction Management**: Uso de `@Transactional` para operações de leitura e escrita

## 🧪 Testes

Para executar os testes:
```bash
mvn test
```

## 📝 Exemplos de Uso com cURL

### Criar um novo usuário:
```bash
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Maria Santos",
    "email": "maria.santos@example.com",
    "areaAtuacao": "Data Science",
    "nivelCarreira": "JUNIOR"
  }'
```

### Criar uma competência:
```bash
curl -X POST http://localhost:8080/api/competencias \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Kubernetes",
    "categoria": "TECNOLOGIA",
    "descricao": "Orquestração de containers"
  }'
```

### Listar todas as trilhas:
```bash
curl -X GET http://localhost:8080/api/trilhas
```

### Criar uma matrícula:
```bash
curl -X POST http://localhost:8080/api/matriculas \
  -H "Content-Type: application/json" \
  -d '{
    "usuarioId": 1,
    "trilhaId": 1
  }'
```

### Buscar trilhas de um usuário:
```bash
curl -X GET http://localhost:8080/api/matriculas/usuarios/1/trilhas
```

## 🔒 Segurança e Boas Práticas

- **Separação de Perfis**: Configurações diferentes para desenvolvimento (H2) e produção (MySQL)
- **Validação de Dados**: Bean Validation em todos os endpoints de entrada
- **Tratamento de Erros**: Respostas padronizadas e informativas
- **Transações**: Gestão adequada de transações com `@Transactional`
- **Constructor Injection**: Imutabilidade de dependências via Lombok
- **Lombok**: Redução de código boilerplate mantendo legibilidade

## 🐳 Docker

### MySQL via Docker Compose

O projeto inclui um arquivo `docker-compose.yml` para facilitar o uso do MySQL:

```bash
# Iniciar MySQL
docker-compose up -d

# Ver logs
docker-compose logs -f mysql

# Parar MySQL
docker-compose down

# Parar e remover dados
docker-compose down -v
```

### Configurações do MySQL no Docker

- **Porta:** 3306
- **Database:** pathcatdb
- **Usuário:** pathcat
- **Senha:** pathcat123
- **Root Password:** root123
- **Volume:** mysql-data (persistência de dados)

## 📊 Endpoints Summary

| Recurso | Método | Endpoint | Descrição |
|---------|--------|----------|-----------|
| **Usuários** | POST | `/api/usuarios` | Criar usuário |
| | GET | `/api/usuarios` | Listar todos |
| | GET | `/api/usuarios/{id}` | Buscar por ID |
| | PUT | `/api/usuarios/{id}` | Atualizar |
| | DELETE | `/api/usuarios/{id}` | Deletar |
| **Trilhas** | POST | `/api/trilhas` | Criar trilha |
| | GET | `/api/trilhas` | Listar todas |
| | GET | `/api/trilhas/{id}` | Buscar por ID |
| | PUT | `/api/trilhas/{id}` | Atualizar |
| | DELETE | `/api/trilhas/{id}` | Deletar |
| **Competências** | POST | `/api/competencias` | Criar competência |
| | GET | `/api/competencias` | Listar todas |
| | GET | `/api/competencias/{id}` | Buscar por ID |
| | PUT | `/api/competencias/{id}` | Atualizar |
| | DELETE | `/api/competencias/{id}` | Deletar |
| **Matrículas** | POST | `/api/matriculas` | Criar matrícula |
| | GET | `/api/matriculas/usuarios/{usuarioId}/trilhas` | Trilhas do usuário |
| | GET | `/api/matriculas/trilhas/{trilhaId}/usuarios` | Usuários da trilha |

## 🤝 Contribuindo

Contribuições são bem-vindas! Por favor:

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença especificada no arquivo LICENSE.