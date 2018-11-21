package person;

public class PersonBuilder {

    private int age;
    private String gender;
    private Address address;
    private String name;

    public PersonBuilder withAge(int age) {
        // throw new IllegalStateException("not implemented");
        this.age = age;
        // System.out.println(this);    // return object
        // System.out.println(this.age);    // return age of object
        return this;
    }

    public PersonBuilder withGender(String gender) {
        this.gender = gender;
        return this;
    }

    public PersonBuilder withTown(String town) {
        Address address = new Address(null, town);
        address.setTown(town);
        this.address = address;
        return this;
    }

    public Person build() {
        // throw new IllegalStateException("not implemented");
        Person person = new Person(name, age, gender, address);
        person.setAddress(address);
        return person;
    }

}
