'''
---------------------------------------------------------------------------
Question
---------------------------------------------------------------------------
In the city of Gotham , rain in pouring down heavily.But thanks to Inspector 
Gordon, now the city has best rainwater harvesting system.The vacant spaces 
between two building act as water container. The height of buildings differ,
hence the container can hold amount of water which is equal to the height of
smaller building.

Given the number of buildings and height of each building, calculate the 
maximum amount of water that can be collected in all the containers. 

-----------------------------------------------------------------------------
Input Format
-----------------------------------------------------------------------------
First line contains N number of test cases 
Following is 2*N lines of input.
For each test case there is B number of building and in the following line B 
number of integers which depicts height H of each building. 

1 <= N <= 100

1 <= B <= 10^5

1 <= H <= 2x10^9

-----------------------------------------------------------------------------
Output Format
-----------------------------------------------------------------------------

Final output is N lines containing the result for each input test case. 

---------------------------------------------------------------------------
'''


def uptillMax(arr):
    """
    Returns an array in which each element is the index of maximum value till
    then in the given array.
    """
    tillMaxArr = []
    tillMaxIndex = 0
    for i in range(len(arr)):
        if arr[i] > arr[tillMaxIndex]:
            tillMaxIndex = i
        tillMaxArr.append(tillMaxIndex)
    return tillMaxArr


def subHarvest(buildingHeights, tillMaxArr):
    """
    Calculates the total water harvested assuming tallest building at the end.
    """
    buildingsCount = len(buildingHeights)
    if buildingsCount is 0:
        return 0
    elif buildingsCount is 1:
        return buildingHeights[0]
    
    val = 0
    tallestBuildingPos = tillMaxArr[-1]
    val += (buildingsCount-tallestBuildingPos) * buildingHeights[tallestBuildingPos]
    
    subBuildingHeights = buildingHeights[:tallestBuildingPos]
    val += subHarvest(subBuildingHeights, tillMaxArr[:tallestBuildingPos])
    return val

def harvest(buildingHeights):
    """
    Calculates the total water harvested by adding the water harvested from 
    both side of the tallest building.
    """
    tallestBuildingPos = buildingHeights.index(max(buildingHeights))
    val = subHarvest(buildingHeights[:tallestBuildingPos], uptillMax(buildingHeights[:tallestBuildingPos]))
    reversedRightBuildings = buildingHeights[-1:tallestBuildingPos:-1]
    val += subHarvest(reversedRightBuildings, uptillMax(reversedRightBuildings))
    return val


if __name__ == "__main__":
   
    testCases = int(input())
    while testCases > 0:
        _ = int(input())
        buildingHeights = list(map(int,input().split()))
        print(harvest(buildingHeights))
        testCases-=1