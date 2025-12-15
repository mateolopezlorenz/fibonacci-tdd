# Instal·lació de l'entorn Java

Instruccions per a instal·lar un entorn de desenvolupament Java sobre GNU Debian/Ubuntu Linux.

## JDK

Actualitza el repository i instal·la OpenJDK:

```bash
sudo apt-get update
sudo apt-get install openjdk-21-jdk
```

Verifica la instal·lació:

```bash
java --version
```

## Maven

Instal·la Maven:

```bash
sudo apt-get install maven
```

Verifica la instal·lació:

```bash
mvn --version
```

## Repositori a Github

Crearem el repositori a Github usant la seva interfícia web:

1. Inicia sessió a [Github](https://github.com/).
2. Crea un nou repositori amb el nom `fibonacci-tdd`.
3. No marquis cap opció (ni README, ni .gitignore, ni llicència). El repositori ha d'estar buit.
4. Copia la URL del repositori, que serà similar a `https://github.com/<usuari>/fibonacci-tdd.git`.

No t'oblidis de configura-hi la teva clau SSH pública, si no ho has fet ja.

## Estructura del projecte

Volem usar la següent estructura de directoris i fitxers pel projecte, seguint el format estàndar de projecte Maven per a Java:

```
fibonacci-tdd/
├── pom.xml
└── src/
    ├── main/
    │   └── java/
    │       └── Fibonacci.java
    └── test/
        └── java/
            └── FibonacciTest.java
```

En comptes de generar aquests fitxers manualment, usarem Maven per a generar aquesta estructura dins la nostre carpeta de projectes. Maven genera automàticament l'estructura del projecte amb JUnit 5 configurat:

```bash
cd ~/Projects
mvn archetype:generate \
  -DgroupId=eu.cifpfbmoll \
  -DartifactId=fibonacci-tdd \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DarchetypeVersion=1.5 \
  -DinteractiveMode=false
```

La primera vegada, Maven descarregarà diversos components. Això pot tardar uns minuts. Un pic finalitzat, entra al directori del projecte i revisa l'estructura generada:

```bash
tree fibonacci-tdd
```

L'estructura generada és:

```
fibonacci-tdd/
├── pom.xml
└── src/
    ├── main/
    │   └── java/
    │       └── eu/
    │           └── cifpfbmoll/
    │               └── App.java
    └── test/
        └── java/
            └── eu/
                └── cifpfbmoll/
                    └── AppTest.java
10 directories, 3 files
```

Resum de comandes Maven:

| Ordre            | Descripció                                        |
|------------------|---------------------------------------------------|
| `mvn test`       | Executa tots els tests                            |
| `mvn clean`      | Elimina els fitxers compilats (carpeta `target/`) |
| `mvn clean test` | Neteja i executa tests                            |
| `mvn package`    | Compila i empaqueta el projecte en un JAR         |

## Fitxer .gitignore

Crea el fitxer `.gitignore` a l'arrel del projecte amb el contingut següent:

```ini
# Compiled class files
target/

# Eclipse
.classpath
.project
.settings/

# IntelliJ IDEA
.idea/
*.iml

# VS Code
.vscode/

# Maven
pom.xml.tag
pom.xml.releaseBackup
pom.xml.versionsBackup
pom.xml.next
release.properties

# OS generated files
.DS_Store
Thumbs.db

# Log files
*.log
```

## Inicialitzar Git i connectar amb Github

Anem a inicialitzar un repositori Git i a connectar-lo amb el que hem creat abans a Github a través de les següents ordres:

```bash
git init
git add .
git commit -m "Projecte inicial generat amb Maven archetype"
git branch -M main
git remote add origin https://github.com/<usuari>/fibonacci-tdd.git
git push -u origin main
```

## Verifica que els tests funcionen

Executa el tests generats per l'achetype, com a prova:

```bash
mvn test
```

Hauries de veure:

```console
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running eu.cifpfbmoll.AppTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] BUILD SUCCESS
```

## Codi font

Elimina els fitxers d'exemple:

```bash
rm src/main/java/eu/cifpfbmoll/App.java
rm src/test/java/eu/cifpfbmoll/AppTest.java
```

Crearem una classe `Fibonacci` esquelet dins el fitxer `src/main/java/eu/cifpfbmoll/Fibonacci.java`:

```java
package eu.cifpfbmoll;

public class Fibonacci {

    public int calculate(int n) {
        return 0; // Implementació incorrecta inicial
    }
}
```

Crearem també una classe `FibonacciTest` esquelet dins el fitxer `src/test/java/eu/cifpfbmoll/FibonacciTest.java`:

```java
package eu.cifpfbmoll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class FibonacciTest {

    private Fibonacci fibonacci = new Fibonacci();

    @Test
    void fibonacciOfZeroIsZero() {
        assertEquals(0, fibonacci.calculate(0));
    }
}
```

## Primera execució dels tests

Tornam a usar la mateixa ordre per a executar el nostre primer test:

```bash
mvn test
```

El primer test passa perquè `fibonacci(0)` ha de retornar `0`, i la nostra implementació inicial retorna `0`.

```console
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

## Cicle TDD

El cicle TDD segueix tres passos: Red → Green → Refactor.

1. Red: Escriu un test que falli.
2. Green: Escriu el codi mínim per fer passar el test.
3. Refactor: Millora el codi sense canviar el comportament.

### Execici guiat

Pas 1: Afegeix un test per a `fibonacci(1)`:

```java
@Test
void fibonacciOfOneIsOne() {
    assertEquals(1, fibonacci.calculate(1));
}
```

Executa `mvn test`. El test fallarà perquè retornem `0` sempre:

```console
[INFO] Tests run: 2, Failures: 1, Errors: 0, Skipped: 0
[INFO] BUILD FAILURE
```

Pas 2: Volem passar de l'estat `Red` a l'estat `Green`. Per això ens cal modificar el fitxer `Fibonacci.java`:

```java
public int calculate(int n) {
    if (n == 0) return 0;
    return 1;
}
```

Executa `nvm test`. Tots els test passen.

Pas 3: Hem de continuar el cicle. fegeix tests per a `fibonacci(2)`, `fibonacci(3)`, etc., i modifica la implementació fins arribar a la solució general.

| n    | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7  | 8  |
|------|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:--:|:--:|
| F(n) | 0 | 1 | 1 | 2 | 3 | 5 | 8 | 13 | 21 |

## Puja els canvis a Github

Cada vegada que completis un cicle TDD, és a dir, un nou test que passa, puja els canvis:

``bash
git add .
git commit -m "Descripció del canvi"
git push
```