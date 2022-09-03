import java.util.*;
/*
 * Randomized QuickSort is an algorithm that uses random numbers to
 * pick the next pivot. The RandomizedQuickSort in this file contains
 * two versions of the Randomized QuickSort algorithm. The first
 * version, RandomizedQSort, makes use of the regular partition while
 * the other, RandomizedQSortHoare, uses Hoare's partition algorithm.
 * Each method can be called using RandomizedQuickSort.RandomizedQSort() 
 * or RandomizedQuickSortHoar.RandomizedQSortHoare().
 * 
 * Example:
 * 
 * int[] array = {5,3,6,68,45,43};
 * 
 * To sort array, call:
 * RandomizedQuickSort.RandomizedQSort(array, 0, array.length - 1)
 * OR
 * RandomizedQuickSortHoare.RandomizedQSortHoare(array, 0, array.length - 1)
 */
class RandomizedQuickSort
{
    /*
     * swap - function to swap two numbers in an array
     * @a: index of first number
     * @b: index of second number
     * Return: nothing
     */
    public static int swapCount = 0;
    public static int compCount = 0;
    static void swap(int arr[], int a, int b)
    {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        swapCount++;
    }
    /*
     * Randomize - function to swap last element of array with random
     * number in array
     * @p: pivot
     * @r: last element in array
     * Return: swap count
     */
    static void Randomize(int arr[], int p, int r)
    {
        Random value = new Random();
        int i = value.nextInt((r - p) + 1) + p;
        swap(arr, i, r);  
    }
    /*
     * RandomizedPartition - Quicksort partition algorithm with random
     * pivot
     * @p: pivot
     * @r: last element in array
     * Return: new pivot
     */
    static int RandomizedPartition(int arr[], int p, int r)
    {
        Randomize(arr, p, r);
        int x = arr[r];
        int i = (p - 1);
        for (int j = p; j < r; j++)
        {
            compCount++; //count number of comparisions
            if (arr[j] <= x)
            {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, r);
        return i + 1;
    }
    /*
     * RandomizedQSort - Randomized quicksort using randomized pivot
     * @arr - input array
     * @pivot: pivot
     * @right: last element in array
     * Return: nothing
     */
    static int[] RandomizedQSort(int arr[], int pivot, int right)
    {
        int[] resArray = new int[2];
        //compCount += right - pivot;
        if (pivot < right)
        {
            int q = RandomizedPartition(arr, pivot, right);
            RandomizedQSort(arr, pivot, q - 1);
            RandomizedQSort(arr, q + 1, right);
        }
        resArray[0] = swapCount;
        resArray[1] = compCount;
        return resArray;
    }
}
class RandomizedQuickSortHoare
{
    public static int swaps = 0;
    public static int comparisons = 0;
    /*
     * swap - function to swap two numbers in an array
     * @a: index of first number
     * @b: index of second number
     * Return: nothing
     */
    static void swap(int arr[], int a, int b)
    {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        swaps++;
    }
    /*
     * Randomize - function to swap last element of array with random
     * number in array
     * @p: pivot
     * @r: last element in array
     * Return: swap count
     */
    static void Randomize(int arr[], int p, int r)
    {
        Random value = new Random();
        int i = value.nextInt((r - p) + 1) + p;
        swap(arr, i, r);  
    }
    /*
     * RandomizedHoarePartition - Hoare's partition algorithm with a
     * random pivot
     * @p: pivot
     * @r: last element in array
     * Return: new pivot
     */
    static int RandomizedHoarePartition(int arr[], int p, int r)
    {
        Randomize(arr, p, r);
        int x = arr[p], i = p - 1, j = r + 1;
        while (true)
        {
            do
            {
                i++;
                comparisons++;
            } while (arr[i] < x);
            do
            {
                j--;
                comparisons++;
            } while (arr[j] > x);
            if (i < j)
            {
                swap(arr, i, j);
            }
            else
            {
                return j;
            }
        }
    }
    /*
     * RandomizedQSortHoare - Randomized quicksort using Hoare's
     * partition algorithm.
     * @arr - input array
     * @pivot: pivot
     * @right: last element in array
     * Return: number of swaps and comparisons
     */
    static int[] RandomizedQSortHoare(int arr[], int pivot, int right)
    {
        int[] resArray = new int[2];
        if (pivot < right)
        {
            int q = RandomizedHoarePartition(arr, pivot, right);
            RandomizedQSortHoare(arr, pivot, q);
            RandomizedQSortHoare(arr, q + 1, right);
        }
        resArray[0] = swaps;
        resArray[1] = comparisons;
        return resArray;
    }
}
class Main
{
    public static void main(String args[]) 
    {
       // Random num = new Random();
        int arr[] = new int[1000];
        int copyArr[];
        int size = arr.length;
        for (int i = 0; i < size; i++)
        {
            arr[i] = (int)(Math.random() * ((1000 - 100) + 1)) + 100;
        }
        copyArr = arr.clone(); //copy array
        int[] s = RandomizedQuickSort.RandomizedQSort(arr, 0, size - 1);
        System.out.println("RandomizedQuickSort Number of swaps = " + s[0]);
        System.out.println("RandomizedQuickSort Number of comparisons = " + s[1]);
        int[] t = RandomizedQuickSortHoare.RandomizedQSortHoare(copyArr, 0, size - 1);
        System.out.println("RandomizedQuickSortHoare Number of swaps = " + t[0]);
        System.out.println("RandomizedQuickSortHoare Number of comparisons = " + t[1]);
    }
}