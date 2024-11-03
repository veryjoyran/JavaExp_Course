import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class Student extends User {
    int id; // 学生ID
    String className; // 班级
    static Vector<Course> sclist = new Vector<>(); // 存储学生的所有课程
    static Vector<Student> studentList = new Vector<>(); // 存储所有学生

    public Student(String name, String pass, int id, String className) {
        super(name, pass);
        this.id = id;
        this.className = className;
    }

    public void show() {
        System.out.println(id + "    " + name + "    " + className);
    }

    // 显示学生所选课程
    public static void showStudentCourses(int studentId) {
        System.out.println(studentId + " 的全部课程:");
        sclist.forEach(Course::show);
    }

    // 判断是否已选某课程
    public boolean isEnrolledInCourse(int courseId) {
        return sclist.stream().anyMatch(course -> course.id == courseId);
    }

    // 输入学生信息
    public static Student inputStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生姓名:");
        String name = sc.next();
        System.out.println("请输入学生密码:");
        String pass = sc.next();
        System.out.println("请输入学生学号:");
        int id = sc.nextInt();
        System.out.println("请输入学生班级:");
        String className = sc.next();
        return new Student(name, pass, id, className);
    }

    // 删除学生信息
    public static void deleteStudent() {
        System.out.println("请输入要删除的学生学号:");
        Scanner sc = new Scanner(System.in);
        int studentId = sc.nextInt();
        boolean deleted = Users.studentList.removeIf(student -> student.id == studentId);
        System.out.println(deleted ? "删除成功!" : "查询不到该学生信息, 删除失败!");
    }

    // 学生选课
    public static void studentCourses() {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入您的学号:");
        int studentId = sc.nextInt();
        Student currentStudent = Users.studentList.stream()
                .filter(student -> student.id == studentId)
                .findFirst()
                .orElse(null);

        if (currentStudent == null) {
            System.out.println("查询不到该学生信息!");
            return;
        }

        while (true) {
            System.out.println("学校总选修课列表如下:");
            OptionalCourse.oclist.forEach(Course::show);
            System.out.println("请输入想上的选修课的课号(输入0退出):");
            int inputId = sc.nextInt();
            if (inputId == 0) break;

            OptionalCourse.oclist.stream()
                    .filter(course -> course.id == inputId)
                    .findFirst()
                    .ifPresentOrElse(course -> {
                        if (currentStudent.isEnrolledInCourse(inputId)) {
                            System.out.println("此选修课程已经选过啦!");
                        } else if (course.stuNum < course.getMaxStuNum()) {
                            course.stuNum++;
                            sclist.add(course);
                            System.out.println("选课成功!");
                        } else {
                            System.out.println("此选修课程选课人数已经达到上限!");
                        }
                    }, () -> System.out.println("请输入正确的选修课的课号!"));
        }
    }

    // 保存学生信息到文件
    public static void saveStudents() {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("E:\\java_project\\JavaExp-Course\\src\\Files\\Students.txt"))) {
            for (Student student : Users.studentList) {
                out.write(student.toString() + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 从文件读取学生信息
    public static void readStudent() {
        try (BufferedReader br = new BufferedReader(new FileReader("E:\\java_project\\JavaExp-Course\\src\\Files\\Students.txt"))) {
            String data;
            while ((data = br.readLine()) != null) {
                String[] ps = data.split(" ");
                int id = Integer.parseInt(ps[0]);
                String pass = ps[1];
                String className = ps[2];
                String name = ps[3];
                Users.studentList.add(new Student(name, pass, id, className));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return id + " " + pass + " " + className + " " + name;
    }
}
