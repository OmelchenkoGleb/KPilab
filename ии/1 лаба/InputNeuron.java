import java.util.HashSet;
import java.util.Set;
public class InputNeuron implements Neuron{
    private Set<Neuron> connections = new HashSet<>();
    @Override
    public void forwardSignalReceived(Neuron from, Double value) {
        connections.forEach(connectNuron -> connectNuron.forwardSignalReceived(this, value));
    }
    @Override
    public void addForwardConnection(Neuron neuron) {
        connections.add(neuron);
    }
    @Override
    public void addBackwardConnection(Neuron neuron, Double weight) {
        System.out.println("Не куда конектиктся назад так как это входные данные");
    }
}
