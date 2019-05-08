import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.function.Function;

public class Sort<T extends Comparable> {

    public static String print(Integer[] arr){
        String out = "";
        for(Integer i: arr)
            out+=i+", ";
        return out.substring(0,out.length()-2);
    }

    public long bubbleSort(T[] data){
        long startTime = System.currentTimeMillis();

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
        return System.currentTimeMillis()-startTime;
    }

    public long selectionSort(T[] data){
        long start = System.currentTimeMillis();

        for(int i=0;i<data.length-1;i++){
            int min=i;
            for(int j=i+1;j<data.length;j++)
                if(data[min].compareTo(data[j])>0)
                    min=j;
            T tmp = data[i];
            data[i]=data[min];
            data[min]=tmp;
        }
        return System.currentTimeMillis()-start;
    }


    public long insertionSort(T[] data){
        long startTime = System.currentTimeMillis();

        for(int i=0;i<data.length;i++){
            T start = data[i];
            int j;
            for(j=i-1;j>=0&&data[j].compareTo(start)>0;j--){
                data[j+1]=data[j];
            }
            data[j+1]=start;
        }
        return System.currentTimeMillis()-startTime;
    }

    public long mergeSort(T[] arr){
        long startTime = System.currentTimeMillis();
        mergeSort(arr,0,arr.length-1);
        return System.currentTimeMillis()-startTime;
    }

    private void mergeSort(T[] data,int start,int end){
        if(start<end){
            int mid=(start+end)/2;
            mergeSort(data,start,mid);
            mergeSort(data,mid+1,end);
            merge(data,start,mid,end);
        }
    }

    private void merge(T[] arr, int start, int mid, int end){
        int i=start;
        int k=0;
        int j=mid+1;
        T[] tmp = (T[])(new Comparable[end-start+1]);

        while(i<=mid&&j<=end)
            if(arr[i].compareTo(arr[j])<=0)
                tmp[k++]=arr[i++];
            else
                tmp[k++]=arr[j++];
        while(i<=mid)
            tmp[k++]=arr[i++];
        while(j<=end)
            tmp[k++]=arr[j++];
        for(int index=0;index<tmp.length;index++)
            arr[index+start]=tmp[index];
    }

    public long quickSort(T[] data){
        long startTime = System.currentTimeMillis();
        quickSort(data,0,data.length-1);
        return System.currentTimeMillis()-startTime;
    }

    private void quickSort(T[] data, int start, int end){
        int small=start-1;
        if(start<end) {
            T pivot = data[end];

            for (int i = start; i < end; i++)
                if (data[i].compareTo(pivot) < 0) {
                    small++;
                    T tmp = data[small];
                    data[small] = data[i];
                    data[i] = tmp;
                }
            T tmp = data[++small];
            data[small] = data[end];
            data[end] = tmp;

            quickSort(data,start,small-1);
            quickSort(data,small+1,end);
        }
    }




    public static long time(Integer[] data, Function<Integer[], Integer[]> sorter){
        long start = System.currentTimeMillis();
        sorter.apply(data);
        return System.currentTimeMillis()-start;
    }

    public static boolean createCSV(String filename, ArrayList<Long> sizes, ArrayList<Long> times){
        File csv = new File(""+filename);
        try {
            csv.createNewFile();
            PrintWriter file = new PrintWriter(new FileWriter(csv));
//        String file="Time,Array Size\n";
            file.println("Time,Array Size");
            for (int i = 0; i < sizes.size(); i++)
                file.println(times.get(i) + "," + sizes.get(i));
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
