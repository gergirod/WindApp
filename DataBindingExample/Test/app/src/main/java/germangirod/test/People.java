package germangirod.test;

import java.util.List;

/**
 * Created by germangirod on 3/16/16.
 */
public class People {

    private List<Person> personList;

    public People(List<Person> personList){
        this.personList = personList;
    }

    public List<Person> getPersonList() {
        return personList;
    }

}
