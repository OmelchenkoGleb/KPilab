import java.util.*;
import static java.lang.Math.exp;
import static java.lang.Math.pow;
public class OutputNeuron implements ActivationFunction, Neuron {
        Map<Neuron, Double> backwardConnections = new HashMap<>();
        Map<Neuron, Double> inputSignals = new HashMap<>();
        int signalReceived;
        double forwardResult;
        @Override
        public void forwardSignalReceived(Neuron from, Double value) {
            signalReceived++;
            inputSignals.put(from, value);
            if (backwardConnections.size() == signalReceived) forwardResult = ActivationFunction(backwardConnections.entrySet().stream().mapToDouble(connection -> inputSignals.get(connection.getKey()) * connection.getValue()).sum());
        }
        @Override
        public double ActivationFunction(Double x) {
            return x;
        }
        @Override
        public void addBackwardConnection(Neuron neuron, Double weight) {
            backwardConnections.put(neuron, weight);
        }
        @Override
        public void addForwardConnection(Neuron neuron) {
            System.out.println("Далее не к кому приконектится так как это выходной элемент");
        }
        public void clear (){
            backwardConnections = new HashMap<>();
            inputSignals = new HashMap<>();
            signalReceived = 0;
            forwardResult = 0;
        }
        @Override
        public double ActivationFunctionDx() {
            return (exp(-backwardConnections.entrySet().stream().mapToDouble(connection -> inputSignals.get(connection.getKey()) * connection.getValue()).sum()))/(pow((1+exp(-backwardConnections.entrySet().stream().mapToDouble(connection -> inputSignals.get(connection.getKey()) * connection.getValue()).sum())),2));
        }
}
