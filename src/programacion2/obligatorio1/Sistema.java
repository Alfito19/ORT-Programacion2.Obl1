package programacion2.obligatorio1;

//Joaquin Hernandez (257620)
//Alfonso Saizar (305968)

import java.time.Instant;
import java.time.Duration;
import java.util.Scanner;
public class Sistema {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        boolean deseaJugar = false;
        System.out.println("¿Desea jugar? Responda utilizando Y para si, N para no");
        while(!deseaJugar){
            String primerRespuesta = in.nextLine();
            // Al seleccionar Y comienza el juego
            if(primerRespuesta.equalsIgnoreCase("Y")){
                Instant tiempoInicial = Instant.now();
                // Creamos el tablero
                tableroCartas tablero = new tableroCartas();
                System.out.println("¿Con qué tipo de tablero le gustaría jugar?");
                System.out.println("a) Tablero desde archivo (datos.txt) \nb) Tablero predeterminado \nc) Tablero al azar, deberá definir las dimensiones del tablero y el nivel de dificultad");
                // En base a la segunda respuesta seteamos el tablero seleccionado
                String segundaRespuesta = in.nextLine();
                boolean tableroSeleccionado = false;
                while(!tableroSeleccionado){
                    if(segundaRespuesta.equalsIgnoreCase("a")){
                        tablero.setTableroArchivo();
                        tableroSeleccionado = true;
                    }else if(segundaRespuesta.equalsIgnoreCase("b")){
                        tablero.setTableroPredeterminado();
                        tableroSeleccionado = true;
                    }else if(segundaRespuesta.equalsIgnoreCase("c")){
                        System.out.println("Ingrese cantidad de filas (3-9)");
                        String filaS = in.nextLine();
                        // Controlo que fila sea un numero valido
                        try{
                            int fila = Integer.parseInt(filaS);
                            if(fila <= 9 && fila >= 3){
                                System.out.println("Ingrese cantidad de columnas (3-9)");
                                String colsS = in.nextLine();
                                // Controlo que cols sea un numero valido
                                try{
                                    int cols = Integer.parseInt(colsS);
                                    if(cols <= 9 && cols >= 3){
                                        System.out.println("Ingrese nivel de dificultad (1-8)");
                                        String nivelS = in.nextLine();
                                        // Controlo que nivel sea un numero valido
                                        try{
                                            int nivel = Integer.parseInt(nivelS);
                                            if(nivel <= 8 && nivel >= 1){
                                                tablero.setTableroRandom(fila, cols, nivel);
                                                tableroSeleccionado = true;
                                            }
                                            else{
                                                System.out.println("Nivel inválido, ingresa nuevamente todos los campos.\nIngresa la fila");
                                            }
                                        } catch (NumberFormatException e) {
                                            System.out.println("Nivel inválido, ingresa nuevamente todos los campos.\nIngresa la fila");
                                        }
                                    }
                                    else{
                                        System.out.println("Cantidad de columnas inválida, ingresa nuevamente todos los campos.\nIngresa la fila");
                                    }
                                    
                                } catch (NumberFormatException e) {
                                    System.out.println("Cantidad de columnas inválida, ingresa nuevamente todos los campos.\nIngresa la fila");
                                }
                            }
                            else{
                                System.out.println("Cantidad de filas inválido, ingresa nuevamente todos los campos.\nIngresa la fila");
                            }
                            
                        } catch (NumberFormatException e) {
                            System.out.println("Cantidad de filas inválido, ingresa nuevamente todos los campos.\nIngresa la fila");
                            filaS = in.nextLine();
                        }
                    }else{
                        System.out.println("Opcion inválida, responda con a, b o c para generar el tablero");
                        segundaRespuesta = in.nextLine();
                    }
                }

                // Mostramos el tablero base, imprimimos menu con opciones y empezamos a contar el tiempo
                tablero.imprimirTablero();
                tiempoInicial = Instant.now();
                System.out.println("\n\nRecuerde que siempre podrá utilizar los sigueintes comandos \n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion");
                System.out.println("En caso contrario, realice un movimiento ingresando la fila correspondiente al movimiento deseado.\n");
                String tercerRespuesta = in.nextLine();
                boolean ListoParaJugar = true;
                while(ListoParaJugar){
                    // Aca confirmamos tercerRespuesta es numero, en caso de que si, ejecuta las siguientes comprobaciones
                    try{
                        int fila = Integer.parseInt(tercerRespuesta);
                        // En caso de que ingrese un numero fuera de rango
                        if(fila > tablero.getTablero().length || fila < -1 || fila == 0){
                            System.out.println("No existe esa fila, recuerda que hay entre 1 y " + tablero.getTablero().length + " filas");
                            System.out.println("Ingresa nuevamente la fila\n");
                            tercerRespuesta = in.nextLine();
                        }
                        // En caso de que por casualidad ingrese -1 en el primer movimiento, le diremos que debe aplicar algun movimiento antes de retroceder.
                        else if(tablero.getHistorial().size() <= 0 && fila == (-1)){
                            System.out.println("Debe realizar un movimiento antes de poder retroceder");
                            System.out.println("Ingrese una de las siguientes opciones\n- (-1) para retroceder un movimiento\n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila correspondiente al movimiento\n");
                            tercerRespuesta = in.nextLine();
                        }
                        else{
                            // Si es posible retroceder solicitara que se repita la opcion -1 o cancelar con cualquier otra orden
                            if(fila == -1){
                                System.out.println("Si desea retroceder un movimiento ingrese (-1) en columna o canele con cualquier otra tecla.");
                            }
                            // En caso de que no retroceda, imprimira que ingrese la columna
                            else{
                                System.out.println("La fila será " + tercerRespuesta + " ingrese columna");
                            }
                            System.out.println();
                            String cuartaRespuesta = in.nextLine();
                            // Aca confirmamos cuartaRespuesta es numero, en caso de que si, ejecuta las siguientes comprobaciones
                            try{
                                int col =Integer.parseInt(cuartaRespuesta);
                                // En caso de que quiera retroceder
                                if(col == (-1) && fila == (-1)){
                                    tablero.retroceder(-1,-1);
                                    tablero.imprimirTablero();
                                    System.out.println("\nIngrese una de las siguientes opciones\n- (-1) para retroceder un movimiento\n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila correspondiente al movimiento\n");
                                    tercerRespuesta = in.nextLine();
                                }
                                // En caso de haber empezado el proceso de retroceder y quiera cancelar
                                else if(fila == -1 && col != -1){
                                    System.out.println("Retroceso cancelado");
                                    System.out.println("Ingrese una de las siguientes opciones\n- (-1) para retroceder un movimiento\n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila correspondiente al movimiento\n");
                                    tercerRespuesta = in.nextLine();
                                }
                                // En caso de que ingrese -1 en col pero no en fila
                                else if(col == -1 && fila != -1){
                                    System.out.println("El proceso de retroceso debe iniciarse con la fila, intentelo de nuevo\n");
                                    tercerRespuesta = in.nextLine();
                                }
                                // En caso de que ingrese un numero fuera de rango
                                else if(col > tablero.getTablero()[0].length || col < -1|| col == 0){
                                    System.out.println("No existe esa columna, recuerda que hay entre 1 y " + tablero.getTablero()[0].length + " columnas");
                                    System.out.println("Ingresa nuevamente la columna\n");
                                    cuartaRespuesta = in.nextLine();
                                }
                                // En caso de que el movimiento a ingresar sea igual al ultimo movimiento ingresado
//                                else if(tablero.getSoluciones().get(tablero.getSoluciones().size()-1).getFilas()+1 == fila && tablero.getSoluciones().get(tablero.getSoluciones().size()-1).getCols()+1 == col){
                                else if(!tablero.getHistorial().isEmpty() && tablero.getHistorial().get(tablero.getHistorial().size()-1).getFilas()+1 == fila && tablero.getHistorial().get(tablero.getHistorial().size()-1).getCols()+1 == col){
                                    tablero.retroceder(col,fila);
                                    tablero.imprimir2Tableros(); // Imprimir tablero con movimiento (dos tableros)
                                    System.out.println();
                                    if(tablero.checkWin()){
                                        System.out.println("¡GANASTE!");
                                        deseaJugar = false;
                                        tableroSeleccionado = false;
                                        ListoParaJugar = false;
                                    }
                                    else{
                                        System.out.println("Ingrese una de las siguientes opciones\n- (-1) para retroceder un movimiento\n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila correspondiente al movimiento\n");
                                        tercerRespuesta = in.nextLine();
                                    }
                                }
                                // En caso de que este todo ok, ingresara el movimiento
                                else{
                                    tablero.agregarMov(col-1, fila-1);
                                    tablero.imprimir2Tableros();// Imprimir tablero con movimiento (dos tableros)
                                    System.out.println("\n");
                                    if(tablero.checkWin()){
                                        System.out.println("¡GANASTE!");
                                        deseaJugar = false;
                                        tableroSeleccionado = false;
                                        ListoParaJugar = false;
                                    }
                                    else{
                                        System.out.println("Ingrese una de las siguientes opciones\n- (-1) para retroceder un movimiento\n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila correspondiente al movimiento\n");
                                        tercerRespuesta = in.nextLine();
                                    }
                                }
                            }
                            //En caso de que cuartaRespuesta no sea numero, confirmamos que sea H para mostrar historial o S para mostrar una solucion, en caso de ser X no entraría al while y en cualquier otro caso mostramos error y opciones nuevamente
                            catch(NumberFormatException e){
                                if(cuartaRespuesta.toUpperCase().equals("H")){
                                    tablero.mostrarHistorial();
                                    System.out.println("Ingrese una de las siguientes opciones\n- (-1) para retroceder un movimiento\n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila correspondiente al movimiento\n");
                                    tercerRespuesta = in.nextLine();
                                }else if(cuartaRespuesta.toUpperCase().equals("S")){
                                    tablero.mostrarSoluciones();
                                    System.out.println("Ingrese una de las siguientes opciones\n- (-1) para retroceder un movimiento\n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila correspondiente al movimiento\n");
                                    tercerRespuesta = in.nextLine();
                                }else if(cuartaRespuesta.toUpperCase().equals("X")){
                                    finDelJuego(tablero, tiempoInicial,cuartaRespuesta);
                                    System.exit(0);
                                }else{
                                    System.out.println("Entrada no válida, recuerda que las opciones son: \n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila correspondiente al movimiento");
                                    if(fila == -1){
                                        System.out.println("- Fila correspondiente al movimiento\n");
                                        tercerRespuesta = in.nextLine();
                                    }
                                    else{
                                        System.out.println();
                                        tercerRespuesta = in.nextLine();
                                    }
                                }
                            }
                        }
                    }
                    // En caso de que tercerRespuesta no sea numero, confirmamos que sea H para mostrar historial o S para mostrar una solucion, en caso de ser X no entraría al while y en cualquier otro caso mostramos error y opciones nuevamente
                    catch(NumberFormatException e){
                        if(tercerRespuesta.toUpperCase().equals("H")){
                            tablero.mostrarHistorial();
                            System.out.println("Ingrese una de las siguientes opciones\n- (-1) para retroceder un movimiento\n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila correspondiente al movimiento\n");
                            tercerRespuesta = in.nextLine();
                        }else if(tercerRespuesta.toUpperCase().equals("S")){
                            tablero.mostrarSoluciones();
                            System.out.println("Ingrese una de las siguientes opciones\n- (-1) para retroceder un movimiento\n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila correspondiente al movimiento\n");
                            tercerRespuesta = in.nextLine();
                        }
                        else if(tercerRespuesta.toUpperCase().equals("X")){
                                finDelJuego(tablero, tiempoInicial,tercerRespuesta);
                                System.exit(0);
                        }else{
                            System.out.println("Entrada no válida, recuerda que las opciones son: \n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila correspondiente al movimiento\n");
                            tercerRespuesta = in.nextLine();
                        }
                    }
                }
                finDelJuego(tablero,tiempoInicial,tercerRespuesta);
                if(tercerRespuesta.equals("X")){
                    System.exit(0);
                }
                else{
                    deseaJugar = false;
                }
            }else if(primerRespuesta.equalsIgnoreCase("N")){
                System.exit(0);
            }
            else{
                System.out.println("Opcion inválida, responda con Y para jugar o N para cerrar el menú");
            }
        }
        in.close();
    }
    
    public static void finDelJuego(tableroCartas tablero,Instant tiempoInicial,String respuesta){
        System.out.println("Fin del juego.");
        Duration duracion = Duration.between(tiempoInicial, Instant.now());
        // Obtiene los minutos y segundos de la duración
        long minutos = duracion.toMinutes();
        long segundos = duracion.minusMinutes(minutos).getSeconds();
        // Imprime el resultado en formato Minutos:Segundos
        System.out.println("Tiempo transcurrido: " + minutos + " minutos y " + segundos + " segundos");
        if (!respuesta.equalsIgnoreCase("X")){
            System.out.println("¿Desea volver a jugar? Y para si, N para no");
        } 
    }
}