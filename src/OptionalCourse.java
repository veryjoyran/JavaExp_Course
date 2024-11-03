import java.util.Vector;

public class OptionalCourse extends Course {
    public int maxStuNum;
    static Vector<Course> oclist = new Vector<>();

    public OptionalCourse(int id, String name) {
        this.id = id;
        this.name = name;
        this.type = 1; // 设置类型为选修课
    }

    public OptionalCourse(int id, String name, int type, int stuNum, String teacher, int maxStuNum) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.teacher = teacher;
        this.stuNum = stuNum;
        this.maxStuNum = maxStuNum;
    }

    @Override
    public String toString() {
        return super.toString() + " " + maxStuNum;
    }

    @Override
    public int getMaxStuNum() {
        return maxStuNum;
    }

    @Override
    public void show() {
        System.out.println(id + "    " + name + "    选修    " + teacher + "    " + stuNum + "    " + maxStuNum);
    }

    public static void showOptionalCourses() {
        System.out.println("编号  课程    类型    教师    选课人数    最大选课人数");
        for (Course oCourse : oclist) {
            oCourse.show();
        }
    }
}
