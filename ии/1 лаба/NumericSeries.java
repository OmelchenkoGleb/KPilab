import java.util.*;
import static java.lang.Math.*;
public class NumericSeries {
    double learning_rate = 0.01;
    int epoch = 50;
    double dx = 0;

    ArrayList<Double> weight = getWeight();
    ArrayList<Double> expected = getExpected();
    ArrayList<ArrayList<Double>> valuee = new ArrayList<>();

    InputNeuron input1 = new InputNeuron();
    InputNeuron input2 = new InputNeuron();
    InputNeuron input3 = new InputNeuron();
    OutputNeuron output = new OutputNeuron() {
        @Override
        public double ActivationFunction(Double x) {return 10./(1.0+exp(-x));}
    };
    NumericSeries(){
        Train();
        print();
        Test();
    }
    public void Test(){
        valuee.add(getValue1());
        valuee.add(getValue2());
        valuee.add(getValue3());
        valuee.add(getValue4());
        valuee.add(getValue5());
        valuee.add(getValue6());
        valuee.add(getValue7());
        valuee.add(getValue8());
        valuee.add(getValue9());
        valuee.add(getValue10());
        ArrayList<Double> expected = getExpected();
        for (int i = 0; i<expected.size(); i++) {
            ArrayList<Double> arrayListValue = valuee.get(i);
            float result = (float) Result(input1, input2, input3, output, weight, arrayListValue);
            System.out.println(arrayListValue.get(0) + " and " + arrayListValue.get(1) + " and " + arrayListValue.get(2) + " => " + result + " || Expected = " + expected.get(i)+ " || Error = " + (result - expected.get(i)));
        }
        ArrayList<Double> value = getValueTest1();
        expected = getExpectedTest();
        float result = (float) Result(input1,input2,input3,output,weight,value);
        System.out.println(value.get(0) + " and " + value.get(1) + " and " + value.get(2) + " => " + result + " || Expected = " + expected.get(0) + " || Error = " + abs(result - expected.get(0)));
        value = getValueTest2();
        result = (float) Result(input1,input2,input3,output,weight,value);
        System.out.println(value.get(0) + " and " + value.get(1) + " and " + value.get(2) + " => " + result + " || Expected = " + expected.get(1) + " || Error = " + abs(result - expected.get(1)));
        print();
    }
    public void Train (){
        print();
        System.out.print("| Weight:    ");
        for (int i = 0; i<weight.size();i++) System.out.print(weight.get(i)+"   |   ");
        System.out.println();
        print();
        ArrayList<ArrayList<Double>> value = new ArrayList<>();
        value.add(getValue1());
        value.add(getValue2());
        value.add(getValue3());
        value.add(getValue4());
        value.add(getValue5());
        value.add(getValue6());
        value.add(getValue7());
        value.add(getValue8());
        value.add(getValue9());
        value.add(getValue10());
        for (int g = 0; g<epoch ; g++){
            double E = 0;
            for (int i = 0; i<expected.size(); i++){
                ArrayList<Double> arrayListValue = value.get(i);
                double actual = Result(input1,input2,input3,output,weight,arrayListValue);
                double erorr = actual - expected.get(i);
                E += pow(erorr,2);
                double []Ei = new double[3];
                double []dw = new double[3];
                for (int k=0; k<3; k++){
                    Ei[k] = erorr*arrayListValue.get(k)*dx;
                    dw[k] = Ei[k]*learning_rate;
                }
                weight.set(0,weight.get(0) - dw[0]);
                weight.set(1,weight.get(1) - dw[1]);
                weight.set(2,weight.get(2) - dw[2]);
            }
            System.out.println("Error: "+E);
        }
        print();
        System.out.print("| Weight:    ");
        for (int i = 0; i<weight.size();i++) System.out.print(weight.get(i)+"   |   ");
        System.out.println();
        print();
    }
    double Result (InputNeuron input1, InputNeuron input2, InputNeuron input3, OutputNeuron outputNeuron, ArrayList<Double> weight, ArrayList<Double> value){
        double [] value2 = new double[3];
        for (int i = 0; i<3 ; i++) {value2 [i] = value.get(i);}
        input1.connect(outputNeuron, weight.get(0));
        input2.connect(outputNeuron, weight.get(1));
        input3.connect(outputNeuron, weight.get(2));
        input1.forwardSignalReceived(outputNeuron, value2[0]);
        input2.forwardSignalReceived(outputNeuron, value2[1]);
        input3.forwardSignalReceived(outputNeuron, value2[2]);
        double result = outputNeuron.forwardResult;
        dx = outputNeuron.ActivationFunctionDx();
        outputNeuron.clear();
        return result;
    }
    public void print(){for (int i = 0; i<43; i++ ) System.out.print("--");System.out.println();}
    ArrayList<Double> getWeight(){
        ArrayList<Double> getWeight = new ArrayList<>();
        getWeight.add(Math.random());
        getWeight.add(Math.random());
        getWeight.add(Math.random());
        return getWeight;
    }
    ArrayList<Double> getExpected(){
        ArrayList<Double> getExpected = new ArrayList<>();
        getExpected.add(5.66); getExpected.add(1.23); getExpected.add(5.50);
        getExpected.add(1.14); getExpected.add(5.29); getExpected.add(1.60);
        getExpected.add(4.31); getExpected.add(0.06) ;getExpected.add(5.33);
        getExpected.add(0.07);
        return getExpected;
    }
    ArrayList<Double> getValue1(){
        ArrayList<Double> getValue1 = new ArrayList<>();
        getValue1.add(1.88);getValue1.add(4.52);getValue1.add(1.91);
        return getValue1;
    }
    ArrayList<Double> getValue2(){
        ArrayList<Double> getValue2 = new ArrayList<>();
        getValue2.add(4.52);getValue2.add(1.91);getValue2.add(5.66);
        return getValue2;
    }
    ArrayList<Double> getValue3(){
        ArrayList<Double> getValue3 = new ArrayList<>();
        getValue3.add(1.91);getValue3.add(5.66);getValue3.add(1.23);
        return getValue3;
    }
    ArrayList<Double> getValue4(){
        ArrayList<Double> getValue4 = new ArrayList<>();
        getValue4.add(5.66);getValue4.add(1.23);getValue4.add(5.50);
        return getValue4;
    }
    ArrayList<Double> getValue5(){
        ArrayList<Double> getValue5 = new ArrayList<>();
        getValue5.add(1.23);getValue5.add(5.50);getValue5.add(1.14);
        return getValue5;
    }
    ArrayList<Double> getValue6(){
        ArrayList<Double> getValue6 = new ArrayList<>();
        getValue6.add(5.50);getValue6.add(1.14);getValue6.add(5.29);
        return getValue6;
    }
    ArrayList<Double> getValue7(){
        ArrayList<Double> getValue7 = new ArrayList<>();
        getValue7.add(1.14);getValue7.add(5.29);getValue7.add(1.60);
        return getValue7;
    }
    ArrayList<Double> getValue8(){
        ArrayList<Double> getValue8 = new ArrayList<>();
        getValue8.add(5.29);getValue8.add(1.60);getValue8.add(4.31);
        return getValue8;
    }
    ArrayList<Double> getValue9(){
        ArrayList<Double> getValue9 = new ArrayList<>();
        getValue9.add(1.60);getValue9.add(4.31);getValue9.add(0.06);
        return getValue9;
    }
    ArrayList<Double>getValue10(){
        ArrayList<Double> getValue10 = new ArrayList<>();
        getValue10.add(4.31);getValue10.add(0.06);getValue10.add(5.33);
        return getValue10;
    }
    ArrayList<Double> getExpectedTest(){
        ArrayList<Double> getExpectedTest = new ArrayList<>();
        getExpectedTest.add(4.62);getExpectedTest.add(0.69);
        return getExpectedTest;
    }
    ArrayList<Double> getValueTest1(){
        ArrayList<Double> getValueTest1 = new ArrayList<>();
        getValueTest1.add(0.06);getValueTest1.add(5.33);getValueTest1.add(0.07);
        return getValueTest1;
    }
    ArrayList<Double> getValueTest2(){
        ArrayList<Double> getValueTest2 = new ArrayList<>();
        getValueTest2.add(5.33);getValueTest2.add(0.07);getValueTest2.add(4.62);
        return getValueTest2;
    }
}
