import java.io.*;
public class TreeNode
{
    private String value;
    private TreeNode left;
    private TreeNode right;

    //TreeNode that is a leaf
    public TreeNode(String value)
    {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    //TreeNode that isn't a leaf
    public TreeNode(String value, TreeNode left, TreeNode right)
    {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    //Getters and setters
    public String getValue()
    {
        return this.value;
    }

    public TreeNode getLeft()
    {
        return this.left;
    }

    public TreeNode getRight()
    {
        return this.right;
    }

    public void setLeft(TreeNode left)
    {
        this.left = left;
    }

    public void setRight(TreeNode right)
    {
        this.right = right;
    }

    //Boolean to check if TreeNode is a leaf or not
    public boolean isLeaf()
    {
        return this.left == null && this.right == null;
    }

    //Methods to save tree to file "filename.txt"
    public void saveToFile(String filename)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename)))
        {
            saveToFile(this, bw);
            System.out.println("Tree saved to " + filename);
        }
        catch (IOException e)
        {
            System.out.println("Error saving the tree to " + filename);
        }
    }

    private static void saveToFile(TreeNode current, BufferedWriter bw) throws IOException
    {
        if (current == null)
        {
            bw.write("null");
            bw.newLine();
            return;
        }

        bw.write(current.getValue());
        bw.newLine();
        saveToFile(current.getLeft(), bw);
        saveToFile(current.getRight(), bw);
    }

    //Methods to load tree from file "filename.txt"
    public static TreeNode loadFromFile(String filename)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            return loadFromFile(br);
        }
        catch (IOException e)
        {
            System.out.println("Error loading the tree from " + filename);
            return null;
        }
    }

    private static TreeNode loadFromFile(BufferedReader br) throws IOException
    {
        String line = br.readLine();
        if (line == null || line.equals("null"))
        {
            return null;
        }

        TreeNode current = new TreeNode(line);
        current.setLeft(loadFromFile(br));
        current.setRight(loadFromFile(br));
        return current;
    }
}