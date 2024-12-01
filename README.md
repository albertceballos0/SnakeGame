# Snake Game en Java

Este es un juego clásico de la serpiente implementado en Java. El objetivo del juego es controlar una serpiente que se desplaza por la pantalla, recogiendo comida y evitando chocar contra las paredes, su propio cuerpo o los obstáculos. El juego tiene diferentes niveles de dificultad y una interfaz gráfica en Java utilizando Swing.

## Características

- **Interfaz gráfica**: Utiliza `Swing` para la interfaz de usuario.
- **Dificultad ajustable**: Tres niveles de dificultad (Fácil, Medio, Difícil).
- **Obstáculos**: Obstáculos generados aleatoriamente en el tablero.
- **Almacenamiento de puntuaciones**: La puntuación aumenta con cada comida que la serpiente recoge.
- **Reinicio y salida**: Permite reiniciar el juego o salir al menú en cualquier momento.

## Requisitos

- **Java 8 o superior**.
- **JUnit 5** (para pruebas unitarias).

## Estructura del Proyecto

El proyecto sigue una arquitectura MVC (Modelo-Vista-Controlador), donde:

- **Modelo (`models`)**: Contiene las clases que representan el estado y la lógica del juego, como la serpiente, la comida, los obstáculos, y el tablero.
- **Vista (`views`)**: Contiene las clases encargadas de la representación gráfica del juego y la interfaz de configuración.
- **Controlador (`controllers`)**: Gestiona la interacción entre el modelo y la vista. Se encarga de procesar las entradas del usuario y actualizar la vista en consecuencia.

### Clases Principales

- **SnakeGame**: Controla la lógica del juego, como la actualización del estado, la detección de colisiones y el manejo de la comida y los obstáculos.
- **SnakeView**: Gestiona la interfaz gráfica del juego, mostrando la serpiente, la comida, los obstáculos y el puntaje en la pantalla.
- **SnakeController**: Maneja las entradas del teclado para controlar el movimiento de la serpiente y los eventos del juego (inicio, reinicio, salir, etc.).
- **GameSetUp**: Presenta una ventana para configurar el juego antes de comenzar (nombre del jugador, tamaño del tablero y nivel de dificultad).

## Instrucciones de Uso
### Instrucciones para Compilar y Ejecutar

#### Paso 1: Clonar el repositorio

Si aún no has clonado el repositorio, usa el siguiente comando:

```bash
git clone https://github.com/albertceballos0/SnakeGame.git
cd SnakeGame
```

#### Paso 2: Compilar el código fuente

Para compilar el código, ejecuta el siguiente comando desde la raíz del proyecto:

```bash
javac -d bin src/main/java/main/Main.java src/main/java/controllers/SnakeController.java src/main/java/models/SnakeGame.java src/main/java/models/SnakeObstacle.java src/main/java/models/SnakeBoard.java src/main/java/models/SnakePlayer.java src/main/java/models/SnakeFood.java src/main/java/views/GameSetUp.java src/main/java/views/SnakeView.java
```

Esto generará los archivos `.class` en el directorio `bin`.

#### Paso 3: Ejecutar el programa

Una vez compilado, puedes ejecutar el juego con el siguiente comando:

```bash
java -cp bin main.Main
```

#### Notas

- Asegúrate de tener instalado el JDK en tu máquina.
- Si tienes algún problema con las dependencias o la compilación, revisa que las rutas de los archivos fuente sean correctas.

### 2. Configuración

Al iniciar el juego, aparecerá una ventana para ingresar el nombre del jugador, seleccionar el tamaño del tablero y elegir la dificultad (fácil, media o difícil). Después de configurar todo, podrás comenzar a jugar.

### 3. Controles

- **Teclas de flecha (Arriba, Abajo, Izquierda, Derecha)**: Mueven la serpiente en la dirección correspondiente.
- **Espacio**: Reinicia el juego si la partida ha terminado.
- **Escape**: Vuelve al menú de configuración.
- **Ctrl+C**: Cierra el juego.

### 4. Fin del Juego

Cuando la serpiente choca con los bordes del tablero, su propio cuerpo o un obstáculo, el juego termina. Puedes presionar "Escape" para volver al menú de configuración o "Espacio" para reiniciar el juego.

## Arquitectura del Código

### 1. **Modelo (models)**

- **SnakeGame**: Controla la lógica del juego (estado de la serpiente, la comida, las colisiones, etc.).
- **SnakePlayer**: Representa a la serpiente, su cuerpo y dirección.
- **SnakeBoard**: Representa el tablero de juego, las dimensiones, la comida y los obstáculos.
- **SnakeFood**: Representa la comida que la serpiente recoge.
- **SnakeObstacle**: Representa un obstáculo en el tablero.

### 2. **Vista (views)**

- **SnakeView**: La vista principal del juego que dibuja la serpiente, la comida, los obstáculos y el puntaje.
- **GameSetUp**: Proporciona una interfaz de usuario para configurar el juego antes de comenzar.

### 3. **Controlador (controllers)**

- **SnakeController**: Controla las entradas del teclado y la lógica para iniciar, reiniciar y cerrar el juego.

## Ejecutar Pruebas

Para ejecutar las pruebas del proyecto, usa el siguiente comando con Maven:

```bash
mvn test
```

Si deseas ejecutar una prueba en particular, puedes usar:

```bash
mvn -Dtest=tests.SnakeBoardTest test
```

Para ejecutar todos los tests en el proyecto:

```bash
mvn test
```

## Integración Continua con GitHub Actions

Este proyecto está configurado para usar **GitHub Actions** para la integración continua. Cada vez que se realice un `push` o `pull request` a la rama principal, GitHub Actions ejecutará las pruebas y construirá el proyecto automáticamente.

El archivo de flujo de trabajo (`.github/workflows/ci.yml`) contiene los pasos para instalar Java, ejecutar Maven y probar el código.

## Contribuciones

Si deseas contribuir al proyecto, por favor, crea un **fork** del repositorio, realiza tus cambios y envía un **pull request**.

## Licencia

Este proyecto está bajo la **Licencia MIT**.

Aquí tienes el README actualizado con la información precisa sobre los modelos, vistas y controladores según la estructura que has indicado:






