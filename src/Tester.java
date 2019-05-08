import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Tester {
    public static void main(String[] args) {
        Sort<Integer> sorter = new Sort();
        int n = 50, maxVal=100;

//        Integer[] data = new Integer[n];
//        for(int i=1;i<n;i++)
//            data[i]=(int)(Math.random()*maxVal+1);
//

        long timeout = (long)(60*1000 * 5);
        double growthRate=1.25;
        long startSize=1000;

        ArrayList<Long> sizes = new ArrayList<>();
        ArrayList<Long> times = new ArrayList<>();




        System.out.println("Insertion Sort:");
        long last=0;
        long size=startSize;
        Integer[] data = randomSeqArr(size);


        System.out.println(""+Sort.print(sorter.quickSort(data,0,data.length-1)));

        while(last<timeout*growthRate){
            System.out.println(size);
            last = Sort.time(data,sorter::insertionSort);
            times.add(last);
            sizes.add(size);
//            System.out.println(last+"ms for an array of "+size+" elements: "+Sort.print(data)+"");
            size*=2;
            data=randomSeqArr(size);
        }
        Sort.createCSV("insertion.csv",sizes,times);

        System.out.println("Bubble Sort:");
        last=0;
        size=startSize;
        data = randomSeqArr(size);
        while(last<timeout*growthRate){
            last = Sort.time(data,sorter::bubbleSort);
            times.add(last);
            sizes.add(size);
            System.out.println(last+"ms for an array of "+size+" elements: "+Sort.print(data)+"\n");
            size*=2;
            data=randomSeqArr(size);
        }
        Sort.createCSV("bubble.csv",sizes,times);

        System.out.println("Selection Sort:");
        last=0;
        size=startSize;
        data = randomSeqArr(size);
        while(last<timeout*growthRate){
            last = Sort.time(data,sorter::selectionSort);
            times.add(last);
            sizes.add(size);
            System.out.println(last+"ms for an array of "+size+" elements: "+Sort.print(data)+"\n");
            size*=2;
            data=randomSeqArr(size);
        }
        Sort.createCSV("selection.csv",sizes,times);

    }


    public static Integer[] randomSeqArr(long size){
        ArrayList<Integer> tmp = new ArrayList<>();
        for(int i=1;i<=size;i++)
            tmp.add(i);
        Collections.shuffle(tmp);
        return tmp.toArray(new Integer[0]);
    }
}
