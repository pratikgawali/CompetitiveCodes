/*
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
Find median of two non-empty sorted arrays.
---------------------------------------------------------------------------
*/
public class MedianOfTwoSortedArrays {

	private static double findMedian(int[] arr1, int[] arr2) {

		// identify smaller and larger array
		int[] smaller = arr1, larger = arr2;
		if (smaller.length > larger.length) {
			smaller = arr2;
			larger = arr1;
		}

		boolean oddLen = (smaller.length + larger.length) % 2 != 0;
		int partLen = (smaller.length + larger.length + 1) / 2;

		// start and end of smaller array
		int i = 0, j = smaller.length;

		int sPart, lPart;
		int sLeftMax, sRightMin, lLeftMax, lRightMin;

		while (true) {

			// partitioning of smaller array (binary search)
			sPart = (j + i) / 2;
			lPart = partLen - sPart;

			sLeftMax = (sPart == 0) ? Integer.MIN_VALUE : smaller[sPart - 1];
			sRightMin = (sPart == smaller.length) ? Integer.MAX_VALUE : smaller[sPart];

			lLeftMax = (lPart == 0) ? Integer.MIN_VALUE : larger[lPart - 1];
			lRightMin = (lPart == larger.length) ? Integer.MAX_VALUE : larger[lPart];

			// correct partitioning
			if (sLeftMax <= lRightMin && lLeftMax <= sRightMin) {
				return oddLen ? Math.max(sLeftMax, lLeftMax)
						: (Math.max(sLeftMax, lLeftMax) + Math.min(sRightMin, lRightMin)) / 2.0;
			}

			// too much on the left
			if (sLeftMax > lRightMin)
				j = sPart - 1;

			// too much to the right
			else
				i = sPart + 1;
		}
	}

	public static void main(String[] args) {

		int[] arr1 = { 1, 4, 7, 11, 13, 18 };
		int[] arr2 = { 0, 2, 3, 5, 9, 12, 14, 15, 19 };

		System.out.println("Median: " + findMedian(arr1, arr2));
	}
}
