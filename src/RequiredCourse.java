import java.util.Vector;

public class RequiredCourse extends Course {
    private int credit; // 学分
    static Vector<Course> rclist = new Vector<>();

    public RequiredCourse(int id, String name) {
        this.id = id;
        this.name = name;
        this.type = 0; // 设置类型为必修课
    }

    public RequiredCourse(int id, String name, int type, int stuNum, String teacher, int credit) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.teacher = teacher;
        this.stuNum = stuNum;
        this.credit = credit;
    }

    @Override
    public int getMaxStuNum() {
        return 0; // 必修课无最大选课人数
    }

    @Override
    public String toString() {
        return super.toString() + " " + credit;
    }

    @Override
    public void show() {
        System.out.println(id + "    " + name + "    必修    " + teacher + "    " + stuNum + "    " + credit);
    }

    public static void showRequiredCourses() {
        System.out.println("编号  课程    类型    教师    选课人数    学分");
        for (Course rCourse : rclist) {
            rCourse.show();
        }
    }
}
