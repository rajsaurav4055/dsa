/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int max = -1;
    public int diameterOfBinaryTree(TreeNode root) {
        calculateHeight(root);
        return max;
    }


    //function to calculate the height and diameter of each node.
    //diameter of a node is left height + right height
    public int calculateHeight(TreeNode root){
        if(root == null)return -1; //pretty important
        int left= 1 + calculateHeight(root.left);
        int right= 1 + calculateHeight(root.right);
        max=Math.max(max, left+right);//left + right is the diameter here
        return Math.max(left, right);
    }
}
