/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perceptronand;


import java.io.FileNotFoundException;
import java.io.IOException;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.nnet.Perceptron;
import org.neuroph.util.TrainingSetImport;
/**
 *
 * @author Dhaby Xiloj <dhabyx@gmail.com>
 */
public class PerceptronAND {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NeuralNetwork neuralNetwork = new Perceptron(2, 1);
        
        DataSet trainingSet = null;
        try {
            trainingSet = TrainingSetImport.importFromFile("and.csv", 2, 1, ",");
        } catch (FileNotFoundException ex) {
            System.err.println("Archivo no encontrado");
        } catch (IOException | NumberFormatException ex) {
            System.err.println("Error leyendo el formato del archivo");
        }
        
        neuralNetwork.learn(trainingSet);
        neuralNetwork.save("perceptron.nnet");
        
        neuralNetwork.setInput(1,1);
        neuralNetwork.calculate();
        
        double[] salida = neuralNetwork.getOutput();
        
        for (int i =0; i<salida.length; i++) {
            System.out.println("Salida No."+(i+1)+": "+salida[i]);
        }
    }
    
}
