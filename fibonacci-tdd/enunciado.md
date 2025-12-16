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
| `mvn package`    | Compila, valida i empaqueta el projecte en un JAR |

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
        return -1; // Implementació incorrecta inicial
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

## Cicle TDD

El cicle TDD segueix tres passos: Red → Green → Refactor.

1. `Red`: Escriu un test que falli.
2. `Green`: Escriu el codi mínim per fer passar el test.
3. `Refactor`: Millora el codi sense canviar el comportament.

## Primera execució dels tests

Tornam a usar la mateixa ordre per a executar el nostre primer test:

```bash
mvn test
```

El primer test no passa perquè `fibonacci(0)` ha de retornar `0`, i la nostra implementació inicial retorna `-1`.

```console
[ERROR] Tests run: 1, Failures: 1, Errors: 0, Skipped: 0
[INFO] BUILD FAILURE
```

### Execici guiat

Pas 0: Escrivim el mínim codi necessari per a passar el nostre primer test (en aquest cas, canviam el `return -1` per un `return 0`) i executam els tests:

```bash
mvn test
```

Ara el test passa perquè `fibonacci(0)` ha de retornar `0`, i la nostra implementació actual ara retorna `0`.

```console
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

Pas 1: Afegeix un test per a `fibonacci(1)`:

```java
@Test
void fibonacciOfOneIsOne() {
    assertEquals(1, fibonacci.calculate(1));
}
```

Executa `mvn test`. El test fallarà perquè retornem `0` sempre:

```console
[ERROR] Tests run: 2, Failures: 1, Errors: 0, Skipped: 0
[INFO] BUILD FAILURE
```

Pas 2: Volem passar de l'estat `Red` a l'estat `Green`. Per això ens cal modificar el fitxer `Fibonacci.java`:

```java
public int calculate(int n) {
    if (n == 1) return 1;
    return 0;
}
```

Executa `nvm test`. Tots els test passen:

```console
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

Pas 3: Hem de continuar el cicle. Afegeix tests per a `fibonacci(2)`, `fibonacci(3)`, etc., fins a `fibonacci(8)`, i modifica la implementació després d'haver afegit cada test.

| n    | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7  | 8  |
|------|:-:|:-:|:-:|:-:|:-:|:-:|:-:|:--:|:--:|
| F(n) | 0 | 1 | 1 | 2 | 3 | 5 | 8 | 13 | 21 |

Eventualment, a mesura que anam afegint tests i el codi que fa que es passin, estant en un estat `Green`, serà un bon moment per a dur a terme una refactorització de codi. Aquestes refactoritzacions, eventualment ens duran a l'algorisme que considerem final:

```java
package eu.cifpfbmoll;

public class Fibonacci {

    public int calculate(int n) {
        if (n <= 1) {
            return n;
        }
            
        int previous = 0;
        int current = 1;

        for (int i = 2; i <= n; i++) {
            int next = previous + current;
            previous = current;
            current = next;
        }

        return current;
    }
}
```

Però encara ens faltaran més tests:

* Validació de paràmetres d'entrada.
* Validació de valors de sortida (generats pel nostre codi).

Començarem per afegir els tests per a verificar que es valida l'entrada correctament i també per a verificar que la sortida és sempre un enter positiu:

```java
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Test
void fibonacciOfNegativeThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> fibonacci.calculate(-1));
}

@ParameterizedTest
@ValueSource(ints = {0, 1, 2, 5, 10, 20})
void fibonacciAlwaysReturnsNonNegativeValue(int n) {
    assertTrue(fibonacci.calculate(n) >= 0);
}
```

Per usar `@ParameterizedTest`, cal afegir la dependència `unit-jupiter-params` al `pom.xml`. Però si l'archetype 1.5 ja genera el projecte amb el BOM de JUnit 5.11.0, només cal afegir:

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-params</artifactId>
    <scope>test</scope>
</dependency>
```

Una vegada afegit els tests, afegirem validació del paràmetre d'entrada al mètode `calculate()` de la classe `Fibonacci`:

```java
public int calculate(int n) {
    if (n < 0) {
        throw new IllegalArgumentException("Només s'accepten enters iguals o majors que zero");
    }
    
    if (n <= 1) {
        return n;
    }

    [..]
```

## Flux principal d'execució

Per acabar, opcionalment, podem afegir una classe `App` per a definir el flux principal d'execució. Per això, crearem el fitxer `src/main/java/eu/cifpfbmoll/App.java` amb el següent contingut:

```java
package eu.cifpfbmoll;

public class App {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Ús: java -jar fibonacci.jar <n>");
            System.exit(1);
        }

        int n;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Error: El paràmetre ha de ser un número enter");
            System.exit(2);
            return;
        }

        if (n < 0) {
            System.err.println("Error: El paràmetre ha de ser major o igual que 0");
            System.exit(2);
        }

        Fibonacci fib = new Fibonacci();
        System.out.println(fib.calculate(n));
    }
}
```

## Puja els canvis a Github

Cada vegada que completis un cicle TDD, és a dir, un nou test que passa, puja els canvis:

```bash
git add .
git commit -m "Descripció del canvi"
git push
```

## Executa el resultat final

Una vegada hagis completat la implementació, pot usar l'ordre `mvn package` per a validar, compilar, testejar i empaquetar l'aplicació.

Però, per a poder generar un JAR executable, primer hauràs configurar Maven per a crear un JAR executable. Per a això cal modificar el fitxer `pom.xml`, especificant la classe principal al plugin `maven-jar-plugin`:

```xml
<build>
  <pluginManagement>
    <plugins>
      <plugin>
        <artifactId>maven-jar-plugin</artifactId>
        <version>3.4.2</version>
        <configuration>
          <archive>
            <manifest>
              <mainClass>eu.cifpfbmoll.App</mainClass>
            </manifest>
          </archive>
        </configuration>
      </plugin>
    </plugins>
  </pluginManagement>
</build>
```

Ara ja pots executar `mvn package`:

```bash
[INFO] Building jar: ~/Projects/fibonacci-tdd/target/fibonacci-tdd-1.0-SNAPSHOT.jar
[INFO] BUILD SUCCESS
```

I ja pots executar el programa:

```bash
java -jar target/fibonacci-tdd-1.0-SNAPSHOT.jar 10
```