import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class Courses {

    static Vector<Course> clist = new Vector<>();

    // 添加一门课程
    public static void addCourse() {
        int id = (clist.size() < 1) ? 1 : clist.size() + 1;
        clist.add(Course.inputCourse(id));
    }

    // 添加多门课程
    public static void addCourses() {
        int i = clist.size();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入第" + (++i) + "门课程信息");
            addCourse();
            System.out.print("是否继续添加课程 y/n: ");
            String inputFlag = sc.next();
            if (inputFlag.equals("y") || inputFlag.equals("Y"))
                continue;
            else
                break;
        }
    }

    // 删除一门课程
    public static void deleteCourse() {
        System.out.println("请输入要删除的课程名称:");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        int flag = 0;

        for (int i = 0; i < clist.size(); i++) {
            if (clist.get(i).name.equals(name)) {
                clist.remove(i);
                flag = 1;
                System.out.println("删除成功!");
                break;
            }
        }
        if (flag == 0) {
            System.out.println("查询不到该课程信息, 删除失败!");
        }
    }

    // 删除多门课程
    public static void deleteCourses() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            deleteCourse();
            System.out.print("是否继续删除课程 y/n: ");
            String inputFlag = sc.next();
            if (inputFlag.equals("y") || inputFlag.equals("Y"))
                continue;
            else {
                System.out.print("结束课程的删除!");
                break;
            }
        }
    }

    // 设置课程教师
    public static void setCourseTeacher() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要设定的课程名称:");
        String name = sc.next();
        int flag = 0;

        for (int i = 0; i < clist.size(); i++) {
            if (clist.get(i).name.equals(name)) {
                System.out.println("请输入要设定的老师:");
                clist.get(i).teacher = sc.next();
                flag = 1;
                System.out.println("重设成功!");
                break;
            }
        }
        if (flag == 0)
            System.out.println("查询不到该课程信息, 设置失败!");
    }

    // 显示课程列表
    public static void showCourses() {
        System.out.println("编号  课程    类型    教师    选课人数    学分/最大选课人数");
        for (int i = 0; i < clist.size(); i++) {
            clist.get(i).show();
        }
    }

    // 以选课人数进行课程排序
    public static void SortCourseList() {
        for (int i = 0; i < clist.size(); i++) {
            for (int j = i + 1; j < clist.size(); j++) {
                if (clist.get(i).stuNum < clist.get(j).stuNum) {
                    Course temp = clist.get(i);
                    clist.set(i, clist.get(j));
                    clist.set(j, temp);
                }
            }
        }
        System.out.print("排序成功!");
    }

    // 以教师姓名查找所授课程
    public static void SearchCourseByTeacher() {
        System.out.println("请输入需要查找的教师名称:");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();

        for (int i = 0; i < clist.size(); i++) {
            Course c = clist.get(i);
            if (c.teacher.equals(name))
                c.show();
        }
    }

    // 将课程列表中所有课程信息写入文件
    public static void saveCourse() {
        File file = new File("E:\\java_project\\JavaExp-Course\\src\\Files\\Courses.txt");
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
            if (!file.exists()) file.createNewFile();
            for (int i = 0; i < clist.size(); i++) {
                out.write(clist.get(i).toString() + "\r\n");
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 从文件中读取课程信息
    public static void readCourse() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\java_project\\JavaExp-Course\\src\\Files\\Courses.txt")))) {
            String data;
            while ((data = br.readLine()) != null) {
                String[] ps = data.split(" ");
                int id = Integer.parseInt(ps[0]);
                String name = ps[1];
                int type = Integer.parseInt(ps[2]);
                int stuNum = Integer.parseInt(ps[3]);
                String teacher = ps[4];

                if (type == 0) {
                    int credit = Integer.parseInt(ps[5]);
                    Course course = new RequiredCourse(id, name, type, stuNum, teacher, credit);
                    clist.add(course);
                    RequiredCourse.rclist.add(course);
                    Student.sclist.add(course);
                } else if (type == 1) {
                    int maxStuNum = Integer.parseInt(ps[5]);
                    Course course = new OptionalCourse(id, name, type, stuNum, teacher, maxStuNum);
                    clist.add(course);
                    OptionalCourse.oclist.add(course);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
