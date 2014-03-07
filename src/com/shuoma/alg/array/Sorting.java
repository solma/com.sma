package com.shuoma.alg.array;

import java.util.*;

public class Sorting {
	public static int MAX_VALUE=1000; //used by count sorting
    public static Random r = new Random();
    
    public static void main(String[] args) {
        new Sorting().main();
    }

    void main() {
        // System.out.println(r.nextInt(100)+r.nextFloat());
        int min = (1 << 31) + -1;
        // System.out.println(Arrays.binarySearch(a, 1) );
        // System.out.println(binarySearch(a, 1));

        testSortAlgorithms();
        
        testSelectionAlgorithms();
   
        testSearchAlgorithms();
    }
    
   

    void testSortAlgorithms() {
        int sortAlgorithmChoice;
        for(int code=1;code<=4;code++){
            sortAlgorithmChoice=code;
            for (int i = 0; i < 10000; i++) {
                int[] a = genRandomArray();
                int[] cpy = Arrays.copyOf(a, a.length);
                switch (sortAlgorithmChoice) {
                case 1:
                    quickSort(cpy, 0, cpy.length - 1);
                    break;
                case 2:
                    cpy=countSort(cpy);
                    break;
                case 3:
                    cpy=insertSort(cpy);
                    break;
                case 4:
                    bubbleSort(cpy);
                    break;
                }
                sortUsingLibrary(a);
                if (!isSame(a, cpy))
                    System.out.println(Arrays.toString(cpy));
            }
        }
    }

        int[] genRandomArray() {
            int length = r.nextInt(10);
            int[] ret = new int[length];
            for (int i = 0; i < length; i++) {
                ret[i] = r.nextInt(MAX_VALUE)*(r.nextBoolean()?1:-1);
            }
            return ret;
        }

        void sortUsingLibrary(int[] a) {
            Arrays.sort(a);
        }

        void swap(int[] a, int i, int j) {
            if (i == j) return;
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }

        boolean isSame(int[] a, int[] cpy) {
            if (a.length != cpy.length)
                return false;
            for (int i = 0; i < a.length; i++) {
                if (a[i] != cpy[i])
                    return false;
            }
            return true;
        }

        
        void bubbleSort(int[] a) {
            //in place 
            int tmp;
            for (int i = 0; i < a.length; i++)
                for (int j = i + 1; j < a.length; j++)
                    if (a[j] < a[i]) {
                        swap(a, i, j);
                    }
        }

        int[] insertSort(int[] a) {
            //not in place
            int[] b = new int[a.length];
            Arrays.fill(b, Integer.MAX_VALUE);
            int i, j, k;
            for (i = 0; i < a.length; i++) {
                for (j = 0; j < b.length && b[j] < a[i]; )  j++;               
                for (k=i-1;k>=j;k--) b[k+1]=b[k];  //move the elements backward
                b[j] = a[i];
                //System.out.println(Arrays.toString(b));
            }
            return b;
        }

        int[] countSort(int[] a) {
            //not in place
            int[] b = new int[a.length];
            int[] c = new int[MAX_VALUE*2+1];
            Arrays.fill(c, 0);
            int i;            
            for (i = 0; i < a.length; i++)
                c[a[i]+MAX_VALUE]++;
            for (i = 1; i < c.length; i++)
                c[i] += c[i - 1];
            for (i = 0; i < a.length; i++) {
                b[c[a[i]+MAX_VALUE] - 1] = a[i];
                c[a[i]+MAX_VALUE]--;
            }
            return b;
        }

        void quickSort(int[] a, int low, int high) {
            //in place
            if (low >= high) return; //tricky line
            int cut = partition(a, low, high);
            quickSort(a, low, cut - 1);
            quickSort(a, cut + 1, high);
        }
    
            int partition(int[] a, int low, int high) {
                int med = low + (high - low) / 2;
                int pivot = a[med];
                swap(a, med, high);
                int storeIdx = low;
                for (int i = low; i < high; i++) {
                    if (a[i] < pivot)
                        swap(a, storeIdx++, i);
                }
                swap(a, storeIdx, high);
                return storeIdx;
            }

    void testSelectionAlgorithms(){
        int sortAlgorithmChoice;
        for(int code=1;code<=1;code++){
            sortAlgorithmChoice=code;
            for (int i = 0; i < 100; i++) {
                int[] a = genRandomArray();
                int K;
                for(int j=1;j<=a.length;j++){
                    K=j;
                    int[] cpy = Arrays.copyOf(a, a.length);
                    switch (sortAlgorithmChoice) {
                    case 1:
                        quickSelect(cpy, 0, cpy.length - 1, K);
                        break;
                    }
                    sortUsingLibrary(a);
                    if ( a[K-1]!=cpy[K-1] ) 
                        System.out.println(Arrays.toString(cpy)+" "+K+" "+a[K-1]);
            
                }
            }
        }
    }
    
        void quickSelect(int[] a, int l, int r, int K){
            //this is actually partial sorting
            if(l>=r) return;
            int cut=partition(a, l, r);
            //System.out.println(cut);
            if(cut==K-1) return;
            if(cut>K-1) quickSelect(a, l, cut-1, K);
            else
                if(cut<K-1) quickSelect(a, cut+1, r, K-cut-1);
        }
        
    void testSearchAlgorithms(){
    	BinarySearch bs=new BinarySearch();
        int sortAlgorithmChoice;
        for(int code=1;code<=2;code++){
            sortAlgorithmChoice=code;
            for (int i = 0; i < 1000; i++) {
                int[] a = genRandomArray();
                if(a.length==0) continue;
                sortUsingLibrary(a);
                int[] cpy = Arrays.copyOf(a, a.length);
                int rand=r.nextInt((int) (a.length*1.2 ));
                int key;
                if(rand>=0&&rand<cpy.length) key=cpy[rand];
                else key=rand;
                int idx=-1;
                switch (sortAlgorithmChoice) {
                case 1:
                    idx=bs.binarySearchFirstAppear(cpy, key);
                    if ( idx!=bs.linearSearchFirstAppear(cpy, key) ) 
                        System.out.println(Arrays.toString(cpy)+" "+key+" "+idx);
                    break;
                case 2:
                    idx=bs.binarySearchLastAppear(cpy, key);
                    if ( idx!=bs.linearSearchLastAppear(cpy, key) ) 
                        System.out.println(Arrays.toString(cpy)+" "+key+" "+idx);
                    break;
                
                }
                
           }
        }
    }
        
}
