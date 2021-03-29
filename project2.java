/*
 * Alexa Tang
 * Project 2
 * CS 3310
 * */
package Selection_Problem;
import java.util.Random;


class Array_functs
{
    public int [] Array_Generator (int size)
    {
        int n = size;
        Random rand = new Random();
        int [] Array = new int[n];
        for (int i = 0; i < size; i++)
        {
                Array [i]=  rand.nextInt(11); // numbers from 0 to 10
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

class Mergesort
{
    public void Mergesort (int arr[], int left, int right)
    {
        if (left < right)
        {
            int middle  = (left + right)/2;
            //sort first half of array
            Mergesort(arr, left, middle);
            //sort second half of array
            Mergesort(arr,(middle + 1), right);
            //merge sorted halves
            //Merge(int arr[], left, middle, right);

        }

        return;
    }

    public void Merge(int arr[], int left, int middle, int right)
    {
        //size of left side
        int sizeL = middle - left + 1;
        //size of right side
        int sizeR = right - middle;

        // temp arrays to hold subarray of arr
        int [] LeftArr = new int [sizeL];
        int [] RightArr = new int [sizeR];

        //filling in temp arrays with lists to be sorted
        for (int i = 0; i < sizeL; i++)
        {
            LeftArr[i] = arr[left + i];
        }
        for (int j = 0; j < sizeR; j++)
        {
            RightArr[j] = arr[middle + 1 + j];
        }

        //merging and sorting the subarrays
        //initial indexes of subarrays?????
        int i = 0;
        int j = 0;

        int k = 1;// why 1???
        while (i < sizeL && j < sizeR)
        {
            //sorting subarrays and putting back into arr
            if (LeftArr[i] <= RightArr[j])
            {
                arr[k] = LeftArr[i];
                i++;
            }
            else
            {
                arr[k] = RightArr[j];
            }
            k++;
        }

        //copying the remaining elements from subarray
        while(i < sizeL)
        {
            arr[k] = LeftArr[i];
            i++;
            k++;
        }
        while(j < sizeR)
        {
            arr[k] = LeftArr[j];
            j++;
            k++;
        }
        /*
        int size = arr.length;
        int i = left;
        int j = middle + 1;
        int k = right;
        int [] U = new int[size];
        int [] V = new int[size];
        while(i <= middle && j <= right)
        {
            if(arr[i] < arr[j])
            {
                U[k] = arr[i];
                i++;
            }
            else
            {
                U[k] = arr[j];
                j++;
            }
            k++
        }
        if(i > middle)
        {

        }
        */
    }

}



public class main
{
    public static void Main(String[] args)
    {
        Array_functs rand_arr = new Array_functs();
        //generating an array of 10 elements, filled in with random numbers
        int [] rand_a1;
        rand_a1 = rand_arr.Array_Generator(10);
        rand_arr.Print_array(rand_a1);

    }
}
