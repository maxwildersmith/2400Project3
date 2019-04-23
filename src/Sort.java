import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.sql.Time;
import java.util.ArrayList;
import java.util.function.Function;

public class Sort<T extends Comparable> {

    public static String print(Integer[] arr){
        String out = "";
        for(Integer i: arr)
            out+=i+", ";
        return out.substring(0,out.length()-2);
    }

    public T[] bubbleSort(T[] in){
        T[] data=in.clone();
        boolean swapped=true;
        while(swapped){
            swapped=false;
            for(int i=0;i<data.length-1;i++)
                if(data[i].compareTo(data[i+1])>0){
                    swapped=true;
                    T tmp = data[i];
                    data[i]=data[i+1];
                    data[i+1]=tmp;
                }
        }
        return data;
    }

    public T[] selectionSort(T[] in){
        T[] data=in.clone();
        for(int i=0;i<data.length-1;i++){
            int min=i;
            for(int j=i+1;j<data.length;j++)
                if(data[min].compareTo(data[j])>0)
                    min=j;
            T tmp = data[i];
            data[i]=data[min];
            data[min]=tmp;
        }
        return data;
    }

    public T[] insertionSort(T[] data){
        for(int i=0;i<data.length;i++){
            T start = data[i];
            int j;
            for(j=i-1;j>=0&&data[j].compareTo(start)>0;j--){
                data[j+1]=data[j];
            }
            data[j+1]=start;
        }
        return data;
    }

    public T[] mergeSort(T[] data){
        return data;
    }

    public T[] quickSort(T[] data){
        return data;
    }

    public static long time(Integer[] data, Function<Integer[], Integer[]> sorter){
        long start = System.currentTimeMillis();
        sorter.apply(data);
        return System.currentTimeMillis()-start;
    }

    public static boolean createCSV(String filename, ArrayList<Long> sizes, ArrayList<Long> times){
        File csv = new File(filename);
        try {
            csv.createNewFile();
            PrintWriter file = new PrintWriter(new FileWriter(csv));
//        String file="Time,Array Size\n";
            file.println("Time,Array Size");
            for (int i = 0; i < sizes.size(); i++)
                file.println(times.get(i) + "," + sizes.get(i));
            file.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
