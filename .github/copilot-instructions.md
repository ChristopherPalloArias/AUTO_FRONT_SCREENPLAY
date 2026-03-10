# Reglas de Proyecto: AUTO_FRONT_POM_FACTORY
Contexto
Este es un proyecto de automatización End-to-End (E2E) utilizando Java, Gradle, Serenity BDD y Cucumber. La aplicación a probar se encuentra en http://localhost:3000.

Estándares de Arquitectura (Obligatorio)
Patrón de Diseño: Se debe usar EXCLUSIVAMENTE Page Object Model (POM) con Page Factory.

Locators: Todos los elementos web deben mapearse utilizando la anotación @FindBy.

Estándares de Código Limpio (Regla de Oro)
Cero Comentarios: Existe una prohibición ABSOLUTA de incluir código comentado o comentarios de documentación (ej. // o /* */) dentro de las clases Java. El código debe ser autodescriptivo.

Nomenclatura Semántica: Nombres de clases, métodos y variables deben ser descriptivos, claros y en idioma INGLÉS.

Estándares de BDD y Gherkin
Idioma: Los archivos .feature deben escribirse en ESPAÑOL (# language: es).

Estilo Declarativo: Los escenarios Gherkin deben enfocarse en el comportamiento de negocio (qué hace el usuario) y NUNCA en interacciones de UI (prohibido usar frases como "hace clic en el botón", "escribe en el campo").

## Estrategia de Locators (Shadcn UI & Radix)
- **Elementos Dinámicos:** La aplicación usa componentes Radix UI. Para los `Select`, nunca uses el tag `<select>`. Debes localizar el trigger (botón) primero, hacer clic, y luego localizar la opción (`role='option'`).
- **Toasts (Notificaciones):** Las alertas de éxito/error usan la librería Sonner. Se deben localizar con el selector `[data-sonner-toast]` usando esperas explícitas, ya que desaparecen rápido.

## Estructura de Serenity BDD
Clases Page: Deben extender de PageObject. Los elementos se inyectan EXCLUSIVAMENTE con @FindBy.

Clases Step: Se anotan con @Steps y deben contener métodos pequeños que llamen a las funciones de las clases Page.

Step Definitions: Solo deben ser un puente ("glue code") entre Cucumber y los @Steps. No deben contener lógica de WebDriver.
