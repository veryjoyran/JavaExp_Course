import java.util.Scanner;

public abstract class Course {
    int id; // 课程ID
    String name; // 课程名称
    int type; // 课程类型（0: 必修, 1: 选修）
    String teacher; // 上课教师
    int stuNum; // 选课人数

    public Course() {}

    public Course(int id, String name, int type, String teacher, int stuNum) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.teacher = teacher;
        this.stuNum = stuNum;
    }

    public abstract int getMaxStuNum(); // 获取子类的最大选课人数

    @Override
    public String toString() {
        return id + " " + name + " " + type + " " + stuNum + " " + teacher;
    }

    public static Course inputCourse(int id) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入课程名:");
        String name = sc.next();

        System.out.println("请输入课程类型(0: 必修, 1: 选修):");
        int type = sc.nextInt();

        System.out.println("请输入授课教师信息:");
        String teacher = sc.next();

        System.out.println("请输入选课人数:");
        int stuNum = sc.nextInt();

        Course course;
        if (type == 0) { // 必修课程
            System.out.println("请输入学分:");
            int credit = sc.nextInt();
            course = new RequiredCourse(id, name, type, stuNum, teacher, credit);
            RequiredCourse.rclist.add(course);
            Student.sclist.add(course);
        } else { // 选修课程
            System.out.println("请输入最大选课人数:");
            int maxStuNum = sc.nextInt();
            course = new OptionalCourse(id, name, type, stuNum, teacher, maxStuNum);
            OptionalCourse.oclist.add(course);
        }

        return course;
    }

    public void show() {
        System.out.print(id + "    " + name + "    ");

        switch (type) {
            case 0:
                System.out.print("必修");
                break;
            case 1:
                System.out.print("选修");
                break;
            default:
                System.out.print("其他");
                break;
        }

        System.out.println("    " + teacher + "    " + stuNum);
    }
}
