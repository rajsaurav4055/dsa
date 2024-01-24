
//TC: O(mlog n)
// class Solution {
//     public boolean searchMatrix(int[][] matrix, int target) {
//         int i=0, j=matrix[0].length-1;
//             while(i < matrix.length && j >= 0){
//                 if(target == matrix[i][j]){
//                     return true;
//                 }else if(target < matrix[i][j]){
//                     j--;
//                 }else{
//                     i++;
//                 }
//         } 
//         return false;
//     }
// }

//TC: O(log(m*n))
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0)return false;
        int rows=matrix.length;
        int columns=matrix[0].length;
        int low=0;
        int high=rows * columns;
        while(low < high){
            int mid =(low + high)/2;
            if(matrix[mid/columns][mid%columns] == target){
                return true;
            }else if(matrix[mid/columns][mid%columns] > target){
                high = mid;
            }else{
                low = mid + 1;
            }
        }
        return false;
    }
}
