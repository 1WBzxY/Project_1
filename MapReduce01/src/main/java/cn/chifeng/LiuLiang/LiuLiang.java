package cn.chifeng.LiuLiang;

import java.io.Serializable;

public class LiuLiang implements Serializable {
    private int upLiu;//上行
    private int downLiu;//下行

    public int getUpLiu() {
        return upLiu;
    }

    public void setUpLiu(int upLiu) {
        this.upLiu = upLiu;
    }

    public int getDownLiu() {
        return downLiu;
    }

    public void setDownLiu(int downLiu) {
        this.downLiu = downLiu;
    }

}
