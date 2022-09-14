package GeneralLibrary;

import java.util.List;

import searchEngine.FoundObject;

public class MergeSort {
    /**
     * Mergesort algorithm.
     * @param a an array of Comparable items.
     */
    public static 
    void mergeSort( List<FoundObject> a )
    {
    	FoundObject[] tmpArray = new FoundObject[a.size()];

        mergeSort( a, tmpArray, 0, a.size() - 1 );
    }

    /**
     * Internal method that makes recursive calls.
     * @param a an array of Comparable items.
     * @param tmpArray an array to place the merged result.
     * @param left the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    private static 
    void mergeSort( List<FoundObject> a, FoundObject [ ] tmpArray,
               int left, int right )
    {
        if( left < right )
        {
            int center = ( left + right ) / 2;
            mergeSort( a, tmpArray, left, center );
            mergeSort( a, tmpArray, center + 1, right );
            merge( a, tmpArray, left, center + 1, right );
        }
    }

    /**
     * Internal method that merges two sorted halves of a subarray.
     * @param a an array of Comparable items.
     * @param tmpArray an array to place the merged result.
     * @param leftPos the left-most index of the subarray.
     * @param rightPos the index of the start of the second half.
     * @param rightEnd the right-most index of the subarray.
     */
    private static 
    void merge( List<FoundObject> a, FoundObject[] tmpArray, int leftPos, int rightPos, int rightEnd )
    {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        // Main loop
        while( leftPos <= leftEnd && rightPos <= rightEnd )
            if( a.get(leftPos).WordCount>=a.get(rightPos).WordCount)
                tmpArray[ tmpPos++ ] = a.get(leftPos++);
            else
                tmpArray[ tmpPos++ ] = a.get(rightPos++);

        while( leftPos <= leftEnd )    // Copy rest of first half
            tmpArray[ tmpPos++ ] = a.get(leftPos++);

        while( rightPos <= rightEnd )  // Copy rest of right half
            tmpArray[ tmpPos++ ] = a.get(rightPos++);

        // Copy tmpArray back
        for( int i = 0; i < numElements; i++, rightEnd-- )
            a.set(rightEnd,tmpArray[ rightEnd ]);
    }

}
