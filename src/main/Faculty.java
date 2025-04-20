package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Faculty extends IdObject{
    private static IdGenerator<Faculty> generator = new IdGenerator<>();
    private String title;
    private Exam[] exams;
    private int neededMark;
    private List<Applicant> applicants = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();
    public Faculty (String title, Exam[] exams, int neededMark){
        super(generator.Generate());
        this.title = title;
        this.exams = exams;
        this.neededMark = neededMark;
    }
    public int getId(){
        return id;
    }
    public List<Applicant> getApplicants(){
        return new ArrayList<>(applicants);
    }
    public List<Teacher> getTeachers(){
        return new ArrayList<>(teachers);
    }
    //Функція, що відповідає за проходження абітурієнтом екзамену та зарахування його на навчання
    public void AddApplicant(Applicant applicant, Scanner scanner){
        Random rand = new Random();
        if (teachers.size() == 0) {
        	System.out.println("На факультеті нема вчителів, щоб прийняти екзамен");
        	return;
        }
        Teacher teacher = teachers.get(rand.nextInt(teachers.size()));
        System.out.println("Екзамен приймав: ");
        teacher.showLess();
        System.out.println();
        int sum = Arrays.stream(exams).mapToInt(exam -> teacher.Examine(exam, applicant)).sum();
        int average = sum / exams.length;
        if (average >= neededMark){
            System.out.println("Абітурієнт пройшов відбір. Його середній бал: " + average);
            applicants.add(applicant);
        }
        else{
            System.out.println("Абітурієнт не набрав достатню кількість балів. Його середній бал: " + average);
        }
        
    }
    public void AddTeacher(Teacher teacher){
        teachers.add(teacher);
    }
    public void showLess(){
        System.out.println(
            "id: " + id +
            " title: " + title +
            " необхідний середній бал: " + neededMark
        );
    }
    public void show(){
        showLess();
        System.out.println("Вчителі: ");
        teachers.forEach(a -> a.showLess());
        System.out.println("Абітурієнти: ");
        applicants.forEach(a -> a.showLess());
    }
}
