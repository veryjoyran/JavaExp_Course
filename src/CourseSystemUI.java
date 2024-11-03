import javax.swing.*;
import java.awt.*;

/**
 * 选课系统的图形用户界面，实现了管理员、教师和学生的登录和功能界面。
 */
public class CourseSystemUI {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Teacher thr; // 当前登录的教师对象
    private Student stu; // 当前登录的学生对象

    public CourseSystemUI() {
        frame = new JFrame("学生选课系统");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // 设置各个面板
        setupMenuPanel();
        setupAdminLoginPanel();
        setupAdminPanel();
        setupTeacherLoginPanel();
        setupTeacherPanel();
        setupStudentLoginPanel();
        setupStudentPanel();

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // 主菜单
    private void setupMenuPanel() {
        JPanel menuPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JLabel label = new JLabel("请选择您的身份:", SwingConstants.CENTER);
        label.setFont(new Font("宋体", Font.BOLD, 24));
        menuPanel.add(label);

        JButton adminButton = new JButton("管理员");
        adminButton.setFont(new Font("宋体", Font.PLAIN, 20));
        adminButton.addActionListener(e -> cardLayout.show(mainPanel, "AdminLogin"));

        JButton teacherButton = new JButton("老师");
        teacherButton.setFont(new Font("宋体", Font.PLAIN, 20));
        teacherButton.addActionListener(e -> cardLayout.show(mainPanel, "TeacherLogin"));

        JButton studentButton = new JButton("学生");
        studentButton.setFont(new Font("宋体", Font.PLAIN, 20));
        studentButton.addActionListener(e -> cardLayout.show(mainPanel, "StudentLogin"));

        menuPanel.add(adminButton);
        menuPanel.add(teacherButton);
        menuPanel.add(studentButton);

        mainPanel.add(menuPanel, "Menu");
    }

    // 管理员登录面板
    private void setupAdminLoginPanel() {
        JPanel adminLoginPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JLabel label = new JLabel("管理员登录", SwingConstants.CENTER);
        label.setFont(new Font("宋体", Font.BOLD, 24));
        adminLoginPanel.add(label);

        JPanel passwordPanel = new JPanel(new FlowLayout());
        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("宋体", Font.PLAIN, 20));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        adminLoginPanel.add(passwordPanel);

        JButton loginButton = new JButton("登录");
        loginButton.setFont(new Font("宋体", Font.PLAIN, 20));
        loginButton.addActionListener(e -> {
            String password = new String(passwordField.getPassword());
            if (password.equals("123456")) {
                cardLayout.show(mainPanel, "AdminPanel");
            } else {
                JOptionPane.showMessageDialog(frame, "密码错误!");
            }
        });
        adminLoginPanel.add(loginButton);

        JButton backButton = new JButton("返回");
        backButton.setFont(new Font("宋体", Font.PLAIN, 20));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        adminLoginPanel.add(backButton);

        mainPanel.add(adminLoginPanel, "AdminLogin");
    }

    // 管理员功能面板
    private void setupAdminPanel() {
        JPanel adminPanel = new JPanel(new GridLayout(13, 1, 5, 5));
        JLabel adminLabel = new JLabel("管理员操作", SwingConstants.CENTER);
        adminLabel.setFont(new Font("宋体", Font.BOLD, 24));
        adminPanel.add(adminLabel);

        JButton createCourseButton = new JButton("新建课程");
        JButton deleteCourseButton = new JButton("删除课程");
        JButton setCourseTeacherButton = new JButton("设置课程老师");
        JButton searchTeacherCourseButton = new JButton("查找老师讲授课程");
        JButton showCoursesButton = new JButton("显示课程列表");
        JButton sortCoursesButton = new JButton("按选课人数排序");
        JButton showStudentsButton = new JButton("显示学生列表");
        JButton showTeachersButton = new JButton("显示教师列表");
        JButton addUserButton = new JButton("添加老师或学生");
        JButton deleteUserButton = new JButton("删除老师和学生");
        JButton resetPasswordButton = new JButton("恢复初始密码");
        JButton backButton = new JButton("返回");

        // 设置按钮字体
        JButton[] buttons = {createCourseButton, deleteCourseButton, setCourseTeacherButton,
                searchTeacherCourseButton, showCoursesButton, sortCoursesButton,
                showStudentsButton, showTeachersButton, addUserButton, deleteUserButton,
                resetPasswordButton, backButton};
        for (JButton btn : buttons) {
            btn.setFont(new Font("宋体", Font.PLAIN, 18));
        }

        // 添加操作逻辑
        createCourseButton.addActionListener(e -> Courses.addCourses());
        deleteCourseButton.addActionListener(e -> Courses.deleteCourses());
        setCourseTeacherButton.addActionListener(e -> Courses.setCourseTeacher());
        searchTeacherCourseButton.addActionListener(e -> Courses.SearchCourseByTeacher());
        showCoursesButton.addActionListener(e -> Courses.showCourses());
        sortCoursesButton.addActionListener(e -> Courses.SortCourseList());
        showStudentsButton.addActionListener(e -> Users.showStudents());
        showTeachersButton.addActionListener(e -> Users.showTeachers());
        addUserButton.addActionListener(e -> Users.addUsers());
        deleteUserButton.addActionListener(e -> Users.deleteUsers());
        resetPasswordButton.addActionListener(e -> Users.setOriginpass());
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        adminPanel.add(createCourseButton);
        adminPanel.add(deleteCourseButton);
        adminPanel.add(setCourseTeacherButton);
        adminPanel.add(searchTeacherCourseButton);
        adminPanel.add(showCoursesButton);
        adminPanel.add(sortCoursesButton);
        adminPanel.add(showStudentsButton);
        adminPanel.add(showTeachersButton);
        adminPanel.add(addUserButton);
        adminPanel.add(deleteUserButton);
        adminPanel.add(resetPasswordButton);
        adminPanel.add(backButton);

        mainPanel.add(adminPanel, "AdminPanel");
    }

    // 教师登录面板
    private void setupTeacherLoginPanel() {
        JPanel teacherLoginPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        JLabel label = new JLabel("教师登录", SwingConstants.CENTER);
        label.setFont(new Font("宋体", Font.BOLD, 24));
        teacherLoginPanel.add(label);

        JPanel idPanel = new JPanel(new FlowLayout());
        JLabel idLabel = new JLabel("工号：");
        idLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        JTextField idField = new JTextField(15);
        idField.setFont(new Font("宋体", Font.PLAIN, 20));
        idPanel.add(idLabel);
        idPanel.add(idField);
        teacherLoginPanel.add(idPanel);

        JPanel passwordPanel = new JPanel(new FlowLayout());
        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("宋体", Font.PLAIN, 20));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        teacherLoginPanel.add(passwordPanel);

        JButton loginButton = new JButton("登录");
        loginButton.setFont(new Font("宋体", Font.PLAIN, 20));
        loginButton.addActionListener(e -> {
            String idText = idField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            try {
                int id = Integer.parseInt(idText);
                thr = Users.loginTeacher(id, password); // 登录后将 thr 设置为当前教师对象
                if (thr != null) {
                    cardLayout.show(mainPanel, "TeacherPanel");
                } else {
                    JOptionPane.showMessageDialog(frame, "登录失败! 工号或密码错误。");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "请输入有效的工号。");
            }
        });
        teacherLoginPanel.add(loginButton);

        JButton backButton = new JButton("返回");
        backButton.setFont(new Font("宋体", Font.PLAIN, 20));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        teacherLoginPanel.add(backButton);

        mainPanel.add(teacherLoginPanel, "TeacherLogin");
    }

    // 教师功能面板
    private void setupTeacherPanel() {
        JPanel teacherPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        JLabel teacherLabel = new JLabel("教师操作", SwingConstants.CENTER);
        teacherLabel.setFont(new Font("宋体", Font.BOLD, 24));
        teacherPanel.add(teacherLabel);

        JButton changePasswordButton = new JButton("修改登录密码");
        JButton viewCoursesButton = new JButton("查看所授课程");
        JButton viewStudentsButton = new JButton("查看所授课程学生列表");
        JButton backButton = new JButton("返回");

        JButton[] buttons = {changePasswordButton, viewCoursesButton, viewStudentsButton, backButton};
        for (JButton btn : buttons) {
            btn.setFont(new Font("宋体", Font.PLAIN, 18));
        }

        // 修改密码按钮
        changePasswordButton.addActionListener(e -> Users.changeTeacherPass());

        // 查看所授课程
        viewCoursesButton.addActionListener(e -> Teacher.showTeacherCourse(thr.name));

        // 查看所授课程学生列表
        viewStudentsButton.addActionListener(e -> Teacher.showCourseStuNum(thr.name, thr.level));

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        teacherPanel.add(changePasswordButton);
        teacherPanel.add(viewCoursesButton);
        teacherPanel.add(viewStudentsButton);
        teacherPanel.add(backButton);

        mainPanel.add(teacherPanel, "TeacherPanel");
    }

    // 学生登录面板
    private void setupStudentLoginPanel() {
        JPanel studentLoginPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        JLabel label = new JLabel("学生登录", SwingConstants.CENTER);
        label.setFont(new Font("宋体", Font.BOLD, 24));
        studentLoginPanel.add(label);

        JPanel idPanel = new JPanel(new FlowLayout());
        JLabel idLabel = new JLabel("学号：");
        idLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        JTextField idField = new JTextField(15);
        idField.setFont(new Font("宋体", Font.PLAIN, 20));
        idPanel.add(idLabel);
        idPanel.add(idField);
        studentLoginPanel.add(idPanel);

        JPanel passwordPanel = new JPanel(new FlowLayout());
        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("宋体", Font.PLAIN, 20));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        studentLoginPanel.add(passwordPanel);

        JButton loginButton = new JButton("登录");
        loginButton.setFont(new Font("宋体", Font.PLAIN, 20));
        loginButton.addActionListener(e -> {
            String idText = idField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            try {
                int id = Integer.parseInt(idText);
                stu = Users.loginStudent(id, password); // 尝试登录
                if (stu != null) {
                    cardLayout.show(mainPanel, "StudentPanel");
                } else {
                    JOptionPane.showMessageDialog(frame, "登录失败! 学号或密码错误。");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "请输入有效的学号。");
            }
        });
        studentLoginPanel.add(loginButton);

        JButton backButton = new JButton("返回");
        backButton.setFont(new Font("宋体", Font.PLAIN, 20));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));
        studentLoginPanel.add(backButton);

        mainPanel.add(studentLoginPanel, "StudentLogin");
    }

    // 学生功能面板
    private void setupStudentPanel() {
        JPanel studentPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        JLabel studentLabel = new JLabel("学生操作", SwingConstants.CENTER);
        studentLabel.setFont(new Font("宋体", Font.BOLD, 24));
        studentPanel.add(studentLabel);

        JButton changePasswordButton = new JButton("修改登录密码");
        JButton viewCoursesButton = new JButton("查看已选课程");
        JButton selectCoursesButton = new JButton("选修课程");
        JButton backButton = new JButton("返回");

        JButton[] buttons = {changePasswordButton, viewCoursesButton, selectCoursesButton, backButton};
        for (JButton btn : buttons) {
            btn.setFont(new Font("宋体", Font.PLAIN, 18));
        }

        // 修改密码按钮
        changePasswordButton.addActionListener(e -> Users.changeStudentPass());

        // 查看已选课程
        viewCoursesButton.addActionListener(e -> Student.showStudentCourses(stu.id));

        // 选修课程
        selectCoursesButton.addActionListener(e -> Student.studentCourses());

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        studentPanel.add(changePasswordButton);
        studentPanel.add(viewCoursesButton);
        studentPanel.add(selectCoursesButton);
        studentPanel.add(backButton);

        mainPanel.add(studentPanel, "StudentPanel");
    }

    public static void main(String[] args) {
        // 加载数据
        Courses.readCourse();
        Student.readStudent();
        Teacher.readTeacher();
        SwingUtilities.invokeLater(CourseSystemUI::new);
    }
}
