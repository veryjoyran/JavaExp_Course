import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class Teacher extends User {
    int workId;
    String level;
    static Vector<Course> courseTaught = new Vector<>(); // 用于存储老师教授的所有课程

    public Teacher(String name, String pass, int id, String level) {
        this.name = name;
        this.pass = pass;
        this.workId = id;
        this.level = level;
    }

    public void show() {
        System.out.println(workId + "    " + name + "    " + level);
    }

    // 教师信息输入
    public static Teacher inputTeacher() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入教师姓名:");
        String name = sc.next();
        System.out.println("请输入教师密码:");
        String pass = sc.next();
        System.out.println("请输入教师工号:");
        int workId = sc.nextInt();
        System.out.println("请输入教师职称:");
        String level = sc.next();
        return new Teacher(name, pass, workId, level);
    }

    // 删除一位教师信息
    public static void deleteTeacher() {
        System.out.println("请输入要删除的教师工号:");
        Scanner sc = new Scanner(System.in);
        int workId = sc.nextInt();
        boolean deleted = Users.teacherList.removeIf(teacher -> teacher.workId == workId);
        System.out.println(deleted ? "删除成功!" : "查询不到该教师信息, 删除失败!");
    }

    // 查找所授课课程
    public static void showTeacherCourse(String name) {
        courseTaught.clear();
        for (Course tCourse : Courses.clist) {
            if (tCourse.teacher.equals(name)) {
                courseTaught.add(tCourse);
            }
        }
        System.out.println(name + ", 您现在所授课程：");
        System.out.println("编号  课程    类型    教师    选课人数    学分/最大选课人数");
        for (Course tCourse : courseTaught) {
            tCourse.show();
        }
    }

    // 查看某门所授课程的上课学生列表
    public static void showCourseStuNum(String name, String level) {
        Scanner sc = new Scanner(System.in);
        System.out.println(name + level + "现在所授课程：");
        for (Course tCourse : courseTaught) {
            tCourse.show();
        }

        System.out.println("请输入要查询的课号(输入0取消查询):");
        int inputId = sc.nextInt();

        if (inputId == 0) {
            System.out.println("结束课程信息的查询!");
            return;
        }

        boolean courseFound = false;
        for (Course tCourse : courseTaught) {
            if (tCourse.id == inputId) {
                courseFound = true;
                System.out.println("选了课程 " + tCourse.name + " 的学生列表:");
                int num = 0;
                for (Student student : Users.studentList) {
                    if (student.isEnrolledInCourse(inputId)) {
                        System.out.print(++num + "   ");
                        student.show();
                    }
                }
                break;
            }
        }
        if (!courseFound) {
            System.out.println("你没有教授这门课程!");
        }
    }

    @Override
    public String toString() {
        return workId + " " + pass + " " + name + " " + level;
    }

    // 保存教师信息到文件
    public static void saveTeachers() {
        File file = new File("E:\\java_project\\JavaExp-Course\\src\\Files\\Teachers.txt");
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
            for (Teacher teacher : Users.teacherList) {
                out.write(teacher.toString() + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 从文件读取教师信息
    public static void readTeacher() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\java_project\\JavaExp-Course\\src\\Files\\Teachers.txt")))) {
            String data;
            while ((data = br.readLine()) != null) {
                String[] ps = data.split(" ");
                int id = Integer.parseInt(ps[0]);
                String pass = ps[1];
                String name = ps[2];
                String level = ps[3];
                Users.teacherList.add(new Teacher(name, pass, id, level));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
