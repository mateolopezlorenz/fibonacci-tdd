# Parte 2: Pipeline con una aplicación Java

## Descripción del ejercicio

Se ha creado la ruta `.github/workflows/` y dentro de ella el archivo `ci.yml`, donde se define el workflow.  
Este workflow se ejecuta automáticamente cuando se produce un cambio en el repositorio, ya puede ser un `push` como ejemplo.

El pipeline realiza las siguientes acciones:
- Descarga el código del repositorio.
- Configura el entorno Java mediante una action existente definida en el `ci.yml` (`actions/setup-java`).
- Ejecuta los tests del proyecto Java utilizando Maven (`mvn test`) desde la ruta que se define en `ci.yml` que en este caso es la raíz del proyecto.

De este modo, los tests de este proyecto se ejecutan automáticamente sin necesidad de hacerlo de forma manual.

## Contenido del archivo `ci.yml`
```
name: CI Java Fibonacci

on:
  push:

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout del repositorio
        uses: actions/checkout@v4

      - name: Configurar Java
        uses: actions/setup-java@v4
        with:
          distribution: 'temurin'
          java-version: '17'

      - name: Ejecutar tests con Maven
        run: mvn test
        working-directory: ./fibonacci-tdd
```


## Enlace a `GitHub Actions`.
```
https://github.com/mateolopezlorenz/fibonacci-tdd/actions/workflows/ci.yml
```