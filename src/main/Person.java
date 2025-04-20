package main;
import java.util.Random;


abstract class Person extends IdObject{
    private String name;
    private String surname;
    private String secondName;
    private String address;
    private String phone;
    protected int skill;
    public Faculty faculty;
    public Person(int id, String name, String surname, String secondName, String address, String phone, int skill){
        super(id);
        this.name = name;
        this.surname = surname;
        this.secondName = secondName;
        this.address = address;
        this.phone = phone;
        this.skill = skill;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getSecondName(){
        return secondName;
    }
    public String getAddress(){
        return address;
    }
    public String getPhone(){
        return phone;
    }
    
    public void show(){
        showLess();
    }
    public void showLess(){
        System.out.println(
            "Ідентифікаційний номер: " + id +
            " Ім'я: " + name +
            " Прізвище: " + surname +
            " По батькові: " + secondName +
            " Адреса: " + address +
            " Номер телефону: " + phone +
            " Номер факультету: " + (faculty == null? "Відсутній" : faculty.getId())
        );
    }
}
class Applicant extends Person{
    private static IdGenerator<Faculty> generator = new IdGenerator<>();
    public Applicant(String name, String surname, String secondName, String address, String phone, int skill){
        super(generator.Generate(), name, surname, secondName, address, phone, skill);
    }
    //Проходження екзамену, в якому враховується складність екзамену
    public int passExam(Exam exam){
        Random random = new Random();
        int range = Math.max(exam.getDifficulty() - skill + 10, 0);
        return 100 - random.nextInt(range + 1);  
    }
}
class Teacher extends Person{
    private static IdGenerator<Faculty> generator = new IdGenerator<>();
    public Teacher(String name, String surname, String secondName, String address, String phone, int skill){
        super(generator.Generate(), name, surname, secondName, address, phone, skill);
    }
    public int Examine(Exam exam, Applicant applicant){
        Random random = new Random();
        int result = applicant.passExam(exam);
        result += random.nextInt((100-skill)*2 + 1) - 100 + skill;
        if (result < 0) result = 0;
        if (result > 100) result = 100;
        return result;
    }
}
