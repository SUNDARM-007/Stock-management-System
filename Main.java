import java.util.*;
public class Main 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        String user[] = {"Admin", "VISHNU A", "MUTHUSAMY A", "SUNDAR M", "VIGNESH PRABHU M", "JEYA ARAVINTH S"};
        String pwd[] = {"admin", "stock1", "stock2", "stock3", "stock4", "stock5"};
        boolean isAuthenticated = false;

        while (!isAuthenticated) {
            System.out.print("\n   --------------------------------------\n");
            System.out.println("     Welcome to Stock Management System");
            System.out.print("   --------------------------------------\n\n");
            System.out.print("        Enter Username : ");
            String username = sc.nextLine();
            String usernameUpper = username.toUpperCase();
            System.out.print("        Enter passkey : ");
            char[] passwordChars = System.console().readPassword();
            String password = new String(passwordChars);
            Arrays.fill(passwordChars, ' ');
            System.out.print("\n   --------------------------------------\n\n");
            for (int i = 0; i < user.length; i++) {
                if (user[i].equalsIgnoreCase(usernameUpper) && pwd[i].equals(password)) {
                    isAuthenticated = true;
                    System.out.println("        Login successful!");
                    System.out.println("        Welcome, Mr. " + username);

                    System.out.print("\n   --------------------------------------\n\n");
                    System.out.println();

                    while (true) {
                        System.out.print(" --------------------\n");
                        System.out.println("   Choose an option  ");
                        System.out.print(" --------------------\n");
                        System.out.println("1. Insert Product");
                        System.out.println("2. Update Product");
                        System.out.println("3. Remove Product");
                        System.out.println("4. View Products");
                        System.out.println("5. Exit");
                        System.out.println();
                        System.out.print("Enter your choice: ");
                        System.out.println();
                        int choice = sc.nextInt();
                        switch (choice) {
                            case 1:
                                Insert I = new Insert();
                                I.add();
                                break;
                            case 2:
                                Update U = new Update();
                                U.update();
                                break;
                            case 3:
                                Remove r = new Remove();
                                r.remove();
                                break;
                            case 4:
                                ViewPro vp = new ViewPro();
                                vp.view();
                                break;
                            case 5:
                                System.out.println("Exiting...");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Invalid choice!");
                        }
                    }
                }
            }

            if (!isAuthenticated) {
                System.out.println("Invalid username or password. Please try again.\n\n");
            }
        }
    }
}






class Data {
    static int i = 0, count = 0;
    static String ProductName[] = new String[10];
    static int ProductID[] = new int[10];
    static int ProductQuantity[] = new int[10];
    static double ProductPrice[] = new double[10];
}




class Insert extends Data {
    String name;
    int ID;
    int quantity;
    double price;

    public void add() {
        Scanner sc = new Scanner(System.in);

        System.out.print("\n --------------------\n");
        System.out.println("   Enter the details  ");
        System.out.print(" --------------------\n\n");

        try {
            System.out.print("Enter Product ID: ");
            ID = sc.nextInt();

            if (checkIDExists(ID)) {
                System.out.println("\nProduct ID already exists. Please enter a different ID.\n");
                return;
            }

            sc.nextLine();

            System.out.print("Enter Product Name: ");
            name = sc.nextLine();

            System.out.print("Enter Product Quantity: ");
            quantity = sc.nextInt();

            System.out.print("Enter Product price: ");
            price = sc.nextDouble();
        }
        catch (InputMismatchException e) {
            System.out.println("\nInvalid input. Please enter a valid number.\n");
            sc.nextLine();
            return;
        }

        ProductName[i] = name;
        ProductID[i] = ID;
        ProductQuantity[i] = quantity;
        ProductPrice[i] = price;

        System.out.print("---------------------------\n\n");
        System.out.println("Successfully inserted\n\n");

        i++;
        count++;
    }

    private boolean checkIDExists(int id) {
        for (int i = 0; i < count; i++) {
            if (ProductID[i] == id) {
                return true;
            }
        }
        return false;
    }
}



class Update extends Data {
    public void update() {
        int Uid;
        Scanner sc = new Scanner(System.in);
        System.out.print("\n ----------------------\n");
        System.out.println("  Update Product Data  ");
        System.out.print(" ----------------------\n");
        System.out.println();
        try {
            System.out.print("Enter Product ID: ");
            Uid = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("\nInvalid input. Please enter a valid number.\n");
            sc.nextLine();
            return;
        }
        sc.nextLine();

        boolean found = false;
        int foundIndex = -1;

        for (int i = 0; i < ProductID.length; i++) {
            if (Uid == ProductID[i]) {
                System.out.println("Product Name: " + ProductName[i]);
                System.out.println("Product Price: " + ProductPrice[i]);
                System.out.println("Product Quantity: " + ProductQuantity[i]);
                foundIndex = i;
                found = true;
                break;
            }
        }

        if (found) {
            System.out.print("Enter increment or decrement(i/d): ");
            char ch = sc.next().charAt(0);

            if (ch == 'i') {
                System.out.print("Enter quantity to increment: ");
                ProductQuantity[foundIndex] += sc.nextInt();
            } else if (ch == 'd') {
                System.out.print("Enter quantity to decrement: ");
                int q = sc.nextInt();
                if (q > ProductQuantity[foundIndex]) {
                    System.out.println("Out of stock");
                } else {
                    ProductQuantity[foundIndex] -= q;
                }
            } else {
                System.out.println("INVALID!!!!");
            }
        } else {
            System.out.println("ID not found");
        }

        System.out.println("---------------------------\n");
        if (found) {
            System.out.println("Successfully updated ");
            System.out.println(ProductQuantity[foundIndex]);
        }
        System.out.println("---------------------------\n");
    }
}


class ViewPro extends Data {
    public void view() {
        System.out.print("\n --------------------------------------------------------------------------------\n");
        System.out.println("                                 Product Details                                ");
        System.out.print(" --------------------------------------------------------------------------------\n");
        if (ProductID.length == ProductName.length && ProductName.length == ProductQuantity.length) {
            System.out.println("  Product ID"+"        "+"Product name"+"         "+"Product quantity"+"         "+"Product price    ");    
            System.out.print(" --------------------------------------------------------------------------------\n\n");

            for (int i = 0; i < count; i++) {
                System.out.println("      "+ProductID[i] + "                  " + ProductName[i] + "                    " + ProductQuantity[i] + "                   " + ProductPrice[i]);
            }
            System.out.print("\n --------------------------------------------------------------------------------\n");

            System.out.println("\n\n");
        } else {
            System.out.println("Somewhat Error happening....");
        }
    }
}



class Remove extends Data {

    public void remove() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n ------------------\n");
        System.out.println("   Remove product  ");
        System.out.print(" ------------------\n\n");
        System.out.println("Choose how you want to delete the product:");
        System.out.println("1. Delete by Product ID");
        System.out.println("2. Delete by Product Name\n");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                removeById();
                break;
            case 2:
                removeByName();
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private void removeById() {
        int index = -1;
        int proid;
        Scanner sc = new Scanner(System.in);
        try {
           
            System.out.print("Enter the product ID to remove: ");
            proid = sc.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("\nInvalid input. Please enter a valid number.\n");
            sc.nextLine();
            return;
        }

        for (int i = 0; i < count; i++) {
            if (proid == ProductID[i]) {
                index = i;
            }
        }

        if (index == -1) {
            System.out.println("Product ID not found");
            return;
        }

        performDeletion(index);
    }

    private void removeByName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the product name to remove: ");
        String pname = sc.nextLine();

        int index = -1;
        for (int i = 0; i < count; i++) {
            if (pname.equalsIgnoreCase(ProductName[i])) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Product name not found");
            return;
        }

        performDeletion(index);
    }

    private void performDeletion(int index) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you sure you want to delete the product? (yes/no)");
        String con = sc.next().toLowerCase();

        if (con.equals("yes")) {
            removeAtIndex(index, ProductID, ProductName, ProductQuantity, ProductPrice);

            System.out.println("Product successfully removed.");
            System.out.println("Products after removing:");
            displayProducts();
        } else {
            System.out.println("Deletion canceled.");
        }
    }

    public static void removeAtIndex(int index, int[] arr, String[] arr1, int[] arr2, double[] arr3) {
        if (index >= 0 && index < arr.length) {
            for (int i = index; i < arr.length - 1; i++) {
                arr[i] = arr[i + 1];
                arr1[i] = arr1[i + 1];
                arr2[i] = arr2[i + 1];
                arr3[i] = arr3[i + 1];
            }
            arr[arr.length - 1] = 0;
        }
    }
   
    private void displayProducts() {
        System.out.println("Product ID   Product name   Product quantity   Product price ");
        for (int i = 0; i < count-1; i++) {
            System.out.println(ProductID[i] + "           " + ProductName[i] + "           " + ProductQuantity[i] + "           " + ProductPrice[i]);
        }
        System.out.println();
    }
}
