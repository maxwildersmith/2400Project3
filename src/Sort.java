public class Sort<T extends Comparable> {

    public static String print(Integer[] arr){
        String out = "";
        for(Integer i: arr)
            out+=i+", ";
        return out.substring(0,out.length()-2);
    }

    public T[] bubbleSort(T[] data){
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

    public T[] selectionSort(T[] data){
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
        return data;
    }

    public T[] mergeSort(T[] data){
        return data;
    }

    public T[] quickSort(T[] data){
        return data;
    }
}
