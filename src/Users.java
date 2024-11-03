import java.util.Scanner;
import java.util.Vector;

public class Users {
    static User admin = new User("admin", "123456");
    static Vector<Student> studentList = new Vector<>();
    static Vector<Teacher> teacherList = new Vector<>();

    // 添加老师或学生
    public static void addUsers() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("你要添加什么用户?\n1.老师\n2.学生\n0.结束");
            int choose = sc.nextInt();
            if (choose == 0) {
                System.out.println("添加完成,再见!");
                break;
            } else if (choose < 1 || choose > 2) {
                System.out.println("输入错误!请重新输入:");
            } else {
                if (choose == 1) {
                    teacherList.add(Teacher.inputTeacher());
                } else {
                    studentList.add(Student.inputStudent());
                }
            }
        }
    }

    // 删除老师或学生
    public static void deleteUsers() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("你要删除什么用户?\n1.老师\n2.学生\n0.结束");
            int choose = sc.nextInt();
            if (choose == 0) {
                System.out.println("删除完成,再见!");
                break;
            } else if (choose < 1 || choose > 2) {
                System.out.println("输入错误!请重新输入:");
            } else {
                if (choose == 1) {
                    Teacher.deleteTeacher();
                } else {
                    Student.deleteStudent();
                }
            }
        }
    }

    // 显示所有学生
    public static void showStudents() {
        System.out.println("学号    名字    班级");
        for (Student student : studentList) {
            student.show();
        }
    }

    // 显示所有教师
    public static void showTeachers() {
        System.out.println("工号    名字    职称");
        for (Teacher teacher : teacherList) {
            teacher.show();
        }
    }

    // 教师登录
    public static Teacher loginTeacher(int inputWorkId, String inputPass) {
        for (Teacher teacher : teacherList) {
            if (teacher.workId == inputWorkId && teacher.pass.equals(inputPass)) {
                System.out.println(teacher.name + " " + teacher.level + ",你好！");
                return teacher;
            }
        }
        return null;
    }

    // 学生登录
    public static Student loginStudent(int inputId, String inputPass) {
        for (Student student : studentList) {
            if (student.id == inputId && student.pass.equals(inputPass)) {
                System.out.println(student.className + " " + student.name + ",你好！");
                return student;
            }
        }
        return null;
    }

    // 修改学生密码
    public static void changeStudentPass() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要设置的学生学号:");
        int inputId = sc.nextInt();
        System.out.println("请输入原始密码:");
        String inputPass = sc.next();
        boolean found = false;

        for (Student student : studentList) {
            if (student.id == inputId && student.pass.equals(inputPass)) {
                System.out.println("请输入新密码:");
                String newPass = sc.next();
                System.out.println("请再次输入新密码:");
                if (newPass.equals(sc.next()) && !newPass.equals(inputPass)) {
                    student.pass = newPass;
                    System.out.println("新密码修改成功!");
                    found = true;
                    break;
                } else {
                    System.out.println("新密码不得与原密码相同或输入不一致!");
                }
            }
        }
        if (!found) {
            System.out.println("查询不到该学生信息或者密码错误!");
        }
    }

    // 修改教师密码
    public static void changeTeacherPass() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要设置的教师工号:");
        int inputWorkId = sc.nextInt();
        System.out.println("请输入原始密码:");
        String inputPass = sc.next();
        boolean found = false;

        for (Teacher teacher : teacherList) {
            if (teacher.workId == inputWorkId && teacher.pass.equals(inputPass)) {
                System.out.println("请输入新密码:");
                String newPass = sc.next();
                System.out.println("请再次输入新密码:");
                if (newPass.equals(sc.next()) && !newPass.equals(inputPass)) {
                    teacher.pass = newPass;
                    System.out.println("新密码修改成功!");
                    found = true;
                    break;
                } else {
                    System.out.println("新密码不得与原密码相同或输入不一致!");
                }
            }
        }
        if (!found) {
            System.out.println("查询不到该老师信息或者密码错误!");
        }
    }

    // 重置密码
    public static void setOriginpass() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("你要重置谁的密码?\n1.老师\n2.学生\n0.结束");
            int choose = sc.nextInt();
            if (choose == 0) {
                System.out.println("重置完成,再见!");
                break;
            } else if (choose < 1 || choose > 2) {
                System.out.println("输入错误!请重新输入:");
            } else {
                boolean found = false;
                if (choose == 1) {
                    System.out.println("请输入要设置的教师工号:");
                    int workId = sc.nextInt();
                    for (Teacher teacher : teacherList) {
                        if (teacher.workId == workId) {
                            teacher.pass = "123456";
                            System.out.println("重置完成!");
                            found = true;
                            break;
                        }
                    }
                } else {
                    System.out.println("请输入要设置的学生学号:");
                    int id = sc.nextInt();
                    for (Student student : studentList) {
                        if (student.id == id) {
                            student.pass = "123456";
                            System.out.println("重置完成!");
                            found = true;
                            break;
                        }
                    }
                }
                if (!found) {
                    System.out.println("查询不到信息, 重置密码失败!");
                }
            }
        }
    }
}
