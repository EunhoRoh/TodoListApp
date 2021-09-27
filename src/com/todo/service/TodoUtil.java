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
		
		String title, category, due_date = null, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[Category] "+ "Insert! ");
		category = sc.next();
		sc.nextLine();//���͸� �Է¹޾ƾ� ���� ���� �Է� ���� ������ ���Ե� ���� ��
		
		System.out.print("Title "+ "Insert! ");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("Duplicate!");
			return;
		}
		sc.nextLine();//���͸� �Է¹޾ƾ� ���� ���� �Է� ���� ������ ���Ե� ���� ��
		
		System.out.println("Description Insert! ");
		desc = sc.nextLine().trim();//trim�� �¿� ���� �������� Ȥ�ö� ����
	
		
		System.out.println("Due_Date Insert! ");
		due_date = sc.nextLine().trim();//trim�� �¿� ���� �������� Ȥ�ö� ����
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
		System.out.println("Add complete.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
			
		System.out.println("[Index Delete]\n" + "Insert you want to delete! ");
		
		int index = sc.nextInt();
		/*
		if (!l.isDuplicate(title)) {
			System.out.println("Duplicate!");
			return;
		}*/
		int count =0;
		char y_n;
		for (TodoItem item : l.getList()) {
			count++;
			
			if(index == count) {
				System.out.println(item.toString());
				System.out.println("Are you gonna remove it? (y/n) > ");
				y_n = sc.next().charAt(0);
				if(y_n=='y') {
					l.deleteItem(item);
					System.out.println("Delete Complete!");
				}
				else if(y_n=='n') {
					System.out.println("Delete Cancel!");
				}
				
				break;
			}
			/*
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("Delete Complete!");
				break;
			}*/
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[Index Edit]\n" + "Insert you want to Edit! ");
		int index = sc.nextInt();
		System.out.println(l.getList().get(index-1));
		/*
		if (!l.isDuplicate(title)) {
			System.out.println("No title!");
			return;
		}*/
		System.out.println("Insert New Title! ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("Duplicate!");
			return;
		}
		sc.nextLine();
		
		System.out.println("Insert New Category! ");
		String new_Category = sc.next().trim();		
		sc.nextLine();

		
		
		System.out.println("Insert Description! ");
		String new_description = sc.next().trim();
		sc.nextLine();
		
		System.out.println("Insert Due_Date! ");
		String new_due_date = sc.next().trim();
		
		int count=0;
		
		for (TodoItem item : l.getList()) {
			
			if ((index-1) == count) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_Category, new_title, new_description, new_due_date);
				l.addItem(t); //�����ð����� ����Ÿ �ٽ� �¾���
				System.out.println("Edit Complete!");
				break;
			}
			count++;
		}

	}
	
	public static void saveList(TodoList l, String filename) {
		// TODO Auto-generated method stub
		try {
			Writer w = new FileWriter(filename);
		    
		    for (TodoItem myitem : l.getList()) {
				
				//w.write(myitem.getTitle()+"##"+myitem.getDesc()+"##"+myitem.getCurrent_date()+"\n");
		    	w.write(myitem.toSaveString());
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
	
			while((oneline = br.readLine())!= null) {
				
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String category =st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date=st.nextToken();
				String current_date = st.nextToken();
				TodoItem t = new TodoItem(category, title, desc, due_date, current_date);
				//t.setCurrent_date(current_date);
				//l.addItem(t);
				l.addItem(t);
			
	
			}
			br.close();
			System.out.println(l.getList().size() + " Data loaded!!!");
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			System.out.println(filename+" is gone.");
			
			
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public static void findList(TodoList l, String find) {
		//Set<String> clist = new HashSet<String>();
		Scanner sc = new Scanner(System.in);
	
		
		//int index = sc.nextInt();
		/*
		if (!l.isDuplicate(title)) {
			System.out.println("Duplicate!");
			return;
		}*/
		int count =0;
		int find_count=0;
		//char y_n;
		for (TodoItem item : l.getList()) {
			count++;
			if(item.getTitle().contains(find) || item.getDesc().contains(find)) {
				System.out.println(count+". "+item.toString());
				find_count++;
			}
			
			
		}
		System.out.println("총 "+find_count+ "개의 항목을 찾았습니다.");
		
	}
	
	
	public static void findCateList (TodoList l, String cate) {
		//Set<String> clist = new HashSet<String>();
		Scanner sc = new Scanner(System.in);
	
		
		//int index = sc.nextInt();
		/*
		if (!l.isDuplicate(title)) {
			System.out.println("Duplicate!");
			return;
		}*/
		int count =0;
		int find_count=0;
		//char y_n;
		for (TodoItem item : l.getList()) {
			count++;
			if(item.getCategory().equals(cate)) {
				System.out.println(count+". "+item.toString());
				find_count++;
			}
			
			
		}
		System.out.println("총 "+find_count+ "개의 항목을 찾았습니다.");
	}
	
	public static void listCateAll(TodoList l) {
		Set<String> clist = new HashSet<String>();
		
		for (TodoItem c : l.getList()) {
			clist.add(c.getCategory());
		}
		
		Iterator it = clist.iterator();
		while(it.hasNext()) {
			String s= (String)it.next();
			System.out.print(s);
			if(it.hasNext())
				System.out.print(" / ");
		}
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.\n", clist.size());
		
	}
	
	public static boolean isExistCategory(List<String> clist, String cate) {
		for(String c: clist) {
			if(c.equals(cate))
				return true;

		}
		return false;
	}
	
	public static void listAll(TodoList l) {
		int num = l.getList().size();
		
		System.out.println("[All List, Num of List : "+num+"]");
		int i=0;
		for (TodoItem item : l.getList()) {
			i++;
			System.out.println(i+". "+item.toString());
		}
	}
}
