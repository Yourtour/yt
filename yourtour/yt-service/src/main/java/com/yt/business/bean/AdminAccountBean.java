package com.yt.business.bean;

import com.yt.business.BaseBeanImpl;
import com.yt.hbase.annotation.HbaseColumn;
import com.yt.hbase.annotation.HbaseTable;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * 管理账号实体
 * Created by 林平 on 2016/3/24.
 */
@HbaseTable(name = "T_ADMIN_ACCOUNT_INFO")
@NodeEntity
public class AdminAccountBean  extends BaseBeanImpl {
    @HbaseColumn(name = "uname")
    @Indexed
    private String userName; // 手机

    private String realName; //真实姓名

    @HbaseColumn(name = "pwd")
    private String pwd; // 登录密码

    public enum Status {
        VALIDATED, INVALIDE, FROOZE
    }

    @HbaseColumn(name = "stat")
    @Indexed
    private Status status = Status.VALIDATED;

    public AdminAccountBean() {
        super();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
