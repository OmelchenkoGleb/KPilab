import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Ex{
    HashMap<Integer, ArrayList<Double>> MapValue = new HashMap<>();
    HashMap<Integer, ArrayList<Double>> MapExpected = new HashMap<>();
    HashMap<Integer, ArrayList<Double>> MapWeight = new HashMap<>();
    HashMap<Integer, ArrayList<Neuron>> MapNeuron = new HashMap<>();
    ArrayList<Integer> SizeIntermidiate = new ArrayList<>();
    HashMap<Integer,ArrayList<Double>> SumMapp;
    String [] NameFile = {"test1.txt","test2.txt","test3.txt","test4.txt","test5.txt"};
    int countWeight;
    int V;
    int count;
    double learning_rate = 0.1;
    int SizeInput = 36, SizeOutput = 3;
    Ex(){
        InicialValueAndExpected();
        ViewValueAndExpected();
        Scanner sc = new Scanner(System.in);
        System.out.println("Здравствуйте, укажите, пожалуйста, количество скрытых шаров (не менее 1-го и не больше " + (SizeInput+SizeOutput)/2 + ")");
        count = sc.nextInt();
        SizeIntermidiate.add(0);
        while (count < 1) {
            System.out.println("Не корректные данные ! Исправьте ошибку: ");
            count = sc.nextInt();
        }
        System.out.println("\nА теперь, пожалуйста, укажите размерность для каждого шара (не меньше 2 и не больше " + (SizeInput+SizeOutput)/count + "):\n ");
        for (int i = 0; i < count; i++) {
            int argument = sc.nextInt();
            while (argument < 1 || argument > (SizeInput+SizeOutput)/count) {
                System.out.println("Не корректные данные ! Исправьте ошибку: ");
                argument = sc.nextInt();
            }
            SizeIntermidiate.add(argument);
        }
        V = FunctionMenu();
        countWeight = 2 + count;
        InicialNeuron();
        IcialWeight();
        Traine();
    }
    public void ViewValueAndExpected(){
        for (int i=0; i<NameFile.length; i++){
            ArrayList<Double> ExpectedList = MapExpected.get(i);
            ArrayList<Double> HashMapResult = MapValue.get(i);
            System.out.println("File: " + i + " ... Value: ");
            for (int g=0; g<HashMapResult.size();g++) {
                if (g%6 ==0) System.out.println();
                System.out.print(HashMapResult.get(g) + "|");
            }
            System.out.println();
            System.out.println();
            System.out.println(" ... Expected ...");
            System.out.print("|");
            for (int g=0; g<ExpectedList.size();g++) {
                System.out.print(ExpectedList.get(g) + "|");
            }
            System.out.println();
            System.out.println("------------------");
        }
    }
    public void Traine(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите количество эпох: ");
        int epoch = sc.nextInt();
        while (epoch < 1) {
            System.out.println("Не корректные данные ! Исправьте ошибку: ");
            epoch = sc.nextInt();
        }
        HashMap<Integer, ArrayList<Double>> MapNewWeightEtotal = new HashMap<>();
        for (int g = 0; g<epoch ; g++){
            System.out.println("Эпоха: "+g);
            if (g>0){
                MapWeight = MapNewWeightEtotal; }
            for (int i=0; i<NameFile.length; i++){
                ArrayList<Double> ExpectedList = MapExpected.get(i);
                HashMap<Integer,ArrayList<Double>> HashMapResult = Result(MapValue.get(i));
                HashMap<Integer,ArrayList<Double>> MapNewWeight = new HashMap<>();
                ArrayList<Double> EPre = new ArrayList<>();

                for (int l = countWeight-2 ; l>=0; l--) {
                    ArrayList<Double> ResultList = HashMapResult.get(l);
                    ArrayList<Double> ResultListPreWeight = HashMapResult.get(l-1);
                    ArrayList<Double> SumList = SumMapp.get(l);
                    ArrayList<Double> Weight = MapWeight.get(l);
                    ArrayList<Double> E = new ArrayList<>(ResultList.size());
                    ArrayList<Double> C = new ArrayList<>(Weight.size());
                    ArrayList<Double> NewWeight = new ArrayList<>();
                    double Etotal = 0;
                    int CountE = 0;
                    int CountH = 0;
                    if (l == countWeight-2) {
                        for (int d = 0; d < ResultList.size(); d++) {
                            Etotal += pow(ResultList.get(d) - ExpectedList.get(d),2)/2;
                            E.add((ResultList.get(d)-ExpectedList.get(d))*resultD(SumList.get(d),V));
                        }
                        System.out.println("Error : "+Etotal);
                        for (int d = 0; d < Weight.size(); d++) {
                            if (CountE == E.size()-1) CountE = 0;
                            if (CountH == ResultListPreWeight.size()-1) CountH = 0;
                            C.add(E.get(CountE)*ResultListPreWeight.get(CountH)*learning_rate);
                            NewWeight.add(Weight.get(d) - C.get(d));
                            CountE++;
                            CountH++;
                        }
                        EPre = E;
                        MapNewWeight.put(l,NewWeight);
                    }
                    if (l != 0 && l!=countWeight-2) {
                        ArrayList<Double> WeightPre = MapWeight.get(l+1);
                        for (int d = 0; d < WeightPre.size(); d++) {
                            if (CountE == EPre.size()){
                                CountE = 0;
                                CountH++;
                            }
                            if (CountH == SumList.size()) CountH = 0;
                            E.add(EPre
                                    .get(CountE)*WeightPre
                                    .get(d)*resultD(SumList
                                    .get(CountH),V));
                            CountE++;
                        }
                        CountE = 0;
                        CountH = 0;
                        for (int d = 0; d < Weight.size(); d++) {
                            if (CountE == E.size()-1) CountE = 0;
                            if (CountH == ResultListPreWeight.size()-1) CountH = 0;
                            C.add(E.get(CountE)*ResultListPreWeight.get(CountH)*learning_rate);
                            NewWeight.add(Weight.get(d) - C.get(d));
                            CountE++;
                            CountH++;
                        }
                        MapNewWeight.put(l,NewWeight);
                    }
                    if (l == 0) {
                        ArrayList<Double> WeightPre = MapWeight.get(l+1);
                        for (int d = 0; d < WeightPre.size(); d++) {
                            if (CountE == EPre.size()){
                                CountE = 0;
                                CountH++;
                            }
                            if (CountH == SumList.size()) CountH = 0;
                            E.add(EPre.get(CountE)*WeightPre.get(d)*resultD(SumList.get(CountH),V));
                            CountE++;
                        }
                        CountE = 0;
                        int Countx = 0;
                        for (int d = 0; d < Weight.size(); d++) {
                            if (CountE == E.size()-1) CountE = 0;
                            if (Countx == MapValue.get(i).size()-1) Countx = 0;
                            C.add(E.get(CountE)*MapValue.get(i).get(Countx)*learning_rate);
                            NewWeight.add(Weight.get(d) - C.get(d));
                            CountE++;
                            Countx++;
                        }
                        MapNewWeight.put(l,NewWeight);
                    }
                    }
                    MapWeight = MapNewWeight;
            }
            MapNewWeightEtotal = MapWeight;
        }
        for (int i=0; i<NameFile.length; i++){
            InicialNeuron();
            ArrayList<Double> ExpectedList = MapExpected.get(i);
            HashMap<Integer,ArrayList<Double>> HashMapResult = Result(MapValue.get(i));
            System.out.print("File: " + i + " ... Value: | ");
            HashMapResult.get(countWeight-2).forEach(x-> System.out.print(x + " |"));
            System.out.print(" ... Expected: |");
            ExpectedList.forEach(x-> System.out.print(x + "|"));
            System.out.println();
        }
    }
    public HashMap<Integer,ArrayList<Double>> Result(ArrayList<Double> Value){
        InicialNeuron();
        ArrayList<InputNeuron> InputNeuronList = new ArrayList<>();
        ArrayList<OutputNeuron> OutputNeuronList = new ArrayList<>();
        HashMap<Integer,ArrayList<IntermediateNeuron>> IntermediateNeuronList = new HashMap<>();
        HashMap<Integer,ArrayList<Double>> ResultMap = new HashMap<>(countWeight-1);
        ArrayList<IntermediateNeuron> ArrayInteNeuron;
        ArrayList<Double> WeightInput;
        SumMapp = new HashMap<>();
        HashMap<Integer,ArrayList<Double>> SumMap = new HashMap<>(countWeight-1);
        for (int i = 0; i<countWeight; i++){
            ArrayList<Neuron> ArrayListNeuron = MapNeuron.get(i);
            if (i==0){
                ArrayListNeuron.forEach(x -> InputNeuronList.add((InputNeuron) x));
            } if (i != 0 && i != (countWeight-1)){
                ArrayList<IntermediateNeuron> IntermList = new ArrayList<>();
                ArrayListNeuron.forEach(x -> IntermList.add((IntermediateNeuron) x));
                IntermediateNeuronList.put(i-1,IntermList);
            } if (i == (countWeight-1)){
                ArrayListNeuron.forEach(x -> OutputNeuronList.add((OutputNeuron) x));
            }
        }
        for (int n = 0; n<countWeight-1; n++){
            int k = 0;
            ArrayList<Double> arrayList = new ArrayList<>();
            ArrayList<Double> arrayList1 = new ArrayList<>();
            WeightInput = MapWeight.get(n);
            if (n==0){
                ArrayInteNeuron = IntermediateNeuronList.get(n);
                for (IntermediateNeuron intermediateNeuron : ArrayInteNeuron) {
                    for (int g = 0; g < InputNeuronList.size(); g++) {
                        InputNeuronList.get(g).connect(intermediateNeuron, WeightInput.get(k++));
                        InputNeuronList.get(g).forwardSignalReceived(intermediateNeuron, Value.get(g));
                    }
                }
                ArrayInteNeuron.forEach(x->{
                    arrayList.add(x.forwardResult);
                    arrayList1.add(x.Sum);
                });
                ResultMap.put(n,arrayList);
                SumMap.put(n,arrayList1);
            } if (n != 0 && n != (countWeight-2)){
                for (int i = 0; i<IntermediateNeuronList.get(n).size();i++){
                    for (int g = 0; g<IntermediateNeuronList.get(n-1).size();g++){
                        IntermediateNeuronList.get(n-1).get(g).connect(IntermediateNeuronList.get(n).get(i), WeightInput.get(k++));
                        IntermediateNeuronList.get(n-1).get(g).forwardSignalNext(IntermediateNeuronList.get(n).get(i), IntermediateNeuronList.get(n-1).get(g).forwardResult);
                    }
                }
                IntermediateNeuronList.get(n).forEach(x -> {
                    arrayList.add(x.forwardResult);
                    arrayList1.add(x.Sum);
                });
                ResultMap.put(n,arrayList);
                SumMap.put(n,arrayList1);
            } if (n == countWeight-2){
                for (OutputNeuron outputNeuron : OutputNeuronList) {
                    for (int g = 0; g < IntermediateNeuronList.get(n - 1).size(); g++) {
                        IntermediateNeuronList.get(n - 1).get(g).connect(outputNeuron, WeightInput.get(k++));
                        IntermediateNeuronList.get(n - 1).get(g).forwardSignalNext(outputNeuron, IntermediateNeuronList.get(n - 1).get(g).forwardResult);
                    }
                }
                OutputNeuronList.forEach(x -> {
                    arrayList.add(x.forwardResult);
                    arrayList1.add(x.Sum);
                });
                ResultMap.put(n,arrayList);
                SumMap.put(n,arrayList1);
            }
        }
        SumMapp = SumMap;
        return ResultMap;
    }
    public void InicialNeuron() {
        MapNeuron = new HashMap<>();
        ArrayList<Neuron> InputArray = new ArrayList<>();
        ArrayList<Neuron> OutputArray = new ArrayList<>();
        for (int i = 0; i < SizeInput; i++) InputArray.add(new InputNeuron());
        MapNeuron.put(0, InputArray);
        for (int i = 1; i <= count; i++) {
            ArrayList<Neuron> neuron = new ArrayList<>();
            for (int g = 0; g < SizeIntermidiate.get(i); g++) {
                neuron.add(new IntermediateNeuron() {
                    @Override
                    public double ActivationFunction(Double x) {
                        return result(x, V);
                    }
                    @Override
                    public double ActivationFunctionDx(Double x){
                        return resultD(x, V);
                    }
                });
            }
            MapNeuron.put(i, neuron);
        }
        for (int i = 0; i < SizeOutput; i++) {
            OutputArray.add(new OutputNeuron() {
                @Override
                public double ActivationFunction(Double x) {
                    return result(x, V);
                }
                @Override
                public double ActivationFunctionDx(Double x){
                    return resultD(x, V);
                }
            });
        }
        MapNeuron.put(count + 1, OutputArray);
    }
    public void IcialWeight(){
        MapWeight = new HashMap<>();
        for (int i = 0; i < countWeight-1; i++) {
            ArrayList<Double> arrayList = new ArrayList<>();
            for (int g = 0; g < MapNeuron.get(i).size()*MapNeuron.get(i+1).size(); g++) {
                arrayList.add(Math.random());
            }
            MapWeight.put(i, arrayList);
        }
    }
    public void InicialValueAndExpected() {
        ArrayList<Double> Value;
        for (int k = 0; k<NameFile.length;k++){
            if ((Value = InicilizalValue(NameFile[k]))!=null) {
                ArrayList<Double> Expected = new ArrayList<>();
                for (int i = 1; i <= 3; i++) {
                    Expected.add(Value.get(Value.size() - i));
                }
                for (int i = 1; i <= 3; i++) {
                    Value.remove(Value.size() - i);
                }
                Collections.reverse(Expected);
                MapValue.put(k,Value);
                MapExpected.put(k,Expected);
            }
        }
    }
    public ArrayList<Double> InicilizalValue(String name) {
        ArrayList<Double> arrayListValue = new ArrayList<>();
        ArrayList<String> arrayListString = new ArrayList<>();
        try (FileReader reader = new FileReader(name)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                arrayListString.add(scan.nextLine());
            }
            for (String value : arrayListString) {
                String[] subStr;
                String delimeter = " ";
                subStr = value.split(delimeter);
                Arrays.stream(subStr).forEach(s -> arrayListValue.add(Double.parseDouble(s)));
            }
            return arrayListValue;
        } catch (IOException ex) {
            System.out.println("Такого файла не существует !");
            System.exit(-1);
            return null;
        }
    }
    public int FunctionMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Выбери функцию активации из представленных: ");
        System.out.println("1. Сигмоидальная.");
        System.out.println("2. Гиперболическая.");
        System.out.println("3. Гауссова.");
        int argument = sc.nextInt();
        while (argument < 1 || argument>3) {
            System.out.println("Не корректные данные ! Исправьте ошибку: ");
            argument = sc.nextInt();
        }
        return argument;
    }
    public double FunctionSigmoidal(Double x) {
        return 1. / (1.0 + exp(-x));
    }
    public double FunctionGiper(Double x) {
        return (exp(x)-exp(-x))/(exp(x)+exp(-x));
    }
    public double FunctionGaysova(Double x) {
        return exp(-pow(x,2));
    }
    public double result(Double x, int V) {
        if (V == 1) return FunctionSigmoidal(x);
        if (V == 2) return FunctionGiper(x);
        if (V == 3) return FunctionGaysova(x);
        else return x;
    }
    public double FunctionDSigmoidal(Double x) {
        return FunctionSigmoidal(x)*(1-FunctionSigmoidal(x));
    }
    public double FunctionDGiper(Double x) {
        return 1-pow(FunctionGiper(x),2);
    }
    public double FunctionDGaysova(Double x) {
        return -2*x*exp(-pow(x,2));
    }
    public double resultD(Double x, int V) {
        if (V == 1) return FunctionDSigmoidal(x);
        if (V == 2) return FunctionDGiper(x);
        if (V == 3) return FunctionDGaysova(x);
        else return x;
    }
}

