package controlador;

import java.util.Scanner;

public class Principal {
    
    /* 
    Proyecto = AH2-RA2
    Nombre = Marco Vinicio Muralles Vasquez
    Carne = 2019-000335
    Descripcion = Juego de serpientes y escaleras
    Fecha = 17/02/2023
    version = 1.0
    */

    int f = 8;
    int c = 8;
    boolean inversa = false;
    boolean gameOver = false;
    boolean win = false;
    int array[][] = new int[f][c];
    String arrayPena[][] = new String[f][c];
    int contador = 0;

    String digitos = "";
    int posicionJugador = 1;
    int posicionPena = 56;
    Scanner entrada = new Scanner(System.in);
    Scanner entradaInt = new Scanner(System.in);
    int randomP = (int) (Math.random() * (7 - 0 + 1) + 0);
    int randomCPorFila = (int) (Math.random() * (3 - 1 + 1) + 1);
    int dados = 0;

    public static void main(String[] args) {
        Principal clase = new Principal();
        Scanner in = new Scanner(System.in);
        boolean salir = true;
        boolean sesion = false;
        int op = 0;

        while (salir) {
            System.out.println("\n===MENU PRINCIPAL==\n"
                    + "1. Juego Nuevo\n"
                    + "2. Retomar Juego\n"
                    + "3. Salir\n"
                    + "===================");
            System.out.print("Introduce la opcion ");
            op = in.nextInt();
            switch (op) {
                case 1:
                    clase.rellenarArrays();
                    clase.jugar();
                    sesion = true;
                    break;
                case 2:
                    if (sesion) {
                        clase.jugar();
                    } else {
                        clase.rellenarArrays();
                        clase.jugar();
                        sesion = true;
                    }
                    break;
                case 3:
                    salir = false;

                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    public void rellenarArrays() {
        contador = 0;
        posicionJugador = 1;
        
        //LLENAR LAS POSICIIONES DE NUMEROS
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                contador++;
                array[i][j] = contador;
            }
        }
        

        //ARRAY LLENAR PENAS DE ESPACIOS
        for (int i = 0; i < f; i++) {
            for (int j = 0; j < c; j++) {
                arrayPena[i][j] = " ";
            }
        }

        //ARRAY LLENAR # EN LAS PENAS
        for (int i = 0; i < f; i++) {
            randomCPorFila = (int) (Math.random() * (3 - 1 + 1) + 1);

            for (int j = 0; j < randomCPorFila; j++) {
                randomP = (int) (Math.random() * (7 - 0 + 1) + 0);
                arrayPena[i][randomP] = "#";
            }
        }
    }

    public void jugar() {

        while (true) {
            System.out.println("\nMAPA");
            System.out.println("-------------------------------------------------");

            for (int i = f - 1; i <= f && i != -1; i--) {

                if (inversa) {
                    //LINEA SUPERIOR
                    for (int j = c - 1; j <= c && j != -1; j--) {
                        digitos = Integer.toString(array[i][j]);
                        if (digitos.length() == 1) {
                            System.out.print("|    " + array[i][j]);

                        } else {
                            System.out.print("|   " + array[i][j]);
                        }
                    }
                    //LINEA INFERIOR
                    System.out.println("|");
                    for (int j = c - 1; j <= c && j != -1; j--) {

                        if (array[i][j] == posicionJugador) {
                            System.out.print("|@   " + arrayPena[i][j]);
                            if (arrayPena[i][j].equals("#")) {
                                gameOver = true;
                            }
                        } else {
                            System.out.print("|    " + arrayPena[i][j]);
                        }
                    }
                    inversa = false;
                } else {
                    //LINEA SUPERIOR
                    for (int j = 0; j < c; j++) {
                        digitos = Integer.toString(array[i][j]);
                        if (digitos.length() == 1) {
                            System.out.print("|    " + array[i][j]);
                        } else {
                            System.out.print("|   " + array[i][j]);
                        }
                    }
                    //LINEA INFERIOR
                    System.out.println("|");
                    for (int j = 0; j < c; j++) {
                        if (array[i][j] == posicionJugador) {
                            System.out.print("|@   " + arrayPena[i][j]);
                            if (arrayPena[i][j].equals("#")) {
                                gameOver = true;
                            }
                        } else {
                            System.out.print("|    " + arrayPena[i][j]);
                        }
                    }
                    inversa = true;
                }
                System.out.println("|");
                System.out.println("-------------------------------------------------");
            }

            if (posicionJugador > 64) {
                win = true;
            }

            System.out.println("Has avanzado " + dados + " casillas\n");

            if (gameOver) {
                System.out.println("¡Has caido en penalizacion!\n");
                gameOver = false;
            }

            if (win) {
                System.out.println("¡Felicidades has ganado!\n");
            }

            //PEQUENO MENU JUEGO
            System.out.print("Tirar dado con r o salir al menu con p? ");
            String respuesta = entradaInt.nextLine();
            if (respuesta.equals("r")) {
                dados = (int) (Math.random() * (6 - 2 + 1) + 2);
                posicionJugador += dados;

            } else if (respuesta.equals("p")) {
                break;

            } else {
                System.out.print("\nDesea salir? ");
                String salir = entrada.nextLine();
                if (salir.equals("s")) {
                    break;
                }
            }
        }
    }
}
