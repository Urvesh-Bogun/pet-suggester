import java.util.Scanner;

public class PetSuggester
{

    public static Scanner kbd = new Scanner(System.in);
    private TreeNode root;
    
    //Construction of initial tree
    public PetSuggester()
    {
        root = new TreeNode("Do you like dogs?",
                                new TreeNode("Do you prefer big dogs?",
                                        new TreeNode("a golden retriever"),
                                        new TreeNode("a chihauhau")),
                            new TreeNode("an iguana"));
    }

    //Getters and setters
    public TreeNode getRoot()
    {
        return root;
    }

    public void setRoot(TreeNode newRoot)
    {
        root = newRoot;
    }
    
    //Recursive method to traverse along the tree
    public void path()
    {
        root = path(root); 
    }
    
    //Method to either print leaf and replace if user is unsatisfied or traverse tree
    private TreeNode path(TreeNode current)
    {
        if (current.isLeaf())
        {
            System.out.println("Perhaps you would like " + current.getValue());
            if (!(askYesNo("Is this satisfactory?")))
            {
                System.out.print("What is the preferred animal?");
                String input = kbd.nextLine();
                TreeNode newNode = new TreeNode(input);
                System.out.println("Tell me a question that distinguishes " + current.getValue() + " from " + input);
                String question = kbd.nextLine();
                if(askYesNo(question))
                {
                    current = new TreeNode(question, newNode, current);
                }
                else
                {
                    current = new TreeNode(question, current, newNode);
                } 
            } 
        }
        else
        {
            if (askYesNo(current.getValue())) 
            {
                current.setLeft(path(current.getLeft()));
            }
            else
            {
                current.setRight(path(current.getRight()));
            }   
        } 
        return current;
    }
   
   //Main method creating the initial tree or loading one from "suggestions.txt" then saving it at the end if wanted
   public static void main(String[] args)
    {
        System.out.println("Welcome! Let's help you find a pet.");
   
        PetSuggester tree = new PetSuggester();
   
        try
        {
            if (askYesNo("Do you want to load a saved tree?"))
            {
                TreeNode loadedRoot = TreeNode.loadFromFile("suggestions.txt");
                if (loadedRoot != null)
                {
                    tree.setRoot(loadedRoot);
                    System.out.println("Tree loaded from suggestions.txt");
                }
                else
                {
                    System.out.println("No saved tree found.");
                }
            }
   
            do
            {
                tree.path();
           } while (askYesNo("Do you want to go again?"));
   
            if (askYesNo("Do you want to save the current tree?"))
            {
                tree.getRoot().saveToFile("suggestions.txt");
            }
        } catch (Exception e)
        {
            System.out.println("An error occurred: " + e.getMessage());
        } finally 
        {
            kbd.close();
        }
    }

    //Boolean to retrieve answer for yes no questions
    public static boolean askYesNo(String question)
    {
        boolean loop = true;
        String ans = "";
        while(loop)
        {
            System.out.print(question + " [y/n]: ");
            ans = kbd.nextLine().trim();
            if (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("n"))
            {
                loop = false;
            }
            else
            {
                System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
            }
        }
        return ans.equalsIgnoreCase("y");
    }
    
}