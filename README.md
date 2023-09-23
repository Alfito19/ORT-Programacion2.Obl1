# ORT-Programacion2
Obligatorio - Programacion 2  
Universidad ORT Uruguay - Licenciatura en sistemas
 
Juego de solitario, que se juega en un tablero rectangular de máximo 9*9. El tablero contiene
en cada posición uno de estos símbolos: “/”, “\”, “-“ o “|”, cada uno de color rojo o azul. El objetivo es tener todo el tablero
de color rojo o todo de color azul. El jugador indica en cada movimiento la posición (indicando fila y columna) a cambiar
de color (de rojo a azul o viceversa). Según el símbolo, se cambia de color completamente la diagonal /, diagonal \, fila - o
columna | que pasa por esa posición. Los símbolos solamente cambian de color. Si se logró el objetivo, termina el juego
informando el tiempo total de la partida.
En el caso general, al comienzo del juego se ingresan la cantidad de filas y de columnas del tablero (entre 3 y 9). También
se indica el nivel (1 a 8). El tablero se genera al azar, pero debe asegurarse que se debe poder resolver en la cantidad de
pasos indicados en el nivel.

La fila y la columna se ingresan de a una por vez, por separado.
Si se ingresa “X” se termina ese juego.
Si se ingresa “H” se muestra una lista con toda la historia de movimientos realizados en formato (fila, columna) Ej. (3,4),
(1,2), etc.
Si se ingresa “S” se debe mostrar una lista con la secuencia de movimientos que llevan del estado actual del tablero a una
solución en formato (fila, columna).

Se pide:
Implementar en Java con interfaz consola el siguiente programa:
El programa comienza preguntando si se desea jugar. En caso de desear jugar las opciones aplicables a ese juego en
particular son:
a) Tomar datos del archivo “datos.txt”
b) Usar el tablero predefinido
c) Usar un tablero al azar. En este caso se ingresa m (cantidad de filas), n (cantidad de columnas), nivel (1 a 8). El
tablero a generar debe ser resoluble en esa cantidad de pasos indicada por el nivel.
En cualquiera de las opciones, se podrá jugar ingresando las coordenadas o las letras “X”, “H”, “S”. Luego de cada jugada
se muestran los tableros (previo y actual) si corresponde. Si se retrocedió, solamente se muestra el correspondiente.

Al terminar la partida, informar el tiempo total insumido, y si se desea jugar una nueva partida (donde se configurarán las
opciones deseadas).
