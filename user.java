package splitwise;
public class user {
    static int userid=0;
    String name;
    user(){
        name=null;
    }
    user(String name){
        this.name=name;
        userid++;
    }
    
}
