import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Tester {

    private static long timeout = (long)(60*1000 * 8), startSize=10000, maxSize=200000;
    private static double growthRate=1.2;

    /**
     * The main method to be run to test all the sorting algorithms.
     */
    public static void main(String[] args) {
        Sort<Integer> sorter = new Sort();


        testMethods(1000);

        Sorter merge = new Sorter(6,"Merge", sorter::mergeSort);
        merge.run();

        Sorter quick = new Sorter(6,"Quick", sorter::quickSort);
        quick.run();

        Sorter bubble = new Sorter(4,"Bubble", sorter::bubbleSort);
        bubble.run();

        Sorter selection = new Sorter(4,"Selection", sorter::selectionSort);
        selection.run();

        Sorter insertion = new Sorter(4,"Insertion", sorter::insertionSort);
        insertion.run();


    }

    /**
     * A helper method that creates that an ordered array of a given size for comparison with the sorted arrays.
     * @param size The size of the array to create.
     * @return The ordered array of a given size.
     */
    private static Integer[] inorderArr(int size) {
        ArrayList<Integer> tmp = new ArrayList<>();
        for(int i=1;i<=size;i++)
            tmp.add(i);
        return tmp.toArray(new Integer[0]);
    }

    /**
     * A helper method that tests each of the sorting algorithms to make sure they properly sort the data set.
     * @param testSize Size of array to test with.
     */
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

        System.out.println("Done testing...\n");
    }

    /**
     * A helper method to generate a randomized Integer array of a given size.
     * @param size The size of the array to create.
     * @return The randomly shuffled array of a given size.
     */
    public static Integer[] randomSeqArr(long size){
        ArrayList<Integer> tmp = new ArrayList<>();
        for(int i=1;i<=size;i++)
            tmp.add(i);
        Collections.shuffle(tmp);
        return tmp.toArray(new Integer[0]);
    }

    /**
     * A helper class that allows the methods to be run and tested in individual threads.
     */
    public static class Sorter extends Thread{
        Function<Integer[], Long> method;
        int maxRuns, runNum;

        /**
         * Constructor that takes the name of the algorithm to test, and a method reference to the algorithm.
         * @param runCount The number of trials to be run for this algorithm.
         * @param name The name of the algorithm that is being tested.
         * @param method The reference to the method that runs the algorithm.
         */
        public Sorter(int runCount,String name, Function<Integer[], Long> method){
            maxRuns = runCount;
            this.method = method;
            this.setName(name);
            runNum=1;
        }

        /**
         * A custom run method from the Thread class that is called when the new thread is created.
         */
        public void run(){
            ArrayList<Long> sizes = new ArrayList<>();
            ArrayList<Long> times = new ArrayList<>();
            long last=0;
            long size=startSize;
            Integer[] data =randomSeqArr(size);

            while(maxSize==0?last<timeout/growthRate:size<maxSize){
                last = method.apply(data);
                times.add(last);
                sizes.add(size);
                size*=growthRate;
                data=randomSeqArr(size);
            }
            Sort.createCSV(getName()+".csv",sizes,times);
            System.out.println("Finished "+getName()+" iteration "+runNum++ +" of "+maxRuns);
            if(runNum<=maxRuns)
                run();
        }
    }


}
