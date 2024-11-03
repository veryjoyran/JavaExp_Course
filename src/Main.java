import java.util.Scanner;

public class Main {
    public static void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请选择您的身份:\n1.管理员\n2.老师\n3.学生\n0.退出");
            int choose = sc.nextInt();
            if (choose == 0) {
                System.out.println("欢迎使用学生选课系统，再见!");
                break;
            } else if (choose < 1 || choose > 3) {
                System.out.println("输入错误!请重新输入:");
            } else {
                switch (choose) {
                    case 1:
                        adminMenu();
                        break;
                    case 2:
                        teacherMenu();
                        break;
                    case 3:
                        stuMenu();
                        break;
                }
            }
        }
    }

    // 管理员页面
    public static void adminMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入管理员密码:");
        String pass = sc.next();
        if (!pass.equals(Users.admin.pass)) {
            System.out.println("密码错误!");
            return;
        }

        while (true) {
            System.out.println("请选择操作:\n" +
                    "1.新建课程\n" +
                    "2.删除课程\n" +
                    "3.设置课程老师\n" +
                    "4.查找老师讲授课程\n" +
                    "5.显示课程列表\n" +
                    "6.按照选课人数排序\n" +
                    "7.显示学生列表\n" +
                    "8.显示教师列表\n" +
                    "9.添加老师或学生\n" +
                    "10.删除老师和学生\n" +
                    "11.恢复学生和教师初始密码\n" +
                    "0.退出");
            int choose = sc.nextInt();
            if (choose == 0) {
                System.out.println("欢迎使用学生选课系统，再见!");
                break;
            } else if (choose < 1 || choose > 11) {
                System.out.println("输入错误!请重新输入:");
            } else {
                switch (choose) {
                    case 1:
                        Courses.addCourses();
                        break;
                    case 2:
                        Courses.deleteCourses();
                        break;
                    case 3:
                        Courses.setCourseTeacher();
                        break;
                    case 4:
                        Courses.SearchCourseByTeacher();
                        break;
                    case 5:
                        Courses.showCourses();
                        break;
                    case 6:
                        Courses.SortCourseList();
                        break;
                    case 7:
                        Users.showStudents();
                        break;
                    case 8:
                        Users.showTeachers();
                        break;
                    case 9:
                        Users.addUsers();
                        break;
                    case 10:
                        Users.deleteUsers();
                        break;
                    case 11:
                        Users.setOriginpass();
                        break;
                }
            }
        }
    }

    // 教师页面
    public static void teacherMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入工号：");
        int inputWorkId = sc.nextInt();
        System.out.println("请输入密码：");
        String inputPass = sc.next();
        Teacher thr = Users.loginTeacher(inputWorkId, inputPass);
        if (thr == null) {
            System.out.println("密码错误!");
            return;
        }

        while (true) {
            System.out.println("请选择操作:\n" +
                    "1.修改登录密码\n" +
                    "2.查看所授课程\n" +
                    "3.查看所授课程的上课学生列表\n" +
                    "0.退出");
            int choose = sc.nextInt();
            if (choose == 0) {
                System.out.println("欢迎使用学生选课系统，再见!");
                break;
            } else if (choose < 1 || choose > 3) {
                System.out.println("输入错误!请重新输入:");
            } else {
                switch (choose) {
                    case 1:
                        Users.changeTeacherPass();
                        break;
                    case 2:
                        Teacher.showTeacherCourse(thr.name);
                        break;
                    case 3:
                        Teacher.showCourseStuNum(thr.name, thr.level);
                        break;
                }
            }
        }
    }

    // 学生页面
    public static void stuMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学号:");
        int inputId = sc.nextInt();
        System.out.println("请输入密码:");
        String inputPass = sc.next();
        Student stu = Users.loginStudent(inputId, inputPass);
        if (stu == null) {
            System.out.println("密码错误!");
            return;
        }

        while (true) {
            System.out.println("请选择操作:\n" +
                    "1.修改登录密码\n" +
                    "2.查看所上课程\n" +
                    "3.选修课选课\n" +
                    "0.退出");
            int choose = sc.nextInt();
            if (choose == 0) {
                System.out.println("欢迎使用学生选课系统，再见!");
                break;
            } else if (choose < 1 || choose > 3) {
                System.out.println("输入错误!请重新输入:");
            } else {
                switch (choose) {
                    case 1:
                        Users.changeStudentPass();
                        break;
                    case 2:
                        Student.showStudentCourses(inputId);
                        break;
                    case 3:
                        Student.studentCourses();
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Courses.readCourse();
        Student.readStudent();
        Teacher.readTeacher();
        menu();
        Courses.saveCourse();
        Student.saveStudents();
        Teacher.saveTeachers();
    }
}
