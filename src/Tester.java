import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Tester {
    private static long timeout = (long)(60*1000 * .5), startSize=10000, maxSize=100000;
    private static double growthRate=1.2;

    public static void main(String[] args) {
        Sort<Integer> sorter = new Sort();
        int n = 50, maxVal=100;

//        Integer[] data = new Integer[n];
//        for(int i=1;i<n;i++)
//            data[i]=(int)(Math.random()*maxVal+1);
//


        testMethods(1000);

        Sorter bubble = new Sorter("Bubble", sorter::bubbleSort);
        bubble.start();

        Sorter selection = new Sorter("Selection", sorter::selectionSort);
        selection.start();

        Sorter insertion = new Sorter("Insertion", sorter::insertionSort);
        insertion.start();

        Sorter merge = new Sorter("Merge", sorter::mergeSort);
        merge.start();

        Sorter quick = new Sorter("Quick", sorter::quickSort);
        quick.start();





//        while(last<timeout*growthRate){
//            System.out.println(size);
//            last = Sort.time(data,sorter::insertionSort);
//            times.add(last);
//            sizes.add(size);
////            System.out.println(last+"ms for an array of "+size+" elements: "+Sort.print(data)+"");
//            size*=2;
//            data=randomSeqArr(size);
//        }
//        Sort.createCSV("insertion.csv",sizes,times);
//
//        System.out.println("Bubble Sort:");
//        last=0;
//        size=startSize;
//        data = randomSeqArr(size);
//        while(last<timeout*growthRate){
//            last = Sort.time(data,sorter::bubbleSort);
//            times.add(last);
//            sizes.add(size);
//            System.out.println(last+"ms for an array of "+size+" elements: "+Sort.print(data)+"\n");
//            size*=2;
//            data=randomSeqArr(size);
//        }
//        Sort.createCSV("bubble.csv",sizes,times);
//
//        System.out.println("Selection Sort:");
//        last=0;
//        size=startSize;
//        data = randomSeqArr(size);
//        while(last<timeout*growthRate){
//            last = Sort.time(data,sorter::selectionSort);
//            times.add(last);
//            sizes.add(size);
//            System.out.println(last+"ms for an array of "+size+" elements: "+Sort.print(data)+"\n");
//            size*=2;
//            data=randomSeqArr(size);
//        }
//        Sort.createCSV("selection.csv",sizes,times);

    }

    private static Integer[] inorderArr(int size) {
        ArrayList<Integer> tmp = new ArrayList<>();
        for(int i=1;i<=size;i++)
            tmp.add(i);
        return tmp.toArray(new Integer[0]);
    }

    public static void testMethods(int testSize){
        Sort<Integer> sorter = new Sort();

        System.out.println("Testing algorithms...");
        Integer[] sample = inorderArr(testSize);
        Integer[] base = randomSeqArr(testSize);

        Integer[] data = base.clone();
        System.out.println("Bubble    | Time: "+sorter.bubbleSort(data)+" \t|"+(Arrays.compare(data,sample)==0?" Working":" ERROR"));
        data = base.clone();
        System.out.println("Selection | Time: "+sorter.selectionSort(data)+" \t|"+(Arrays.compare(data,sample)==0?" Working":" ERROR"));
        data = base.clone();
        System.out.println("Insertion | Time: "+sorter.insertionSort(data)+" \t|"+(Arrays.compare(data,sample)==0?" Working":" ERROR"));
        data = base.clone();
        System.out.println("Merge     | Time: "+sorter.mergeSort(data)+" \t|"+(Arrays.compare(data,sample)==0?" Working":" ERROR"));
        data = base.clone();
        System.out.println("Quick     | Time: "+sorter.quickSort(data)+" \t|"+(Arrays.compare(data,sample)==0?" Working":" ERROR"));

        System.out.println("Done testing...\n\n");
    }

    public static Integer[] randomSeqArr(long size){
        ArrayList<Integer> tmp = new ArrayList<>();
        for(int i=1;i<=size;i++)
            tmp.add(i);
        Collections.shuffle(tmp);
        return tmp.toArray(new Integer[0]);
    }

    public static class Sorter extends Thread{
        Function<Integer[], Long> method;

        public Sorter(String name, Function<Integer[], Long> method){
            this.method = method;
            this.setName(name);
        }
        public void run(){
            ArrayList<Long> sizes = new ArrayList<>();
            ArrayList<Long> times = new ArrayList<>();
            long last=0;
            long size=startSize;
            Integer[] data =randomSeqArr(size);

//            while(last<timeout*growthRate){
            while(size<maxSize){
//                System.out.println(size);
                last = method.apply(data);
                times.add(last);
                sizes.add(size);
//            System.out.println(last+"ms for an array of "+size+" elements: "+Sort.print(data)+"");
                size*=growthRate;
                data=randomSeqArr(size);
            }

            Sort.createCSV(getName()+".csv",sizes,times);
            System.out.println("Finished "+getName());
        }
    }
}
