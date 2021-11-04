import java.util.*;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

public class IntermediateNeuron implements ActivationFunction, Neuron{
    Map<Neuron, Double> backwardConnections = new HashMap<>();
    Set<Neuron> forwardConnections = new HashSet<>();
    Map<Neuron, Double> inputSignals = new HashMap<>();
    int signalReceived;
    double forwardResult;
    @Override
    public void addForwardConnection(Neuron neuron) {
        forwardConnections.add(neuron);
    }
    @Override
    public void addBackwardConnection(Neuron neuron, Double weight) {
        backwardConnections.put(neuron, weight);
        inputSignals.put(neuron, Double.NaN);
    }
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
    public void forwardSignalNext(Neuron from, Double value) {forwardConnections.forEach(connectNuron -> connectNuron.forwardSignalReceived(this, value));}
    public void clear (){
        backwardConnections = new HashMap<>();
        inputSignals = new HashMap<>();
        forwardConnections = new HashSet<>();
        signalReceived = 0;
        forwardResult = 0;
    }
    @Override
    public double ActivationFunctionDx() {
        return (exp(-backwardConnections.entrySet().stream().mapToDouble(connection -> inputSignals.get(connection.getKey()) * connection.getValue()).sum()))/(pow((1+exp(-backwardConnections.entrySet().stream().mapToDouble(connection -> inputSignals.get(connection.getKey()) * connection.getValue()).sum())),2));
    }
}
