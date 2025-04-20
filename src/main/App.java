package main;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.CancellationException;
public class App {
    static List<Faculty> faculties = new ArrayList<>();
    static List<Exam> exams = new ArrayList<>();
    static List<Applicant> applicants = new ArrayList<>();
    static List<Teacher> teachers = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        //Ініціалізація початкових даних для роботи
        // Створення екзаменів (складність від 0 до 100)
        Exam matematika = new Exam("Математика", 75);
        Exam fizyka = new Exam("Фізика", 80);
        Exam informatyka = new Exam("Інформатика", 65);
        Exam biolohiya = new Exam("Біологія", 70);
        Exam khimiya = new Exam("Хімія", 85);
        Exam istoriya = new Exam("Історія", 50);
        Exam literatura = new Exam("Література", 40);
        Exam heohrafiya = new Exam("Географія", 45);
        exams.add(matematika);
        exams.add(fizyka);
        exams.add(informatyka);
        exams.add(biolohiya);
        exams.add(khimiya);
        exams.add(istoriya);
        exams.add(literatura);
        exams.add(heohrafiya);
        // Створення викладачів (рівень навичок від 0 до 100)
        Teacher teacher1 = new Teacher("Іван", "Іваненко", "Петрович", "вул. Шевченка, 1", "0961111111", 78);
        Teacher teacher2 = new Teacher("Петро", "Петренко", "Іванович", "вул. Грушевського, 2", "0972222222", 85);
        Teacher teacher3 = new Teacher("Ольга", "Шевченко", "Сергіївна", "вул. Франка, 3", "0983333333", 70);
        Teacher teacher4 = new Teacher("Ніна", "Коваль", "Миколаївна", "вул. Хмельницького, 4", "0994444444", 88);
        Teacher teacher5 = new Teacher("Тарас", "Франко", "Ярославович", "вул. Січових Стрільців, 5", "0935555555", 81);
        Teacher teacher6 = new Teacher("Оксана", "Марченко", "Володимирівна", "вул. Лесі Українки, 6", "0956666666", 76);
        Teacher teacher7 = new Teacher("Дмитро", "Бондар", "Олександрович", "вул. Сагайдачного, 7", "0947777777", 69);
        teachers.add(teacher1);
        teachers.add(teacher2);
        teachers.add(teacher3);
        teachers.add(teacher4);
        teachers.add(teacher5);
        teachers.add(teacher6);
        teachers.add(teacher7);
        // Створення факультетів
        Faculty fakultetIT = new Faculty("Факультет інформаційних технологій", new Exam[]{matematika, informatyka, fizyka}, 70);
        Faculty fakultetBio = new Faculty("Біологічний факультет", new Exam[]{biolohiya, khimiya, fizyka, matematika}, 50);
        Faculty fakultetHuman = new Faculty("Факультет гуманітарних наук", new Exam[]{istoriya, literatura, heohrafiya}, 60);

        // Додавання викладачів до факультетів
        fakultetIT.AddTeacher(teacher1);
        fakultetIT.AddTeacher(teacher2);
        fakultetIT.AddTeacher(teacher3);

        fakultetBio.AddTeacher(teacher4);
        fakultetBio.AddTeacher(teacher5);

        fakultetHuman.AddTeacher(teacher6);
        fakultetHuman.AddTeacher(teacher7);

        // Створення абітурієнтів для кожного факультету
        Applicant applicant1 = new Applicant("Марія", "Бондаренко", "Сергіївна", "вул. Лесі Українки, 8", "0971234567", 82);
        Applicant applicant2 = new Applicant("Анна", "Шевченко", "Анатоліївна", "вул. Сагайдачного, 9", "0957654321", 70);
        Applicant applicant3 = new Applicant("Олександр", "Мельник", "Вікторович", "вул. Франка, 10", "0982233445", 90);
        Applicant applicant4 = new Applicant("Ігор", "Іванов", "Дмитрович", "вул. Шевченка, 11", "0938877665", 65);
        Applicant applicant5 = new Applicant("Тетяна", "Даниленко", "Миколаївна", "вул. Грушевського, 12", "0973344556", 75);
        Applicant applicant6 = new Applicant("Юлія", "Коваленко", "Ігорівна", "вул. Січових Стрільців, 13", "0942233445", 80);
        applicants.add(applicant1);
        applicants.add(applicant2);
        applicants.add(applicant3);
        applicants.add(applicant4);
        applicants.add(applicant5);
        applicants.add(applicant6);
        // Додавання факультетів до загального списку
        faculties.add(fakultetIT);
        faculties.add(fakultetBio);
        faculties.add(fakultetHuman);
        System.setProperty("file.encoding", "UTF-8");
        Scanner scanner = new Scanner(System.in, "UTF8");
        boolean f = true;
        //Реалізація меню
        while (f){
            System.out.println("Зробіть вибір: ");
            System.out.println("1) Створити абітурієнта");
            System.out.println("2) Створити вчителя");
            System.out.println("3) Створити екзамен");
            System.out.println("4) Створити факультет");
            System.out.println("5) Пройти екзамен");
            System.out.println("6) Список факультетів");
            System.out.println("7) Список абітурієнтів");
            System.out.println("8) Список вчителів");
            System.out.println("9) Список екзаменів");
            System.out.println("10) Повна інформція про факультет");
            System.out.println("0) Припинити виконання програми");
            switch (scanner.nextLine()) {
                case "1":
                    try{
                        applicants.add((Applicant)Creator.CreatePerson(scanner, typeOfPersons.applicant));
                    }
                    catch(CancellationException e){
                        System.out.println("Операцію було скасовано");
                    }
                    break;
                case "2":
                    try{
                        teachers.add((Teacher)Creator.CreatePerson(scanner, typeOfPersons.teacher));
                    }
                    catch(CancellationException e){
                        System.out.println("Операцію було скасовано");
                    }
                    break;
                case "3":
                    try{
                        exams.add(Creator.CreateExam(scanner));
                    }
                    catch(CancellationException e){
                        System.out.println("Операцію було скасовано");
                    }
                    break;
                case "4":
                    try{
                        faculties.add(Creator.CreateFaculty(scanner));
                    }
                    catch(CancellationException e){
                        System.out.println("Операцію було скасовано");
                    }
                    break;
                case "5":
                    try{
                        System.out.println("Виберіть факультет");
                        Faculty faculty = ChooseObject(faculties, scanner);
                        System.out.println("Виберіть абітурієнта: ");
                        Applicant applicant = ChooseObject(applicants, scanner);
                        faculty.AddApplicant(applicant, scanner);
                    }
                    catch(CancellationException e){
                        System.out.println("Операцію було скасовано");
                    }
                    break;
                case "6":
                    faculties.forEach(a -> a.showLess());
                    break;
                case "7":
                    applicants.forEach(a -> a.showLess());
                    break;
                case "8":
                    teachers.forEach(a -> a.showLess());
                    break;
                case "9":
                    exams.forEach(a -> a.showLess());
                    break;
                case "10":
                    try{
                        System.out.println("Виберіть факультет");
                        Faculty faculty = ChooseObject(faculties, scanner);
                        faculty.show();
                    }
                    catch(CancellationException e){
                        System.out.println("Операцію було скасовано");
                    }
                    break;
                case "0":
                    f = false;
                    break;
                default:
                    System.out.println("Wrong Format!!! Можна використовувати лише: '0 - 10' integer values");
                    break;
            }
        }
        scanner.close();
    }
    //Функція для вибору об'єкту з певної колекціїї
    static public <T extends IdObject> T ChooseObject(List<T> objects, Scanner scanner) throws CancellationException{
        System.out.println("-1 - для скасування; 0 - для виведення списку");
        while (true){
            try{
                int id = Integer.parseInt(scanner.nextLine());
                switch (id) {
                    case 0:
                        objects.forEach(a -> a.showLess());
                        break;
                    case -1:
                        throw new CancellationException();
                    default:
                        T idObj = objects.stream().filter(a-> a.getId() == id).findFirst().orElseThrow();
                        return idObj;
                }
            }
            catch(NumberFormatException e){
                System.out.println("Wrong format!!!. Ви повинні ввести число");
            }
            catch(NoSuchElementException e){
                System.out.println("Об'єкта з таким id немає немає");
            }
        }
    }
    enum typeOfPersons{
        applicant,
        teacher
    }
}
//#region IdObject
class IdGenerator<T extends IdObject>{
    private int last = 1;
    public int Generate(){
        return last++;
    }
}
abstract class IdObject{
    protected int id;
    public IdObject(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    abstract void show();
    abstract void showLess();
}
//#endregion
class Exam extends IdObject{
    private static IdGenerator<Faculty> generator = new IdGenerator<>();
    private String title;
    private int difficulty;
    public Exam(String title, int difficulty){
        super(generator.Generate());
        this.title = title;
        this.difficulty = difficulty;
    }
    public int getDifficulty(){
        return difficulty;
    }
    public void showLess(){
        System.out.println("Id: " + id + " назва: "+ title + " складність: " + difficulty);
    }
    public void show(){
        showLess();
    }
}
