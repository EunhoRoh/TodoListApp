package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		TodoUtil.loadList(l, "todolist.txt"); //���� �߰�
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "find":
				String find = sc.next();
				TodoUtil.findList(l, find);
				break;
				
			case "find_cate":
				String cate = sc.next();
				TodoUtil.findCateList(l, cate);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name":
				l.sortByName();
				System.out.println("제목순으로 정렬하였습니다.");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("제목역순으로 정렬하였습니다.");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("날짜순으로 정렬하였습니다.");
				isList = true;
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				System.out.println("날짜역순으로 정렬하였습니다.");
				isList = true;
				break;
			
			case "ls_cate":
				TodoUtil.listCateAll(l);
				break;
			
			//help �߰�
			case "help":
				Menu.displaymenu();
				break;

			case "exit":
				quit = true;
				break;

			default:
				System.out.println("no command. (insert - help)");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		sc.close(); 
		TodoUtil.saveList(l, "todolist.txt");
	}
}
