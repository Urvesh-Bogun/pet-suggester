public class TreeNode
{    

    String QorA;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(String QorA)
    {
        this.QorA = QorA;
        this.left = null;
        this.right = null;
    }
        
    public TreeNode(String QorA, TreeNode left, TreeNode right)
    {
        this.QorA = QorA;
        this.left = left;
        this.right = right;
    }
    
    public String getQorA()
    {
        return this.QorA;
    }
    
    public TreeNode getLeft()
    {
        return this.left;
    }
        
    public TreeNode getRight()
    {
        return this.right;
    }
    
    
    public boolean isLeaf()
    {
        return((this.left == null) && (this.right == null));
    }
    
}



    
    
    
