/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivoaccesoaleatorio2;

import java.io.*;

/**
 *
 * @author Daniel Hebrero Núñez
 */
public class Registro {
    
    protected char nombre[];
    protected float parcial1, parcial2, talleres, proyecto;
    protected int tam; //Almacena el tamaño del registro
    
    public Registro(char nombre[], float nota1, float nota2, float nota3, float nota4){
        this.nombre = new char[25];
        this.nombre = nombre;
        
        parcial1 = nota1;
        parcial2 = nota2;
        talleres = nota3;
        proyecto = nota4;
    }    
        /*
        El tamaño total del registro será de 66 bytes:
        (25*2) + (4*4)
        (25 char que forman el nombre * 2 bytes que ocupa cada char ) +
        (4 notas de tipo float * 4 bytes, ya que los datos de tipo float ocupan 4 bytes)
        */
        
        //Ejemplo de escritura. Recibimos el objeto RandomAccessFile, el objeto Registro y el objeto File
        
        public static void grabarRegistro(RandomAccessFile aa, Registro estudiante, File fal) throws IOException{
            
            //Desplazarse al final del archivo
            int lon = (int)fal.length();
            aa.seek(lon);
            
            //Escribir datos del registro en el archivo aleatorio
            for (int i=0; i< (estudiante.nombre).length; i++)
                aa.writeChar(estudiante.nombre[i]);
            
            aa.writeFloat(estudiante.parcial1);
            aa.writeFloat(estudiante.parcial2);
            aa.writeFloat(estudiante.talleres);
            aa.writeFloat(estudiante.proyecto);
            
            //mostramos el tamaño del fichero una vez escrito en el fichero
            lon = (int)fal.length();
            System.out.println("Length file: " + lon);
        }
        /*
        Declaramos el procedimiento que primero calcula cuantos registros hay
        en el fichero, luego llama a una función que se encarga de leer todos
        los registros del fichero, y por último, mostramos por pantalla dichos
        registros
        */
        
        //Recibimos el objeto RandomAccessFile y el objeto File
        public static void leerDatos(RandomAccessFile aa, File fal)throws IOException{
            int pos = 0;
            int lon = (int)fal.length();
            /*
            Con la función length nos devuelve lo que ocupa el fichero.
            Lo convertiremos a un valor de tipo int y lo almacenamos en la variable
            longitud
            */
            int lr = 66; //Lo que ocupa cada registro.
            int regs = lon / lr; //número de registros en el fichero
            System.out.println("Número total de registros en el fichero: " + 
                    regs);
            System.out.println("Cada registro ocupa: " + lr);
            
            /*
            Si hay algún registro en el fichero, vamos a ir leyendo cada registro
            del fichero. Para leer un registro llamamos a la función leerRegistro,
            que nos devuelve un objeto de la clase registro. Dicho objeto lo vamos
            a ir almacenando en un array que va a contener datos de tipo registro.
            De esa manera, cuando lleguemos al final del fichero, tendremos en ese
            array todos los registros que habia en el fichero
            */
            
            if(regs > 0){
                Registro vector[] = leerRegistro(aa, regs, lr);
                for(int i=0; i<regs; i++){
                    /*
                    Hacemos tantas pasadas en el bucle como registros haya. Recordar
                    que quien me indica el número de registros es la variable regs
                    */
                    
                    //Convertimos el array[] del campo nombre a string, y mostramos por
                    //Pantalla el alumno y sus notas.
                    
                    String nom = new String(vector[i].nombre);
                    System.out.println(nom + " " + vector[i].parcial1 +
                            " " + vector[i].parcial2 + " " + vector[i].talleres +
                            " " + vector[i].proyecto);
                }
            }
            else
                System.out.println("Archivo vacio !!!");
        }
            /*
            Se define la función que va a leer los registros que hay en el fichero
            y va a devolver como resultado un array de registro con todos los registros
            del fichero.
            */
            public static Registro[] leerRegistro(RandomAccessFile aa, int regs, int lr)throws IOException{
                /*
                Se crea un array de tipo registro que es lo que va a devolver la funcion,
                indicando con la variable regs el número de registros que va a tener
                */
                Registro vector[] = new Registro[regs];
                //Nos posicionamos dentro del archivo
                aa.seek(0);

                for (int i = 0; i < regs; i++){
                    /*
                    Hacemos tantas pasadas como registros haya en el fichero
                    */
                    char nombre[] = new char[25];
                    /*
                    Leemos el nombre carácter a carácter a través de un bucle. Hacemos tantas
                    pasadas como longitud tenga el nombre. Recordad que el nombre está almacenado en un
                    array char de 25 posiciones
                    */
                    for (int k = 0; k < nombre.length; k++)
                        nombre[k] = aa.readChar();
                    //Almacenamos el nombre leido en una variable string.
                    String aux = new String(nombre);

                    float nota1 = aa.readFloat(); //leemos las 4 notas
                    float nota2 = aa.readFloat();
                    float nota3 = aa.readFloat();
                    float nota4 = aa.readFloat();

                    /*
                    Una vez leído el primer registro, almacenamos los campos de dicho
                    registro en una variable de tipo registro
                    */
                    Registro datos = new Registro(nombre, nota1, nota2, nota3, nota4);

                    /*
                    Se almacena dicho registro en la siguiente posicion vacia del array de
                    registros
                    */
                    vector[i] = datos;
                }
                //Devolvemos el array con los registros del fichero
                return vector;
            }
}   
    