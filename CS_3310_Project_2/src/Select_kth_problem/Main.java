package Select_kth_problem;
/*
 * Alexa Tang
 * Project 2
 * CS 3310
 * */

import java.lang.reflect.Array;
import java.util.Random;
import java.lang.System;
import java.util.Arrays;

class Array_functs
{
    public int [] Array_Generator (int size)
    {
        int n = size;
        Random rand = new Random();
        int [] Array = new int[n];
        for (int i = 0; i < size; i++)
        {
            Array [i]=  rand.nextInt(101); // numbers from 0 to 100
        }
        return Array;
    }

    public void Print_array (int [] arr)
    {
        int size = arr.length;
        for (int i = 0; i < arr.length; i++)
        {
            System.out.println("arr[" + i + "] = " + arr[i]);
        }
    }
}
class MergeSortAlgo
{
    public void Mergesort (int arr[], int left, int right)
    {
        if (left < right)
        {
            //middle point
            int middle  = (left + right) / 2;
            //System.out.println("middle = " + middle);

            //sort first (left) half of array
            Mergesort(arr, left, middle);

            //sort second (right) half of array
            Mergesort(arr,middle + 1, right);

            //merge sorted halves
            Merge(arr, left, middle, right);
        }
        else
            return;
    }

    public void Merge(int arr[], int left, int middle, int right)
    {

        //index of left subarray
        int i = left;
        //index of right subarray
        int j = middle + 1;
        //index of sorted array
        int k = left;
        //temp array
        int [] sort_arr = new int[arr.length];

        //comparing the corresponding elements of the sorted subarrays
        while(i <= middle && j <= right)
        {
            if(arr[i] < arr[j])
            {
                sort_arr[k] = arr[i] ;
                i++;
            }
            else
            {
                sort_arr[k] = arr[j];
                j++;
            }
            k++;
        }
        //copying the rest of the elements into temp array
        if(i > middle)
        {
            while(j <= right)
            {
                sort_arr[k] = arr[j];
                j++;
                k++;
            }
        }
        else
        {
            while(i <= middle)
            {
                sort_arr[k] = arr[i];
                i++;
                k++;
            }
        }

        //copy sorted array back to original array
        for(int a = left; a <= right; a++)
        {
            arr[a] = sort_arr[a];
        }
    }
}

class QuickSort
{
    public void quickSortRecursive(int [] arr, int left, int right, int k)
    {
        //if p = q, there is only one element in the array
        //arrays of 1 element are always sorted
        if (left < right)
        {
            int pivot = partition(arr, left, right);
            //partitioning until the pivot item is at the kth slot
            if(pivot == k - 1)
            {
                System.out.println("k = " + k + ", is " + arr[k-1]);
                return;
            }

            quickSortRecursive(arr, left, pivot - 1, k);
            quickSortRecursive(arr, pivot + 1, right, k);
        }
    }
    public int partition(int [] arr, int left, int right)
    {
        //pivot is first element
        int pivot = arr[left];
        int j = left;
        int temp;
        //arr[i] .... arr[j]
        for (int i = left + 1; i <= right; i++) {
            if (arr[i] < pivot) {
                j++;
                //arr[i] <-> arr[j]
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int pivotposition = j;
        //putting pivot item at pivot position
        temp = arr[left];
        arr[left] = arr[pivotposition];
        arr[pivotposition] = temp;
        return pivotposition;
    }
    public void quickSortIterative(int [] arr, int n, int kth)
    {
        int left = 0;
        int right = n - 1;
        int pivotposition;
        do
        {
            pivotposition = partition(arr, left, right);
            if(kth - 1  == pivotposition)
            {
                System.out.println("k = " + kth + ", is " + arr[kth-1]);
                return;
            }
            else if(kth - 1  < pivotposition)
            {
                right = pivotposition - 1;
            }
            else
            {
                left = pivotposition + 1;
            }
        }while(kth - 1 != pivotposition);
    }

}

public class Main
{
    public static void main(String[] args)
    {
        //generating lists to sort////////////////////////////////////////
        /*
        arr_10 = rand_array.Array_Generator(10);
        arr_50 = rand_array.Array_Generator(50);
        arr_100 = rand_array.Array_Generator(100);
        arr_250 = rand_array.Array_Generator(250);
        arr_500 = rand_array.Array_Generator(500);
        arr_1000 = rand_array.Array_Generator(1000);

        System.out.println("size 10");
        System.out.println(Arrays.toString(arr_10));
        System.out.println("size 50");
        System.out.println(Arrays.toString(arr_50));
        System.out.println("size 100");
        System.out.println(Arrays.toString(arr_100));
        System.out.println("size 250");
        System.out.println(Arrays.toString(arr_250));
        System.out.println("size 500");
        System.out.println(Arrays.toString(arr_500));
        System.out.println("size 1000");
        System.out.println(Arrays.toString(arr_1000));
        */
        Array_functs rand_array = new Array_functs();
        MergeSortAlgo MergeSortalgo = new MergeSortAlgo();
        QuickSort quickSort = new QuickSort();
        //number of times the lists will be sorted using each algorithm for each kth smallest value
        int testnum = 200;
        int counter, k;
        //temp arrays to copy original lists
        int [] temp_10 = new int [10];
        int [] temp_50 = new int [50];
        int [] temp_100 = new int [100];
        int [] temp_250 = new int [250];
        int [] temp_500 = new int [500];
        int [] temp_1000 = new int [1000];

        int [] arr_10 = {31, 93, 70, 41, 30, 55, 94, 7, 12, 26};
        int [] arr_50 = {12, 56, 72, 58, 28, 63, 66, 99, 48, 83, 10, 65, 48, 85, 52, 52, 95, 22, 27, 98, 21, 10, 76, 7, 16, 27, 66, 80, 46, 68, 27, 71, 81, 100, 68, 64, 90, 32, 86, 75, 79, 9, 61, 53, 26, 97, 56, 74, 75, 10};
        int [] arr_100 = {52, 94, 42, 96, 35, 7, 94, 85, 100, 57, 58, 11, 76, 0, 20, 3, 25, 36, 73, 7, 57, 20, 17, 30, 69, 8, 64, 4, 65, 0, 31, 88, 85, 41, 100, 70, 94, 82, 1, 80, 14, 17, 65, 27, 46, 59, 5, 68, 24, 13, 41, 32, 76, 64, 50, 1, 49, 62, 35, 69, 12, 6, 71, 60, 2, 71, 93, 36, 3, 23, 17, 44, 5, 37, 75, 27, 71, 26, 59, 32, 85, 39, 8, 59, 7, 75, 18, 37, 40, 68, 98, 65, 66, 99, 74, 84, 8, 80, 40, 100};
        int [] arr_250 = {86, 98, 33, 68, 62, 30, 27, 24, 41, 46, 60, 97, 71, 1, 12, 15, 21, 41, 30, 8, 81, 98, 19, 49, 56, 39, 76, 65, 45, 13, 48, 24, 6, 67, 50, 11, 78, 20, 19, 64, 57, 99, 8, 81, 46, 29, 16, 68, 61, 28, 10, 86, 76, 69, 47, 70, 93, 96, 98, 43, 100, 69, 85, 4, 48, 16, 24, 62, 25, 65, 41, 13, 71, 4, 41, 33, 76, 84, 44, 23, 84, 95, 53, 27, 88, 18, 67, 86, 9, 84, 63, 26, 18, 85, 75, 88, 49, 65, 45, 30, 95, 0, 57, 62, 18, 40, 30, 96, 49, 6, 70, 99, 28, 50, 0, 20, 98, 28, 67, 76, 1, 30, 14, 73, 14, 66, 60, 83, 80, 95, 1, 23, 79, 73, 33, 63, 19, 3, 65, 34, 46, 59, 31, 94, 81, 59, 4, 96, 14, 17, 40, 98, 35, 78, 85, 47, 54, 24, 25, 92, 7, 55, 61, 48, 36, 23, 98, 67, 19, 71, 20, 28, 68, 58, 38, 67, 58, 15, 85, 63, 75, 18, 90, 2, 80, 39, 51, 33, 85, 78, 7, 40, 92, 67, 75, 10, 86, 84, 8, 42, 99, 12, 78, 54, 21, 99, 85, 91, 53, 74, 0, 2, 81, 29, 84, 6, 76, 75, 92, 15, 84, 15, 93, 95, 13, 45, 2, 54, 18, 19, 37, 18, 1, 57, 27, 38, 99, 1, 82, 51, 36, 56, 36, 21, 94, 28, 36, 84, 75, 7};
        int [] arr_500 = {80, 98, 62, 53, 26, 6, 80, 58, 32, 76, 28, 8, 79, 15, 16, 4, 85, 66, 21, 28, 43, 15, 55, 56, 20, 92, 95, 39, 32, 94, 99, 3, 69, 74, 43, 40, 74, 68, 46, 41, 43, 72, 94, 62, 0, 79, 96, 73, 29, 63, 83, 12, 75, 16, 0, 43, 95, 60, 68, 18, 65, 12, 65, 78, 17, 75, 60, 0, 43, 96, 97, 24, 58, 67, 100, 92, 11, 50, 84, 40, 54, 4, 10, 10, 12, 23, 100, 61, 28, 35, 46, 55, 31, 56, 89, 22, 7, 63, 50, 57, 18, 87, 28, 11, 47, 17, 95, 53, 99, 72, 98, 11, 71, 1, 70, 78, 4, 72, 77, 1, 75, 6, 26, 4, 81, 79, 5, 93, 77, 87, 65, 51, 50, 29, 70, 14, 75, 80, 41, 32, 44, 6, 12, 28, 7, 88, 78, 39, 86, 29, 16, 11, 75, 94, 3, 81, 64, 97, 85, 27, 87, 73, 20, 29, 82, 93, 6, 77, 60, 42, 47, 78, 4, 51, 25, 68, 60, 67, 40, 86, 68, 70, 29, 100, 91, 58, 24, 90, 69, 67, 48, 39, 12, 92, 32, 16, 86, 5, 79, 35, 77, 94, 84, 65, 76, 86, 1, 89, 13, 75, 100, 96, 2, 57, 10, 74, 83, 31, 66, 13, 28, 95, 30, 49, 97, 32, 25, 69, 93, 81, 24, 16, 77, 96, 70, 24, 30, 19, 64, 84, 57, 56, 55, 48, 14, 79, 27, 29, 36, 52, 21, 53, 93, 92, 41, 83, 53, 21, 1, 57, 81, 45, 80, 93, 99, 23, 11, 32, 32, 77, 41, 52, 32, 84, 87, 67, 68, 55, 61, 68, 56, 81, 86, 89, 69, 71, 44, 16, 27, 71, 44, 86, 32, 36, 65, 49, 31, 74, 39, 3, 20, 45, 20, 40, 49, 5, 87, 17, 15, 3, 31, 38, 48, 95, 13, 7, 29, 95, 68, 12, 54, 14, 10, 16, 14, 7, 59, 100, 25, 52, 67, 12, 77, 28, 65, 40, 31, 29, 60, 6, 5, 35, 86, 34, 39, 91, 0, 40, 18, 80, 33, 71, 58, 72, 64, 21, 97, 44, 84, 27, 96, 84, 81, 44, 28, 67, 63, 28, 90, 28, 66, 39, 38, 17, 83, 58, 76, 39, 14, 30, 27, 69, 78, 82, 29, 98, 98, 44, 22, 68, 32, 32, 36, 33, 52, 47, 68, 88, 74, 47, 34, 28, 35, 94, 81, 95, 5, 26, 32, 38, 50, 22, 3, 88, 100, 78, 72, 25, 90, 96, 19, 1, 53, 66, 64, 98, 43, 47, 32, 96, 50, 2, 80, 42, 19, 9, 3, 79, 37, 58, 35, 93, 21, 42, 54, 0, 34, 91, 85, 99, 61, 13, 66, 19, 15, 86, 2, 30, 46, 57, 48, 92, 41, 6, 17, 89, 16, 11, 69, 100, 98, 61, 57, 92, 93, 59, 33, 18, 6, 21, 32, 99, 55, 62, 87, 15, 42, 88, 99, 70, 36, 27, 96, 31, 51, 67, 87, 25, 2, 51};
        int [] arr_1000 = {53, 79, 21, 73, 24, 99, 37, 8, 20, 42, 52, 12, 47, 86, 33, 97, 88, 94, 5, 76, 61, 95, 18, 11, 82, 72, 71, 50, 27, 63, 18, 30, 66, 52, 35, 78, 56, 73, 11, 56, 49, 6, 73, 87, 5, 100, 71, 33, 24, 95, 30, 4, 78, 82, 81, 56, 25, 38, 21, 4, 77, 53, 54, 21, 94, 18, 64, 77, 3, 43, 93, 19, 29, 14, 38, 58, 46, 70, 46, 35, 75, 86, 90, 40, 10, 76, 70, 42, 52, 2, 89, 65, 38, 53, 88, 41, 3, 76, 4, 94, 0, 25, 88, 12, 40, 2, 65, 2, 70, 29, 68, 52, 82, 38, 50, 16, 37, 24, 11, 60, 72, 66, 19, 8, 32, 65, 5, 10, 31, 77, 37, 20, 33, 71, 10, 73, 100, 80, 55, 18, 64, 20, 94, 11, 93, 48, 34, 20, 44, 72, 83, 76, 16, 33, 63, 32, 50, 81, 61, 98, 82, 80, 8, 60, 52, 3, 93, 97, 62, 53, 79, 21, 18, 45, 63, 9, 63, 10, 35, 93, 86, 64, 3, 71, 91, 61, 19, 1, 55, 7, 10, 69, 57, 41, 95, 67, 11, 0, 86, 1, 53, 22, 62, 23, 6, 24, 61, 74, 52, 53, 54, 37, 54, 69, 28, 27, 1, 32, 80, 55, 30, 56, 55, 40, 92, 90, 32, 36, 96, 77, 70, 71, 97, 26, 35, 55, 35, 82, 67, 89, 71, 14, 56, 41, 35, 13, 41, 50, 86, 92, 67, 47, 78, 52, 74, 83, 38, 30, 32, 45, 38, 42, 82, 86, 5, 46, 65, 83, 35, 46, 74, 11, 39, 8, 2, 82, 43, 5, 72, 47, 91, 19, 36, 66, 45, 48, 39, 48, 12, 39, 96, 17, 61, 33, 16, 35, 1, 22, 80, 98, 51, 34, 57, 0, 75, 38, 76, 36, 0, 61, 96, 33, 0, 23, 32, 87, 82, 63, 90, 49, 14, 24, 35, 8, 37, 60, 17, 60, 99, 76, 37, 63, 64, 66, 80, 25, 2, 1, 43, 12, 13, 27, 90, 100, 63, 73, 55, 9, 23, 42, 39, 9, 99, 1, 79, 13, 49, 76, 42, 62, 81, 30, 91, 6, 54, 54, 45, 21, 65, 34, 38, 14, 73, 12, 53, 36, 94, 22, 0, 34, 100, 57, 63, 76, 3, 70, 7, 23, 64, 75, 78, 85, 81, 41, 99, 96, 34, 18, 56, 65, 82, 86, 3, 70, 93, 63, 67, 89, 11, 99, 19, 76, 10, 24, 35, 20, 54, 46, 38, 41, 33, 11, 66, 28, 48, 4, 65, 64, 78, 21, 23, 20, 8, 62, 98, 17, 81, 31, 27, 41, 16, 56, 51, 85, 85, 50, 52, 35, 88, 91, 8, 16, 50, 10, 50, 65, 19, 19, 56, 62, 94, 84, 40, 82, 12, 73, 65, 91, 68, 86, 89, 18, 32, 88, 69, 56, 50, 19, 11, 6, 0, 91, 44, 64, 99, 34, 28, 30, 49, 44, 8, 5, 24, 30, 45, 10, 87, 10, 52, 0, 82, 3, 32, 59, 100, 76, 71, 25, 75, 91, 34, 91, 65, 40, 96, 79, 25, 42, 91, 71, 2, 39, 97, 61, 26, 76, 18, 78, 74, 64, 0, 8, 65, 14, 61, 71, 58, 32, 51, 30, 93, 67, 45, 34, 77, 73, 81, 91, 60, 69, 69, 5, 10, 91, 41, 98, 7, 40, 96, 95, 31, 32, 98, 65, 47, 57, 46, 62, 13, 70, 48, 23, 36, 90, 100, 30, 16, 100, 74, 93, 76, 99, 38, 11, 94, 39, 16, 6, 44, 81, 81, 82, 5, 70, 6, 46, 32, 91, 91, 13, 97, 71, 1, 45, 15, 100, 2, 83, 54, 53, 99, 35, 20, 29, 92, 59, 41, 45, 0, 14, 91, 11, 84, 33, 15, 46, 74, 13, 95, 79, 99, 27, 8, 92, 99, 61, 41, 46, 0, 73, 46, 78, 30, 58, 22, 23, 34, 65, 92, 92, 59, 6, 68, 68, 86, 45, 10, 83, 99, 39, 65, 95, 47, 61, 19, 87, 47, 46, 54, 38, 39, 40, 46, 33, 2, 32, 34, 19, 55, 33, 70, 27, 68, 72, 80, 5, 96, 9, 38, 85, 34, 64, 90, 46, 91, 24, 22, 1, 82, 2, 73, 17, 26, 58, 64, 63, 60, 65, 56, 62, 18, 75, 32, 78, 29, 90, 2, 73, 33, 35, 86, 13, 25, 9, 48, 6, 24, 35, 44, 32, 97, 35, 79, 26, 22, 62, 12, 81, 17, 48, 90, 70, 91, 58, 26, 72, 15, 82, 76, 42, 6, 77, 99, 86, 41, 25, 3, 68, 26, 38, 73, 77, 40, 13, 15, 66, 7, 66, 3, 99, 19, 72, 3, 6, 29, 29, 60, 22, 50, 23, 99, 92, 50, 14, 76, 17, 11, 67, 55, 71, 63, 78, 78, 50, 1, 95, 31, 99, 42, 11, 90, 96, 90, 81, 79, 25, 7, 99, 60, 33, 91, 2, 56, 43, 50, 42, 32, 86, 91, 61, 88, 96, 49, 43, 12, 65, 100, 41, 2, 50, 4, 91, 64, 26, 37, 63, 79, 63, 91, 36, 23, 90, 88, 94, 17, 86, 73, 48, 51, 81, 92, 6, 80, 79, 24, 55, 11, 77, 61, 58, 92, 40, 51, 38, 71, 80, 13, 48, 5, 64, 61, 91, 78, 6, 75, 82, 39, 46, 10, 98, 98, 64, 87, 43, 89, 76, 28, 71, 71, 88, 61, 90, 29, 45, 59, 7, 38, 75, 80, 37, 27, 84, 56, 59, 89, 0, 99, 6, 37, 54, 90, 4, 84, 95, 0, 95, 52, 76, 22, 90, 17, 82, 95, 12, 97, 5, 91, 69, 53, 65, 40, 75, 5, 31, 12, 39, 75, 77, 16, 51, 85, 24, 77, 16, 0, 51, 49, 71, 38, 90, 75, 41, 64, 99, 10, 13, 21, 89, 71, 1, 2, 19, 85, 56, 2, 85, 10, 72, 50, 47, 1, 86, 66, 23, 4, 78, 4, 94, 59, 40, 47, 34, 24, 83, 26, 23, 70, 61, 89, 52, 86, 0, 75, 53, 17, 69, 95, 15, 74, 65};



////////////////////////////   MERGE SORT   ///////////////////////////////////////////////////////
        /*   n = 10

        counter = 0;
        //start counting run time
        long totalTime0;
        System.out.println("Mergesort, array[10], k = 1");
        k = 1;
        System.arraycopy(arr_10, 0, temp_10, 0, 10);

        long startTime0 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_10, 0, arr_10.length - 1);
            System.out.println("k = 1, " + temp_10[k-1]);
            counter++;
        }
        long endTime0 = System.nanoTime();
        totalTime0 = endTime0 - startTime0;
        System.out.println("total time: ");
        System.out.println(totalTime0);


        //reset counter
        counter = 0;
        //start counting run time
        long totalTime1;
        System.out.println("Mergesort, array[10], k = n/4");
        k = 10/4;
        //temp array to copy original array into
        System.arraycopy(arr_10, 0, temp_10, 0, 10);
        long startTime1 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_10, 0, arr_10.length - 1);
            System.out.println("k = n/4, " + temp_10[k-1]);
            counter++;
        }
        long endTime1 = System.nanoTime();
        totalTime1 = endTime1 - startTime1;
        System.out.println("total time: ");
        System.out.println( totalTime1 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime2;
        System.out.println("Mergesort, array[10], k = n/2");
        k = 10/2;
        //temp array to copy original array into
        System.arraycopy(arr_10, 0, temp_10, 0, 10);
        long startTime2 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_10, 0, arr_10.length - 1);
            System.out.println("k = n/2, " + temp_10[k-1]);
            counter++;
        }
        long endTime2 = System.nanoTime();
        totalTime2 = endTime2 - startTime2;
        System.out.println("total time: ");
        System.out.println( totalTime2 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime3;
        System.out.println("Mergesort, array[10], k = 3n/4");
        k = (10*3)/4;
        System.out.println("k = " + k);
        //temp array to copy original array into
        System.arraycopy(arr_10, 0, temp_10, 0, 10);
        long startTime3 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_10, 0, arr_10.length - 1);
            System.out.println("k = 3n/4, " + temp_10[k-1]);
            counter++;
        }
        long endTime3 = System.nanoTime();
        totalTime3 = endTime3 - startTime3;
        System.out.println("total time: ");
        System.out.println( totalTime3 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime4;
        System.out.println("Mergesort, array[10], k = n");
        k = 10;
        //temp array to copy original array into
        System.arraycopy(arr_10, 0, temp_10, 0, 10);
        long startTime4 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_10, 0, arr_10.length - 1);
            System.out.println("k = n, " + temp_10[k-1]);
            counter++;
        }
        long endTime4 = System.nanoTime();
        totalTime4 = endTime4 - startTime4;
        System.out.println("total time: ");
        System.out.println( totalTime4 );


        ///////////// n = 50////////////////////////////
        counter = 0;
        //start counting run time
        long totalTime0;
        System.out.println("Mergesort, array[50], k = 1");
        k = 1;
        System.arraycopy(arr_50, 0, temp_50, 0, 50);
        long startTime0 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_50, 0, arr_50.length - 1);
            System.out.println("k = 1, " + temp_50[k-1]);
            counter++;
        }
        long endTime0 = System.nanoTime();
        totalTime0 = endTime0 - startTime0;
        System.out.println("total time: ");
        System.out.println(totalTime0);


        //reset counter
        counter = 0;
        //start counting run time
        long totalTime1;
        System.out.println("Mergesort, array[50], k = n/4");
        k = 50/4;
        //temp array to copy original array into
        System.arraycopy(arr_50, 0, temp_50, 0, 50);
        long startTime1 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_50, 0, arr_50.length - 1);
            System.out.println("k = n/4, " + temp_50[k-1]);
            counter++;
        }
        long endTime1 = System.nanoTime();
        totalTime1 = endTime1 - startTime1;
        System.out.println("total time: ");
        System.out.println( totalTime1 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime2;
        System.out.println("Mergesort, array[50], k = n/2");
        k = 50/2;
        //temp array to copy original array into
        System.arraycopy(arr_50, 0, temp_50, 0, 50);
        long startTime2 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_50, 0, arr_50.length - 1);
            System.out.println("k = n/2, " + temp_50[k-1]);
            counter++;
        }
        long endTime2 = System.nanoTime();
        totalTime2 = endTime2 - startTime2;
        System.out.println("total time: ");
        System.out.println( totalTime2 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime3;
        System.out.println("Mergesort, array[50], k = 3n/4");
        k = (50*3)/4;
        //temp array to copy original array into
        System.arraycopy(arr_50, 0, temp_50, 0, 50);
        long startTime3 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_50, 0, arr_50.length - 1);
            System.out.println("k = 3n/4, " + temp_50[k-1]);
            counter++;
        }
        long endTime3 = System.nanoTime();
        totalTime3 = endTime3 - startTime3;
        System.out.println("total time: ");
        System.out.println( totalTime3 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime4;
        System.out.println("Mergesort, array[50], k = n");
        k = 50;
        //temp array to copy original array into
        System.arraycopy(arr_50, 0, temp_50, 0, 50);
        long startTime4 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_50, 0, arr_50.length - 1);
            System.out.println("k = n, " + temp_50[k-1]);
            counter++;
        }
        long endTime4 = System.nanoTime();
        totalTime4 = endTime4 - startTime4;
        System.out.println("total time: ");
        System.out.println( totalTime4 );


               //////////////////// n = 100/////////////////////////////////////////
        counter = 0;
        //start counting run time
        long totalTime0;
        System.out.println("Mergesort, array[100], k = 1");
        k = 1;
        System.arraycopy(arr_100, 0, temp_100, 0, 100);
        long startTime0 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_100, 0, arr_100.length - 1);
            System.out.println("k = 1, " + temp_100[k-1]);
            counter++;
        }
        long endTime0 = System.nanoTime();
        totalTime0 = endTime0 - startTime0;
        System.out.println("total time: ");
        System.out.println(totalTime0);


        //reset counter
        counter = 0;
        //start counting run time
        long totalTime1;
        System.out.println("Mergesort, array[100], k = n/4");
        k = 100/4;
        //temp array to copy original array into
        System.arraycopy(arr_100, 0, temp_100, 0, 100);
        long startTime1 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_100, 0, arr_100.length - 1);
            System.out.println("k = n/4, " + temp_100[k-1]);
            counter++;
        }
        long endTime1 = System.nanoTime();
        totalTime1 = endTime1 - startTime1;
        System.out.println("total time: ");
        System.out.println( totalTime1 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime2;
        System.out.println("Mergesort, array[100], k = n/2");
        k = 100/2;
        //temp array to copy original array into
        System.arraycopy(arr_100, 0, temp_100, 0, 100);
        long startTime2 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_100, 0, arr_100.length - 1);
            System.out.println("k = n/2, " + temp_100[k-1]);
            counter++;
        }
        long endTime2 = System.nanoTime();
        totalTime2 = endTime2 - startTime2;
        System.out.println("total time: ");
        System.out.println( totalTime2 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime3;
        System.out.println("Mergesort, array[100], k = 3n/4");
        k = (100*3)/4;
        //temp array to copy original array into
        System.arraycopy(arr_100, 0, temp_100, 0, 100);
        long startTime3 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_100, 0, arr_100.length - 1);
            System.out.println("k = 3n/4, " + temp_100[k-1]);
            counter++;
        }
        long endTime3 = System.nanoTime();
        totalTime3 = endTime3 - startTime3;
        System.out.println("total time: ");
        System.out.println( totalTime3 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime4;
        System.out.println("Mergesort, array[100], k = n");
        k = 100;
        //temp array to copy original array into
        System.arraycopy(arr_100, 0, temp_100, 0, 100);
        long startTime4 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_100, 0, arr_100.length - 1);
            System.out.println("k = n, " + temp_100[k-1]);
            counter++;
        }
        long endTime4 = System.nanoTime();
        totalTime4 = endTime4 - startTime4;
        System.out.println("total time: ");
        System.out.println( totalTime4 );



          ////////////// n = 250/////////////////////////////////////
        counter = 0;
        //start counting run time
        long totalTime0;
        System.out.println("Mergesort, array[250], k = 1");
        k = 1;
        System.arraycopy(arr_250, 0, temp_250, 0, 250);
        long startTime0 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_250, 0, arr_250.length - 1);
            System.out.println("k = 1, " + temp_250[k-1]);
            counter++;
        }
        long endTime0 = System.nanoTime();
        totalTime0 = endTime0 - startTime0;
        System.out.println("total time: ");
        System.out.println(totalTime0);


        //reset counter
        counter = 0;
        //start counting run time
        long totalTime1;
        System.out.println("Mergesort, array[250], k = n/4");
        k = 250/4;
        //temp array to copy original array into
        System.arraycopy(arr_250, 0, temp_250, 0, 250);
        long startTime1 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_250, 0, arr_250.length - 1);
            System.out.println("k = n/4, " + temp_250[k-1]);
            counter++;
        }
        long endTime1 = System.nanoTime();
        totalTime1 = endTime1 - startTime1;
        System.out.println("total time: ");
        System.out.println( totalTime1 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime2;
        System.out.println("Mergesort, array[250], k = n/2");
        k = 250/2;
        //temp array to copy original array into
        System.arraycopy(arr_250, 0, temp_250, 0, 250);
        long startTime2 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_250, 0, arr_250.length - 1);
            System.out.println("k = n/2, " + temp_250[k-1]);
            counter++;
        }
        long endTime2 = System.nanoTime();
        totalTime2 = endTime2 - startTime2;
        System.out.println("total time: ");
        System.out.println( totalTime2 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime3;
        System.out.println("Mergesort, array[250], k = 3n/4");
        k = (250*3)/4;
        //temp array to copy original array into
        System.arraycopy(arr_250, 0, temp_250, 0, 250);
        long startTime3 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_250, 0, arr_250.length - 1);
            System.out.println("k = 3n/4, " + temp_250[k-1]);
            counter++;
        }
        long endTime3 = System.nanoTime();
        totalTime3 = endTime3 - startTime3;
        System.out.println("total time: ");
        System.out.println( totalTime3 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime4;
        System.out.println("Mergesort, array[250], k = n");
        k = 250;
        //temp array to copy original array into
        System.arraycopy(arr_250, 0, temp_250, 0, 250);
        long startTime4 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_250, 0, arr_250.length - 1);
            System.out.println("k = n, " + temp_250[k-1]);
            counter++;
        }
        long endTime4 = System.nanoTime();
        totalTime4 = endTime4 - startTime4;
        System.out.println("total time: ");
        System.out.println( totalTime4 );


         ////////////// n = 500/////////////////////////////////////
        counter = 0;
        //start counting run time
        long totalTime0;
        System.out.println("Mergesort, array[500], k = 1");
        k = 1;
        System.arraycopy(arr_500, 0, temp_500, 0, 500);
        long startTime0 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_500, 0, arr_500.length - 1);
            System.out.println("k = 1, " + temp_500[k-1]);
            counter++;
        }
        long endTime0 = System.nanoTime();
        totalTime0 = endTime0 - startTime0;
        System.out.println("total time: ");
        System.out.println(totalTime0);


        //reset counter
        counter = 0;
        //start counting run time
        long totalTime1;
        System.out.println("Mergesort, array[500], k = n/4");
        k = 500/4;
        //temp array to copy original array into
        System.arraycopy(arr_500, 0, temp_500, 0, 500);
        long startTime1 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_500, 0, arr_500.length - 1);
            System.out.println("k = n/4, " + temp_500[k-1]);
            counter++;
        }
        long endTime1 = System.nanoTime();
        totalTime1 = endTime1 - startTime1;
        System.out.println("total time: ");
        System.out.println( totalTime1 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime2;
        System.out.println("Mergesort, array[500], k = n/2");
        k = 500/2;
        //temp array to copy original array into
        System.arraycopy(arr_500, 0, temp_500, 0, 500);
        long startTime2 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_500, 0, arr_500.length - 1);
            System.out.println("k = n/2, " + temp_500[k-1]);
            counter++;
        }
        long endTime2 = System.nanoTime();
        totalTime2 = endTime2 - startTime2;
        System.out.println("total time: ");
        System.out.println( totalTime2 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime3;
        System.out.println("Mergesort, array[500], k = 3n/4");
        k = (500*3)/4;
        //temp array to copy original array into
        System.arraycopy(arr_500, 0, temp_500, 0, 500);
        long startTime3 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_500, 0, arr_500.length - 1);
            System.out.println("k = 3n/4, " + temp_500[k-1]);
            counter++;
        }
        long endTime3 = System.nanoTime();
        totalTime3 = endTime3 - startTime3;
        System.out.println("total time: ");
        System.out.println( totalTime3 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime4;
        System.out.println("Mergesort, array[500], k = n");
        k = 500;
        //temp array to copy original array into
        System.arraycopy(arr_500, 0, temp_500, 0, 500);
        long startTime4 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_500, 0, arr_500.length - 1);
            System.out.println("k = n, " + temp_500[k-1]);
            counter++;
        }
        long endTime4 = System.nanoTime();
        totalTime4 = endTime4 - startTime4;
        System.out.println("total time: ");
        System.out.println( totalTime4 );


         ////////////// n = 1000/////////////////////////////////////
        counter = 0;
        //start counting run time
        long totalTime0;
        System.out.println("Mergesort, array[1000], k = 1");
        k = 1;
        System.arraycopy(arr_1000, 0, temp_1000, 0, 1000);
        long startTime0 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_1000, 0, arr_1000.length - 1);
            System.out.println("k = 1, " + temp_1000[k-1]);
            counter++;
        }
        long endTime0 = System.nanoTime();
        totalTime0 = endTime0 - startTime0;
        System.out.println("total time: ");
        System.out.println(totalTime0);


        //reset counter
        counter = 0;
        //start counting run time
        long totalTime1;
        System.out.println("Mergesort, array[1000], k = n/4");
        k = 1000/4;
        //temp array to copy original array into
        System.arraycopy(arr_1000, 0, temp_1000, 0, 1000);
        long startTime1 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_1000, 0, arr_1000.length - 1);
            System.out.println("k = n/4, " + temp_1000[k-1]);
            counter++;
        }
        long endTime1 = System.nanoTime();
        totalTime1 = endTime1 - startTime1;
        System.out.println("total time: ");
        System.out.println( totalTime1 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime2;
        System.out.println("Mergesort, array[1000], k = n/2");
        k = 1000/2;
        //temp array to copy original array into
        System.arraycopy(arr_1000, 0, temp_1000, 0, 1000);
        long startTime2 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_1000, 0, arr_1000.length - 1);
            System.out.println("k = n/2, " + temp_1000[k-1]);
            counter++;
        }
        long endTime2 = System.nanoTime();
        totalTime2 = endTime2 - startTime2;
        System.out.println("total time: ");
        System.out.println( totalTime2 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime3;
        System.out.println("Mergesort, array[1000], k = 3n/4");
        k = (1000*3)/4;
        //temp array to copy original array into
        System.arraycopy(arr_1000, 0, temp_1000, 0, 1000);
        long startTime3 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_1000, 0, arr_1000.length - 1);
            System.out.println("k = 3n/4, " + temp_1000[k-1]);
            counter++;
        }
        long endTime3 = System.nanoTime();
        totalTime3 = endTime3 - startTime3;
        System.out.println("total time: ");
        System.out.println( totalTime3 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime4;
        System.out.println("Mergesort, array[1000], k = n");
        k = 1000;
        //temp array to copy original array into
        System.arraycopy(arr_1000, 0, temp_1000, 0, 1000);
        long startTime4 = System.nanoTime();
        while (counter < testnum)
        {
            MergeSortalgo.Mergesort(temp_1000, 0, arr_1000.length - 1);
            System.out.println("k = n, " + temp_1000[k-1]);
            counter++;
        }
        long endTime4 = System.nanoTime();
        totalTime4 = endTime4 - startTime4;
        System.out.println("total time: ");
        System.out.println( totalTime4 );
        */

////////////////////////////   QUICKSORT ITERATIVE   ///////////////////////////////////////////////////////
        //n = 10
/*
        counter = 0;
        //start counting run time
        long totalTime0;
        System.out.println("Quicksort I, array[10], k = 1");
        k = 1;
        System.arraycopy(arr_10, 0, temp_10, 0, 10);

        long startTime0 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_10,temp_10.length,k);
            System.out.println("k = 1, " + temp_10[k-1]);
            counter++;
        }
        long endTime0 = System.nanoTime();
        totalTime0 = endTime0 - startTime0;
        System.out.println("total time: ");
        System.out.println(totalTime0);


        //reset counter
        counter = 0;
        //start counting run time
        long totalTime1;
        System.out.println("quicksort i, array[10], k = n/4");
        k = 10/4;
        //temp array to copy original array into
        System.arraycopy(arr_10, 0, temp_10, 0, 10);
        long startTime1 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_10,temp_10.length,k);
            System.out.println("k = n/4, " + temp_10[k-1]);
            counter++;
        }
        long endTime1 = System.nanoTime();
        totalTime1 = endTime1 - startTime1;
        System.out.println("total time: ");
        System.out.println( totalTime1 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime2;
        System.out.println("quicksort i, array[10], k = n/2");
        k = 10/2;
        //temp array to copy original array into
        System.arraycopy(arr_10, 0, temp_10, 0, 10);
        long startTime2 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_10,temp_10.length,k);
            System.out.println("k = n/2, " + temp_10[k-1]);
            counter++;
        }
        long endTime2 = System.nanoTime();
        totalTime2 = endTime2 - startTime2;
        System.out.println("total time: ");
        System.out.println( totalTime2 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime3;
        System.out.println("quicksort i, array[10], k = 3n/4");
        k = (10*3)/4;
        System.out.println("k = " + k);
        //temp array to copy original array into
        System.arraycopy(arr_10, 0, temp_10, 0, 10);
        long startTime3 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_10,temp_10.length,k);
            System.out.println("k = 3n/4, " + temp_10[k-1]);
            counter++;
        }
        long endTime3 = System.nanoTime();
        totalTime3 = endTime3 - startTime3;
        System.out.println("total time: ");
        System.out.println( totalTime3 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime4;
        System.out.println("quicksort i , array[10], k = n");
        k = 10;
        //temp array to copy original array into
        System.arraycopy(arr_10, 0, temp_10, 0, 10);
        long startTime4 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_10,temp_10.length,k);
            System.out.println("k = n, " + temp_10[k-1]);
            counter++;
        }
        long endTime4 = System.nanoTime();
        totalTime4 = endTime4 - startTime4;
        System.out.println("total time: ");
        System.out.println( totalTime4 );


        ///////////// n = 50////////////////////////////
        counter = 0;
        //start counting run time
        long totalTime0;
        System.out.println("Quicksort i, array[50], k = 1");
        k = 1;
        System.arraycopy(arr_50, 0, temp_50, 0, 50);
        long startTime0 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_50,temp_50.length,k);
            System.out.println("k = 1, " + temp_50[k-1]);
            counter++;
        }
        long endTime0 = System.nanoTime();
        totalTime0 = endTime0 - startTime0;
        System.out.println("total time: ");
        System.out.println(totalTime0);


        //reset counter
        counter = 0;
        //start counting run time
        long totalTime1;
        System.out.println("quicksort i, array[50], k = n/4");
        k = 50/4;
        //temp array to copy original array into
        System.arraycopy(arr_50, 0, temp_50, 0, 50);
        long startTime1 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_50,temp_50.length,k);
            System.out.println("k = n/4, " + temp_50[k-1]);
            counter++;
        }
        long endTime1 = System.nanoTime();
        totalTime1 = endTime1 - startTime1;
        System.out.println("total time: ");
        System.out.println( totalTime1 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime2;
        System.out.println("quicksort i, array[50], k = n/2");
        k = 50/2;
        //temp array to copy original array into
        System.arraycopy(arr_50, 0, temp_50, 0, 50);
        long startTime2 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_50,temp_50.length,k);
            System.out.println("k = n/2, " + temp_50[k-1]);
            counter++;
        }
        long endTime2 = System.nanoTime();
        totalTime2 = endTime2 - startTime2;
        System.out.println("total time: ");
        System.out.println( totalTime2 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime3;
        System.out.println("quicksort i, array[50], k = 3n/4");
        k = (50*3)/4;
        //temp array to copy original array into
        System.arraycopy(arr_50, 0, temp_50, 0, 50);
        long startTime3 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_50,temp_50.length,k);
            System.out.println("k = 3n/4, " + temp_50[k-1]);
            counter++;
        }
        long endTime3 = System.nanoTime();
        totalTime3 = endTime3 - startTime3;
        System.out.println("total time: ");
        System.out.println( totalTime3 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime4;
        System.out.println("quicksort i, array[50], k = n");
        k = 50;
        //temp array to copy original array into
        System.arraycopy(arr_50, 0, temp_50, 0, 50);
        long startTime4 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_50,temp_50.length,k);
            System.out.println("k = n, " + temp_50[k-1]);
            counter++;
        }
        long endTime4 = System.nanoTime();
        totalTime4 = endTime4 - startTime4;
        System.out.println("total time: ");
        System.out.println( totalTime4 );


               //////////////////// n = 100/////////////////////////////////////////
        counter = 0;
        //start counting run time
        long totalTime0;
        System.out.println("quicksort i, array[100], k = 1");
        k = 1;
        System.arraycopy(arr_100, 0, temp_100, 0, 100);
        long startTime0 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_100,temp_100.length,k);
            System.out.println("k = 1, " + temp_100[k-1]);
            counter++;
        }
        long endTime0 = System.nanoTime();
        totalTime0 = endTime0 - startTime0;
        System.out.println("total time: ");
        System.out.println(totalTime0);


        //reset counter
        counter = 0;
        //start counting run time
        long totalTime1;
        System.out.println("quicksort i, array[100], k = n/4");
        k = 100/4;
        //temp array to copy original array into
        System.arraycopy(arr_100, 0, temp_100, 0, 100);
        long startTime1 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_100,temp_100.length,k);
            System.out.println("k = n/4, " + temp_100[k-1]);
            counter++;
        }
        long endTime1 = System.nanoTime();
        totalTime1 = endTime1 - startTime1;
        System.out.println("total time: ");
        System.out.println( totalTime1 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime2;
        System.out.println("quicksort i, array[100], k = n/2");
        k = 100/2;
        //temp array to copy original array into
        System.arraycopy(arr_100, 0, temp_100, 0, 100);
        long startTime2 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_100,temp_100.length,k);
            System.out.println("k = n/2, " + temp_100[k-1]);
            counter++;
        }
        long endTime2 = System.nanoTime();
        totalTime2 = endTime2 - startTime2;
        System.out.println("total time: ");
        System.out.println( totalTime2 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime3;
        System.out.println("quicksort i, array[100], k = 3n/4");
        k = (100*3)/4;
        //temp array to copy original array into
        System.arraycopy(arr_100, 0, temp_100, 0, 100);
        long startTime3 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_100,temp_100.length,k);
            System.out.println("k = 3n/4, " + temp_100[k-1]);
            counter++;
        }
        long endTime3 = System.nanoTime();
        totalTime3 = endTime3 - startTime3;
        System.out.println("total time: ");
        System.out.println( totalTime3 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime4;
        System.out.println("quicksort i, array[100], k = n");
        k = 100;
        //temp array to copy original array into
        System.arraycopy(arr_100, 0, temp_100, 0, 100);
        long startTime4 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_100,temp_100.length,k);
            System.out.println("k = n, " + temp_100[k-1]);
            counter++;
        }
        long endTime4 = System.nanoTime();
        totalTime4 = endTime4 - startTime4;
        System.out.println("total time: ");
        System.out.println( totalTime4 );


          ////////////// n = 250/////////////////////////////////////
        counter = 0;
        //start counting run time
        long totalTime0;
        System.out.println("quicksort i, array[250], k = 1");
        k = 1;
        System.arraycopy(arr_250, 0, temp_250, 0, 250);
        long startTime0 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_250,temp_250.length,k);
            System.out.println("k = 1, " + temp_250[k-1]);
            counter++;
        }
        long endTime0 = System.nanoTime();
        totalTime0 = endTime0 - startTime0;
        System.out.println("total time: ");
        System.out.println(totalTime0);


        //reset counter
        counter = 0;
        //start counting run time
        long totalTime1;
        System.out.println("quicksort i array[250], k = n/4");
        k = 250/4;
        //temp array to copy original array into
        System.arraycopy(arr_250, 0, temp_250, 0, 250);
        long startTime1 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_250,temp_250.length,k);
            System.out.println("k = n/4, " + temp_250[k-1]);
            counter++;
        }
        long endTime1 = System.nanoTime();
        totalTime1 = endTime1 - startTime1;
        System.out.println("total time: ");
        System.out.println( totalTime1 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime2;
        System.out.println("quicksort i, array[250], k = n/2");
        k = 250/2;
        //temp array to copy original array into
        System.arraycopy(arr_250, 0, temp_250, 0, 250);
        long startTime2 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_250,temp_250.length,k);
            System.out.println("k = n/2, " + temp_250[k-1]);
            counter++;
        }
        long endTime2 = System.nanoTime();
        totalTime2 = endTime2 - startTime2;
        System.out.println("total time: ");
        System.out.println( totalTime2 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime3;
        System.out.println("quicksort i, array[250], k = 3n/4");
        k = (250*3)/4;
        //temp array to copy original array into
        System.arraycopy(arr_250, 0, temp_250, 0, 250);
        long startTime3 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_250,temp_250.length,k);
            System.out.println("k = 3n/4, " + temp_250[k-1]);
            counter++;
        }
        long endTime3 = System.nanoTime();
        totalTime3 = endTime3 - startTime3;
        System.out.println("total time: ");
        System.out.println( totalTime3 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime4;
        System.out.println("quicksort i, array[250], k = n");
        k = 250;
        //temp array to copy original array into
        System.arraycopy(arr_250, 0, temp_250, 0, 250);
        long startTime4 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_250,temp_250.length,k);
            System.out.println("k = n, " + temp_250[k-1]);
            counter++;
        }
        long endTime4 = System.nanoTime();
        totalTime4 = endTime4 - startTime4;
        System.out.println("total time: ");
        System.out.println( totalTime4 );


         ////////////// n = 500/////////////////////////////////////
        counter = 0;
        //start counting run time
        long totalTime0;
        System.out.println("quicksort i, array[500], k = 1");
        k = 1;
        System.arraycopy(arr_500, 0, temp_500, 0, 500);
        long startTime0 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_500,temp_500.length,k);
            System.out.println("k = 1, " + temp_500[k-1]);
            counter++;
        }
        long endTime0 = System.nanoTime();
        totalTime0 = endTime0 - startTime0;
        System.out.println("total time: ");
        System.out.println(totalTime0);


        //reset counter
        counter = 0;
        //start counting run time
        long totalTime1;
        System.out.println("quicksort i, array[500], k = n/4");
        k = 500/4;
        //temp array to copy original array into
        System.arraycopy(arr_500, 0, temp_500, 0, 500);
        long startTime1 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_500,temp_500.length,k);
            System.out.println("k = n/4, " + temp_500[k-1]);
            counter++;
        }
        long endTime1 = System.nanoTime();
        totalTime1 = endTime1 - startTime1;
        System.out.println("total time: ");
        System.out.println( totalTime1 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime2;
        System.out.println("quicksort i, array[500], k = n/2");
        k = 500/2;
        //temp array to copy original array into
        System.arraycopy(arr_500, 0, temp_500, 0, 500);
        long startTime2 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_500,temp_500.length,k);
            System.out.println("k = n/2, " + temp_500[k-1]);
            counter++;
        }
        long endTime2 = System.nanoTime();
        totalTime2 = endTime2 - startTime2;
        System.out.println("total time: ");
        System.out.println( totalTime2 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime3;
        System.out.println("quicksort i, array[500], k = 3n/4");
        k = (500*3)/4;
        //temp array to copy original array into
        System.arraycopy(arr_500, 0, temp_500, 0, 500);
        long startTime3 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_500,temp_500.length,k);
            System.out.println("k = 3n/4, " + temp_500[k-1]);
            counter++;
        }
        long endTime3 = System.nanoTime();
        totalTime3 = endTime3 - startTime3;
        System.out.println("total time: ");
        System.out.println( totalTime3 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime4;
        System.out.println("quicksort i, array[500], k = n");
        k = 500;
        //temp array to copy original array into
        System.arraycopy(arr_500, 0, temp_500, 0, 500);
        long startTime4 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_500,temp_500.length,k);
            System.out.println("k = n, " + temp_500[k-1]);
            counter++;
        }
        long endTime4 = System.nanoTime();
        totalTime4 = endTime4 - startTime4;
        System.out.println("total time: ");
        System.out.println( totalTime4 );


         ////////////// n = 1000/////////////////////////////////////
        counter = 0;
        //start counting run time
        long totalTime0;
        System.out.println("quicksort i, array[1000], k = 1");
        k = 1;
        System.arraycopy(arr_1000, 0, temp_1000, 0, 1000);
        long startTime0 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_1000,temp_1000.length,k);
            System.out.println("k = 1, " + temp_1000[k-1]);
            counter++;
        }
        long endTime0 = System.nanoTime();
        totalTime0 = endTime0 - startTime0;
        System.out.println("total time: ");
        System.out.println(totalTime0);


        //reset counter
        counter = 0;
        //start counting run time
        long totalTime1;
        System.out.println("quicksort i, array[1000], k = n/4");
        k = 1000/4;
        //temp array to copy original array into
        System.arraycopy(arr_1000, 0, temp_1000, 0, 1000);
        long startTime1 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_1000,temp_1000.length,k);
            System.out.println("k = n/4, " + temp_1000[k-1]);
            counter++;
        }
        long endTime1 = System.nanoTime();
        totalTime1 = endTime1 - startTime1;
        System.out.println("total time: ");
        System.out.println( totalTime1 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime2;
        System.out.println("quicksort i, array[1000], k = n/2");
        k = 1000/2;
        //temp array to copy original array into
        System.arraycopy(arr_1000, 0, temp_1000, 0, 1000);
        long startTime2 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_1000,temp_1000.length,k);
            System.out.println("k = n/2, " + temp_1000[k-1]);
            counter++;
        }
        long endTime2 = System.nanoTime();
        totalTime2 = endTime2 - startTime2;
        System.out.println("total time: ");
        System.out.println( totalTime2 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime3;
        System.out.println("quicksort i, array[1000], k = 3n/4");
        k = (1000*3)/4;
        //temp array to copy original array into
        System.arraycopy(arr_1000, 0, temp_1000, 0, 1000);
        long startTime3 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_1000,temp_1000.length,k);
            System.out.println("k = 3n/4, " + temp_1000[k-1]);
            counter++;
        }
        long endTime3 = System.nanoTime();
        totalTime3 = endTime3 - startTime3;
        System.out.println("total time: ");
        System.out.println( totalTime3 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime4;
        System.out.println("quicksort i, array[1000], k = n");
        k = 1000;
        //temp array to copy original array into
        System.arraycopy(arr_1000, 0, temp_1000, 0, 1000);
        long startTime4 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortIterative(temp_1000,temp_1000.length,k);
            System.out.println("k = n, " + temp_1000[k-1]);
            counter++;
        }
        long endTime4 = System.nanoTime();
        totalTime4 = endTime4 - startTime4;
        System.out.println("total time: ");
        System.out.println( totalTime4 );


        ////////////////////////////   QUICKSORT RECURSIVE   ///////////////////////////////////////////////////////
        //n = 10
        /*
        counter = 0;
        //start counting run time
        long totalTime0;
        System.out.println("Quicksort recursive, array[10], k = 1");
        k = 1;
        System.arraycopy(arr_10, 0, temp_10, 0, 10);

        long startTime0 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_10, 0, temp_10.length - 1,k);
            System.out.println("k = 1, " + temp_10[k-1]);
            counter++;
        }
        long endTime0 = System.nanoTime();
        totalTime0 = endTime0 - startTime0;
        System.out.println("total time: ");
        System.out.println(totalTime0);


        //reset counter
        counter = 0;
        //start counting run time
        long totalTime1;
        System.out.println("quicksort recur, array[10], k = n/4");
        k = 10/4;
        //temp array to copy original array into
        System.arraycopy(arr_10, 0, temp_10, 0, 10);
        long startTime1 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_10, 0, temp_10.length - 1,k);
            System.out.println("k = n/4, " + temp_10[k-1]);
            counter++;
        }
        long endTime1 = System.nanoTime();
        totalTime1 = endTime1 - startTime1;
        System.out.println("total time: ");
        System.out.println( totalTime1 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime2;
        System.out.println("quicksort recur, array[10], k = n/2");
        k = 10/2;
        //temp array to copy original array into
        System.arraycopy(arr_10, 0, temp_10, 0, 10);
        long startTime2 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_10, 0, temp_10.length - 1,k);
            System.out.println("k = n/2, " + temp_10[k-1]);
            counter++;
        }
        long endTime2 = System.nanoTime();
        totalTime2 = endTime2 - startTime2;
        System.out.println("total time: ");
        System.out.println( totalTime2 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime3;
        System.out.println("quicksort recur, array[10], k = 3n/4");
        k = (10*3)/4;
        System.out.println("k = " + k);
        //temp array to copy original array into
        System.arraycopy(arr_10, 0, temp_10, 0, 10);
        long startTime3 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_10, 0, temp_10.length - 1,k);
            System.out.println("k = 3n/4, " + temp_10[k-1]);
            counter++;
        }
        long endTime3 = System.nanoTime();
        totalTime3 = endTime3 - startTime3;
        System.out.println("total time: ");
        System.out.println( totalTime3 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime4;
        System.out.println("quicksort recur , array[10], k = n");
        k = 10;
        //temp array to copy original array into
        System.arraycopy(arr_10, 0, temp_10, 0, 10);
        long startTime4 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_10, 0, temp_10.length - 1,k);
            System.out.println("k = n, " + temp_10[k-1]);
            counter++;
        }
        long endTime4 = System.nanoTime();
        totalTime4 = endTime4 - startTime4;
        System.out.println("total time: ");
        System.out.println( totalTime4 );


        ///////////// n = 50////////////////////////////
        counter = 0;
        //start counting run time
        long totalTime0;
        System.out.println("Quicksort recur, array[50], k = 1");
        k = 1;
        System.arraycopy(arr_50, 0, temp_50, 0, 50);
        long startTime0 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_50, 0, temp_50.length - 1,k);
            System.out.println("k = 1, " + temp_50[k-1]);
            counter++;
        }
        long endTime0 = System.nanoTime();
        totalTime0 = endTime0 - startTime0;
        System.out.println("total time: ");
        System.out.println(totalTime0);


        //reset counter
        counter = 0;
        //start counting run time
        long totalTime1;
        System.out.println("quicksort recur, array[50], k = n/4");
        k = 50/4;
        //temp array to copy original array into
        System.arraycopy(arr_50, 0, temp_50, 0, 50);
        long startTime1 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_50, 0, temp_50.length - 1,k);
            System.out.println("k = n/4, " + temp_50[k-1]);
            counter++;
        }
        long endTime1 = System.nanoTime();
        totalTime1 = endTime1 - startTime1;
        System.out.println("total time: ");
        System.out.println( totalTime1 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime2;
        System.out.println("quicksort recur, array[50], k = n/2");
        k = 50/2;
        //temp array to copy original array into
        System.arraycopy(arr_50, 0, temp_50, 0, 50);
        long startTime2 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_50, 0, temp_50.length - 1,k);
            System.out.println("k = n/2, " + temp_50[k-1]);
            counter++;
        }
        long endTime2 = System.nanoTime();
        totalTime2 = endTime2 - startTime2;
        System.out.println("total time: ");
        System.out.println( totalTime2 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime3;
        System.out.println("quicksort recur, array[50], k = 3n/4");
        k = (50*3)/4;
        //temp array to copy original array into
        System.arraycopy(arr_50, 0, temp_50, 0, 50);
        long startTime3 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_50, 0, temp_50.length - 1,k);
            System.out.println("k = 3n/4, " + temp_50[k-1]);
            counter++;
        }
        long endTime3 = System.nanoTime();
        totalTime3 = endTime3 - startTime3;
        System.out.println("total time: ");
        System.out.println( totalTime3 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime4;
        System.out.println("quicksort recur, array[50], k = n");
        k = 50;
        //temp array to copy original array into
        System.arraycopy(arr_50, 0, temp_50, 0, 50);
        long startTime4 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_50, 0, temp_50.length - 1,k);
            System.out.println("k = n, " + temp_50[k-1]);
            counter++;
        }
        long endTime4 = System.nanoTime();
        totalTime4 = endTime4 - startTime4;
        System.out.println("total time: ");
        System.out.println( totalTime4 );


               //////////////////// n = 100/////////////////////////////////////////
        counter = 0;
        //start counting run time
        long totalTime0;
        System.out.println("quicksort recur, array[100], k = 1");
        k = 1;
        System.arraycopy(arr_100, 0, temp_100, 0, 100);
        long startTime0 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_100, 0, temp_100.length - 1,k);
            System.out.println("k = 1, " + temp_100[k-1]);
            counter++;
        }
        long endTime0 = System.nanoTime();
        totalTime0 = endTime0 - startTime0;
        System.out.println("total time: ");
        System.out.println(totalTime0);


        //reset counter
        counter = 0;
        //start counting run time
        long totalTime1;
        System.out.println("quicksort r, array[100], k = n/4");
        k = 100/4;
        //temp array to copy original array into
        System.arraycopy(arr_100, 0, temp_100, 0, 100);
        long startTime1 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_100, 0, temp_100.length - 1,k);
            System.out.println("k = n/4, " + temp_100[k-1]);
            counter++;
        }
        long endTime1 = System.nanoTime();
        totalTime1 = endTime1 - startTime1;
        System.out.println("total time: ");
        System.out.println( totalTime1 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime2;
        System.out.println("quicksort r, array[100], k = n/2");
        k = 100/2;
        //temp array to copy original array into
        System.arraycopy(arr_100, 0, temp_100, 0, 100);
        long startTime2 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_100, 0, temp_100.length - 1,k);
            System.out.println("k = n/2, " + temp_100[k-1]);
            counter++;
        }
        long endTime2 = System.nanoTime();
        totalTime2 = endTime2 - startTime2;
        System.out.println("total time: ");
        System.out.println( totalTime2 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime3;
        System.out.println("quicksort r, array[100], k = 3n/4");
        k = (100*3)/4;
        //temp array to copy original array into
        System.arraycopy(arr_100, 0, temp_100, 0, 100);
        long startTime3 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_100, 0, temp_100.length - 1,k);
            System.out.println("k = 3n/4, " + temp_100[k-1]);
            counter++;
        }
        long endTime3 = System.nanoTime();
        totalTime3 = endTime3 - startTime3;
        System.out.println("total time: ");
        System.out.println( totalTime3 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime4;
        System.out.println("quicksort, array[100], k = n");
        k = 100;
        //temp array to copy original array into
        System.arraycopy(arr_100, 0, temp_100, 0, 100);
        long startTime4 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_100, 0, temp_100.length - 1,k);
            System.out.println("k = n, " + temp_100[k-1]);
            counter++;
        }
        long endTime4 = System.nanoTime();
        totalTime4 = endTime4 - startTime4;
        System.out.println("total time: ");
        System.out.println( totalTime4 );



          ////////////// n = 250/////////////////////////////////////
        counter = 0;
        //start counting run time
        long totalTime0;
        System.out.println("quicksort r, array[250], k = 1");
        k = 1;
        System.arraycopy(arr_250, 0, temp_250, 0, 250);
        long startTime0 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_250, 0, temp_250.length - 1,k);
            System.out.println("k = 1, " + temp_250[k-1]);
            counter++;
        }
        long endTime0 = System.nanoTime();
        totalTime0 = endTime0 - startTime0;
        System.out.println("total time: ");
        System.out.println(totalTime0);


        //reset counter
        counter = 0;
        //start counting run time
        long totalTime1;
        System.out.println("quicksort r, array[250], k = n/4");
        k = 250/4;
        //temp array to copy original array into
        System.arraycopy(arr_250, 0, temp_250, 0, 250);
        long startTime1 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_250, 0, temp_250.length - 1,k);
            System.out.println("k = n/4, " + temp_250[k-1]);
            counter++;
        }
        long endTime1 = System.nanoTime();
        totalTime1 = endTime1 - startTime1;
        System.out.println("total time: ");
        System.out.println( totalTime1 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime2;
        System.out.println("quicksort r, array[250], k = n/2");
        k = 250/2;
        //temp array to copy original array into
        System.arraycopy(arr_250, 0, temp_250, 0, 250);
        long startTime2 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_250, 0, temp_250.length - 1,k);
            System.out.println("k = n/2, " + temp_250[k-1]);
            counter++;
        }
        long endTime2 = System.nanoTime();
        totalTime2 = endTime2 - startTime2;
        System.out.println("total time: ");
        System.out.println( totalTime2 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime3;
        System.out.println("quicksort r, array[250], k = 3n/4");
        k = (250*3)/4;
        //temp array to copy original array into
        System.arraycopy(arr_250, 0, temp_250, 0, 250);
        long startTime3 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_250, 0, temp_250.length - 1,k);
            System.out.println("k = 3n/4, " + temp_250[k-1]);
            counter++;
        }
        long endTime3 = System.nanoTime();
        totalTime3 = endTime3 - startTime3;
        System.out.println("total time: ");
        System.out.println( totalTime3 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime4;
        System.out.println("quicksort r, array[250], k = n");
        k = 250;
        //temp array to copy original array into
        System.arraycopy(arr_250, 0, temp_250, 0, 250);
        long startTime4 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_250, 0, temp_250.length - 1,k);
            System.out.println("k = n, " + temp_250[k-1]);
            counter++;
        }
        long endTime4 = System.nanoTime();
        totalTime4 = endTime4 - startTime4;
        System.out.println("total time: ");
        System.out.println( totalTime4 );


         ////////////// n = 500/////////////////////////////////////
        counter = 0;
        //start counting run time
        long totalTime0;
        System.out.println("quicksort r, array[500], k = 1");
        k = 1;
        System.arraycopy(arr_500, 0, temp_500, 0, 500);
        long startTime0 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_500, 0, temp_500.length - 1,k);
            System.out.println("k = 1, " + temp_500[k-1]);
            counter++;
        }
        long endTime0 = System.nanoTime();
        totalTime0 = endTime0 - startTime0;
        System.out.println("total time: ");
        System.out.println(totalTime0);


        //reset counter
        counter = 0;
        //start counting run time
        long totalTime1;
        System.out.println("quicksort r, array[500], k = n/4");
        k = 500/4;
        //temp array to copy original array into
        System.arraycopy(arr_500, 0, temp_500, 0, 500);
        long startTime1 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_500, 0, temp_500.length - 1,k);
            System.out.println("k = n/4, " + temp_500[k-1]);
            counter++;
        }
        long endTime1 = System.nanoTime();
        totalTime1 = endTime1 - startTime1;
        System.out.println("total time: ");
        System.out.println( totalTime1 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime2;
        System.out.println("quicksort r, array[500], k = n/2");
        k = 500/2;
        //temp array to copy original array into
        System.arraycopy(arr_500, 0, temp_500, 0, 500);
        long startTime2 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_500, 0, temp_500.length - 1,k);
            System.out.println("k = n/2, " + temp_500[k-1]);
            counter++;
        }
        long endTime2 = System.nanoTime();
        totalTime2 = endTime2 - startTime2;
        System.out.println("total time: ");
        System.out.println( totalTime2 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime3;
        System.out.println("quicksort r, array[500], k = 3n/4");
        k = (500*3)/4;
        //temp array to copy original array into
        System.arraycopy(arr_500, 0, temp_500, 0, 500);
        long startTime3 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_500, 0, temp_500.length - 1,k);
            System.out.println("k = 3n/4, " + temp_500[k-1]);
            counter++;
        }
        long endTime3 = System.nanoTime();
        totalTime3 = endTime3 - startTime3;
        System.out.println("total time: ");
        System.out.println( totalTime3 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime4;
        System.out.println("quicksort r, array[500], k = n");
        k = 500;
        //temp array to copy original array into
        System.arraycopy(arr_500, 0, temp_500, 0, 500);
        long startTime4 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_500, 0, temp_500.length - 1,k);
            System.out.println("k = n, " + temp_500[k-1]);
            counter++;
        }
        long endTime4 = System.nanoTime();
        totalTime4 = endTime4 - startTime4;
        System.out.println("total time: ");
        System.out.println( totalTime4 );


         ////////////// n = 1000/////////////////////////////////////
        counter = 0;
        //start counting run time
        long totalTime0;
        System.out.println("quicksort r, array[1000], k = 1");
        k = 1;
        System.arraycopy(arr_1000, 0, temp_1000, 0, 1000);
        long startTime0 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_1000, 0, temp_1000.length - 1,k);
            System.out.println("k = 1, " + temp_1000[k-1]);
            counter++;
        }
        long endTime0 = System.nanoTime();
        totalTime0 = endTime0 - startTime0;
        System.out.println("total time: ");
        System.out.println(totalTime0);


        //reset counter
        counter = 0;
        //start counting run time
        long totalTime1;
        System.out.println("quicksort r, array[1000], k = n/4");
        k = 1000/4;
        //temp array to copy original array into
        System.arraycopy(arr_1000, 0, temp_1000, 0, 1000);
        long startTime1 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_1000, 0, temp_1000.length - 1,k);
            System.out.println("k = n/4, " + temp_1000[k-1]);
            counter++;
        }
        long endTime1 = System.nanoTime();
        totalTime1 = endTime1 - startTime1;
        System.out.println("total time: ");
        System.out.println( totalTime1 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime2;
        System.out.println("quicksort r, array[1000], k = n/2");
        k = 1000/2;
        //temp array to copy original array into
        System.arraycopy(arr_1000, 0, temp_1000, 0, 1000);
        long startTime2 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_1000, 0, temp_1000.length - 1,k);
            System.out.println("k = n/2, " + temp_1000[k-1]);
            counter++;
        }
        long endTime2 = System.nanoTime();
        totalTime2 = endTime2 - startTime2;
        System.out.println("total time: ");
        System.out.println( totalTime2 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime3;
        System.out.println("quicksort r, array[1000], k = 3n/4");
        k = (1000*3)/4;
        //temp array to copy original array into
        System.arraycopy(arr_1000, 0, temp_1000, 0, 1000);
        long startTime3 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_1000, 0, temp_1000.length - 1,k);
            System.out.println("k = 3n/4, " + temp_1000[k-1]);
            counter++;
        }
        long endTime3 = System.nanoTime();
        totalTime3 = endTime3 - startTime3;
        System.out.println("total time: ");
        System.out.println( totalTime3 );

        //reset counter
        counter = 0;
        //start counting run time
        long totalTime4;
        System.out.println("quicksort r, array[1000], k = n");
        k = 1000;
        //temp array to copy original array into
        System.arraycopy(arr_1000, 0, temp_1000, 0, 1000);
        long startTime4 = System.nanoTime();
        while (counter < testnum)
        {
            quickSort.quickSortRecursive(temp_1000, 0, temp_1000.length - 1,k);
            System.out.println("k = n, " + temp_1000[k-1]);
            counter++;
        }
        long endTime4 = System.nanoTime();
        totalTime4 = endTime4 - startTime4;
        System.out.println("total time: ");
        System.out.println( totalTime4 );
        */
    }
}
