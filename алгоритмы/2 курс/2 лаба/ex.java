import java.util.Arrays;
public class ex {
    public int[] Sort20 = new int[20];
    public int[] Sort5000 = new int[5000];
    public int[] Sort50000= new int[50000];

    ex () {
//        quickSortTestSort();
//        quickSortTestNotSort();
//        quickSortTestRandomSort();
//        bucketSortTestSort();
//        bucketSortTestNotSort();
//        bucketSortTestRandomSort();
    }
    void quickSortTestSort(){
//        quickSortTesttSort20();
//        quickSortTesttSort5000();
//        quickSortTesttSort50000();
    }
    void quickSortTestNotSort(){
//        quickSortTestNotSort20();
//        quickSortTestNotSort5000();
//        quickSortTestNotSort50000();
    }
    void quickSortTestRandomSort(){
//        quickSortTestRandomSort20();
//        quickSortTestRandomSort5000();
//        quickSortTestRandomSort50000();
    }
    void bucketSortTestSort(){
//        bucketSortTesttSort20();
//        bucketSortTesttSort5000();
//        bucketSortTesttSort50000();
    }
    void bucketSortTestNotSort(){
//        bucketSortTestNotSort20();
//        bucketSortTestNotSort5000();
//        bucketSortTestNotSort50000();
    }
    void bucketSortTestRandomSort(){
//        bucketSortTestRandomSort20();
//        bucketSortTestRandomSort5000();
//        bucketSortTestRandomSort50000();
    }






    void quickSortTesttSort20 () {
        PrintSort20();
        quickSort(Sort20, 0, Sort20.length - 1);
        System.out.println(Arrays.toString(Sort20));
    }
    void quickSortTesttSort5000 () {
        PrintSort5000();
        quickSort(Sort5000, 0, Sort5000.length - 1);
        System.out.println(Arrays.toString(Sort5000));
    }
    void quickSortTesttSort50000 () {
        PrintSort50000();
        quickSort(Sort50000, 0, Sort50000.length - 1);
        System.out.println(Arrays.toString(Sort50000));
    }


    void quickSortTestNotSort20 () {
        PrintNotSort20();
        quickSort(Sort20, 0, Sort20.length - 1);
        System.out.println(Arrays.toString(Sort20));
    }
    void quickSortTestNotSort5000 () {
        PrintNotSort5000();
        quickSort(Sort5000, 0, Sort5000.length - 1);
        System.out.println(Arrays.toString(Sort5000));
    }
    void quickSortTestNotSort50000 () {
        PrintNotSort50000();
        quickSort(Sort50000, 0, Sort50000.length - 1);
        System.out.println(Arrays.toString(Sort50000));
    }


    void quickSortTestRandomSort20 () {
        PrintRandomSort20();
        quickSort(Sort20, 0, Sort20.length - 1);
        System.out.println(Arrays.toString(Sort20));
    }
    void quickSortTestRandomSort5000 () {
        PrintRandomSort5000();
        quickSort(Sort5000, 0, Sort5000.length - 1);
        System.out.println(Arrays.toString(Sort5000));
    }
    void quickSortTestRandomSort50000 () {
        PrintRandomSort50000();
        quickSort(Sort50000, 0, Sort50000.length - 1);
        System.out.println(Arrays.toString(Sort50000));
    }







    void bucketSortTesttSort20 () {
        PrintSort20();
        bucketSort(Sort20);
        System.out.println(Arrays.toString(Sort20));
    }
    void bucketSortTesttSort5000 () {
        PrintSort5000();
        bucketSort(Sort5000);
        System.out.println(Arrays.toString(Sort5000));
    }
    void bucketSortTesttSort50000 () {
        PrintSort50000();
        bucketSort(Sort50000);
        System.out.println(Arrays.toString(Sort50000));
    }


    void bucketSortTestNotSort20 () {
        PrintNotSort20();
        bucketSort(Sort20);
        System.out.println(Arrays.toString(Sort20));
    }
    void bucketSortTestNotSort5000 () {
        PrintNotSort5000();
        bucketSort(Sort5000);
        System.out.println(Arrays.toString(Sort5000));
    }
    void bucketSortTestNotSort50000 () {
        PrintNotSort50000();
        bucketSort(Sort50000);
        System.out.println(Arrays.toString(Sort50000));
    }


    void bucketSortTestRandomSort20 () {
        PrintRandomSort20();
        bucketSort(Sort20);
        System.out.println(Arrays.toString(Sort20));
    }
    void bucketSortTestRandomSort5000 () {
        PrintRandomSort5000();
        bucketSort(Sort5000);
        System.out.println(Arrays.toString(Sort5000));
    }
    void bucketSortTestRandomSort50000 () {
        PrintRandomSort50000();
        bucketSort(Sort50000);
        System.out.println(Arrays.toString(Sort50000));
    }





    void PrintSort20(){
        System.out.println("Исходный:");
        for (int i=0; i<Sort20.length; i++){
            Sort20[i] = i;
        }
        System.out.println(Arrays.toString(Sort20));
        System.out.println("Отсортированный:");
    }
    void PrintNotSort20(){
        System.out.println("Исходный:");
        int n = 0;
        for (int i=Sort20.length-1; i>=0; i--){
            Sort20[n] = i;
            n++;
        }
        System.out.println(Arrays.toString(Sort20));
        System.out.println("Отсортированный:");
    }
    void PrintRandomSort20(){
        System.out.println("Исходный:");
        for (int i = 0; i < Sort20.length; i++) {
            Sort20[i] = ((int)(Math.random() * 20));
        }
        System.out.println(Arrays.toString(Sort20));
        System.out.println("Отсортированный:");
    }


    void PrintSort5000(){
        System.out.println("Исходный:");
        for (int i=0; i<Sort5000.length; i++){
            Sort5000[i] = i;
        }
        System.out.println(Arrays.toString(Sort5000));
        System.out.println("Отсортированный:");
    }
    void PrintNotSort5000(){
        System.out.println("Исходный:");
        int n = 0;
        for (int i=Sort5000.length-1; i>=0; i--){
            Sort5000[n] = i;
            n++;
        }
        System.out.println(Arrays.toString(Sort5000));
        System.out.println("Отсортированный:");
    }
    void PrintRandomSort5000(){
        System.out.println("Исходный:");
        for (int i = 0; i < Sort5000.length; i++) {
            Sort5000[i] = ((int)(Math.random() * 5000));
        }
        System.out.println(Arrays.toString(Sort5000));
        System.out.println("Отсортированный:");
    }


    void PrintSort50000(){
        System.out.println("Исходный:");
        for (int i=0; i<Sort50000.length; i++){
            Sort50000[i] = i;
        }
        System.out.println(Arrays.toString(Sort50000));
        System.out.println("Отсортированный:");
    }
    void PrintNotSort50000(){
        System.out.println("Исходный:");
        int n = 0;
        for (int i=Sort50000.length-1; i>=0; i--){
            Sort50000[n] = i;
            n++;
        }
        System.out.println(Arrays.toString(Sort50000));
        System.out.println("Отсортированный:");
    }
    void PrintRandomSort50000(){
        System.out.println("Исходный:");
        for (int i = 0; i < Sort50000.length; i++) {
            Sort50000[i] = ((int)(Math.random() * 50000));
        }
        System.out.println(Arrays.toString(Sort50000));
        System.out.println("Отсортированный:");
    }


    public static void quickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return;
        if (low >= high)
            return;
        int middle = low + (high - low) / 2;
        int opora = array[middle];
        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }
            while (array[j] > opora) {
                j--;
            }
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }
    public void bucketSort (int[] arr) {
        int i,j,k =0;
        int[] bucket = new int[arr.length+1];
        Arrays.fill(bucket, 0);

        for (i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        for (i = 0; i < bucket.length; i++) {
            for (j = 0; j<bucket[i]; j++) {
                arr[k++] = i;
            }
        }
    }
}
