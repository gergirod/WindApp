package germangirod.databindingexample;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by germangirod on 3/14/16.
 */
public class User extends BaseObservable {

    private String name;
    private String surname;
    private String age;

    public User(String firstName, String surname, String age) {
        this.name = firstName;
        this.surname = surname;
        this.age = age;
    }

    @Bindable
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(germangirod.databindingexample.BR.name);

    }

    @Bindable
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        notifyPropertyChanged(germangirod.databindingexample.BR.surname);
    }

    @Bindable
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
        notifyPropertyChanged(germangirod.databindingexample.BR.age);

    }
}
