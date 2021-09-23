package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;


import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil { //��ü�� ���� �ʿ���� Ŭ���� �޼ҵ� ��밡�� static
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[Title]"
				+ "Insert! ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("Duplicate!");
			return;
		}
		sc.nextLine();//���͸� �Է¹޾ƾ� ���� ���� �Է� ���� ������ ���Ե� ���� ��
		System.out.println("Description Insert! ");
		desc = sc.nextLine().trim();//trim�� �¿� ���� �������� Ȥ�ö� ����
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("Add complete.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
			
		System.out.println("[Title]" + "Insert you want to delete! ");
		String title = sc.next();
		if (!l.isDuplicate(title)) {
			System.out.println("Duplicate!");
			return;
		}
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("Delete Complete!");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[Title]" + "Insert! ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("No title!");
			return;
		}

		System.out.println("Insert New Title! ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("Duplicate!");
			return;
		}
		sc.nextLine();
		System.out.println("Insert Description! ");
		String new_description = sc.next().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t); //�����ð����� ����Ÿ �ٽ� �¾���
				System.out.println("Edit Complete!");
			}
		}

	}
	
	public static void saveList(TodoList l, String filename) {
		// TODO Auto-generated method stub
		try {
			Writer w = new FileWriter(filename);
		    
		    for (TodoItem myitem : l.getList()) {
				
				w.write(myitem.getTitle()+"##"+myitem.getDesc()+"##"+myitem.getCurrent_date()+"\n");
			}
			
			
		
			
			w.close();
			
			
			System.out.println("Data Saved.");
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	
	public static void loadList(TodoList l, String filename) {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String oneline;
			int count =0;
			while((oneline = br.readLine())!= null) {
				count++;
				StringTokenizer st = new StringTokenizer(oneline, "##");
				
				String title = st.nextToken();
				String desc = st.nextToken();
				String current_date = st.nextToken();
				
				TodoItem t = new TodoItem(title, desc, current_date);
				l.addItem(t);
	
			}
			br.close();
			System.out.println(count + " Data loaded!!!");
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println("todolist.txt is gone.");
			
			
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		

	}

	public static void listAll(TodoList l) {
		System.out.println("[List]");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
}
