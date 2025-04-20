package main;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

import main.App.typeOfPersons;

class Creator{

    private static String titlePattern = "^.{3,40}$";
    private static String namePattern = "^[\\p{IsCyrillic}\\p{IsLatin}]{3,20}$";
    private static String phonePattern = "^[\\+]?\\d{6,12}$";
    private static String addressPattern = "^.{3,30},[ ]?\\d{1,4}";
    static public Person CreatePerson(Scanner scanner, typeOfPersons type) throws CancellationException{
        Person person;
        System.out.println("Створення " + (type == typeOfPersons.applicant? "абітурієнта" : "викладача"));
        System.out.print("Ім'я: ");
        String name = getStringParameter(namePattern, scanner);
        System.out.print("Прізвище: ");
        String surname = getStringParameter(namePattern, scanner);
        System.out.print("По батькові: ");
        String secondName = getStringParameter(namePattern, scanner);
        System.out.print("Адреса: ");
        String address = getStringParameter(addressPattern, scanner);
        System.out.print("Номер телефону: ");
        String phone = getStringParameter(phonePattern, scanner);
        System.out.print("Рівень вмінь: ");
        int skill = getIntegerParameter(0,100,scanner);
        if (type == typeOfPersons.applicant) person = new Applicant(name, surname, secondName, address, phone, skill);
        else person = new Teacher(name, surname, secondName, address, phone, skill);
        if (type == typeOfPersons.teacher){
            System.out.println("Вкажіть id факультету(0 для виведення списку факультетів; -1 для відміни): ");
            Faculty faculty = App.ChooseObject(App.faculties, scanner);
            faculty.AddTeacher((Teacher)person);
        }
        System.out.println("Об'єкт було створено");
        return person;
    }
    public static Exam CreateExam(Scanner scanner) throws CancellationException{
        System.out.println("Введіть назву: ");
        String title = getStringParameter(titlePattern, scanner);
        System.out.println("Введіть складність: ");
        int difficulty = getIntegerParameter(0, 100, scanner);
        System.out.println("Об'єкт було створено");
        return new Exam(title, difficulty);
    }
    public static Faculty CreateFaculty(Scanner scanner) throws CancellationException{
        System.out.println("Введіть назву: ");
        String title = getStringParameter(titlePattern, scanner);
        System.out.println("Введіть необхідний бал: ");
        int neededMark = getIntegerParameter(0, 100, scanner);
        System.out.println("Введіть список екзаменів: ");
        Exam[] exams = getExams(0,100, scanner);
        System.out.println("Об'єкт було створено");
        return new Faculty(title, exams, neededMark);
    }
    static String getStringParameter(String validator, Scanner scanner) throws CancellationException{
        String s = scanner.nextLine();
        while (!s.matches(validator)) {
            System.out.print("Неправильний формат. Введіть ше раз(-1 для відміни): ");
            s = scanner.nextLine();
            if (s.equals("-1")) throw new CancellationException();
        }
        return s;
    }
    public static int getIntegerParameter(int lower, int upper, Scanner scanner) throws CancellationException{
        while (true) {
            String s = scanner.nextLine();
            try {
                int n = Integer.parseInt(s);
                if (n == -1) throw new CancellationException();
                if (n >= lower && n <= upper) return n;
                else{
                    System.out.print("Не входить в межі. Введіть ше раз(-1 для відміни): ");
                }
            }
            catch(NumberFormatException e){
                System.out.print("Неправильний формат. Введіть ше раз(-1 для відміни): ");
            }
            
        }
    }
    public static Exam[] getExams(int lower, int upper, Scanner scanner){
        List<Exam> result = new ArrayList<>();
        while(true){
            try{
                result.add(App.ChooseObject(App.exams, scanner));
            }
            catch (CancellationException e){
                break;
            }
        }
        return result.toArray(new Exam[0]);
    }
}
