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
public class ArchivoAccesoAleatorio2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException {
        try{
            File ff = new File("ficheroAleatorio.txt");
            RandomAccessFile aa = new RandomAccessFile(ff, "rw");
            
            char nombre[] = new char[25]; //Creamos el array del nombre
            nombre[0] = 'D';
            nombre[1] = 'a';
            nombre[2] = 'n';
            nombre[3] = 'i';
            nombre[4] = 'e';
            nombre[5] = 'l'; 
            /*
            El resto de valores estarán vacios
            */
            
            //Creamos un objeto registro nuevo y le damos valores
            Registro reg = new Registro(nombre,5.0f,4.0f,4.0f,9.25f);
            
            //Se llama a los procedimientos y finalizamos el programa
            
            Registro.grabarRegistro(aa, reg, ff);
            Registro.leerDatos(aa, ff);
            
        
        }catch(IOException ioex){
            
        }
    }
    
}
