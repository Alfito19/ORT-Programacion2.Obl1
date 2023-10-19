package programacion2.obligatorio1;

//Joaquin Hernandez (257620)
//Alfonso Saizar (305968)

import java.util.Scanner;
public class Sistema {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        boolean deseaJugar = true;
        System.out.println("�Desea jugar? Responda utilizando Y para si, N para no");
        while(deseaJugar){
            String primerRespuesta = in.nextLine();
            // Al seleccionar Y comienza el juego
            if(primerRespuesta.equalsIgnoreCase("Y")){           
                // Creamos el tablero
                tableroCartas tablero = new tableroCartas();
                tablero.setTiempoInicial();
                System.out.println("�Con qu� tipo de tablero le gustar�a jugar?");
                System.out.println("a) Tablero desde archivo (datos.txt) \nb) Tablero predeterminado \nc) Tablero al azar, deber� definir las dimensiones del tablero y el nivel de dificultad");
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
                                                System.out.println("Nivel inv�lido, ingresa nuevamente todos los campos.\nIngresa la fila");
                                            }
                                        } catch (NumberFormatException e) {
                                            System.out.println("Nivel inv�lido, ingresa nuevamente todos los campos.\nIngresa la fila");
                                        }
                                    }
                                    else{
                                        System.out.println("Cantidad de columnas inv�lida, ingresa nuevamente todos los campos.\nIngresa la fila");
                                    }
                                    
                                } catch (NumberFormatException e) {
                                    System.out.println("Cantidad de columnas inv�lida, ingresa nuevamente todos los campos.\nIngresa la fila");
                                }
                            }
                            else{
                                System.out.println("Cantidad de filas inv�lido, ingresa nuevamente todos los campos.\nIngresa la fila");
                            }
                            
                        } catch (NumberFormatException e) {
                            System.out.println("Cantidad de filas inv�lido, ingresa nuevamente todos los campos.\nIngresa la fila");
                            filaS = in.nextLine();
                        }
                    }else{
                        System.out.println("Opcion inv�lida, responda con a, b o c para generar el tablero");
                        segundaRespuesta = in.nextLine();
                    }
                }

                // Mostramos el tablero base, imprimimos menu con opciones y empezamos a contar el tiempo
                imprimirTablero(tablero);
                tablero.setTiempoInicial();
                System.out.println("\n\nRecuerde que siempre podr� utilizar los sigueintes comandos \n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion");
                System.out.println("En caso contrario, realice un movimiento ingresando por separado la fila y columna correspondientes al movimiento deseado.\n");
                // Declara "tercerRespuesta (fila)" y "cuartaRespuesta (col)" para luego en el try-catch, transformarlo a numero, en caso de que no pueda lo tomará como excepción y verificará si es una de las opciones [X, H, S]. En caso de que se pueda transformar a numero, lo procesará como movimiento chequeando que sea un movimiento válido.
                String tercerRespuesta = "";
                String cuartaRespuesta= "";
                
                boolean ListoParaJugar = true;
                boolean interactuado = false;
                while(ListoParaJugar){
                    tercerRespuesta = in.nextLine();
                    if(tercerRespuesta.equalsIgnoreCase("H")){
                        interactuado = true;
                        mostrarHistorial(tablero);
                        System.out.println("Ingrese una de las siguientes opciones\n- (-1) para retroceder un movimiento\n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila y columna correspondiente al movimiento\n");
                    }else if(tercerRespuesta.equalsIgnoreCase("S")){
                        interactuado = true;
                        mostrarSoluciones(tablero);
                        System.out.println("Ingrese una de las siguientes opciones\n- (-1) para retroceder un movimiento\n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila y columna correspondiente al movimiento\n");
                    }
                    else if(tercerRespuesta.equalsIgnoreCase("X")){
                        interactuado = true;
                        tableroSeleccionado = false;
                        ListoParaJugar = false;
                    }
                    else{
                        System.out.println("Ingrese columna\n");
                        cuartaRespuesta = in.nextLine(); 
                        if(cuartaRespuesta.equalsIgnoreCase("H")){
                            interactuado = true;
                            mostrarHistorial(tablero);
                            System.out.println("Ingrese una de las siguientes opciones\n- (-1) para retroceder un movimiento\n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila y columna correspondiente al movimiento\n");
                        }else if(cuartaRespuesta.equalsIgnoreCase("S")){
                            interactuado = true;
                            mostrarSoluciones(tablero);
                            System.out.println("Ingrese una de las siguientes opciones\n- (-1) para retroceder un movimiento\n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila y columna correspondiente al movimiento\n");
                        }
                        else if(cuartaRespuesta.equalsIgnoreCase("X")){
                            interactuado = true;
                            tableroSeleccionado = false;
                            ListoParaJugar = false;
                        }  
                    }
                    // Aca confirmamos tercerRespuesta es numero, en caso de que si, ejecuta las siguientes comprobaciones
                    try{
                        int fila = Integer.parseInt(tercerRespuesta);
                        int col = Integer.parseInt(cuartaRespuesta);
                        if(tablero.movimientoValido(col,fila)){
                            tablero.agregarMov(col,fila);
                            if(col == (-1) && fila == (-1)){
                                imprimirTablero(tablero);
                            }
                            else{
                                imprimir2Tableros(tablero); // Imprimir tablero con movimiento (dos tableros)
                            }
                            System.out.println();
                            if(tablero.checkWin()){
                                System.out.println("�GANASTE!");
                                tableroSeleccionado = false;
                                ListoParaJugar = false;
                            }
                            else{
                                System.out.println("Ingrese una de las siguientes opciones\n- (-1) para retroceder un movimiento\n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila y columna correspondiente al movimiento\n");
                            }
                        }
                        else{
                            System.out.println("Entrada no v�lida, recuerda que las opciones son: \n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila y columna correspondiente al movimiento\n");
                        }
                    }
                    // En caso de que tercerRespuesta o cuartaRespuesta no sea numero, confirmamos que sea H para mostrar historial o S para mostrar una solucion, en caso de ser X no entraría al while y en cualquier otro caso mostramos error y opciones nuevamente
                    catch(NumberFormatException e){
                        if(!interactuado){
                            System.out.println("Entrada no v�lida, recuerda que las opciones son: \n- X para cerrar el juego \n- H para ver el historial de movimientos \n- S para ver una solucion \n- Fila y columna correspondiente al movimiento\n");
                        }
                        else{
                            interactuado = false;
                        }
                    }
                }
                System.out.println(tablero.finDelJuego());
                if(tercerRespuesta.equalsIgnoreCase("N") || cuartaRespuesta.equalsIgnoreCase("N")){
                    System.exit(0);
                }
            }else if(primerRespuesta.equalsIgnoreCase("N")){
                System.exit(0);
            }
            else{
                System.out.println("Opcion inv�lida, responda con Y para jugar o N para cerrar el men�");
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
    public static void imprimirTablero(tableroCartas unTablero){
        Carta[][] tablero = unTablero.getTablero();
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
        Carta[][] tablero = unTablero.getTablero();
        Carta[][] anterior = unTablero.getTableroAnterior();
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
                System.out.printf(" %s |", anterior[i][j]+Carta.NC);
            }
            System.out.print("   ==>   "+(i+1)+" |");
            for (int j = 0; j < tablero[i].length; j++) {
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