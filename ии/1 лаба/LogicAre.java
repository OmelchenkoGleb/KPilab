public class LogicAre {
    LogicAre(){
        System.out.println("LogicAre");
        InputNeuron input1 = new InputNeuron();
        InputNeuron input2 = new InputNeuron();
        OutputNeuron outputNeuron = new OutputNeuron() {
            @Override
            public double ActivationFunction(Double x) {
                return x >= 0.5 ? 1 : 0;
            }
        };
        double [] value = new double[2];
        value[0] = 0; value[1] = 0;
        Result(input1,input2,outputNeuron,value);
        value[0] = 1; value[1] = 0;
        Result(input1,input2,outputNeuron,value);
        value[0] = 0; value[1] = 1;
        Result(input1,input2,outputNeuron,value);
        value[0] = 1; value[1] = 1;
        Result(input1,input2,outputNeuron,value);
    }
    void Result (InputNeuron input1, InputNeuron input2, OutputNeuron outputNeuron, double [] value){
        input1.connect(outputNeuron, 1.);
        input2.connect(outputNeuron, 1.);
        input1.forwardSignalReceived(outputNeuron, value[0]);
        input2.forwardSignalReceived(outputNeuron, value[1]);
        System.out.println(value[0] + " and "+value[1]+" => " + outputNeuron.forwardResult);
        outputNeuron.clear();
    }
}
