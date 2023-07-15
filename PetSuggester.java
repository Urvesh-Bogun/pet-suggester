import java.util.Scanner;

public class PetSuggester
{

    public static Scanner reader = new Scanner(System.in);
    private TreeNode root;
    
    public PetSuggester()
    {
        root = new TreeNode("Do you like dogs?",
                                new TreeNode("Do you prefer big dogs?",
                                        new TreeNode("A golden retriever"),
                                        new TreeNode("A chihauhau")),
                            new TreeNode("An iguana"));
    }
    
    public void path()
    {
        root = path(root); 
    }
    
    private TreeNode path(TreeNode current)
    {
        if (current.isLeaf())
        {
            System.out.println(current.QorA);
            if (askYesNo("Is this satisfactory?"))
            {
                System.out.println();
            }
            else
            {
                System.out.print("What is the preferred animal?");
                System.out.println(" ");
                TreeNode answer = new TreeNode(reader.nextLine());
                System.out.println("Tell me a question that distinguishes my sugestion from your answer");
                System.out.println(" ");
                String question = reader.nextLine();
                current = new TreeNode(question, answer, current); 
            } 
        }
        else
        {
            if (askYesNo(current.QorA)) 
            {
                current.left = path(current.left);
            }
            else
            {
                current.right = path(current.right); 
            }   
        } 
      return current;
   }
   
    public static void main(String[] args)
    {
     
        System.out.println(" ");
        System.out.println(" ");                    
        System.out.println("Welcome! Let's help you find a pet.");      
        
        System.out.println(" ");
        
        PetSuggester tree = new PetSuggester();
        do
        {
            tree.path();
        } while (tree.askYesNo("Do you want to go again?"));
    }

    
    public static boolean askYesNo(String ans)
    {
        System.out.print(ans + "[y/n]");
        System.out.println(" ");
        ans = reader.nextLine();
        if (ans.equalsIgnoreCase("y"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
      
  
}

