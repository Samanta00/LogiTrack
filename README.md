# Este Projeto está funcional porém no momento atual estou realizando melhorias nele

# LogiTrack API & Frontend

Sistema de gerenciamento logístico para controle de:

* Veículos
* Viagens
* Manutenções
* Regras de conflito de agenda
* API REST com Spring Boot
* Frontend React + TypeScript

---

# Tecnologias Utilizadas

## Backend

* Java 17
* Spring Boot
* Spring Data JPA
* Spring Security
* Hibernate
* MySQL
* Docker

## Frontend

* React
* TypeScript
* Axios
* Framer Motion
* TailwindCSS
* Lucide React


<img width="1920" height="935" alt="formulario" src="https://github.com/user-attachments/assets/37428f1a-63be-48db-8e57-3179a00012cf" />
<img width="1613" height="803" alt="logitrack" src="https://github.com/user-attachments/assets/6a622093-85ab-48aa-a12b-d5992a0134eb" />


---

# Estrutura do Projeto

```bash

# 📁 Estrutura Completa do Projeto

```bash
LogiTrack/
│
├── Frontend/
│   └── logitrack/
│       │
│       ├── public/
│       │
│       ├── src/
│       │   ├── api/
│       │   │   └── api.ts
│       │   │
│       │   ├── assets/
│       │   │
│       │   ├── components/
│       │   │   ├── Card.tsx
│       │   │   └── Navbar.tsx
│       │   │
│       │   ├── pages/
│       │   │   ├── Dashboard.tsx
│       │   │   ├── Manutencao.tsx
│       │   │   └── Viagem.tsx
│       │   │
│       │   ├── personalizacao/
│       │   │   └── dashboard/
│       │   │       └── index.css
│       │   │
│       │   ├── types/
│       │   │
│       │   ├── App.tsx
│       │   ├── App.css
│       │   ├── main.tsx
│       │   └── index.css
│       │
│       ├── eslint.config.js
│       ├── index.html
│       ├── package.json
│       ├── package-lock.json
│       ├── postcss.config.js
│       ├── tailwind.config.js
│       ├── tsconfig.json
│       ├── tsconfig.app.json
│       ├── tsconfig.node.json
│       ├── vite.config.ts
│       └── README.md
│
├── Api-segura/
│   └── api/
│       │
│       ├── Docker/
│       │   ├── docker-compose.yml
│       │   │
│       │   └── mysql/
│       │       └── init.sql
│       │
│       ├── src/
│       │   ├── main/
│       │   │   ├── java/
│       │   │   │   └── com/
│       │   │   │       └── api/
│       │   │   │           └── seguranca/
│       │   │   │               └── api/
│       │   │   │
│       │   │   │                   ├── config/
│       │   │   │                   │   ├── DataInitializer.java
│       │   │   │                   │   ├── SecurityConfig.java
│       │   │   │                   │   └── WebConfig.java
│       │   │   │
│       │   │   │                   ├── dashboard/
│       │   │   │                   │   ├── controller/
│       │   │   │                   │   │   └── DashboardController.java
│       │   │   │                   │   │
│       │   │   │                   │   ├── dto/
│       │   │   │                   │   │   ├── DashboardDTO.java
│       │   │   │                   │   │   ├── ManutencaoDTO.java
│       │   │   │                   │   │   ├── ProjecaoFinanceiraDTO.java
│       │   │   │                   │   │   ├── ProjecaoTipoDTO.java
│       │   │   │                   │   │   ├── RankingVeiculoDTO.java
│       │   │   │                   │   │   └── VolumePorCategoriaDTO.java
│       │   │   │                   │   │
│       │   │   │                   │   ├── repository/
│       │   │   │                   │   │   └── DashboardRepository.java
│       │   │   │                   │   │
│       │   │   │                   │   └── service/
│       │   │   │                   │       └── DashboardService.java
│       │   │   │
│       │   │   │                   ├── exception/
│       │   │   │                   │   ├── ConflitoViagemException.java
│       │   │   │                   │   └── GlobalExceptionHandler.java
│       │   │   │
│       │   │   │                   ├── viagem/
│       │   │   │                   ├── manutencao/
│       │   │   │                   ├── veiculo/
│       │   │   │                   │
│       │   │   │                   └── ApiApplication.java
│       │   │   │
│       │   │   └── resources/
│       │   │
│       │   └── test/
│       │
│       ├── target/
│       │
│       ├── Dockerfile
│       ├── mvnw
│       ├── mvnw.cmd
│       └── pom.xml
│
└── README.md


```

---

# Regras de Negócio

## Veículos

Cada veículo possui:

* placa única
* modelo
* tipo
* ano

---

## Viagens

Um veículo:

✅ pode ter várias viagens

❌ NÃO pode possuir duas viagens no mesmo período.

Exemplo inválido:

| Veículo    | Saída | Chegada |
| ---------- | ----- | ------- |
| Caminhão 1 | 10:00 | 14:00   |
| Caminhão 1 | 11:00 | 13:00   |

Porque o veículo já está em viagem.

---

## Manutenções

Um veículo:

✅ pode ter várias manutenções

❌ NÃO pode possuir duas manutenções no mesmo período.

Exemplo inválido:

| Veículo | Início | Finalização |
| ------- | ------ | ----------- |
| Van 1   | 01/05  | 10/05       |
| Van 1   | 05/05  | 08/05       |

Porque o veículo já está em manutenção.

---

# Banco de Dados

## Tabela: veiculos

```sql
CREATE TABLE veiculos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(10) UNIQUE NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    tipo VARCHAR(20),
    ano INTEGER
);
```

---

## Tabela: viagens

```sql
CREATE TABLE viagens (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    veiculo_id BIGINT,
    data_saida TIMESTAMP NOT NULL,
    data_chegada TIMESTAMP,
    origem VARCHAR(100),
    destino VARCHAR(100),
    km_percorrida DECIMAL(10,2),

    CONSTRAINT fk_viagem_veiculo
        FOREIGN KEY (veiculo_id)
        REFERENCES veiculos(id)
        ON DELETE CASCADE
);
```

---

## Tabela: manutencoes

```sql
CREATE TABLE manutencoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    veiculo_id BIGINT,
    data_inicio DATE NOT NULL,
    data_finalizacao DATE,
    tipo_servico VARCHAR(100),
    custo_estimado DECIMAL(10,2),
    status VARCHAR(20) DEFAULT 'PENDENTE',

    CONSTRAINT fk_manutencao_veiculo
        FOREIGN KEY (veiculo_id)
        REFERENCES veiculos(id)
        ON DELETE CASCADE
);
```

---

# Foreign Key

```sql
FOREIGN KEY (veiculo_id)
REFERENCES veiculos(id)
ON DELETE CASCADE
```

Significa que:

* `veiculo_id` aponta para um veículo existente
* não é possível criar viagens/manutenções para veículos inexistentes
* ao deletar um veículo, todas as viagens e manutenções dele também são removidas automaticamente

---

# Validação de Conflito de Viagem

## Repository

```java
@Query("""
    SELECT COUNT(v) > 0
    FROM ViagemEntity v
    WHERE v.veiculo.id = :veiculoId
    AND (
        :dataSaida BETWEEN v.dataSaida AND v.dataChegada
        OR
        :dataChegada BETWEEN v.dataSaida AND v.dataChegada
        OR
        v.dataSaida BETWEEN :dataSaida AND :dataChegada
    )
""")
boolean existsConflitoViagem(
    Long veiculoId,
    LocalDateTime dataSaida,
    LocalDateTime dataChegada
);
```

---

## Service

```java
if (viagemRepository.existsConflitoViagem(
        dto.veiculoId(),
        dto.dataSaida(),
        dto.dataChegada()
)) {
    throw new ConflitoViagemException(
        "Veículo já possui viagem nesse período."
    );
}
```

---

# Tratamento Global de Exceções

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflitoViagemException.class)
    public ResponseEntity<?> handleConflitoViagem(
            ConflitoViagemException ex
    ){
        return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(Map.of(
            "timestamp", LocalDateTime.now(),
            "status", 409,
            "error","Conflito de Viagem",
            "message", ex.getMessage()
        ));
    }
}
```

---

# Resposta da API

## Sucesso

```json
{
  "id": 1,
  "origem": "São Paulo",
  "destino": "Rio de Janeiro"
}
```

---

## Erro de conflito

```json
{
  "timestamp": "2026-05-10T17:06:02",
  "status": 409,
  "error": "Conflito de Viagem",
  "message": "Veículo já possui viagem nesse período."
}
```

---

# Frontend

## Tratamento de erro Axios

```tsx
import axios from "axios";

catch (erro) {
  if (axios.isAxiosError(erro)) {
    const mensagem =
      erro.response?.data?.message ||
      erro.response?.data?.error ||
      "Erro inesperado";

    alert(mensagem);
  }
}
```

---

# Rodando o Projeto

## Docker + Backend Java
entre na pasta : 
cd Api-segura/api/Docker 
e execute: 

```bash
docker-compose up --build
```

---

# Frontend
entre na pasta: 
cd Frontend/logitrack

## Instalar dependências

```bash
npm install
```

## Rodar

```bash
npm run dev
```

---

# Endpoints

## Veículos

| Método | Rota           |
| ------ | -------------- |
| GET    | /veiculos      |
| GET    | /veiculos/{id} |
| POST   | /veiculos      |
| PUT    | /veiculos/{id} |
| DELETE | /veiculos/{id} |

---

## Viagens

| Método | Rota          |
| ------ | ------------- |
| GET    | /viagens      |
| GET    | /viagens/{id} |
| POST   | /viagens      |
| PUT    | /viagens/{id} |
| DELETE | /viagens/{id} |

---

## Manutenções

| Método | Rota              |
| ------ | ----------------- |
| GET    | /manutencoes      |
| GET    | /manutencoes/{id} |
| POST   | /manutencoes      |
| PUT    | /manutencoes/{id} |
| DELETE | /manutencoes/{id} |

---

# Segurança

O projeto utiliza:

* Spring Security
* HTTPS
* autenticação básica (em desenvolvimento)

---

# Melhorias Futuras

* JWT Authentication
* Dashboard analítico
* Controle de motoristas
* Rastreamento em tempo real
* Upload de documentos
* Relatórios PDF
* Logs de auditoria
* Testes automatizados

---

# Autor: Ellen Samanta

Projeto desenvolvido para estudo e prática de:

* arquitetura REST
* Spring Boot
* JPA/Hibernate
* React + TypeScript
* Docker
* regras de negócio reais em logística
