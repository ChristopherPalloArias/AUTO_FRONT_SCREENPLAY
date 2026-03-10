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

## 🏗️ Arquitectura Screenplay

El framework está estructurado en cinco capas semánticas que constituyen el núcleo del patrón Screenplay puro:

| Capa | Paquete | Responsabilidad |
|---|---|---|
| 🎭 **Actores** | `stepdefinitions` | `OnlineCast` instancia actores con `BrowseTheWeb` inyectado automáticamente |
| ✅ **Tasks** | `tasks` | Orquestan Interacciones de negocio de alto nivel. Cada Task cumple el **SRP** |
| ⚡ **Interactions** | `interactions` | Acciones atómicas reutilizables (ej. click vía JavaScript para Radix UI) |
| 🎯 **Targets** | `userinterfaces` | Mapa de elementos UI usando `Target.the(desc).located(By...)` |
| ❓ **Questions** | `questions` | Consultan el estado observable de la UI para alimentar aserciones |

```
src/test/java/com/budgetapp/qa/
├── userinterfaces/       # Targets (LoginUI, DashboardUI, TransactionUI)
├── tasks/                # Tasks SRP (Login, MapsTo, RegisterIncome)
├── interactions/         # Interactions personalizadas (ClickViaJavaScript)
├── questions/            # Questions (FinancialReport)
├── stepdefinitions/      # Glue BDD: attemptsTo() + seeThat()
└── runners/              # CucumberTestSuite
```

---

## 🏆 Checklist de Calidad (Criterios de Evaluación)

Este framework ha sido auditado y cumple al 100% con los siguientes criterios mandatorios de la semana 5:

- [x] **Screenplay Puro:** Uso exclusivo de `Actor`, `Task`, `Interaction`, `Target` y `Question`. Ningún rastro de POM, `PageObject` ni `@FindBy` en el codebase.
- [x] **Principio de Responsabilidad Única (SRP):** Cada clase `Task` realiza **una y sólo una acción de negocio** (`Login` autentica, `MapsTo` navega, `RegisterIncome` completa el formulario). Ninguna Task mezcla responsabilidades.
- [x] **Zero Comments (Clean Code CRÍTICO):** Ausencia total, estricta e intransigente de cualquier tipo de código comentado, línea `//` explicativa o *Javadoc* interno en las clases `.java`. El código es su propia fuente documental.
- [x] **Nomenclatura Semántica:** Patrón `camelCase` riguroso, nombrando métodos auto-descriptivos y declarativos en inglés (semántica de negocio, no de UI).
- [x] **Glue Limpio:** Las `StepDefinitions` sólo contienen `attemptsTo()` y `seeThat()`. Cero lógica de WebDriver ni lógica de negocio en esta capa.
- [x] **OnStage / OnlineCast:** Setup canónico de Screenplay con `OnStage.setTheStage(new OnlineCast())` en `@Before`, garantizando inyección automática de `BrowseTheWeb` por actor.

---

## ⚡ Instrucciones de Clonado y Setup (Entorno Local)

La suite de pruebas valida interacciones *End-to-End* orgánicas operando en tiempo real contra un sistema de microservicios.

### Paso 1: Clonar este Repositorio de Pruebas (El Framework)

Clona este repositorio donde residen los scripts E2E que auditarán el sistema:

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

2. Arranca el cluster de microservicios en caché desacoplado:
   ```bash
   docker compose up --build -d
   ```

3. 🕒 **Pausa Crítica:** Espera de 30 a 45 segundos para que los *healthchecks* de los contenedores de **MySQL** y el broker de eventos **RabbitMQ** transicionen a un estado `healthy`.

4. Verifica manualmente el levantamiento del front end navegando a [http://localhost:3000](http://localhost:3000).

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
  Los componentes *Select* no-nativos del DOM son gestionados por la interacción personalizada `ClickViaJavaScript`, que implementa la interfaz `Interaction` de Screenplay. Ejecuta un `arguments[0].click()` vía JavaScript sobre el `Target` del trigger, eludiendo las capas z-index y las *exit animations* de los overlays modales de Radix.

* **Notificaciones de Microservicios Desacopladas (`Sonner Toasts`):**  
  Las transacciones viajan hacia RabbitMQ rebotando eventos vía frontend de forma asíncrona. La `Question` `FinancialReport.isVisible()` introduce un `WaitUntil` idiomático sobre el `Target` del reporte, garantizando que la aserción se ejecute sólo cuando el estado de la UI sea observable y estable.

* **Navegación por URL Directa:**  
  El `Task` `Login` usa `Open.url("http://localhost:3000")` seguido de un `WaitUntil` de visibilidad sobre el campo de email. Esta estrategia es más robusta que los resolvers de nombres de páginas, evitando `UnknownPageException` en entornos de CI/CD sin configuración de `pages {}` en `serenity.conf`.
