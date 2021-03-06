import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

public class Sort<T extends Comparable> {

    /**
     * Public method to bubble sort a data array and time the operation.
     * @param data The array of data to be sorted.
     * @return The time it took to sort in ms.
     */
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

    /**
     * Public method to perform and time selection sort on a data array.
     * @param data The array of data to be sorted.
     * @return The time in ms it took to sort data.
     */
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

    /**
     * Public method to perform and time insertion sort on a data array.
     * @param data The array of data to be sorted.
     * @return The time in ms it took to sort data.
     */
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

    /**
     * The public merge sort method that times and sets up the first merge sort call.
     * @param arr The array of data to sort.
     * @return The time in ms it took to sort the array.
     */
    public long mergeSort(T[] arr){
        long startTime = System.currentTimeMillis();
        mergeSort(arr,0,arr.length-1);
        return System.currentTimeMillis()-startTime;
    }

    /**
     * The private recursive method for performing merge sort.
     * @param data The data array to sort.
     * @param start The starting index of the subarray to sort.
     * @param end The end index of te subarray to sort.
     */
    private void mergeSort(T[] data,int start,int end){
        if(start<end){
            int mid=(start+end)/2;
            mergeSort(data,start,mid);
            mergeSort(data,mid+1,end);
            merge(data,start,mid,end);
        }
    }

    /**
     * Private recursive method to merge arrays, used by the merge sort algorithm.
     * @param arr The array of data to merge.
     * @param start The starting index representing the left data array.
     * @param mid The mid point representing the break between the left and right data array.
     * @param end The end point of the data to look at representing the end of the right data array.
     */
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

    /**
     * Public quick sort method that times and sets up the recursive calls.
     * @param data Teh data to sort.
     * @return The time it took to perform of the sorting in ms.
     */
    public long quickSort(T[] data){
        long startTime = System.currentTimeMillis();
        quickSort(data,0,data.length-1);
        return System.currentTimeMillis()-startTime;
    }

    /**
     * Private, recursive quick sort method.
     * @param data The data to sort.
     * @param start The starting index to look at.
     * @param end The end index to look up to.
     */
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

    /**
     * A helper method mainly used in debugging to print out an array.
     * @param arr The array to print.
     * @return The String representing the array.
     */
    public static String print(Integer[] arr){
        String out = "";
        for(Integer i: arr)
            out+=i+", ";
        return out.substring(0,out.length()-2);
    }

    /**
     * Static helper method to export a CSV file for a given set of algorithm times and array sizes. If the CSV file
     * already exists, it will append to the file, and add additional array size values if needed.
     * @param filename The name of the file to create.
     * @param sizes An ArrayList of the dataset sizes.
     * @param times An ArrayList of the time to sort in order of the sizes.
     * @return True if successfully created the CSV, False if there was some IO error.
     */
    public static boolean createCSV(String filename, ArrayList<Long> sizes, ArrayList<Long> times){
        File csv = new File(""+filename);
        try {
            if(csv.createNewFile()) {
                PrintWriter file = new PrintWriter(new FileWriter(csv));
                file.print("Array Size");
                for (int i = 0; i < sizes.size(); i++)
                    file.print(","+sizes.get(i));
                file.print("\nTimes");
                for(Long time:times)
                    file.print(","+time);
                file.println();
                file.close();

            } else {
                Scanner fileReader = new Scanner(csv);
                String currentSizes = fileReader.nextLine();
                int size=currentSizes.split(",").length-1;
                if(size<sizes.size()){
                    for(int i=size;i<sizes.size();i++)
                        currentSizes+=","+sizes.get(i);
                    currentSizes+="\n";
                    while(fileReader.hasNextLine())
                        currentSizes+=fileReader.nextLine()+"\n";
                    FileWriter writer = new FileWriter(csv);
                    writer.write(currentSizes);
                    writer.close();
                }

                PrintWriter file = new PrintWriter(new FileWriter(csv,true));
                file.print("Times");
                for(Long time:times)
                    file.print(","+time);
                file.println();
                file.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
