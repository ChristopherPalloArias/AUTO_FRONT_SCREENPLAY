# language: es
Característica: Gestión de ingresos y reportes financieros para balance mensual
  Como analista financiero de la aplicación de presupuesto
  Quiero registrar los ingresos del período categorizados por tipo
  Para visualizar su impacto en el balance general y los reportes consolidados del panel principal

  Antecedentes:
    Dado que Christopher se autentica exitosamente en la plataforma financiera

  Esquema del escenario: Registro exitoso de un ingreso y validación en el reporte financiero
    Cuando el registra un nuevo ingreso de tipo "<descripcion>" por un monto de <monto>
    Entonces el reporte financiero del panel principal debe reflejar el ingreso registrado

    Ejemplos:
      | descripcion     | monto |
      | Salario Mensual | 2000  |
