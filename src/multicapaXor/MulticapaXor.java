/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicapaXor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JFileChooser;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.util.TrainingSetImport;

/**
 *
 * @author Dhaby Xiloj <dhabyx@gmail.com>
 */
public class MulticapaXor {
    
    JFileChooser fc;
    
    public MulticapaXor() {
        fc = new JFileChooser(System.getProperty("user.dir"));
    }
    
    public String cargarArchivo(){
        int resultado = fc.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile().getAbsolutePath();
        }
        else 
            return null;
    }
    
    public void RedXor(String archivoRed, DataSet datos) {
        NeuralNetwork redXor = NeuralNetwork.createFromFile(archivoRed);
        for (DataSetRow fila: datos.getRows() ){
            redXor.setInput(fila.getInput());
            redXor.calculate();
            double[] salida = redXor.getOutput();
            System.out.print("Entrada: "+Arrays.toString(fila.getInput()));
            if (salida[0]<0.2)
                System.out.println(" Salida: 0");
            else if (salida[0]>0.8)
                System.out.println(" Salida: 1");
            else
                System.out.println(" Salida: "+salida[0]);
        }   
    }
    
    public static void main(String[] args) {
        
        MulticapaXor xor = new MulticapaXor();
        DataSet datosEntrada = null;
        try {
            datosEntrada = TrainingSetImport.importFromFile("xorInput.csv", 2, 1, ",");
        } catch (FileNotFoundException ex ) {
            System.err.println("No se encuentra el archivo");
        } catch (IOException | NumberFormatException ex) {
            System.err.println("Error en el formato del archivo");
        }
        String archivo = xor.cargarArchivo();
        xor.RedXor(archivo, datosEntrada);
        
    }
}
