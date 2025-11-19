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

- **Java 17** - Linguagem de programação
- **Spring Boot 3.2.0** - Framework para aplicações Java
- **Spring Data JPA** - Persistência de dados
- **H2 Database** - Banco de dados em memória
- **Spring Validation** - Validação de dados
- **Lombok** - Redução de código boilerplate
- **Maven** - Gerenciamento de dependências

### Dependências Principais

```xml
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-validation
- h2
- lombok
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

- Java 17 ou superior
- Maven 3.6 ou superior

### Como Executar

1. **Clone o repositório:**
```bash
git clone https://github.com/M-Hisamoto/PathCat.git
cd PathCat
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

### Console H2

Acesse o console do banco de dados H2 em: `http://localhost:8080/h2-console`

- **JDBC URL:** `jdbc:h2:mem:pathcatdb`
- **Username:** `sa`
- **Password:** (deixe em branco)

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

#### Listar Todas as Competências
```bash
GET /api/competencias
```

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

O sistema implementa validações automáticas:

- **Nome**: Obrigatório e não pode ser vazio
- **Email**: Obrigatório, deve ser válido e único no sistema
- **Carga Horária**: Mínimo de 1 hora
- **Nível de Trilha**: INICIANTE, INTERMEDIARIO ou AVANCADO
- **Nível de Carreira**: JUNIOR, PLENO, SENIOR ou TRANSICAO
- **Status de Matrícula**: ATIVA, CONCLUIDA ou CANCELADA

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

O projeto segue uma arquitetura em camadas:

1. **Controller**: Recebe requisições HTTP e retorna respostas
2. **Service**: Contém a lógica de negócio
3. **Repository**: Interface de acesso aos dados
4. **Domain**: Entidades do domínio
5. **DTO**: Objetos de transferência de dados
6. **Exception**: Tratamento centralizado de exceções

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

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/NovaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/NovaFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👥 Autor

Desenvolvido como parte do projeto PathCat - Plataforma de Upskilling/Reskilling

## 📞 Contato

Para dúvidas ou sugestões, abra uma issue no repositório do GitHub.