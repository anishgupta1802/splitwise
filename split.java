package splitwise;
import java.util.*;
public class split extends user {
    List <Integer> userchange;
    List <Double> nonequal;
    public static List <List<Double>> balance;
    split(){
    	userchange = new ArrayList<>();
    	nonequal = new ArrayList<>();
    }
    public void equal(int userpaid,int amt){
        for(int i=0;i<userchange.size();i++){
            balance.get(userchange.get(i)-1).set(userpaid-1, balance.get(userchange.get(i)-1).get(userpaid-1)+(double)amt/userchange.size());
        }
    }
    public void exact(int userpaid) {
    	for(int i=0;i<userchange.size();i++) {
    		balance.get(userchange.get(i)-1).set(userpaid-1,balance.get(userchange.get(i)-1).get(userpaid-1)+nonequal.get(i));
    	}
    }
    public void percent(int userpaid) {
    	for(int i=0;i<userchange.size();i++) {
    		balance.get(userchange.get(i)-1).set(userpaid-1,balance.get(userchange.get(i)-1).get(userpaid-1)+nonequal.get(i));
    	}
    }
}

