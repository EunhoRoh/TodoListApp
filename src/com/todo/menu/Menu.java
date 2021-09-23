package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("<ToDoList Managing Commands>");
        System.out.println("1. add");
        System.out.println("2. del");
        System.out.println("3. edit");
        System.out.println("4. ls - list all");
        System.out.println("5. ls_name_asc - sort by name");
        System.out.println("6. ls_name_desc - sort by reverse");
        System.out.println("7. ls_date - sort by date");
        System.out.println("8. exit");
        
    }
    public static void prompt() {
    	System.out.print("\nInsert Command > ");
    }
}
