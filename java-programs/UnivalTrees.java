/*

Assuming that uni-value subtrees means that there is only one unique value
over all the nodes in the subtree, a linear-time algorithm follows from
implementing the following method and then calling it from the root.

If this node is a leaf, increment a global counter, annotate this node as
being the root of a uni-value subtree, and return. (This is the base case.)
Otherwise, this node is not a leaf. Recursively check if each node is the
root of a uni-value subtree and furthermore see if each child matches in
value with the current node. If a single node fails either of these checks,
then this node is not a uni-value subtree. Otherwise, it is, so increment
the global counter, annotate the node as being the root a uni-value subtree
and return.

This runs in linear time because each node will be the main parameter of
the recursive method exactly once, and each node, having a unique parent,
is considered exactly once in the body of the method.

*/


public class UnivalTrees
{

	static int count=0;
	
	public static boolean findUni(BSTNode <Integer> node){
	    if(node == null) return true;
	    boolean left = findUni(node.left);
	    boolean right =  findUni(node.right);
	    if(left && right && (node.left==null || node.left.data == node.data) && 
	            (node.right==null ||node.right.data == node.data)){
	        count++;
	        return true;
	    }
	    return false;
	}
}