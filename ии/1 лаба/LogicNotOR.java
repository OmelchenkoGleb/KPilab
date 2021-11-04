public class LogicNotOR {
    LogicNotOR(){
        System.out.println("LogicNotOR");
        InputNeuron input1 = new InputNeuron();
        InputNeuron input2 = new InputNeuron();
        IntermediateNeuron intermediate1 = new IntermediateNeuron() {
            @Override
            public double ActivationFunction(Double x) {return x >= 0.5 ? 1 : 0;}
        };
        IntermediateNeuron intermediate2 = new IntermediateNeuron() {
            @Override
            public double ActivationFunction(Double x) {return x >= 0.5 ? 1 : 0;}
        };
        OutputNeuron outpu1 = new OutputNeuron() {
            @Override
            public double ActivationFunction(Double x) {return x >= 0.5 ? 1 : 0;}
        };

        double [] value = new double[2];
        value[0] = 0; value[1] = 0;
        Result(input1,input2,intermediate1,intermediate2, outpu1,value);
        value[0] = 1; value[1] = 0;
        Result(input1,input2,intermediate1,intermediate2, outpu1,value);
        value[0] = 0; value[1] = 1;
        Result(input1,input2,intermediate1,intermediate2, outpu1,value);
        value[0] = 1; value[1] = 1;
        Result(input1,input2,intermediate1,intermediate2, outpu1,value);
    }
    void Result (InputNeuron input1, InputNeuron input2,IntermediateNeuron intermediate1, IntermediateNeuron intermediate2, OutputNeuron outpu1, double [] value){
        input1.connect(intermediate1,1.);
        input1.connect(intermediate2,-1.);
        input2.connect(intermediate1,-1.);
        input2.connect(intermediate2,1.);
        input1.forwardSignalReceived(null,value[0]);
        input2.forwardSignalReceived(null, value[1]);
        intermediate1.connect(outpu1,1.);
        intermediate2.connect(outpu1,1.);
        intermediate1.forwardSignalNext(null, intermediate1.forwardResult);
        intermediate2.forwardSignalNext(null, intermediate2.forwardResult);
        System.out.println(value[0]+" and "+value[1]+" => " + outpu1.forwardResult);
        intermediate1.clear();
        intermediate2.clear();
        outpu1.clear();
    }
}
