<div align="center">
  
# 🚀 AUTO_FRONT_SCREENPLAY
### Taller Semana 5: Maestría en Automatización

**Autor:** Christopher Pallo  
**Entregable:** 2 de 3  
**Proyecto:** Automatización End-to-End (E2E) robusta centrada en el patrón **Serenity Screenplay**, arquitectura limpia y máxima expresividad en el modelado del comportamiento del usuario.

<br />

### 🛠️ Technology Stack

**Automation Framework**
<br />
<a href="https://skillicons.dev">
  <img src="https://skillicons.dev/icons?i=java,gradle,selenium,cucumber" alt="Automation Stack" />
</a>

**Application Under Test (Microservices Ecosystem)**
<br />
<a href="https://skillicons.dev">
  <img src="https://skillicons.dev/icons?i=ts,react,vite,tailwind,spring,java,rabbitmq,docker,mysql" alt="App Stack" />
</a>

</div>

---

## 🎯 Contexto del Reto

Este repositorio corresponde al **Entregable 2 de 3** de la Maestría en Automatización. El objetivo es certificar el máximo dominio técnico sobre la implementación del patrón arquitectónico **Screenplay** de Serenity BDD, reemplazando el paradigma POM por un modelo centrado en el **Actor** y su capacidad de ejecutar **Tareas** compuestas por **Interacciones** atómicas sobre **Objetivos (Targets)** de la UI, cuyo estado se valida mediante **Preguntas (Questions)**. Todo orquestado bajo metodología BDD con Cucumber.

---

## 🔄 Escenario E2E: Ciclo de Vida Completo

El framework ejecuta un flujo End-to-End **idempotente** y **autocontenido** que garantiza la validación desde cero, sin datos residuales:

```
Registro de usuario nuevo ➜ Login automático ➜ Creación de ingreso ➜ Validación de balance
```

### Estrategia de Idempotencia

Cada ejecución genera un **correo electrónico único** mediante inyección de timestamp, eliminando fallos por duplicidad de usuarios en ejecuciones consecutivas:

```
chris.qa@test.com  →  chris.qa+1741654000000@test.com
```

Esta lógica reside en la Task `SignUp.java`, manteniendo la generación dinámica encapsulada dentro del patrón Screenplay.

### Flujo Gherkin (Declarativo)

```gherkin
# language: es
Esquema del escenario: CP-E2E - Creación de cuenta nueva y validación de ingreso inicial
  Dado que el visitante accede a la página de registro de la plataforma
  Cuando él crea una cuenta nueva con el nombre "<nombre>" y el correo base "<correo_base>"
  Y registra un nuevo ingreso de tipo "<descripcion>" por un monto de <monto>
  Entonces el reporte financiero del panel principal debe reflejar el balance exacto de <monto>
```

---

## 🏗️ Arquitectura Screenplay

El framework está estructurado en capas semánticas que constituyen el núcleo del patrón Screenplay puro de Sofka:

| Capa | Paquete | Responsabilidad |
|---|---|---|
| 🎭 **Actores** | `stepdefinitions` | `OnlineCast` instancia actores con `BrowseTheWeb` inyectado automáticamente |
| ✅ **Tasks** | `tasks` | Orquestan Interacciones de negocio de alto nivel vía `Tasks.instrumented()` |
| ⚡ **Interactions** | `interactions` | Acciones atómicas reutilizables (clicks JS, limpieza de campos, pausas) |
| 🎯 **Targets** | `ui` | Mapa de elementos UI usando `Target.the(desc).located(By...)` |
| ❓ **Questions** | `questions` | Consultan el estado observable de la UI para alimentar aserciones |

```
src/test/java/com/budgetapp/qa/
├── ui/                   # Targets (SignUpUI, LoginUI, DashboardUI, TransactionUI)
├── tasks/                # Tasks SRP (SignUp, Login, MapsTo, RegisterIncome)
├── interactions/         # Interactions (Pause, ClickViaJavaScript, ClearFieldViaJavaScript, SelectFromRadix)
├── questions/            # Questions (FinancialReport)
├── model/                # Data classes (TransactionData)
├── stepdefinitions/      # Glue BDD: wasAbleTo() + attemptsTo() + seeThat()
└── runners/              # CucumberTestSuite (JUnit Platform 5)
```

---

## 🏆 Cumplimiento de Rúbrica (Clean Code & SOLID)

Este framework ha sido auditado exhaustivamente y cumple al 100% con los criterios mandatorios:

### Clean Code

- [x] **Zero Comments (Regla de Oro):** Ausencia total de `//`, `/* */` y `/** */` en las 15 clases Java. El código es su propia fuente documental.
- [x] **Nomenclatura Semántica 100% en Inglés:** Clases (`SignUp`, `RegisterIncome`, `FinancialReport`), métodos (`withNameAndEmail`, `forSeconds`, `balanceValue`) y variables (`uniqueEmail`, `displayName`, `emailBase`) — todo en inglés técnico.
- [x] **Targets en UPPER_SNAKE_CASE:** Los 25 Targets del proyecto siguen la convención `DISPLAY_NAME_INPUT`, `BALANCE_VALUE`, `CREATE_TRANSACTION_BUTTON`.

### SOLID

- [x] **S — Single Responsibility:** Cada Task ejecuta **una sola acción de negocio** (`SignUp` registra, `Login` autentica, `MapsTo` navega, `RegisterIncome` crea transacción).
- [x] **O — Open/Closed:** Nuevos tipos de transacción (`RegisterExpense`) se añaden sin modificar código existente.
- [x] **L — Liskov Substitution:** Todas las Tasks, Interactions y Questions son sustituibles vía sus interfaces (`Task`, `Interaction`, `Question<T>`).
- [x] **I — Interface Segregation:** Interfaces mínimas: `performAs()` para Tasks/Interactions, `answeredBy()` para Questions.
- [x] **D — Dependency Inversion:** Cero URLs hardcodeadas — las Tasks leen `webdriver.base.url` desde `serenity.conf` vía `SystemEnvironmentVariables`. Cero WebDriver directo en Tasks.

### Screenplay Pattern

- [x] **`Tasks.instrumented()`:** Todas las Tasks usan `Tasks.instrumented(Class.class, args)` para habilitar el AOP de Serenity y el tracking de `@Step`.
- [x] **Glue Limpio:** Las StepDefinitions usan `wasAbleTo()` para precondiciones (Given), `attemptsTo()` para acciones (When) y `should(seeThat(...))` para aserciones (Then).
- [x] **OnStage / OnlineCast:** Setup canónico con `OnStage.setTheStage(new OnlineCast())` en `@Before`.
- [x] **Interactions encapsuladas:** Operaciones de bajo nivel aisladas en `ClearFieldViaJavaScript`, `ClickViaJavaScript`, `SelectFromRadix` y `Pause`. Cero `BrowseTheWeb` directo en Tasks.

---

## ⚙️ Gestión de Configuración

### `serenity.conf`

| Propiedad | Valor | Propósito |
|-----------|-------|-----------|
| `webdriver.base.url` | `http://localhost:3000` | URL base centralizada — consumida por Tasks vía `SystemEnvironmentVariables` |
| `serenity.take.screenshots` | `FOR_EACH_ACTION` | Captura de evidencia en cada clic e ingreso de texto |
| `serenity.type.delay` | `150` | 150ms entre teclas para tipeo visible en Live Demo |
| `headless.mode` | `false` | Ejecución visual para demostraciones en vivo |
| `goog:chromeOptions.args` | `--start-maximized`, `--no-sandbox` | Chrome optimizado para entorno local |

### `build.gradle`

| Aspecto | Detalle |
|---------|---------|
| **Serenity BDD** | `5.3.2` (última versión estable) |
| **Cucumber** | `7.18.1` (JUnit Platform Engine) |
| **JUnit Platform** | `5.10.2` |
| **Java** | `17` (source + target compatibility) |
| **Reportes** | `single-page-html` + reporte principal Serenity |

---

## ⚡ Instrucciones de Clonado y Setup (Entorno Local)

La suite de pruebas valida interacciones *End-to-End* orgánicas operando en tiempo real contra un sistema de microservicios.

### Paso 1: Clonar este Repositorio de Pruebas (El Framework)

```bash
git clone https://github.com/ChristopherPalloArias/AUTO_FRONT_SCREENPLAY.git
cd AUTO_FRONT_SCREENPLAY
```

### Paso 2: Desplegar el Ecosistema de Prueba (La Aplicación)

Las pruebas colapsarán si el entorno base no está operando, puesto que interceptan el flujo de negocio del *Registro de Transacciones Financieras*.

1. Sitúate fuera del directorio del framework y clona la rama base específica del ecosistema:
   ```bash
   git clone -b release/1.2.1 https://github.com/ChristopherPalloArias/Budget_Management_App.git
   cd Budget_Management_App
   ```

2. Arranca el cluster de microservicios:
   ```bash
   docker compose up --build -d
   ```

3. 🕒 **Pausa Crítica:** Espera de 30 a 45 segundos para que los *healthchecks* de los contenedores de **MySQL** y el broker de eventos **RabbitMQ** transicionen a un estado `healthy`.

4. Verifica manualmente el levantamiento del frontend navegando a [http://localhost:3000](http://localhost:3000).

---

## ▶️ Ejecución y Generación de Reportes

Retorna al directorio del framework de automatización (`AUTO_FRONT_SCREENPLAY`). Para despachar la suite en modo local e invocar el proceso de agregación de Serenity, ejecuta:

```bash
./gradlew clean test aggregate
```

Al concluir exitosamente, el framework compilará en tiempo real un **Living Documentation**, una evidencia paso a paso y gráficamente trazable a nivel de Cucumber y Serenity del flujo de negocio automatizado, narrado desde la perspectiva del Actor.

* **El index maestro del informe lo encuentras en:**
  ```text
  target/site/serenity/index.html
  ```
*(Abre el archivo desde tu explorador web de preferencia).*

---

## 🧩 Consideraciones Técnicas y Retos de Interfaz de Usuario (UI)

* **Sincronización Reactiva — Portals de Radix UI (`@radix-ui/react-select`):**  
  Los componentes *Select* no-nativos del DOM son gestionados por la interacción personalizada `SelectFromRadix`, que implementa la interfaz `Interaction` de Screenplay. Ejecuta un `arguments[0].click()` vía JavaScript sobre el `Target` del trigger, eludiendo las capas z-index y las *exit animations* de los overlays modales de Radix.

* **Estabilización del Campo Monto (`ClearFieldViaJavaScript`):**  
  Los inputs numéricos del formulario de transacciones contienen un valor por defecto `0`. La Interaction `ClearFieldViaJavaScript` ejecuta `arguments[0].value=''` vía JavaScript antes de que Serenity escriba el monto real, previniendo valores concatenados como `02000`.

* **Notificaciones de Microservicios Desacopladas (`Sonner Toasts`):**  
  Las transacciones viajan hacia RabbitMQ rebotando eventos vía frontend de forma asíncrona. La `Question` `FinancialReport.balanceValue()` introduce un `WaitUntil` idiomático sobre el `Target` del balance, garantizando que la aserción se ejecute sólo cuando el estado de la UI sea observable y estable.

* **Gestión de URLs desde Configuración (`serenity.conf`):**  
  Las Tasks `SignUp` y `Login` resuelven la URL base en runtime leyendo `webdriver.base.url` desde `serenity.conf` vía `SystemEnvironmentVariables.createEnvironmentVariables()`. Esto permite cambiar el entorno objetivo (local, staging, CI/CD) sin modificar código fuente.
