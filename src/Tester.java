public class Tester {
    public static void main(String[] args) {
        Sort<Integer> sorter = new Sort();
        int n = 10, maxVal=100;
        Integer[] data = new Integer[n];
        for(int i=0;i<n;i++)
            data[i]=(int)(Math.random()*maxVal+1);
        System.out.println(Sort.print(data));
        System.out.println(Sort.print(sorter.selectionSort(data)));
        System.out.println(Sort.print(data));

    }
}
