import java.util.*;

class Node {
	private String word;
	private Node left, right;
	private Dictionary next_letter;
	private LinkedList queries;
	private LinkedList next_letter_list;
//	Needed Node data
	private String link;
	private String description;
	private String title;
	private int viewed;
	
	//Node initialized as a normal path
	Node(String word) {
		this.word=word;
		this.left=null;
		this.right=null;
		this.next_letter=null;
		this.queries=new LinkedList();
		this.next_letter_list=new LinkedList();
	}
	
	//Node initialized to store a link
	Node(String word, String link, String description, String title){
		
		this.link=link;
		this.description=description;
		this.title=title;
		this.word=word;
		this.left=null;
		this.right=null;
		this.next_letter=null;
	}
	/*void setResult() {
		viewed=0;
		this.link=link;
		this.description=description;
		this.title=title;
	}*/
	String getLink() {
		return link;
	}
	String getDescription() {
		return description;
	}
	String getTitle() {
		return title;
	}
	//To add a path
	void addWord(String l) {
		//System.out.println("ADDING");
		next_letter_list.insert(l);
		//displayList();
	}
	//To add a link
	void addWordData(String l, String link, String description, String title) {
		//System.out.println("ADDING");
		queries.insert(l, link, description,title);
		//displayList();
	}
	LinkedList getList() {
		return queries;
	}
	LinkedList getListP() {
		return next_letter_list;
	}
	void getWord(String l) {
		queries.search(l);
	}
	Dictionary initNextDict() {
		if(next_letter==null)
			next_letter=new Dictionary();
		return next_letter;
	}
	Node getNode(String s) {
		return next_letter.getNode(s);
	}
	String getData() {
		return word;
	}
	Node getLeft() {
		return left;
	}
	Node getRight() {
		return right;
	}
	int getViews() {
		return viewed;
	}
	void hasViewed() {
		viewed++;
	}
	void setLeft(Node l) {
		this.left=l;
	}
	void setRight(Node r) {
		this.right=r;
	}
	void displayList() {
		queries.display();
	}
	void displayListP() {
		next_letter_list.display();
	}
	
	String displayList(String a) {
		return queries.display(a);
	}
	void showResult() {
		System.out.println(getLink());
		System.out.println(getDescription());
		System.out.println(getViews());
				
	}
	
}
class LinkedList {
	private Node root;
	LinkedList() {
		root=null;
	}
	Node getRoot() {
		return root;
	}
	void setRoot(Node root) {
		this.root=root;
	}
	void insert(String a) {
		Node p=new Node(a);
		Node curr=root;
		if(root==null) {
			root=p;
		//	//System.out.println("YO");
		}
		else {
			while((curr.getData()).compareToIgnoreCase(a)<0 && curr.getLeft()!=null) {
				curr=curr.getLeft();
			}
			if(curr.getLeft()==null) {
				if((curr.getData()).compareToIgnoreCase(a)<0)
					curr.setLeft(p);
		//			//System.out.println("YO");
			}
			else {
				if(curr==root) {
					p.setLeft(root);
					root=p;
		//			//System.out.println("YO");
				}
				else {
					p.setLeft(curr.getLeft());
					curr.setLeft(p);
		//			//System.out.println("YO");
				}
			}
		}
	}
	void insert(String a, String l, String d, String t) {
		if(search(a)==null) {
			Node p=new Node(a);
			LinkedList resultR=p.getList();
			Node r=new Node(a, l, d, t);
			resultR.setRoot(r);
			Node curr=root;
			//System.out.println("ADDING FIRST TIME");
			if(root==null) {
				root=p;
				//System.out.println("YO");
			}
			else {
				while((curr.getData()).compareToIgnoreCase(a)<0 && curr.getLeft()!=null) {
					curr=curr.getLeft();
				}
				if(curr.getLeft()==null) {
					if((curr.getData()).compareToIgnoreCase(a)<0)
						curr.setLeft(p);
			//			//System.out.println("YO");
				}
				else {
					if(curr==root) {
						p.setLeft(root);
						root=p;
			//			//System.out.println("YO");
					}
					else {
						p.setLeft(curr.getLeft());
						curr.setLeft(p);
			//			//System.out.println("YO");
					}
				}
			}
		}
		else {
			if(!alreadyExists(a, l)) {
				Node p=new Node(a, l, d, t);
				Node curr=search(a);
				LinkedList resultR=curr.getList();
				Node tmp=resultR.getRoot();
				Node tra=tmp;
			
				while(tra.getViews()<p.getViews() && tra.getLeft()!=null) {
					tra=tra.getLeft();
				}
				if(tmp.getLeft()==null) {
					if(tra.getViews()<=p.getViews())
						tra.setLeft(p);
				//	//System.out.println("YO");
				}
				else {
					if(tra==tmp) {
						p.setLeft(tra);
						resultR.setRoot(p);
				//		//System.out.println("YO");
					}
					else {
						p.setLeft(tra.getLeft());
						tra.setLeft(p);
				//		//System.out.println("YO");
					}
				}
			}
			//display(a);//OMIT WHEN REQUIRED
		}
	}
	boolean alreadyExists(String a, String link) {
		Node curr=search(a);
		if(curr!=null) {
			LinkedList resultR=curr.getList();
			Node tmp=resultR.getRoot();
			Node tra=tmp;
			while(tra!=null) {
				if(tra.getLink().equals(link))
					return true;
				tra=tra.getLeft();
			}	
		}
		return false;
	}
	Node search(String a) {
		Node curr=root;
		while(curr!=null) {
			if((curr.getData()).compareToIgnoreCase(a)==0) {
				//System.out.println("YES");
				return curr;
			}
			curr=curr.getLeft();
		}
		return null;
	}
	String display(String a) {
		Node curr=search(a);
		String ans=null;
		if(curr!=null) {
			LinkedList resultR=curr.getList();
			Node tmp=resultR.getRoot();
			Node tra=tmp;
			while(tra!=null) {
				ans+="<p>";
				ans+=(tra.getLink());
				ans+="</br>";
				ans+=(tra.getDescription());
				ans+="</br>";
				ans+=(tra.getViews());
				ans+="</br> </p>";
				
				tra=tra.getLeft();
			}	
		}
		return ans;
	}
	void display() {
		Node curr=root;
		while(curr!=null) {
			System.out.println(curr.getData());
			display(curr.getData());
			curr=curr.getLeft();
		}
	}
}
class Dictionary { // dictionary which we are using is a binary search tree
	private Node root;
	Dictionary() {
		this.root=null;
		init('A', 'Z');
	}
	
	
	//Creating a dictionary by adding all letters of alphabet
	public void init(char a, char b) {
		if(a<=b) {
			char m=(char)((int)((a+b)/2));
			////System.out.println(m);
			add(""+m);
			init(a, (char)(m-1));
			init((char)(m+1), b);
		}
	}
		//char m=char l;
		/*if(a<b) {
			add(""+m);
			init(a, (char)(m-1));
			init(m, b);
		}*/
	/*
	public void init() {
		for(char i='A';i<'Z';i++)
			add("" +i);
	}*/
		
	public void add(String str) {
		Node tmp=new Node(str);
		this.root=add(root, tmp);
	}
	
	private Node add(Node root, Node tmp) {
		if(root==null)
			return tmp;
		else {
			if(root.getData().compareToIgnoreCase(tmp.getData())>0)
				root.setLeft(add(root.getLeft(), tmp));
			else
				if(root.getData().compareToIgnoreCase(tmp.getData())<0)
					root.setRight(add(root.getRight(), tmp));
				
			return root;
		}
	}
	public void display() {
		display(root);
		//System.out.println();
		postfix(root);
		//System.out.println();
		prefix(root);
		//System.out.println();
	}
	
	private void postfix(Node root) {
		if(root!=null) {
			display(root.getLeft());
			display(root.getRight());
			//System.out.print(root.getData() + " ");
		}
	}
	private void prefix(Node root) {
		if(root!=null) {
			//System.out.print(root.getData() + " ");
			display(root.getLeft());
			display(root.getRight());
		}
	}
	private void display(Node root) {
		if(root!=null) {
			display(root.getLeft());
			//System.out.print(root.getData() + " ");
			display(root.getRight());
		}
	}
	int count;
	public void search(String str) {
		count=0;
		Node c=getNode(root, str);
		//if(c!=null)
			//System.out.println("Word " + str +"found" + count);
		//else
			//System.out.println("Word Not found");
	}
	public Node getNode(Node root, String str) {
		if(root==null)
			return null;
		else {
			count++;
			if(root.getData().compareToIgnoreCase(str)>0)
				return getNode(root.getLeft(), str);
			else {
				if(root.getData().compareToIgnoreCase(str)<0)
					return getNode(root.getRight(), str);
				else
					return root;
			}
		}
	}
	public Node getNode(String str) {
		return getNode(root, str);
	}
	public void addWord(String str,  String li, String des, String t) {
		if(str!=null) {
			Node a=getNode(""+str.charAt(0));
			Dictionary l=null;
			for(int i=1;i<str.length();i++) {
				////System.out.println(a.getData());
				a.addWord(""+ str.charAt(i));
				l=a.initNextDict();
				//l.display();
				a=l.getNode(""+str.charAt(i));
			}
			////System.out.println(a.getData());
			a.addWordData(str, li, des, t);
			//a.displayList();
		}
	}
	public void predict(String str) {
		if(str!=null) {
			Node a=getNode(""+str.charAt(0));
			Dictionary l=null;
			for(int i=1;i<str.length();i++) {
				////System.out.println(a.getData());
				l=a.initNextDict();
				//l.display();
				a=l.getNode(""+str.charAt(i));
			}
			LinkedList lets=a.getListP();
			Node curr=lets.getRoot();
			if(curr==null)
				System.out.println(str);
			while(curr!=null) {
				a.displayList();
				predict(str+ curr.getData());
				curr=curr.getLeft();
			}
		}	
	}
	public Node words(String str) {
		if(str!=null) {
			Node a=getNode(""+str.charAt(0));
			Dictionary l=null;
			for(int i=1;i<str.length();i++) {
				////System.out.println(a.getData());
				l=a.initNextDict();
				//l.display();
				a=l.getNode(""+str.charAt(i));
			}
			
			//LinkedList resultR=a.getList();
			//Node tmp=resultR.getRoot();
			
			return a;
		}
		else
			return null;
	}
	public Node getWord(String str) {
		if(str!=null) {
			Node a=getNode(""+str.charAt(0));
			Dictionary l=null;
			for(int i=1;i<str.length();i++) {
				////System.out.println(a.getData());
				l=a.initNextDict();
				//l.display();
				a=l.getNode(""+str.charAt(i));
			}
			
			LinkedList resultR=a.getList();
			//Node tmp=resultR.getRoot();
			
			return resultR.search(str);
		}
		else
			return null;
	} 
}
class Dict {
	public static void main(String argv[]) {
		Scanner input=new Scanner(System.in);
		Dictionary D=new Dictionary();
		D.addWord("M", "L", "", "");
		D.addWord("Moon","D", "", "");
		D.addWord("Mellow","W", "", "");
		D.addWord("Mellons", "Q", "", "");
		D.addWord("Must", "R", "", "");
		D.addWord("Mustard", "T", "", "");
		D.addWord("Doctor", "R", "", "");
		D.addWord("Who", "N", "", "");
		D.addWord("M", "Z", "", "");
		System.out.println("INSERTED");
		//D.display();
		//D.search("M");
		//D.search("Potato");
		/*
		for(char a='A';a<='Z';a++)
			D.search(""+a);*/
		//if(argv.length==1)
			//System.out.println(argv[0]);
		
	}
}
