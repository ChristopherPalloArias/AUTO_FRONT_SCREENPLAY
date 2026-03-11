# language: es
Característica: Ciclo de vida E2E de usuario nuevo y primera transacción
  Como analista financiero de la aplicación de presupuesto
  Quiero registrar un usuario nuevo y registrar su primer ingreso
  Para garantizar que el sistema inicia con un balance exacto desde cero y sin datos residuales

  Esquema del escenario: CP-E2E - Creación de cuenta nueva y validación de ingreso inicial
    Dado que el visitante accede a la página de registro de la plataforma
    Cuando él crea una cuenta nueva con el nombre "<nombre>" y el correo base "<correo_base>"
    Y registra un nuevo ingreso de tipo "<descripcion>" por un monto de <monto>
    Entonces el reporte financiero del panel principal debe reflejar el balance exacto de <monto>

    Ejemplos:
      | nombre      | correo_base       | descripcion     | monto |
      | Christopher | chris.qa@test.com | Salario Inicial | 2000  |