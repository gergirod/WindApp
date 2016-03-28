import android.content.Context;
import germangirod.test.AgeValidation;
import germangirod.test.MainActivity;
import germangirod.test.People;
import germangirod.test.Person;
import germangirod.test.R;
import java.util.Arrays;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by germangirod on 3/16/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class AgeValidatorTest {
    private static final String FAKE_STRING = "HELLO WORLD";
    private Context context;

    @Before
    public void SetUp() {

    }

    @Test
    public void validateAge(){

        AgeValidation ageValidation = Mockito.mock(AgeValidation.class);

        // define return value for method getUniqueId()
        when(ageValidation.getAge(2016,1988)).thenReturn(28);

        assertEquals(ageValidation.getAge(2016,1988),28);

    }

    @Test
    public void moreThanOneReturnValue() {

        Iterator i = mock(Iterator.class);
        when(i.next()).thenReturn("Mockito").thenReturn("rocks");
        String result = i.next()+" "+i.next();

        assertEquals("Mockito rocks", result);

    }

    @Test
    public void testPerson(){

        Person person1 = new Person("german","girod",28);
        Person person2 = new Person("hernan","suarez",31);
        Person person = mock(Person.class);

        when(person.getName()).thenReturn(person1.getName());
        when(person.getSurname()).thenReturn(person1.getSurname());
        when(person.getAge()).thenReturn(person1.getAge());

        assertEquals(person.getName(), person1.getName());
        assertEquals(28, person1.getAge());

    }

    @Test
    public void getMultiplePersons(){

        People people = mock(People.class);
        Person person = mock(Person.class);
        Person person1 = new Person("german","girod",28);
        Person person2 = new Person("hernan","suarez",31);

        when(people.getPersonList()).thenReturn(Arrays.asList(person1, person2));
        person= people.getPersonList().get(0);
        assertEquals(people.getPersonList().get(0), person1);
        assertEquals("german", person1.getName());
    }

    @Test
    public void getMyName(){

        MainActivity mainActivity = mock(MainActivity.class);


        String appName =mainActivity.getResources().getString(R.string.app_name);

        when(mainActivity.getName()).thenReturn("chimbulain");

        assertEquals("german",mainActivity.getName());

    }

    @Test
    public void getAppName(){
        MainActivity mainActivity = mock(MainActivity.class);
        String appName =mainActivity.getResources().getString(R.string.app_name);
        when(mainActivity.getResources().getString(R.string.app_name)).thenReturn(appName);

        assertEquals("chimbulain", appName);

    }
}
