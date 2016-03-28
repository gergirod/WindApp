package germangirod.test;

/**
 * Created by germangirod on 3/16/16.
 */
public class Person {

    public String name;
    public String surname;
    public int age;

    public Person(String name, String surname, int age){
        this.name= name;
        this.surname= surname;
        this.age=age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }
}
