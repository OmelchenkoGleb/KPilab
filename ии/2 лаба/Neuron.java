public interface Neuron {

    void forwardSignalReceived(Neuron from, Double value);
    void addForwardConnection(Neuron neuron);
    void addBackwardConnection(Neuron neuron, Double weight);
    default void connect(Neuron neuron, Double weight) {
        this.addForwardConnection(neuron);
        neuron.addBackwardConnection(this, weight);
    }
}
