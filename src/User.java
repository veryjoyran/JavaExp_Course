import java.util.Scanner;

public class User {
    String name;
    protected String pass;

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public User() {}

    public void show() {
        System.out.println("用户名：" + name);
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        if (pass.length() == 6) {
            this.pass = pass;
        }
    }

    // 设置新密码，确保输入一致且长度为6
    public void setPassWord() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("请输入新密码6位: ");
            String xpass1 = sc.next();
            System.out.print("请再次输入密码6位: ");
            String xpass2 = sc.next();
            if (xpass1.equals(xpass2) && xpass1.length() == 6) {
                this.pass = xpass1;
                System.out.println("密码已成功更新！");
                break;
            } else {
                System.out.println("密码不一致或长度不正确，请重试。");
            }
        }
    }
}
