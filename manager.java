package splitwise;
import java.util.*;


public class manager extends split{
    private static List <user> userlist;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        userlist = new ArrayList<>();
        balance = new ArrayList<>();
        while(true){
            String s = sc.nextLine();
            String[] token = s.split(" ");
            if(token[0].equals("EXIT"))break;
            else if(token[0].equals("ADDUSER")){
                int k=userentry(token[1]);
                System.out.println("user id of "+ token[1] +" is "+ k);
            }
            else if(token[0].equals("SHOW")){
            	calc();
                if(token[1].equals("ALL")){
                    show();
                }
                else{
                	int w=0;
                    for(int i=0;i<balance.size();i++){
                        if(balance.get(Integer.parseInt(token[1])-1).get(i)!=0) {
                        System.out.println(token[1] +" owes "+ (i+1) + " : "+ balance.get(Integer.parseInt(token[1])-1).get(i));
                        w=1;
                        }
                    }
                    if(w==0)System.out.println("none");
                }
            }
            else if(token[0].equals("EXPENSE")){
                split spl = new split();
                int n =Integer.parseInt(token[2]);
                for(int i=3;i<n+3;i++){
                    spl.userchange.add(Integer.parseInt(token[i]));
                }
                if(token[n+3].equals("EQUAL"))
                spl.equal(Integer.parseInt(token[1]),Integer.parseInt(token[n+4]));
                if(token[n+3].equals("EXACT")){
                	for(int i=n+4;i<token.length;i++) {
                		spl.nonequal.add((double)Integer.parseInt(token[i]));
                	}
                	spl.exact(Integer.parseInt(token[1]));
                }
                if(token[n+3].equals("PERCENT")){
                	for(int i=n+4;i<token.length-1;i++) {
                		spl.nonequal.add((double)((Integer.parseInt(token[i])*Integer.parseInt(token[token.length-1])))/100);
                	}
                	spl.exact(Integer.parseInt(token[1]));
                }


            }

        }
    }

    private static int userentry(String s){
        user u = new user(s);
        userlist.add(u);
        for(int i=0;i<balance.size();i++)balance.get(i).add(0.0);
        List<Double> temp= new ArrayList<>();
        for(int i=0;i<balance.size()+1;i++){
            temp.add(0.0);

        }
        balance.add(temp);
        return user.userid;
    }

    private static void show(){
        for(int j=0;j<balance.size();j++){
            for(int i=0;i<balance.size();i++){
                 if(balance.get(j).get(i)!=0)
                 System.out.println(userlist.get(j).name +" owes "+ userlist.get(i).name + " : "+ balance.get(j).get(i));
            }
        }
//    for(int j=0;j<balance.size();j++){
//        for(int i=0;i<balance.size();i++){
//            System.out.print(balance.get(j).get(i)+ " ");
//        }
//        System.out.println();
//    }
}
    
    private static void calc() {
    	List<Double> net = new ArrayList<>();
    	for(int i=0;i<balance.size();i++) {
    		double temp1=0,temp2=0;
    		for(int j=0;j<balance.size();j++) {
    			temp1+=balance.get(i).get(j);
    			temp2+=balance.get(j).get(i);
    		}
    		net.add(temp1-temp2);
    	}
    	for(int i=0;i<balance.size();i++) {
    		for(int j=0;j<balance.size();j++) {
    			balance.get(i).set(j, 0.0);
    		}
    	}
    	for(int i=0;isempty(net)==false;i++) {
    		if(i>1000)break;
    		
    		balance.get(max_index(net)).set(min_index(net),balance.get(max_index(net)).get(min_index(net))+Collections.max(net) );
    		double max=Collections.max(net),min=Collections.min(net);
    		if(max>Math.abs(min)) {
    		net.set(max_index(net), max+min);
    		net.set(max_index(net), 0.0);
    		}
    		else {
        		net.set(min_index(net), max+min);
    			net.set(max_index(net), 0.0);
    		}
//    		for(int L=0;L<net.size();L++){
//        		System.out.println(net.get(L));
//        	}
    	}
    	
    	
    	
    }
    
    private static int max_index(List<Double> lt) {
    	double temp=Collections.max(lt,null);
    	int i;
    	for(i=0;i<lt.size();i++) {
    		if(lt.get(i)==temp) {
    			break;
    		}
    	}
    	return i;
    }
    private static int min_index(List<Double> lt) {
    	double temp=Collections.min(lt,null);
    	int i;
    	for(i=0;i<lt.size();i++) {
    		if(lt.get(i)==temp) {
    			break;
    		}
    	}
    	return i;
    }
    
    private static boolean isempty(List <Double> lt) {
    	boolean b = true;
    	for(int i=0;i<lt.size();i++) {
    		if(lt.get(i)!=0.0)b=false;
    	}
    	return b;
    }


}
