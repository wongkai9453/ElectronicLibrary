package bean;

/**
 * @ClassName SysUsers
 * @Description user信息
 * @Author wk
 * @Date 2020/6/12 0012 16:44
 * @Version 1.0
 */
public class SysUsers {
    private int userid;

    private String username;

    private String password;

    private String chinesename;

    public SysUsers() {
    }

    public SysUsers(int userid, String username, String password, String chinesename) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.chinesename = chinesename;
    }

    @Override
    public String toString() {
        return "SysUsers{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", chinesename='" + chinesename + '\'' +
                '}';
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getChinesename() {
        return chinesename;
    }

    public void setChinesename(String chinesename) {
        this.chinesename = chinesename;
    }
}
