package programacion2.obligatorio1;

//Joaquin Hernandez (257620)
//Alfonso Saizar (305968)

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
                // Creamos el tablero
                tableroCartas tablero = new tableroCartas();
                tablero.setTiempoInicial();
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
                imprimirTablero(tablero.getTablero());
                tablero.setTiempoInicial();
                System.out.println("\n\nRecuerde que siempre podrá utilizar los sigueintes comandos \n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion");
                System.out.println("En caso contrario, realice un movimiento ingresando por separado la fila y columna correspondientes al movimiento deseado.\n");
                // Solicita "tercerRespuesta (fila)" y "cuartaRespuesta (col)" para luego en el try-catch, transformarlo a numero, en caso de que no pueda lo tomará como excepción y verificará si es una de las opciones [X, H, S]. En caso de que se pueda transformar a numero, lo procesará como movimiento chequeando que sea un movimiento válido.
                String tercerRespuesta = in.nextLine();
                if(tercerRespuesta.toUpperCase().equals("H")){
                    mostrarHistorial(tablero);
                    System.out.println("Ingrese una de las siguientes opciones\n- (-1) para retroceder un movimiento\n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila y columna correspondiente al movimiento\n");
                    tercerRespuesta = in.nextLine();
                }else if(tercerRespuesta.toUpperCase().equals("S")){
                    mostrarSoluciones(tablero);
                    System.out.println("Ingrese una de las siguientes opciones\n- (-1) para retroceder un movimiento\n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila y columna correspondiente al movimiento\n");
                    tercerRespuesta = in.nextLine();
                }
                else if(tercerRespuesta.toUpperCase().equals("X")){
                    System.out.println(tablero.finDelJuego(tercerRespuesta, "X"));
                    System.exit(0);
                }
                System.out.println("Ingrese columna\n");
                String cuartaRespuesta = in.nextLine();
                boolean ListoParaJugar = true;
                while(ListoParaJugar){
                    // Aca confirmamos tercerRespuesta es numero, en caso de que si, ejecuta las siguientes comprobaciones
                    try{
                        int fila = Integer.parseInt(tercerRespuesta);
                        int col = Integer.parseInt(cuartaRespuesta);
                        if(tablero.movimientoValido(col,fila)){
                            tablero.agregarMov(col,fila);
                            imprimir2Tableros(tablero); // Imprimir tablero con movimiento (dos tableros)
                            System.out.println();
                            if(tablero.checkWin()){
                                System.out.println("¡GANASTE!");
                                deseaJugar = false;
                                tableroSeleccionado = false;
                                ListoParaJugar = false;
                            }
                            else{
                                System.out.println("Ingrese una de las siguientes opciones\n- (-1) para retroceder un movimiento\n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila y columna correspondiente al movimiento\n");
                                tercerRespuesta = in.nextLine();
                                System.out.println("Ingrese columna\n");
                                cuartaRespuesta = in.nextLine();
                            }
                        }
                        else{
                            System.out.println("Entrada no válida, recuerda que las opciones son: \n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila y columna correspondiente al movimiento\n");
                            tercerRespuesta = in.nextLine();
                            System.out.println("Ingrese columna\n");
                            cuartaRespuesta = in.nextLine();
                        }
                    }
                    // En caso de que tercerRespuesta no sea numero, confirmamos que sea H para mostrar historial o S para mostrar una solucion, en caso de ser X no entraría al while y en cualquier otro caso mostramos error y opciones nuevamente
                    catch(NumberFormatException e){
                        if(tercerRespuesta.toUpperCase().equals("H") || (cuartaRespuesta.toUpperCase().equals("H"))){
                            mostrarHistorial(tablero);
                            System.out.println("Ingrese una de las siguientes opciones\n- (-1) para retroceder un movimiento\n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila y columna correspondiente al movimiento\n");
                            tercerRespuesta = in.nextLine();
                            System.out.println("Ingrese columna\n");
                            cuartaRespuesta = in.nextLine();
                        }else if(tercerRespuesta.toUpperCase().equals("S") || (cuartaRespuesta.toUpperCase().equals("S"))){
                            mostrarSoluciones(tablero);
                            System.out.println("Ingrese una de las siguientes opciones\n- (-1) para retroceder un movimiento\n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila y columna correspondiente al movimiento\n");
                            tercerRespuesta = in.nextLine();
                            System.out.println("Ingrese columna\n");
                            cuartaRespuesta = in.nextLine();
                        }
                        else if(tercerRespuesta.toUpperCase().equals("X") || (cuartaRespuesta.toUpperCase().equals("X"))){
                                System.out.println(tablero.finDelJuego(tercerRespuesta, cuartaRespuesta));
                                System.exit(0);
                        }else{
                            System.out.println("Entrada no válida, recuerda que las opciones son: \n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila y columna correspondiente al movimiento\n");
                            tercerRespuesta = in.nextLine();
                            System.out.println("Ingrese columna\n");
                            cuartaRespuesta = in.nextLine();
                        }
                    }
                }
            System.out.println(tablero.finDelJuego(tercerRespuesta, cuartaRespuesta));
            if(tercerRespuesta.equals("X") || cuartaRespuesta.equals("X")){
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
    
    // Mostrar historial
    public static void mostrarHistorial(tableroCartas tablero){
        for(int i=0; i < tablero.getHistorial().size(); i++){
            System.out.println(tablero.getHistorial().get(i));
        }
    }

    // Mostrar Soluciones
    public static void mostrarSoluciones(tableroCartas tablero){
        for(int i=0; i < tablero.getSoluciones().size(); i++){
            System.out.println(tablero.getSoluciones().get(i));
        }
    }
    
    // Imprimir 1 tablero
    public static void imprimirTablero(Carta[][] tablero){
        System.out.print(Carta.NC+" ");
        for (int j = 0; j < tablero[0].length; j++) {
            System.out.print("   "+(j+1));
        }
        System.out.println();
        // Imprimir las filas de la matriz
        for (int i = 0; i < tablero.length; i++) {
            System.out.print("  +");
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print("---+");
            }

            System.out.println();
            
            // Imprimir los elementos de la fila
            System.out.print(i + 1 + " |");
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.printf(" %s |", tablero[i][j]+Carta.NC);
            }
            System.out.println();
        }
        System.out.print("  +");
        for (int j = 0; j < tablero[0].length; j++) {
            System.out.print("---+");
        }
    }
    
    // Imprimir 2 tableros.
    public static void imprimir2Tableros(tableroCartas unTablero){
        Carta[][] tablero2 = unTablero.tableroAnterior();
        Carta[][] tablero = unTablero.getTablero();
        System.out.print(Carta.NC+" ");
        for (int j = 0; j < tablero[0].length; j++) {
            System.out.print("   "+(j+1));
        }
        System.out.print("            ");
        for (int j = 0; j < tablero[0].length; j++) {
            System.out.print("   "+(j+1));
        }
        System.out.println();
        // Imprimir las filas de la matriz
        for (int i = 0; i < tablero.length; i++) {
            System.out.print("  +");
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print("---+");
            }
            System.out.print("           +");
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print("---+");
            }
            
            System.out.println();
            
            // Imprimir los elementos de la fila
            System.out.print(i + 1 + " |");
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.printf(" %s |", tablero[i][j]+Carta.NC);
            }
            System.out.print("   ==>   "+(i+1)+" |");
            for (int j = 0; j < tablero2[i].length; j++) {
                System.out.printf(" %s |", tablero[i][j]+Carta.NC);
            }
            
            System.out.println();
        }
        System.out.print("  +");
        for (int j = 0; j < tablero[0].length; j++) {
            System.out.print("---+");
        }
        System.out.print("           +");
        for (int j = 0; j < tablero[0].length; j++) {
            System.out.print("---+");
        }
    }
}