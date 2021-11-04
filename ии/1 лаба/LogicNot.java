public class LogicNot {
    LogicNot(){
        System.out.println("LogicNot");
        InputNeuron input1 = new InputNeuron();
        OutputNeuron outputNeuron = new OutputNeuron() {
            @Override
            public double ActivationFunction(Double x) {
                return x >= -1 ? 1 : 0;
            }
        };
        Result(input1,outputNeuron,0);
        Result(input1,outputNeuron,1);
    }
    void Result (InputNeuron input1, OutputNeuron outputNeuron, double value){
        input1.connect(outputNeuron, -1.5);
        input1.forwardSignalReceived(outputNeuron, value);
        System.out.println(value + " not => " + outputNeuron.forwardResult);
        outputNeuron.clear();
    }
}
